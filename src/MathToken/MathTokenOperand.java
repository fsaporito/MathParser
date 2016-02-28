package MathToken;

import Exceptions.WrongInputException;

public class MathTokenOperand extends MathToken {

	public MathTokenOperand (String value) throws WrongInputException {

		super (value, "Operand");
		
		this.type = "operand";
		
		if (!this.isDigit(value)) {
			
			throw new WrongInputException ("MathTokenOperand: Value Is Not A Digit!!!");
			
		}
		
	}
	
	public MathTokenOperand (double value) throws WrongInputException {

		super (Double.toString(value), "Operand");
		
		this.type = "operand";
		
	}
	
	public MathTokenOperand (int value) throws WrongInputException {

		super (Integer.toString(value), "Operand");
		
		this.type = "operand";
		
	}
	
	
	public double getValueDouble () {
		
		return Double.valueOf(this.value);
		
	}

	@Override
	protected int myCompareTo(MathTokenOperand arg0) {
	
		int compareValue = 0;		
		
		compareValue = this.value.compareTo(arg0.getValue());
		
		return compareValue;	
	
	}
	

}
