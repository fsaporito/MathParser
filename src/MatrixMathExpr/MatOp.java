package MatrixMathExpr;

import java.util.ArrayList;

import Exceptions.WrongCalculationException;
import Exceptions.WrongExpressionException;
import Exceptions.WrongInputException;
import MathExpr.MathExpr;
import MathExpr.functionMathExpr;

/**
 * Extend Operators and Functions To Matrices 
 * @author sapo93
 *
 */
public class MatOp {

	/** Sum Two Matrices A,B
	 * 
	 * C = A+B
	 * 
	 * @param A first matrix to sum
	 * @param B second matrix to sum
	 * @return C = A+B, result matrix
	 * @throws WrongInputException
	 * @throws WrongExpressionException
	 * @throws WrongCalculationException
	 */
	public static Matrix sumMM (Matrix A, Matrix B) throws WrongInputException, WrongExpressionException, WrongCalculationException{
		
		if (A == null) {
			
			throw new WrongInputException ("sumMatrix() - Null Matrix A!!!");
			
		}
		
		if (B == null) {
			
			throw new WrongInputException ("sumMatrix() - Null Matrix B!!!");
			
		}
		
		if (A.getC() != B.getC()) {
			
			throw new WrongInputException ("sumMatrix() - Dimensions Not Compatible!!!");
			
		}
		
		if (A.getR() != B.getR()) {
			
			throw new WrongInputException ("sumMatrix() - Dimensions Not Compatible!!!");
			
		}
		
		ArrayList<MathExpr> listA = new ArrayList<MathExpr>();
		
		ArrayList<MathExpr> listB = new ArrayList<MathExpr>();
		
		ArrayList<MathExpr> listSum = new ArrayList<MathExpr>();
		
		listA = A.matrixElementsList();
		
		listB = B.matrixElementsList();
		
		for (int i = 0; i < listA.size(); i++) {
			
			listSum.add(functionMathExpr.plus(listA.get(i), listB.get(i)));
			
		}
		
		return (new Matrix (listSum, A.getC(), A.getR()));
		
	}
	
	/** Sum Two Matrices A,B
	 * 
	 * C = A+B
	 * 
	 * @param A first matrix to sum
	 * @param B second matrix to sum
	 * @return C = A+B, result matrix
	 * @throws WrongInputException
	 * @throws WrongExpressionException
	 * @throws WrongCalculationException
	 */
	public static Matrix minusMM (Matrix A, Matrix B) throws WrongInputException, WrongExpressionException, WrongCalculationException{
		
		if (A == null) {
			
			throw new WrongInputException ("sumMatrix() - Null Matrix A!!!");
			
		}
		
		if (B == null) {
			
			throw new WrongInputException ("sumMatrix() - Null Matrix B!!!");
			
		}
		
		if (A.getC() != B.getC()) {
			
			throw new WrongInputException ("sumMatrix() - Dimensions Not Compatible!!!");
			
		}
		
		if (A.getR() != B.getR()) {
			
			throw new WrongInputException ("sumMatrix() - Dimensions Not Compatible!!!");
			
		}
		
		ArrayList<MathExpr> listA = new ArrayList<MathExpr>();
		
		ArrayList<MathExpr> listB = new ArrayList<MathExpr>();
		
		ArrayList<MathExpr> listMinus = new ArrayList<MathExpr>();
		
		listA = A.matrixElementsList();
		
		listB = B.matrixElementsList();
		
		for (int i = 0; i < listA.size(); i++) {
			
			listMinus.add(functionMathExpr.minus_b(listA.get(i), listB.get(i)));
			
		}
		
		return (new Matrix (listMinus, A.getC(), A.getR()));
		
	}
	
	
	/** Multiply Two Matrices A,B
	 * 
	 * C = A*B
	 * 
	 * Note that A*B != B*A
	 * 
	 * @param A first matrix to multiply
	 * @param B second matrix to multiply
	 * @return C = A*B, result matrix
	 * @throws WrongInputException
	 * @throws WrongExpressionException
	 * @throws WrongCalculationException
	 */
	public static Matrix multMM (Matrix A, Matrix B) throws WrongInputException, WrongExpressionException, WrongCalculationException{
		
		if (A == null) {
			
			throw new WrongInputException ("multMatrix() - Null Matrix A!!!");
			
		}
		
		if (B == null) {
			
			throw new WrongInputException ("multMatrix() - Null Matrix B!!!");
			
		}
		
		if (A.getC() != B.getR()) {
			
			throw new WrongInputException ("multMatrix() - Dimensions Not Compatible!!!");
			
		}
		
		ArrayList<MathExpr> row = new ArrayList<MathExpr>();
		
		ArrayList<MathExpr> col = new ArrayList<MathExpr>();
		
		ArrayList<MathExpr> listMult = new ArrayList<MathExpr>();
		
		ArrayList<MathExpr> list = new ArrayList<MathExpr>();
		
		for (int i = 0; i < A.getR(); i++) {
			
			for (int j = 0; j < B.getC(); j++) {
				
				row = A.getRowList(i);
				
				col = B.getColumnList(j);
				
				listMult = new ArrayList<MathExpr>();
				
				for (int k = 0; k < row.size(); k++) {
					
					listMult.add(functionMathExpr.mult(row.get(k), col.get(k)));
					
				}
				
				list.add(functionMathExpr.plus(listMult));
				
			}			
			
		}
		
		return (new Matrix (list, A.getC(), B.getR()));
		
	}
	
	
	/** Multiply The Matrix A and a mathematical expression m
	 * 
	 * B = m*A
	 * 
	 * @param A matrix to multiply
	 * @param m Mathematical expression
	 * @return C = A*B, result matrix
	 * @throws WrongInputException
	 * @throws WrongExpressionException
	 * @throws WrongCalculationException
	 */
	public static Matrix multmM (Matrix A, MathExpr m) throws WrongInputException, WrongExpressionException, WrongCalculationException{
		
		if (A == null) {
			
			throw new WrongInputException ("multMatrix() - Null Matrix A!!!");
			
		}
		
		if (m == null) {
			
			throw new WrongInputException ("multMatrix() - Null Mathematical Expression B!!!");
			
		}
		
			
		ArrayList<MathExpr> listMult = new ArrayList<MathExpr>();
		
		ArrayList<MathExpr> list = new ArrayList<MathExpr>();
		
		list = A.matrixElementsList();
		
		for (int i = 0; i < list.size(); i++) {
			
			listMult.add(functionMathExpr.mult( m, list.get(i)));			
			
		}
		
		return (new Matrix (listMult, A.getC(), A.getR()));
		
	}

}
