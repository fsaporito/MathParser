package MathToken;

import Exceptions.WrongInputException;

public class MathTokenParenthesis extends MathToken {
	
	public MathTokenParenthesis(String value) throws WrongInputException {
		
		super(value, "parenthesis");
		
		this.type = "parenthesis";
		
		if ( (!value.equals("(")) || (!value.equals(")")) ) {
			
			throw new WrongInputException ("MathTokenParenthesis: Value Is Not A Digit!!!");
			
		}
		
	}
	
	
	
	/**
	 * Check If The Parenthesis Is A Left One
	 * @return TRUE if the parenthesis is a left one, false otherwise
	 */
	public boolean isLeft () {
		
		boolean returnValue = false;
		
		if (this.value.equals("(")) {
			
			returnValue = true;
			
		}
		
		return returnValue;
		
	}
	
	
	/**
	 * Check If The Parenthesis Is A Right One
	 * @return TRUE if the parenthesis is a right one, false otherwise
	 */
	public boolean isRight () {
		
		boolean returnValue = false;
		
		if (this.value.equals(")")) {
			
			returnValue = true;
			
		}
		
		return returnValue;
		
	}
	
	
	@Override
	protected int myCompareTo (MathTokenParenthesis arg0) {
	
		int compareValue = 0;
		
		compareValue = this.value.compareTo(arg0.getValue());
		
		return compareValue;
	
	
	}

}
