import DataStructures.Stack;


public class MathParser {

	
	/** Infix Expression (Operator Before Operands) */
	private String infix;
	
	/** Infix Expression (Operator Between Operands) */
	private String prefix;
	
	/** Postfix Expression (Operator After Operands) */
	private String postfix;
	
	/** Lexer */
	private MathLexer lexer;
	
	/** Tokenised Input */
	private Stack<MathToken> tokenList;
	
	/** Operator Stack */
	private Stack<String> operatorStack;
	
	/** Operand Stack */
	private Stack<String> operandStack;
	
	
	
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
		
		if (!type.equals("infix") || !type.equals("prefix") || !type.equals("postfix")) {
			
			throw new WrongInputException ("field type must be either infix, prefic or postfix");
			
		}

		
		// Fields Initialisation		
		this.infix = new String ();		
		this.prefix = new String ();		
		this.postfix = new String ();		
		this.operatorStack = new Stack<String> ();		
		this.operandStack = new Stack<String> ();
		
		
		// Tokenising Input Via MathLexer		
		lexer = new MathLexer (input, type);		
		this.tokenList = lexer.getTokenList();
		
		
		// Creating Missing Notations
		if (type.equals("infix")) {
			
			this.infix = input;
			
			this.infixToPrefix();
			
			this.prefixToPostfix();
			
		} else if (type.equals("prefix")) {
			
			this.prefix = input;
			
			this.prefixToPostfix();
			
			this.postfixToInfix();
			
		} else if (type.equals("infix")) {
			
			this.postfix = input;
			
			this.postfixToInfix();
			
			this.postfixToPrefix();			
			
		} 
		
		
	}
	
	
	
	/**
	 * Converts From Infix To Prefix Notation
	 */
	private void infixToPrefix() {
		
		// Clearing Stacks
		this.operatorStack.clear();
		this.operatorStack.clear();
		
	}

	
	
	/**
	 * Converts From prefix To Postfix Notation
	 */
	private void prefixToPostfix() {
		
		// Clearing Stacks
		this.operatorStack.clear();
		this.operatorStack.clear();
		
		
		
	}


	
	
	/**
	 * Converts From Postfix To Infix Notation
	 */
	private void postfixToInfix() {
		
		// Clearing Stacks
		this.operatorStack.clear();
		this.operatorStack.clear();
		
	}


	
	
	/**
	 * Converts From Postfix To Prefix Notation
	 */
	private void postfixToPrefix() {
		
		// Clearing Stacks
		this.operatorStack.clear();
		this.operatorStack.clear();
		
	}

	
	
	
	/**
	 * @return The infix Expression
	 */
	public String getInfix() {
		
		return this.infix;
	
	}


	

	/**
	 * @return The prefix Expression
	 */
	public String getPrefix() {
		
		return this.prefix;
	
	}



	
	/**
	 * @return The postfix Expression
	 */
	public String getPostfix() {
		
		return this.postfix;
	
	}



	
	/** 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		String returnString = "MathLexer";
		
		returnString += "\nInfix: " + this.infix;
		
		returnString += "\nPrefix: " + this.prefix;
		
		returnString += "\nPostfix: " + this.postfix;
		
		
		return returnString;
	}
	

	
	
}
