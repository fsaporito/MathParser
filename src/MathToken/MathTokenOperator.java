package MathToken;

import Exceptions.WrongInputException;

public class MathTokenOperator extends MathToken {

	
	/**
	 * Precedences:
	 * 1:
	 * 2: PLUS BINARY_MINUS
	 * 3: MOLT DIV
	 * 4: UNARY_MINUS
	 * 5: MATH FUNCTIONS
	 * 
	 */
	private int precedence;
	
	private int argNum;
	
	public MathTokenOperator (String name, String value, int precedence, int argNum) throws WrongInputException {
		
		super (value, name);
		
		this.type = "operator";
		
		this.precedence = precedence;
		
		if (argNum > 0) {
			
			this.argNum = argNum;
			
		} else {
			
			throw new WrongInputException ("Arguments Number Must Be A Non Negative Integer");
			
		}
		
	}
	
	
	/**
	 * @return the precedence
	 */
	public int getPrecedence () {
	
		return this.precedence;
	
	}
	
	
	/**
	 * @return the argument number
	 */
	public int getArgNum () {
	
		return this.argNum;
	
	}
	
	
	
	@Override
	protected int myCompareTo(MathTokenOperator arg0) {
	
		int compareValue = 0;
		
		compareValue = this.precedence - arg0.getPrecedence();
		
		return compareValue;
	
	
	}
	
	
	/** 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		return "MathToken " + this.name + " value=" + value + ", type=" + type + "" + ", precedence=" + precedence;
		
	}
	
	

}
