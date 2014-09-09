package Parser;
import java.util.ArrayList;

import DataStructures.Queue;
import Exceptions.WrongInputException;


public class MathLexer {

	/** Raw Input */
	private String input;
	
	/** Tokenised Input */
	private Queue<MathToken> TokenList;
	
	/** Tokenised String */
	private String TokenString;

	/** ArrayList With The Accepted Numbers*/
	private ArrayList<Character> numArrayList;

	/** ArrayList With The Accepted Operators*/
	private ArrayList<Character> operatorArrayList;
	
	
	/**
	 * Constructor:
	 * - Checks The Input (MathScanner)
	 * - Tokenise It
	 * 
	 * @param input Mathematical Expression
	 * @throws WrongInputException The Input Isn't A Correct Mathematical Expression
	 */
	public MathLexer (String input) throws WrongInputException {
		
		if (input == null) { // Input Mustn't Be Null
			
			throw new NullPointerException ("Input is null!!!");
			
		}
		
		if (input.length() == 0) { // Input Mustn't Be Empty
			
			throw new NullPointerException ("Input is empty!!!");
			
		}
		
		this.input = input;
		
		this.TokenString = "";
		
		this.TokenList = new Queue<MathToken> ();
		
		this.instatitiateCharacterArrayList();		
		
		this.tokenise();	
		
		ArrayList<MathToken> tokenArrTMP = this.TokenList.toArrayList();
		
		for (int i = 0; i < tokenArrTMP.size(); i++) {
			
			this.TokenString += tokenArrTMP.get(i).getValue() + " ";
			
		}
		
		this.TokenString = this.TokenString.substring(0, this.TokenString.length()-1); // Removes Final Space
		
	}

	
	
	/** 
	 * Instantiate The Character ArrayList
	 * - num+point  ArrayList
	 * - operator arrayList
	 */
	private void instatitiateCharacterArrayList() {
		
		// ArrayList With Valid Digit Character
		this.numArrayList = new ArrayList<Character> ();				
		this.numArrayList.add('0');
		this.numArrayList.add('1');
		this.numArrayList.add('2');
		this.numArrayList.add('3');
		this.numArrayList.add('4');
		this.numArrayList.add('5');
		this.numArrayList.add('6');
		this.numArrayList.add('7');
		this.numArrayList.add('8'); 
		this.numArrayList.add('9'); 
		this.numArrayList.add('.');
		
		// ArrayList With Valid Operator Character
		this.operatorArrayList = new ArrayList<Character> ();				
		this.operatorArrayList.add('+');
		this.operatorArrayList.add('-');
		this.operatorArrayList.add('*');
		this.operatorArrayList.add('/');		
		
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
	 * Tokenise The Input
	 */
	private void tokenise () {
		
		String tmpString = new String();
		
		tmpString = this.spaceRemover(this.input);
		
		String valueString = new String ();
		MathTokenOperator operatorTMP;
		MathTokenOperand operandTMP;
		MathTokenParenthesis parenthesisTMP;
		
		for (int i = 0; i < tmpString.length(); i++) {
			
			if (this.isDigit(tmpString.substring(i, i+1))) {			
				
				int lastDigitIndex = i;
				
				boolean notDigitFlag = true;
				
				for (int j = i+1; (j < tmpString.length() && notDigitFlag); j++) {
										
					if (this.isDigit(tmpString.substring(i, j+1))) {
						
						lastDigitIndex++;
						
					} else {
						
						notDigitFlag = false;
						
					}
					
				}
				
				valueString = tmpString.substring(i, lastDigitIndex+1);
				
				operandTMP = new MathTokenOperand (valueString);
				
				this.TokenList.enQueue(operandTMP);
				
				i = lastDigitIndex;
				
			} else if (tmpString.charAt(i) == '+') {
				
				valueString = tmpString.substring(i, i+1);
				
				operatorTMP = new MathTokenOperator (valueString, 2);
				
				this.TokenList.enQueue(operatorTMP);
				
			} else if (tmpString.charAt(i) == '-') {
				
				valueString = tmpString.substring(i, i+1);
				
				operatorTMP = new MathTokenOperator (valueString, 2);
				
				this.TokenList.enQueue(operatorTMP);
				
			} else if (tmpString.charAt(i) == '*') {
				
				valueString = tmpString.substring(i, i+1);
				
				operatorTMP = new MathTokenOperator (valueString, 3);
				
				this.TokenList.enQueue(operatorTMP);
				
			} else if (tmpString.charAt(i) == '/') {
				
				valueString = tmpString.substring(i, i+1);
				
				operatorTMP = new MathTokenOperator (valueString, 3);
				
				this.TokenList.enQueue(operatorTMP);
				
			} else if (tmpString.charAt(i) == '(' || tmpString.charAt(i) == ')') {
					
					valueString = tmpString.substring(i, i+1);
					
					parenthesisTMP = new MathTokenParenthesis (valueString);
					
					this.TokenList.enQueue(parenthesisTMP);
				
			} else {
				
				System.out.println ("Character Not Recognised: " + tmpString.charAt(i));
				
			}
			
		}	
		
	}
	
	
	
	/**
	 * Check If The Given String Is A Number
	 * 
	 * @param s String To Check
	 * @return True If The String Is A Number, False Otherwise
	 */
	private boolean isDigit (String s) {
		
		
		// Return Value
		boolean returnValue = true;
		
		// Point Counter
		int pointCount = 0;
		
		
		
	
		// Null String isn't a number
		if (s == null) {
		
			returnValue = false;
		
		// Empty String isn't a number
		} else if (s.length() == 0) {
		
			returnValue = false;
		
		} else {
	
			// A number cannot start with 0 unless it's a decimal (0.x)
			if (s.length() > 1 && s.charAt(0) == '0' && s.charAt(1) != '.') {
			
				returnValue = false;
				
			}
		
			// A number cannot start with .
			if (s.charAt(0) == '.') {
						
				returnValue = false;
							
			}
		
			
			// Check if every character is a number or a point (max one)
			for (int i = 0; (i < s.length() && returnValue); i++) {
			
				if (!this.numArrayList.contains(s.charAt(i))) {
				
					returnValue = false;
				
				}
			
				if (s.charAt(i) == '.') {
				
					pointCount++;
				
					if (pointCount > 1) {
					
						returnValue = false;
					
					}
				
				}
			
			}	
		
		}	
	
		return returnValue;
	
	}

	
	/**
	 * @return the input
	 */
	public String getInput() {
		
		return this.input;
	
	}
	
	
	/**
	 * @return the tokenString
	 */
	public String getTokenString() {
		
		return this.TokenString;
	
	}

	

	/**
	 * @return the tokenList
	 */
	public Queue<MathToken> getTokenList() {
		
		return this.TokenList;
	
	}
	
	

}
