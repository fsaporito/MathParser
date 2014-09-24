package Parser;

public class MathTokenOperator extends MathToken {

	
	/**
	 * Precedences:
	 * 1:
	 * 2: PLUS BINARY_MINUS
	 * 3: MOLT DIV
	 * 4: UNARY_MINUS
	 */
	private int precedence;
	
	public MathTokenOperator (String name, String value, int precedence) {
		
		super (value, name);
		
		this.type = "operator";
		
		this.precedence = precedence;
		
	}
	
	
	/**
	 * @return the precedence
	 */
	public int getPrecedence () {
	
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
		
		return "MathToken " + this.name + " value=" + value + ", type=" + type + "" + ", precedence=" + precedence;
		
	}
	
	

}
