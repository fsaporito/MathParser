package MathToken;

import Exceptions.WrongCompareException;


public abstract class MathToken implements Comparable<MathToken> {

	protected String value;
	
	protected String type; 
	
	protected String name;
	
	public MathToken (String value, String name) {
		
		if (value == null) { // Value Mustn't Be Null
			
			throw new NullPointerException ("Value is null!!!");
			
		}
		
		if (value.length() == 0) { // Value Mustn't Be Empty
			
			throw new NullPointerException ("Value is empty!!!");
			
		}	
		
		if (name == null) { // Name Mustn't Be Null
			
			throw new NullPointerException ("Name is null!!!");
			
		}
		
		if (name.length() == 0) { // Name Mustn't Be Empty
			
			throw new NullPointerException ("Name is empty!!!");
			
		}
		
		this.value = value;	
		
		this.name = name;
		
	}
	
	
	
	/**
	 * Check If The Token Is An Operand
	 * @return TRUE If The Token Is An Operand, False Otherwise
	 */
	public boolean isOperand() {
		
		boolean returnValue;
		
		if (this.type.equals("operand")) {
			
			returnValue = true;
			
		} else {
			
			returnValue = false;
			
		}
		
		return returnValue;
		
	}

	

	/**
	 * Check If The Token Is An Operator
	 * @return TRUE If The Token Is An Operator, False Otherwise
	 */
	public boolean isOperator() {
		
		boolean returnValue;
		
		if (this.type.equals("operator")) {
			
			returnValue = true;
			
		} else {
			
			returnValue = false;
			
		}
		
		return returnValue;
		
	}
	
	
	
	/**
	 * Check If The Token Is A Parenthesis
	 * @return TRUE If The Token Is A Parenthesis, False Otherwise
	 */
	public boolean isParenthesis() {
		
		boolean returnValue;
		
		if (this.type.equals("parenthesis")) {
			
			returnValue = true;
			
		} else {
			
			returnValue = false;
			
		}
		
		return returnValue;
		
	}

	
	
	
	/**
	 * @return the value
	 */
	public String getValue() {
		
		return this.value;
	
	}




	/**
	 * @return the type
	 */
	public String getType() {
		
		return this.type;
		
	}
	
	/**
	 * @return the name
	 */
	public String getName () {
		
		return this.name;
		
	}


	
	
	@Override
	public int compareTo(MathToken arg0) {
		
		int compareValue = 0;
		
		try {
			
			if (!this.type.equals(arg0.getType())) {
						
				throw new WrongCompareException ("Token Not Comparable (Different Types)");
			
			}
			
		} catch (WrongCompareException e) {
				
				e.printStackTrace();
			
		}
		
		if (this.type.equals ("operand")) {
			
			compareValue = this.myCompareTo((MathTokenOperand) arg0);
			
		} else if (this.type.equals ("operator")) {
			
			compareValue = this.myCompareTo((MathTokenOperator) arg0);
			
		} else if (this.type.equals("parenthesis")) {
			
			compareValue = this.myCompareTo((MathTokenParenthesis) arg0);
			
		}
		
		return compareValue;
		
	}


	protected int myCompareTo(MathTokenOperand arg0) {return 0;}
	
	protected int myCompareTo(MathTokenOperator arg0) {return 0;}
	
	protected int myCompareTo(MathTokenParenthesis arg0) {return 0;}


	/** 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		return "MathToken value=" + value + ", type=" + type + "";
		
	}
	
	/** 
	 * @see java.lang.Object#toString()
	 */
	public String toStringValue() {
		
		return this.value.toString();
		
	}
	

	
	
	
}
