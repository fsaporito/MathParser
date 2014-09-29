package Parser;
import java.util.ArrayList;

import MathToken.MathToken;
import MathToken.MathTokenOperand;
import MathToken.MathTokenOperator;
import MathToken.MathTokenParenthesis;

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
	
	/** Operators */
	private MathTokenOperator plus;
	private MathTokenOperator minus_u;
	private MathTokenOperator minus_b;
	private MathTokenOperator molt;
	private MathTokenOperator div;
	private MathTokenOperator sqrt_u;
	private MathTokenOperator sqrt_b;
	private MathTokenOperator log_u;
	private MathTokenOperator log_b;
	private MathTokenOperator exp;
	private MathTokenOperator pow;
	private MathTokenOperator fact;
	private MathTokenOperator cos;
	private MathTokenOperator sin;
	private MathTokenOperator tan;
	private MathTokenOperator acos;
	private MathTokenOperator asin;
	private MathTokenOperator atan;
	private MathTokenOperator cosh;
	private MathTokenOperator sinh;
	private MathTokenOperator tanh;
	
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
		
		this.plus = new MathTokenOperator ("PLUS", "+", 2, 2);
		this.minus_u = new MathTokenOperator ("UNARY_MINUS", "-", 4, 1);
		this.minus_b = new MathTokenOperator ("BINARY_MINUS", "-", 2, 2);
		this.molt = new MathTokenOperator ("MOLT", "*", 3, 2);
		this.div = new MathTokenOperator ("DIV", "/", 3, 2);
		
		this.sqrt_u = new MathTokenOperator ("UNARY_SQRT", "sqrt", 5, 1);
		this.sqrt_b = new MathTokenOperator ("BINARY_SQRT", "sqrt", 5, 2);
		this.log_u = new MathTokenOperator ("UNARY_LOG", "log", 5, 1);
		this.log_b = new MathTokenOperator ("BINARY_LOG", "log", 5, 2);;
		this.exp = new MathTokenOperator ("EXP", "exp", 5, 1);
		this.pow = new MathTokenOperator ("POW", "pow", 5, 2);

		this.cos = new MathTokenOperator ("COS", "cos", 5, 1);
		this.sin = new MathTokenOperator ("SIN", "sin", 5, 1);
		this.tan = new MathTokenOperator ("TAN", "tan", 5, 1);
		this.acos = new MathTokenOperator ("ACOS", "arcos", 5, 1);
		this.asin  = new MathTokenOperator ("ASIN", "arcsin", 5, 1);
		this.atan = new MathTokenOperator ("ATAN", "artan", 5, 1);
		this.cosh = new MathTokenOperator ("COSH", "cosh", 5, 1);
		this.sinh = new MathTokenOperator ("SINH", "sinh", 5, 1);
		this.tanh = new MathTokenOperator ("TANH", "tanh", 5, 1);
		
		
		this.fact = new MathTokenOperator ("FACT", "fact", 5, 1);
		
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
				
				operatorTMP = this.plus;
				
				this.TokenList.enQueue(operatorTMP);
				
			} else if (tmpString.charAt(i) == '-') {
				
				valueString = tmpString.substring(i, i+1);
				
				/*
				 * Unary Minus:
				 * - If it's the first token in the string
				 * - If it comes after an operator
				 * - If it's the first token after a left parenthesis
				 */
				if (this.TokenList.emptyQueue() || 
					this.TokenList.topQueue().isOperator() ||
					this.TokenList.topQueue().getValue().equals("(")) { 
					
					operatorTMP = this.minus_u;
					
				} else { // Binary Minus
					
					operatorTMP = this.minus_b;
					
				}				
				
				this.TokenList.enQueue(operatorTMP);
				
			} else if (tmpString.charAt(i) == '*') {
				
				valueString = tmpString.substring(i, i+1);
				
				operatorTMP = this.molt;
				
				this.TokenList.enQueue(operatorTMP);
				
			} else if (tmpString.charAt(i) == '/') {
				
				valueString = tmpString.substring(i, i+1);
				
				operatorTMP = this.div;
				
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
