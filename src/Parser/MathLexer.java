package Parser;
import java.util.ArrayList;

import MathToken.MathToken;
import MathToken.MathTokenOperand;
import MathToken.MathTokenOperator;
import MathToken.MathTokenParenthesis;
import MathToken.Operators;

import DataStructures.Queue;
import Exceptions.WrongInputException;


public class MathLexer {

	/** Raw Input */
	private String input;
	
	/** Input Type (Prefix, Infix, Postfix) */
	private String type;
	
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
	 * @param input Mathematical Expression as String
	 * @param type Expression Type (Prefix, Infix, Postfix)
	 * @throws WrongInputException The Input Isn't A Correct Mathematical Expression
	 */
	public MathLexer (String input, String type) throws WrongInputException {
		
		if (input == null) { // Input Mustn't Be Null
			
			throw new NullPointerException ("Input is null!!!");
			
		}
		
		if (input.length() == 0) { // Input Mustn't Be Empty
			
			throw new NullPointerException ("Input is empty!!!");
			
		}
		
		if (type == null) {
			
			throw new NullPointerException ("Type is null!!!");
			
		}
		
		if (!type.equals("prefix")
			&& !type.equals("infix")
			&& !type.equals("postfix")) {
			
			throw new WrongInputException ("Type Must Be Either prefix, infix or postfix!!!");
			
		}
				
		this.input = input;
		
		this.type = type;
		
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
	 * Tokenise The Input
	 */
	private void tokenise () {
		
		String tmpString = new String();
		
		tmpString = this.input;
		
		String valueString = new String ();
		MathTokenOperator operatorTMP;
		MathTokenOperand operandTMP;
		MathTokenParenthesis parenthesisTMP;
		
		for (int i = 0; i < tmpString.length(); i++) {
			
			if (tmpString.charAt(i) == ' ') { // Do Nothing
			
				continue;
				
			} else if (this.isDigit(tmpString.substring(i, i+1))) {			
				
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
				
				operatorTMP = Operators.plus();
				
				this.TokenList.enQueue(operatorTMP);
				
			} else if (tmpString.charAt(i) == '-') {
				
				valueString = tmpString.substring(i, i+1);
				
				if (this.type.equals("infix")) {
				
					/*
					 * Unary Minus:
					 * - If it's the first token in the string
					 * - If it comes after an operator
					 * - If it's the first token after a left parenthesis
					 */
					if (this.TokenList.emptyQueue() || 
						this.TokenList.toArrayList().get(this.TokenList.size()-1).isOperator() ||
						this.TokenList.toArrayList().get(this.TokenList.size()-1).getValue().equals("(")) { 
					
						operatorTMP = Operators.minus_u();
					
					} else { // Binary Minus
					
						operatorTMP = Operators.minus_b();
						
					}
					
					this.TokenList.enQueue(operatorTMP);
					
				} else if (this.type.equals("prefix"))	{
					
					operatorTMP = Operators.minus_b();
					
					this.TokenList.enQueue(operatorTMP);
					
				} else if (this.type.equals("postfix")) {
					
					operatorTMP = Operators.minus_b();
					
					this.TokenList.enQueue(operatorTMP);
					
				}
				
				
				
			} else if (tmpString.charAt(i) == '*') {
				
				valueString = tmpString.substring(i, i+1);
				
				operatorTMP = Operators.molt();
				
				this.TokenList.enQueue(operatorTMP);
				
			} else if (tmpString.charAt(i) == '/') {
				
				valueString = tmpString.substring(i, i+1);
				
				operatorTMP = Operators.div();
				
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
