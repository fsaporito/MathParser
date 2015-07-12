package Parser;

import MathExpr.MathExpr;
import MathToken.MathToken;
import MathToken.MathTokenOperator;
import MathToken.MathTokenParenthesis;
import DataStructures.Queue;
import DataStructures.Stack;
import Exceptions.WrongExpressionException;
import Exceptions.WrongInputException;


public class MathParser {

	
	/** Lexer */
	private MathLexer lexer;
	
	/** Tokenised Input */
	private Queue<MathToken> tokenList;
	
	/** Operator Stack */
	private Stack<MathToken> operatorStack;
	
	/** MathExpr Stack */
	private Stack<MathExpr> exprStack;
	
	/** MathExpr */
	private MathExpr expr;
	
	
	
	
	/**
	 * Constructor:
	 * - Calls MathLexer To Tokenise The Input
	 * - Creates All The Notation Forms (infix, prefix, postfix)
	 * 
	 * @param input Mathematical Expression
	 * @param type input Notation Type (infix, prefix, postfix)
	 * @throws WrongInputException The Input Isn't A Correct Mathematical Expression
	 * @throws MismatchedParenthesisException 
	 * @throws WrongExpressionException 
	 */
	public MathParser (String input, String type) throws WrongInputException, WrongExpressionException {
		
		if (input == null) { // Input Mustn't Be Null
			
			throw new NullPointerException ("Input is null!!!");
			
		}
		
		if (input.length() == 0) { // Input Mustn't Be Empty
			
			throw new NullPointerException ("Input is empty!!!");
			
		}
		
		
		if (type == null) { // type Mustn't Be Null
			
			throw new NullPointerException ("type is null!!!");			
			
		}
		
		if (!type.equals("infix") && !type.equals("prefix") && !type.equals("postfix")) {
			
			throw new WrongInputException ("field type must be either infix, prefix or postfix");
			
		}

		
		// Fields Initialisation		
		this.operatorStack = new Stack<MathToken> ();		
		this.exprStack = new Stack<MathExpr> ();
		
		
		
		// Tokenising Input Via MathLexer		
		lexer = new MathLexer (input, type);		
		this.tokenList = lexer.getTokenList();
		

		
		// Creating Missing Notations
		if (type.equals("infix")) {
			
			this.infixParse(); // OK
			
		} else if (type.equals("prefix")) {
			
			this.prefixParse();
			
		} else if (type.equals("postfix")) {
			
			this.postfixParse(); // OK
			
		} 
		
	}
	

	
	/**
	 * Converts From Infix To MathExpr
	 * @throws MismatchedParenthesisException 
	 * @throws WrongExpressionException 
	 * @throws WrongInputException 
	 */
	private void infixParse() throws WrongExpressionException, WrongInputException {
		
		// Clearing Stacks&Queues
		this.exprStack.clear();
		this.operatorStack.clear();
		
		// Work Copy Of Tokens
		Queue<MathToken> tokenListTMP = this.tokenList.clone();
		
		// TMP Variable
		MathToken readToken = null;
		MathTokenOperator operatorStackToken = null;
		MathExpr exprTMP1 = null;
		MathExpr exprTMP2 = null;
		MathExpr exprTMP3 = null;
		
		while (!tokenListTMP.emptyQueue()) {
			
			readToken = tokenListTMP.deQueue();			
			
			if (readToken.isOperand()) { // Operand
				
				// Creates New Operand Expression
				exprTMP1 = new MathExpr (readToken);
				
				// Push To Expression Stack
				this.exprStack.pushStack(exprTMP1);
				
			} else if (readToken.isSymbol()) { // Operand
				
				// Creates New Symbol Expression
				exprTMP1 = new MathExpr (readToken);
				
				// Push To Expression Stack
				this.exprStack.pushStack(exprTMP1);
				
			} else if (readToken.isOperator()) { // Operator	
				
				if (this.operatorStack.emptyStack()) {
					
					// No Operator Present, Push The Read One To The Stack					
					this.operatorStack.pushStack((MathTokenOperator) readToken);
					
				} else {
					
					// Check If The Top Operator Is Really An Operator
					if (this.operatorStack.topStack().getType() == "operator") {
				
						// Check If readToken Has Less Precedence Than The One In The Stack
						if (readToken.compareTo(this.operatorStack.topStack()) <= 0) {
						
							// Pop Operator From Stack
							operatorStackToken = (MathTokenOperator) this.operatorStack.popStack();
						
							if (this.exprStack.size() < operatorStackToken.getArgNum()) { // Not Enough Arguments
								
								throw new WrongInputException ("Parser - Not Enough Arguments For Operator " + operatorStackToken.getName() 
																+ ", Required At Least " + operatorStackToken.getArgNum() + " !!!");
								
							} else {
								
								if (operatorStackToken.getArgNum() == 1) { // Unary Operators
								
									// Pop Argument
									exprTMP1 = this.exprStack.popStack();
																		
									// Creates New Expression
									exprTMP2 = new MathExpr (operatorStackToken, exprTMP1);
																		
									// Push exprTMP2 To Expression Stack
									this.exprStack.pushStack(exprTMP2);
									
								} else if (operatorStackToken.getArgNum() == 2) { // Binary Operations
									
									// Pop Argument 2
									exprTMP2 = this.exprStack.popStack();
									
									// Pop Argument 1
									exprTMP1 = this.exprStack.popStack();
									
									// Creates New Expression
									exprTMP3 = new MathExpr (operatorStackToken, exprTMP1, exprTMP2);
									
									// Push exprTMP3 To Expression Stack
									this.exprStack.pushStack(exprTMP3);
									
								}
								
							}
							
						}
						
					}	
					
					// Push reaToken To Stack					
					this.operatorStack.pushStack((MathTokenOperator) readToken);						
					
				}
				
			} else if (readToken.isParenthesis()) { // Parenthesis
				
				if (((MathTokenParenthesis) readToken).isLeft()) { // Left Parenthesis
				
					// Push Left Parenthesis To Stack
					this.operatorStack.pushStack(readToken);
				
				} else if (((MathTokenParenthesis) readToken).isRight()) { // Right Parenthesis	
					
					// Left Parenthesis Found Flag
					boolean parFlag = false;
					
					// Until Left Parenthesis Found
					while (!parFlag) {
						
						if (this.operatorStack.emptyStack()) { // Mismatched Parenthesis
							
							throw new WrongInputException ("Parser - Mismatched Parenthesis!!!");
							
						}
						
						if (this.operatorStack.topStack().getValue().equals("(")) { // Left Parenthesis Found
							
							// Set Flag
							parFlag = true;
							
							// Remove Left Parenthesis
							this.operatorStack.popStack();
							
						} else {
							
							// Pop Operator From Stack
							operatorStackToken = (MathTokenOperator) this.operatorStack.popStack();
						
							if (this.exprStack.size() < operatorStackToken.getArgNum()) { // Not Enough Arguments
								
								throw new WrongInputException ("Parser - Not Enough Arguments For Operator " + operatorStackToken.getName() 
																+ ", Required At Least " + operatorStackToken.getArgNum() + " !!!");
								
							} else {
								
								if (operatorStackToken.getArgNum() == 1) { // Unary Operators
								
									// Pop Argument
									exprTMP1 = this.exprStack.popStack();
									
									// Creates New Expression
									exprTMP2 = new MathExpr (operatorStackToken, exprTMP1);
									
									// Push exprTMP2 To Expression Stack
									this.exprStack.pushStack(exprTMP2);
									
								} else if (operatorStackToken.getArgNum() == 2) { // Binary Operations
									
									// Pop Argument 2
									exprTMP2 = this.exprStack.popStack();
									
									// Pop Argument 1
									exprTMP1 = this.exprStack.popStack();
									
									// Creates New Expression
									exprTMP3 = new MathExpr (operatorStackToken, exprTMP1, exprTMP2);
									
									// Push exprTMP3 To Expression Stack
									this.exprStack.pushStack(exprTMP3);
									
								}
								
							}
							
						}
					
					}
				
				}
				
			}
			
		}
		
		while (!this.operatorStack.emptyStack()) { // Empty Operator Stacks
			
			if (this.operatorStack.topStack().isParenthesis()) { // Mismatched Parenthesis
				
				throw new WrongInputException ("Parser - Mismatched Parenthesis!!!");
				
			}	
			
			operatorStackToken =(MathTokenOperator) this.operatorStack.popStack();	
			
			if (operatorStackToken.getArgNum() == 1) { // Unary Operators
				
				// Pop Argument
				exprTMP1 = this.exprStack.popStack();
				
				// Creates New Expression
				exprTMP2 = new MathExpr (operatorStackToken, exprTMP1);
				
				// Push exprTMP2 To Expression Stack
				this.exprStack.pushStack(exprTMP2);
				
			} else if (operatorStackToken.getArgNum() == 2) { // Bynary Operations
				
				// Pop Argument 2
				exprTMP2 = this.exprStack.popStack();
				
				// Pop Argument 1
				exprTMP1 = this.exprStack.popStack();
								
				// Creates New Expression
				exprTMP3 = new MathExpr (operatorStackToken, exprTMP1, exprTMP2);
				
				// Push exprTMP3 To Expression Stack
				this.exprStack.pushStack(exprTMP3);
				
			}
			
		}
		
		if (this.exprStack.size() != 1) { // More Expressions Than Expected
			
			// Remove Correct Expression
			this.exprStack.popStack();
			
			// Exception String
			String exception = "Parser - There Are Operand Not Related To Any Operator: ";
			
			// Print Every Remaining Expression
			while (!this.exprStack.emptyStack()) {
				
				exception += "\n" + this.exprStack.popStack().toString();
				
			}
			
			exception += "\n\nCheck The Input!!!";
			
			throw new WrongInputException (exception);
			
		} else {
			
			// Correct Mathematical Expression Created
			this.expr = this.exprStack.popStack();
			
		}
		
	}
	
		
	
	/**
	 * Converts From Prefix To MathExpr
	 */
	private void prefixParse() {
		
	}


		
	/**
	 * Converts From Postfix To MathExpr
	 */
	private void postfixParse() {
		
		
	
	}

	

	/**
	 * @return the infixString
	 */
	public String getInfixString() {
		
		return this.expr.toStringInfix();
		
	}
	
	

	/**
	 * @return the prefixString
	 */
	public String getPrefixString() {
		
		return this.expr.toStringPrefix();
	
	}
	
	

	/**
	 * @return the postfixString
	 */
	public String getPostfixString() {
		
		return this.expr.toStringPostfix();
		
	}
	
	
	
	/**
	 * @return the Token List
	 */
	public Queue<MathToken> getTokenList() {
		
		return this.tokenList;
		
	}
	
	
	/**
	 * @return the MathExpr
	 */
	public MathExpr getMathExpr() {
		
		return this.expr;
		
	}

	
	
	

	/** 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		String returnString = "MathParser";		
		
		returnString += "\nInfix: " + this.getInfixString();
		
		returnString += "\nPrefix: " + this.getPrefixString();
		
		returnString += "\nPostfix: " + this.getPostfixString();		
		
		return returnString;
		
	}
	

	
	
}
