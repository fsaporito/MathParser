package Parser;
import java.util.ArrayList;

import MathToken.MathToken;
import MathToken.MathTokenOperand;
import MathToken.MathTokenOperator;
import MathToken.MathTokenParenthesis;
import MathToken.MathTokenSymbol;
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
				
		this.input = input.toLowerCase();
		
		this.type = type;
		
		this.TokenString = "";
		
		this.TokenList = new Queue<MathToken> ();
		
		this.instatiateCharacterArrayList();		
		
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
	private void instatiateCharacterArrayList() {
		
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
		this.operatorArrayList.add('^');
		
	}
	
	

	/**
	 * Tokenise The Input
	 */
	private void tokenise () throws WrongInputException {
		
		String tmpString = new String();
		
		tmpString = this.input;
		
		String valueString = new String ();
		MathTokenOperator operatorTMP;
		MathTokenOperand operandTMP;
		MathTokenParenthesis parenthesisTMP;
		MathTokenSymbol symbolTMP;
		
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
				
			} else if (tmpString.charAt(i) == 'e' && !(tmpString.length() > i+2 && tmpString.charAt(i+1) == 'x' && tmpString.charAt(i+2) == 'p')) { // e number
								
				valueString = "2.718";
				
				operandTMP = new MathTokenOperand (valueString);
				
				this.TokenList.enQueue(operandTMP);
				
			} else if (tmpString.charAt(i) == 'p') { // PI number
				
				if (i+1 != this.input.length() && (tmpString.charAt(i+1) == 'i')) {
				
					valueString = "PI";
					
					operandTMP = new MathTokenOperand (valueString);
					
					this.TokenList.enQueue(operandTMP);
					
					i++;
				
				} else {
					
					valueString = tmpString.substring(i, i+1);
					
					symbolTMP = new MathTokenSymbol (valueString);
					
					this.TokenList.enQueue(symbolTMP);
					
				}				
				
			} else if (tmpString.charAt(i) == '+') { // Plus operator	
				
				operatorTMP = Operators.plus();
				
				this.TokenList.enQueue(operatorTMP);
				
			} else if (tmpString.charAt(i) == '-') { // Minus operator
				
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
				
			} else if (tmpString.charAt(i) == '#') { // Unary Minus
				
				operatorTMP = Operators.minus_u();
				
				this.TokenList.enQueue(operatorTMP);
				
			} else if (tmpString.charAt(i) == '*') { // Multiplication Operator
				
				operatorTMP = Operators.mult();
				
				this.TokenList.enQueue(operatorTMP);
				
			} else if (tmpString.charAt(i) == '/') { // Division Operator
				
				operatorTMP = Operators.div();
				
				this.TokenList.enQueue(operatorTMP);
				
			} else if (tmpString.charAt(i) == '^') {
				
				operatorTMP = Operators.pow();
				
				this.TokenList.enQueue(operatorTMP);
				
			} else if (i+3 < this.input.length() && this.input.substring(i, i+3).equals("log")) { // Log Operators
				
				operatorTMP = Operators.log();
							
				this.TokenList.enQueue(operatorTMP);
							
				i = i+2;	
					
			} else if (i+3 < this.input.length() && this.input.substring(i, i+3).equals("exp")) {
					
				operatorTMP = Operators.exp();
							
				this.TokenList.enQueue(operatorTMP);
							
				i = i+2;
					
			} else if (i+3 < this.input.length() && this.input.substring(i, i+3).equals("pow")) {
					
				operatorTMP = Operators.pow();
							
				this.TokenList.enQueue(operatorTMP);
							
				i = i+2;
					
			} else if (i+3 < this.input.length() && this.input.substring(i, i+3).equals("cos")) { // Cos And Cosh
					
				if (i+4 < this.input.length() && this.input.charAt(i+4) == 'h') { // Cosh
					
					operatorTMP = Operators.cosh();
					
					this.TokenList.enQueue(operatorTMP);
					
					i = i+3;
					
				} else { // Cos
				
					operatorTMP = Operators.cos();
							
					this.TokenList.enQueue(operatorTMP);
							
					i = i+2;
					
				}
					
			} else if (i+3 < this.input.length() && this.input.substring(i, i+3).equals("sin")) { // Sin And Sinh
				
				if (i+4 < this.input.length() && this.input.charAt(i+4) == 'h') { // Sinh
					
					operatorTMP = Operators.sinh();
					
					this.TokenList.enQueue(operatorTMP);
					
					i = i+3;
					
				} else { // Sin
				
					operatorTMP = Operators.sin();
							
					this.TokenList.enQueue(operatorTMP);
							
					i = i+2;
					
				}
					
			} else if (i+3 < this.input.length() && this.input.substring(i, i+3).equals("tan")) { // Tan And Tanh
				
				if (i+4 < this.input.length() && this.input.charAt(i+4) == 'h') { // Tanh
					
					operatorTMP = Operators.tanh();
					
					this.TokenList.enQueue(operatorTMP);
					
					i = i+3;
					
				} else { // Tan
				
					operatorTMP = Operators.tan();
							
					this.TokenList.enQueue(operatorTMP);
							
					i = i+2;
					
				}
					
			} else if (i+4 < this.input.length() && this.input.substring(i, i+4).equals("fact")) { // Factorial
					
				operatorTMP = Operators.fact();
					
				this.TokenList.enQueue(operatorTMP);
							
				i = i+3;
					
			} else if (i+4 < this.input.length() && this.input.substring(i,i+4).equals("sqrt")) { // Sqrt
					
				operatorTMP = Operators.sqrt();
				
				this.TokenList.enQueue(operatorTMP);
							
				i = i+3;
					
					
			} else if (i+4 < this.input.length() && this.input.substring(i, i+4).equals("acos")) { // Arcos
										
				operatorTMP = Operators.acos();
						
				this.TokenList.enQueue(operatorTMP);
								
				i = i+3;
					
			}  else if (i+4 < this.input.length() && this.input.substring(i, i+4).equals("asin")) { // Arcsin
					
				operatorTMP = Operators.asin();
					
				this.TokenList.enQueue(operatorTMP);
							
				i = i+3;
				
			} else if (i+4 < this.input.length() && this.input.substring(i, i+4).equals("atan")) { // Arctan
					
				operatorTMP = Operators.atan();
					
				this.TokenList.enQueue(operatorTMP);
							
				i = i+3;
				
			} else if (tmpString.charAt(i) == '(' || tmpString.charAt(i) == ')') {
					
				valueString = tmpString.substring(i, i+1);
					
				parenthesisTMP = new MathTokenParenthesis (valueString);
					
				this.TokenList.enQueue(parenthesisTMP);
		
			} else if (tmpString.charAt(i) >= 'a' && tmpString.charAt(i) <= 'z') {
				
				valueString = tmpString.substring(i, i+1);
				
				symbolTMP = new MathTokenSymbol (valueString);
				
				this.TokenList.enQueue(symbolTMP);
				
			} else {
				
				throw new WrongInputException ("Lexer - Character Not Recognised: " + tmpString.charAt(i));
				
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
