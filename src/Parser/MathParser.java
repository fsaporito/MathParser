package Parser;

import MathExpr.MathExpr;
import MathToken.MathToken;
import MathToken.MathTokenOperator;
import MathToken.MathTokenParenthesis;
import DataStructures.Queue;
import DataStructures.Stack;
import Exceptions.MismatchedParenthesisException;
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
	public MathParser (String input, String type) throws WrongInputException, MismatchedParenthesisException, WrongExpressionException {
		
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
	private void infixParse() throws MismatchedParenthesisException, WrongExpressionException, WrongInputException {
		
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
			
			if (readToken.isOperand()) {
				
				exprTMP1 = new MathExpr (readToken);
				
				this.exprStack.pushStack(exprTMP1);
				
			} else if (readToken.isOperator()) {
				
				if (this.operatorStack.emptyStack()) {
					
					this.operatorStack.pushStack((MathTokenOperator) readToken);
					
				} else {
					
					if (this.operatorStack.topStack().getType() == "operator") {
				
						if (readToken.compareTo(this.operatorStack.topStack()) <= 0) {
						
							operatorStackToken = (MathTokenOperator) this.operatorStack.popStack();
						
							if (this.exprStack.size() < operatorStackToken.getArgNum()) {
								
								throw new WrongInputException ("Not Enough Arguments For Operator " + operatorStackToken.getName() 
																+ ", Required At Least " + operatorStackToken.getArgNum() + " !!!");
								
							} else {
								
								if (operatorStackToken.getArgNum() == 1) { // Unary Operators
								
									exprTMP1 = this.exprStack.popStack();
									
									exprTMP2 = new MathExpr (operatorStackToken, exprTMP1);
									
									this.exprStack.pushStack(exprTMP2);
									
								} else if (operatorStackToken.getArgNum() == 2) { // Binary Operations
									
									exprTMP2 = this.exprStack.popStack();
									
									exprTMP1 = this.exprStack.popStack();
									
									exprTMP3 = new MathExpr (operatorStackToken, exprTMP1, exprTMP2);
									
									this.exprStack.pushStack(exprTMP3);
									
								}
								
							}
							
						}
						
					}	
					
					this.operatorStack.pushStack((MathTokenOperator) readToken);						
					
				}
				
			} else if (readToken.isParenthesis()) {
				
				if (((MathTokenParenthesis) readToken).isLeft()) {	
				
					this.operatorStack.pushStack(readToken);
				
				} else if (((MathTokenParenthesis) readToken).isRight()) {	
					
					boolean parFlag = false;
					
					while (!parFlag) {
						
						if (this.operatorStack.emptyStack()) {
							
							throw new WrongInputException ("Mismatched Parenthesis!!!");
							
						}
						
						if (this.operatorStack.topStack().getValue().equals("(")) {
							
							parFlag = true;
							
							this.operatorStack.popStack();
							
						} else {
							
							operatorStackToken =(MathTokenOperator) this.operatorStack.popStack();
							
							if (this.exprStack.size() < operatorStackToken.getArgNum()) {
								
								throw new WrongInputException ("Not Enough Arguments For Operator " + operatorStackToken.getName() 
																+ ", Required At Least " + operatorStackToken.getArgNum() + " !!!");
								
							} else {
								
								if (((MathTokenOperator) operatorStackToken).getArgNum() == 1) { // Unary Operators
								
									exprTMP1 = this.exprStack.popStack();
									
									exprTMP2 = new MathExpr (operatorStackToken, exprTMP1);
									
									this.exprStack.pushStack(exprTMP2);
									
								} else if (operatorStackToken.getArgNum() == 2) { // Binary Operations
									
									exprTMP2 = this.exprStack.popStack();
									
									exprTMP1 = this.exprStack.popStack();
									
									exprTMP3 = new MathExpr (operatorStackToken, exprTMP1, exprTMP2);
									
									this.exprStack.pushStack(exprTMP3);
									
								}
							
							}
							
						}
					
					}
				
				}
				
			}
			
		}
		
		while (!this.operatorStack.emptyStack()) { // Empty Operator Stacks
			
			if (this.operatorStack.topStack().isParenthesis()) {
				
				throw new MismatchedParenthesisException ();
				
			}	
			
			operatorStackToken =(MathTokenOperator) this.operatorStack.popStack();			
			
			if (operatorStackToken.getArgNum() == 1) { // Unary Operators
				
				exprTMP1 = this.exprStack.popStack();
				
				exprTMP2 = new MathExpr (operatorStackToken, exprTMP1);
				
				this.exprStack.pushStack(exprTMP2);
				
			} else if (operatorStackToken.getArgNum() == 2) { // Bynary Operations
				
				exprTMP2 = this.exprStack.popStack();
				
				exprTMP1 = this.exprStack.popStack();
				
				exprTMP3 = new MathExpr (operatorStackToken, exprTMP1, exprTMP2);
				
				this.exprStack.pushStack(exprTMP3);
				
			}
			
		}
		
		if (this.exprStack.size() != 1) { // More Expressions Than Expected
			
			this.exprStack.popStack();
			
			String exception = "There Are Operand Not Related To Any Operator: ";
			
			while (!this.exprStack.emptyStack()) {
				
				exception += "\n" + this.exprStack.popStack().toString();
				
			}
			
			exception += "\n\nCheck The Input!!!";
			
			throw new WrongInputException (exception);
			
		} else {
			
			this.expr = this.exprStack.popStack();
			
		}
		
	}
	
		
	
	/**
	 * Converts From Prefix To MathExpr
	 */
	private void prefixParse() {
		
		/*
		
		// Clearing Stacks&Queues
		this.operandStack.clear();
		this.operatorStack.clear();	
		this.tokenListPostfix.clear();
		
		// TokenTMP
		MathToken tmpToken;
		
		// Work Copy Of Tokens
		Queue<MathToken> tokenListTMP = this.tokenListPrefix.clone();		
		
		// Revert The Queue
		tokenListTMP.reverseQueue();
		
		while (!tokenListTMP.emptyQueue()) {
			
			tmpToken = tokenListTMP.deQueue();
			
			if (tmpToken.equals(this.leftPar)) {
				
				tmpToken = this.rightPar;
				
			} else if (tmpToken.equals(this.rightPar)) {
				
				tmpToken = this.leftPar;
				
			}
			
			this.tokenListPostfix.enQueue(tmpToken);
			
		}
		
		*/
		
		
		
	}


		
	/**
	 * Converts From Postfix To MathExpr
	 */
	private void postfixParse() {
		
		/*
		 
		// Clearing Stacks&Queues
		this.operandStack.clear();
		this.operatorStack.clear();
		this.tokenListInfix.clear();
		
		// Work Copy Of Tokens
		Queue<MathToken> tokenListTMP = this.tokenListPostfix.clone();
		
		System.out.print ("\nPostfixToInfix: ");
		
		for (int i = 0; i < tokenListTMP.toArrayList().size(); i++) {
			
			System.out.print ((tokenListTMP.toArrayList().get(i).toStringValue()) + " ");
			
		}
		
		System.out.println ("");
		
		// TMP Variables
		MathToken readToken;
		MathToken operatorStackToken;
		MathToken operandStackToken;
		
		while (!tokenListTMP.emptyQueue()) {
			
			readToken = tokenListTMP.deQueue();
			
			if (readToken.isOperand()) {
				
				this.operandStack.pushStack(readToken);	
				
			} else if (readToken.isOperator()) {
				
				operatorStackToken = readToken;
						
				operandStackToken = operandStack.popStack();
				
		//		this.tokenListInfix.enQueue(this.leftPar);
				
				
						
				if (operandStack.topStack() != null) {
					
					// UNARY_MINUS requires only one parameter
					if (!operatorStackToken.getName().equals("UNARY_MINUS")) {
										
						this.tokenListInfix.enQueue(operandStack.popStack());
						
					}
												
				}
						
				if (operatorStackToken != null) {
						
					this.tokenListInfix.enQueue(operatorStackToken);
							
				}
						
				if (operandStackToken != null) {
						
					this.tokenListInfix.enQueue(operandStackToken);
							
				}
				
			//	this.tokenListInfix.enQueue(this.rightPar);
				
			}
			
			
			
		}
		
		while (!this.operatorStack.emptyStack()) {
			
			operatorStackToken = this.operatorStack.popStack();
			
			operandStackToken = this.operandStack.popStack();
			
			if (this.operandStack.topStack() != null) {
			
				this.tokenListInfix.enQueue(this.operandStack.popStack());
				
			}
			
			if (operatorStackToken != null) {
			
				this.tokenListInfix.enQueue(operatorStackToken);
				
			}
			
			if (operandStackToken != null) {
			
				this.tokenListInfix.enQueue(operandStackToken);
				
			}
			
		}
		
		*/
	
	
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
