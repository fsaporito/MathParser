package MathToken;

public class MathTokenOperand extends MathToken {

	public MathTokenOperand (String value) {

		super (value, "Operand");
		
		this.type = "operand";
		
		
	}

	@Override
	protected int myCompareTo(MathTokenOperand arg0) {
	
		int compareValue = 0;		
		
		compareValue = this.value.compareTo(arg0.getValue());
		
		return compareValue;	
	
	}
	

}
