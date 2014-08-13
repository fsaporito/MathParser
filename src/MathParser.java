import DataStructures.Queue;
import DataStructures.Stack;


public class MathParser {

	
	/** Infix Expression (Operator Before Operands) */
	private String infixString;
	
	/** Infix Expression (Operator Between Operands) */
	private String prefixString;
	
	/** Postfix Expression (Operator After Operands) */
	private String postfixString;
	
	/** Lexer */
	private MathLexer lexer;
	
	/** Tokenised Infix Input */
	private Queue<MathToken> tokenListInfix;
	
	/** Tokenised Infix Input */
	private Queue<MathToken> tokenListPrefix;
	
	/** Tokenised Infix Input */
	private Queue<MathToken> tokenListPostfix;
	
	/** Operator Stack */
	private Stack<MathToken> operatorStack;
	
	/** Operand Stack */
	private Stack<MathToken> operandStack;
	
	
	
	/**
	 * Constructor:
	 * - Calls MathLexer To Tokenise The Input
	 * - Creates All The Notation Forms (infix, prefix, postfix)
	 * 
	 * @param input Mathematical Expression
	 * @param type input Notation Type (infix, prefix, postfix)
	 * @throws WrongInputException The Input Isn't A Correct Mathematical Expression
	 */
	public MathParser (String input, String type) throws WrongInputException {
		
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
		this.infixString = new String ();		
		this.prefixString = new String ();		
		this.postfixString = new String ();		
		this.tokenListInfix = new Queue<MathToken> ();
		this.tokenListPrefix = new Queue<MathToken> ();
		this.tokenListPostfix = new Queue<MathToken> ();
		this.operatorStack = new Stack<MathToken> ();		
		this.operandStack = new Stack<MathToken> ();
		
		
		// Tokenising Input Via MathLexer		
		lexer = new MathLexer (input, type);		

		
		// Creating Missing Notations
		if (type.equals("infix")) {
			
			this.infixString = input;
			
			this.tokenListInfix = lexer.getTokenList();
			
			this.infixToPostfix();
			
			this.postfixToPrefix();
			
		} else if (type.equals("prefix")) {
			
			this.prefixString = input;
			
			this.tokenListPrefix = lexer.getTokenList();
			
			this.prefixToPostfix();
			
			this.postfixToInfix();
			
		} else if (type.equals("infix")) {
			
			this.postfixString = input;
			
			this.tokenListPostfix = lexer.getTokenList();
			
			this.postfixToInfix();
			
			this.postfixToPrefix();			
			
		} 
		
		this.infixTokenToString();
		
		this.prefixTokenToString();
		
		this.postfixTokenToString();
		
		System.out.println (this.toString());		
		
	}
	
	
	
	/**
	 * Converts From Infix To Prefix Notation
	 */
	private void infixToPrefix() {
		
		// Clearing Stacks&Queues
		this.operatorStack.clear();
		this.operatorStack.clear();
		this.tokenListPrefix.clear();
		
		// Work Copy Of Tokens
		Queue<MathToken> tokenListTMP = this.tokenListInfix.clone();
		
		// TMP Variable
		String tmpString = new String ();
		MathToken readToken;
		MathToken operatorStackToken;
		
		
		
	}
	
	
	/**
	 * Converts From Infix To Postix Notation
	 */
	private void infixToPostfix() {
		
		// Clearing Stacks&Queues
		this.operatorStack.clear();
		this.operatorStack.clear();
		this.tokenListPostfix.clear();
		
		// Work Copy Of Tokens
		Queue<MathToken> tokenListTMP = this.tokenListInfix.clone();
		
		// TMP Variable
		String tmpString = new String ();
		MathToken readToken;
		MathToken operatorStackToken;
		
		while (!tokenListTMP.emptyQueue()) {
			
			readToken = tokenListTMP.deQueue();
			
			if (readToken.isOperand()) {
				
				this.tokenListPostfix.enQueue(readToken);	
				
			} else if (readToken.isOperator()) {
				
				if (this.operatorStack.emptyStack()) {
					
					this.operatorStack.pushStack(readToken);
					
				} else {
					
					if (readToken.compareTo(this.operatorStack.topStack()) <= 0) {
						
						operatorStackToken = this.operatorStack.popStack();
						
						this.tokenListPostfix.enQueue(operatorStackToken);	
						
					} 
					
					this.operatorStack.pushStack(readToken);
					
				}
				
			}
			
		}
		
		while (!this.operatorStack.emptyStack()) {
			
			operatorStackToken = this.operatorStack.popStack();
			
			this.tokenListPostfix.enQueue(operatorStackToken);	
			
		}
		
	}

	
	
	/**
	 * Converts From prefix To Postfix Notation
	 */
	private void prefixToPostfix() {
		
		// Clearing Stacks&Queues
		this.operatorStack.clear();
		this.operatorStack.clear();	
		this.tokenListPostfix.clear();
		
		// Work Copy Of Tokens
		Queue<MathToken> tokenListTMP = this.tokenListPrefix.clone();
		
		// TMP Variable
		String tmpString = new String ();
		MathToken readToken;
		MathToken operatorStackToken;
		
		
		
	}


	
	
	/**
	 * Converts From Postfix To Infix Notation
	 */
	private void postfixToInfix() {
		
		// Clearing Stacks&Queues
		this.operatorStack.clear();
		this.operatorStack.clear();
		this.tokenListInfix.clear();
		
		// Work Copy Of Tokens
		Queue<MathToken> tokenListTMP = this.tokenListPostfix.clone();
		
		// TMP Variable
		String tmpString = new String ();
		MathToken readToken;
		MathToken operatorStackToken;
		
	}


	
	
	/**
	 * Converts From Postfix To Prefix Notation
	 */
	private void postfixToPrefix() {
		
		// Clearing Stacks&Queues
		this.operatorStack.clear();
		this.operatorStack.clear();
		this.tokenListPrefix.clear();
		
		// Work Copy Of Tokens
		Queue<MathToken> tokenListTMP = this.tokenListPostfix.clone();		
		
		// Revert The Queue
		tokenListTMP.reverseQueue();
		
		while (!tokenListTMP.emptyQueue()) {
			
			this.tokenListPrefix.enQueue(tokenListTMP.deQueue());
			
		}		
		
	}

	
	

	/**
	 * @return the infixString
	 */
	public String getInfixString() {
		
		return this.infixString;
		
	}
	
	
	



	/**
	 * @return the prefixString
	 */
	public String getPrefixString() {
		
		return this.prefixString;
	
	}
	
	
	



	/**
	 * @return the postfixString
	 */
	public String getPostfixString() {
		
		return this.postfixString;
		
	}
	
	
	



	/**
	 * @return the tokenListInfix
	 */
	public Queue<MathToken> getTokenListInfix() {
		
		return this.tokenListInfix;
		
	}
	
	
	



	/**
	 * @return the tokenListPrefix
	 */
	public Queue<MathToken> getTokenListPrefix() {
		
		return this.tokenListPrefix;
		
	}
	
	
	



	/**
	 * @return the tokenListPostfix
	 */
	public Queue<MathToken> getTokenListPostfix() {
		
		return this.tokenListPostfix;
	
	}

	
	
	/**
	 * Create A String From The Token List
	 */
	private void infixTokenToString () {
		
		Queue<MathToken> tokenListTMP = this.tokenListInfix;
		
		while (!tokenListTMP.emptyQueue()) {
			
			this.infixString += tokenListTMP.deQueue().getValue() + " ";
			
		}		
		
	}

	

	/**
	 * Create A String From The Token List
	 */
	private void prefixTokenToString () {
		
		Queue<MathToken> tokenListTMP = this.tokenListPrefix;
		
		while (!tokenListTMP.emptyQueue()) {
			
			this.prefixString += tokenListTMP.deQueue().getValue() + " ";
			
		}		
		
	}
	
	
	
	/**
	 * Create A String From The Token List
	 */
	private void postfixTokenToString () {
		
		Queue<MathToken> tokenListTMP = this.tokenListPostfix;
		
		while (!tokenListTMP.emptyQueue()) {
						
			this.postfixString += tokenListTMP.deQueue().getValue() + " ";
			
		}		
		
	}
	
	



	/** 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		String returnString = "MathParser";
		
		returnString += "\nInfix: " + this.infixString;
		
		returnString += "\nPrefix: " + this.prefixString;
		
		returnString += "\nPostfix: " + this.postfixString;
		
		
		return returnString;
	}
	

	
	
}
