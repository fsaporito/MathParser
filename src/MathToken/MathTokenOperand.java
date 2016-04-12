package MathToken;

import java.math.BigDecimal;

import Exceptions.WrongInputException;

public class MathTokenOperand extends MathToken {

	public MathTokenOperand (String value) throws WrongInputException {

		//super (value, "Operand");

		// (new BigDecimal(value)).toPlainString()
		// Trick To Remove Scientific Notation, Which Creates Problem In The Parsing
		super ((new BigDecimal(value)).toPlainString(), "Operand");


		this.type = "operand";

		/*if (!this.isDigit(value)) {

			throw new WrongInputException ("MathTokenOperand: Value " + value + " Is Not A Digit!!!");

		}*/

	}

	public MathTokenOperand (double value) throws WrongInputException {

		//super (Double.toString(value), "Operand");

		// (new BigDecimal(Double.toString(value))).toPlainString()
		// Trick To Remove Scientific Notation, Which Creates Problem In The Parsing
		super ((new BigDecimal(Double.toString(value))).toPlainString(), "Operand");

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
