package MatrixMathExpr;

import java.util.ArrayList;

import Exceptions.WrongCalculationException;
import Exceptions.WrongExpressionException;
import Exceptions.WrongInputException;
import MathExpr.MathExpr;

public class ArrayExprC extends Matrix {

	public ArrayExprC(ArrayList<MathExpr> elements, int length) throws WrongInputException, WrongCalculationException {
		
		super(elements, length, 1);
		
	}

	public ArrayExprC(MathExpr[] matrixElements, int length) throws WrongInputException, WrongCalculationException {
		
		super(matrixElements, length, 1);
		
	}

	public ArrayExprC(Double[] matrixElements, int length) throws WrongInputException, WrongCalculationException, WrongExpressionException {
		
		super(matrixElements, length, 1);
		
	}

	public ArrayExprC(Integer[] matrixElements, int length) throws WrongInputException, WrongCalculationException, WrongExpressionException {
		
		super(matrixElements, length, 1);
		
	}	
	
	
	/**
	 * Transpose The Current Array
	 * @return the transposed array
	 * @throws WrongInputException
	 * @throws WrongCalculationException 
	 */
	@Override
	public Matrix transpose () throws WrongInputException, WrongCalculationException {
		
		return (new ArrayExprC (this.matrixElementsList(), this.r));
		
	}

}

