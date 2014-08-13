import DataStructures.Stack;


public class MathLexer {

	/** Raw Input */
	private String input;
	
	/** Tokenised Input */
	private Stack<MathToken> TokenList;
	
	
	/**
	 * Constructor:
	 * - Checks The Input (MathScanner)
	 * - Tokenise It
	 * 
	 * @param input Mathematical Expression
	 * @param type input Notation Type (infix, prefix, postfix)
	 * @throws WrongInputException The Input Isn't A Correct Mathematical Expression
	 */
	public MathLexer (String input, String type) throws WrongInputException {
		
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
		
		
		if (type.equals("infix")) {
			
			this.tokeniseInfix();
			
		} else if (type.equals("prefix")) {
			
			this.tokenisePrefix();
			
		} else if (type.equals("infix")) {
			
			this.tokenisePostfix();			
			
		} 
		
		
		
		
	}

	
	/**
	 * Remove All The Spaces From The Input String
	 * @param input
	 */
	private String spaceRemover(String input) {
		
		String returnString = new String ();
		
		for (int i = 0; i < input.length(); i++) {
			
			if (input.charAt(i) != ' ') {
				
				returnString += input.charAt(i);
				
			}
			
		}
		
		return returnString;
		
	}



	/**
	 * Tokenise The Infix Input
	 */
	private void tokeniseInfix() {
		
		String tmpString = new String();
		
		tmpString = this.spaceRemover(this.input);
		
		for (int i = 0; i < tmpString.length(); i++) {
			
			// Tokenise tmpString
			
		}		
		
		
	}
	
	

	/**
	 * Tokenise The Prefix Input
	 */
	private void tokenisePrefix() {
		
		String tmpString = new String();
		
		tmpString = this.input;
		
		for (int i = 0; i < tmpString.length(); i++) {
			
			// Tokenise tmpString
			
		}
		
	}


	
	/**
	 * Tokenise The Postfix Input
	 */
	private void tokenisePostfix() {
		
		String tmpString = new String();
		
		tmpString = this.input;
		
		for (int i = 0; i < tmpString.length(); i++) {
			
			// Tokenise tmpString
			
		}
		
	}


	
	/**
	 * @return the input
	 */
	public String getInput() {
		
		return this.input;
	
	}

	

	/**
	 * @return the tokenList
	 */
	public Stack<MathToken> getTokenList() {
		
		return this.TokenList;
	
	}
	
	

}
