
public class MathTokenOperator extends MathToken {

	private int precedence;
	
	public MathTokenOperator (String value, int precedence) {
		
		super (value);
		
		this.type = "operator";
		
		this.precedence = precedence;
		
	}
	
	
	/**
	 * @return the precedence
	 */
	public int getPrecedence() {
	
		return this.precedence;
	
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
		
		return "MathToken value=" + value + ", type=" + type + "" + ", precedence=" + precedence;
		
	}
	
	

}
