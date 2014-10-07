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
	
	/** Operator Arguments Number*/
	private int argNum;
	
	
	/**
	 * Constructor That Creates A New Operator
	 * 
	 * @param name Operator Name
	 * @param value Operator Value (Symbol)
	 * @param precedence Operator Precedence (Higher -> More Precedence)
	 * @param argNum Operator Arguments Number
	 * @throws WrongInputException
	 */
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
	
	
	/**
	 * Custom Implementation Of CompareTo
	 */
	@Override
	protected int myCompareTo(MathTokenOperator arg0) {
	
		int compareValue = 0;
		
		compareValue = this.precedence - arg0.getPrecedence();
		
		return compareValue;
	
	
	}
	
	
	/**
	 * HashCode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + argNum;
		result = prime * result + precedence;
		return result;
	}


	/**
	 * Equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MathTokenOperator other = (MathTokenOperator) obj;
		if (argNum != other.argNum)
			return false;
		if (precedence != other.precedence)
			return false;
		return true;
	}


	/** 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		return "MathToken " + this.name + " value=" + value + ", type=" + type + "" + ", precedence=" + precedence;
		
	}
	
	

}
