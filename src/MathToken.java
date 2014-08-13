
public abstract class MathToken implements Comparable<MathToken> {

	protected String value;
	
	protected String type; 
	
	
	public MathToken (String value) {
		
		if (value == null) { // Value Mustn't Be Null
			
			throw new NullPointerException ("Value is null!!!");
			
		}
		
		if (value.length() == 0) { // Input Mustn't Be Empty
			
			throw new NullPointerException ("Value is empty!!!");
			
		}	
		
		this.value = value;	
		
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


	
	
	@Override
	public int compareTo(MathToken arg0) {
		
		int compareValue = 0;
		
		try {
			
			if (this.type.equals(arg0.getType())) {
						
				throw new WrongCompareException ("Token Not Comparable (Different Types)");
			
			}
			
		} catch (WrongCompareException e) {
				
				e.printStackTrace();
			
		}
		
		if (this.type.equals ("operand")) {
			
			compareValue = this.myCompareTo((MathTokenOperand) arg0);
			
		} else if (this.type.equals ("operator")) {
			
			compareValue = this.myCompareTo((MathTokenOperator) arg0);
			
		}
		
		return compareValue;
		
	}


	protected int myCompareTo(MathTokenOperand arg0) {return 0;}
	
	protected int myCompareTo(MathTokenOperator arg0) {return 0;}


	/** 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		return "MathToken value=" + value + ", type=" + type + "";
		
	}

	
	
	
	
	
}
