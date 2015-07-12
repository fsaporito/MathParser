package MathToken;

public class MathTokenSymbol extends MathToken {

	public MathTokenSymbol (String value) {

		super (value, "Symbol" + value);
		
		this.type = "symbol";
		
		
	}

	@Override
	protected int myCompareTo(MathTokenSymbol arg0) {
	
		int compareValue = 0;		
		
		compareValue = this.value.compareTo(arg0.getValue());
		
		return compareValue;	
	
	}

}
