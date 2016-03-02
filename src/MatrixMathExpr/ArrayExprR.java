package MatrixMathExpr;

import java.util.ArrayList;

import Exceptions.WrongCalculationException;
import Exceptions.WrongExpressionException;
import Exceptions.WrongInputException;
import MathExpr.MathExpr;

public class ArrayExprR extends Matrix {

	public ArrayExprR(ArrayList<MathExpr> elements, int length) throws WrongInputException, WrongCalculationException {
		
		super(elements, 1, length);
		
	}

	public ArrayExprR(MathExpr[] matrixElements, int length) throws WrongInputException, WrongCalculationException {
		
		super(matrixElements, 1, length);
		
	}
	
	public ArrayExprR(Double[] matrixElements, int length) throws WrongInputException, WrongCalculationException, WrongExpressionException {
		
		super(matrixElements, 1, length);
		
	}
	
	public ArrayExprR(double[] matrixElements, int length) throws WrongInputException, WrongCalculationException, WrongExpressionException {
		
		super(matrixElements, 1, length);
		
	}

	public ArrayExprR(Integer[] matrixElements, int length) throws WrongInputException, WrongCalculationException, WrongExpressionException {
	
		super(matrixElements, 1, length);
	
	}
	
	public ArrayExprR(int[] matrixElements, int length) throws WrongInputException, WrongCalculationException, WrongExpressionException {
		
		super(matrixElements, 1, length);
	
	}
	
	
	/**
	 * Transpose The Current Array
	 * @return the transposed array
	 * @throws WrongInputException
	 * @throws WrongCalculationException 
	 */
	@Override
	public Matrix transpose () throws WrongInputException, WrongCalculationException {
		
		return (new ArrayExprC (this.matrixElementsList(), this.c));
		
	}

}
