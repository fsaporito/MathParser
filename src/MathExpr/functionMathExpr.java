package MathExpr;

import java.util.ArrayList;

import Exceptions.WrongCalculationException;
import Exceptions.WrongExpressionException;
import Exceptions.WrongInputException;
import MathToken.MathTokenOperand;
import MathToken.Operators;

public class functionMathExpr {

	
	/**
	 * Generates And Returns The Plus Operator
	 * 
	 * @return Plus Operator
	 * @throws WrongInputException 
	 * @throws WrongCalculationException 
	 * @throws WrongExpressionException 
	 */
	public static MathExpr plus (MathExpr A, MathExpr B) throws WrongInputException, WrongExpressionException, WrongCalculationException {
		
		if (A == null) {
			
			throw new WrongInputException ("plus() - Null MathExpression");
			
		}
		
		if (B == null) {
			
			throw new WrongInputException ("plus() - Null MathExpression");
			
		}
		
		ArrayList<MathExpr> list = new ArrayList<MathExpr>();
		
		list.add(A);
		
		list.add(B);
		
		return functionMathExpr.plus(list);
		
	}

	/**
	 * Generates And Returns The Plus Operator
	 * 
	 * @return Plus Operator
	 * @throws WrongInputException 
	 * @throws WrongCalculationException 
	 * @throws WrongExpressionException 
	 */
	public static MathExpr plus (ArrayList<MathExpr> list) throws WrongInputException, WrongExpressionException, WrongCalculationException {
		
		if (list == null) {
			
			throw new WrongInputException ("plus() - Null MathExpression List");
			
		}
		
		for (int i = 0; i < list.size(); i++) {
			
			if (list.get(i) == null) {
				
				throw new WrongInputException ("plus() - Null MathExpression " + i);
				
			}
			
		}
		
		return (new MathExpr (Operators.plus(), list));
		
	}
		
	/**
	 * Sum Every MathExpression By m
	 * 
	 * @return Result
	 * @throws WrongInputException 
	 * @throws WrongCalculationException 
	 * @throws WrongExpressionException 
	 */
	public static ArrayList<MathExpr> sumAll (ArrayList<MathExpr> list, MathExpr m) throws WrongInputException, WrongExpressionException, WrongCalculationException {
		
		if (list == null) {
			
			throw new WrongInputException ("sumAll() - Null MathExpression List");
			
		}
		
		for (int i = 0; i < list.size(); i++) {
			
			if (list.get(i) == null) {
				
				throw new WrongInputException ("sumAll() - Null MathExpression " + i);
				
			}
			
		}
		
		if (m == null) {
			
			throw new WrongInputException("sumAll() - Null MathExpression m");
			
		}
		
		// Return ArrayList
		ArrayList<MathExpr> listReturn = new ArrayList<MathExpr>(list.size());
		
		for (int i = 0; i < list.size(); i++) {
		
			listReturn.add(new MathExpr(Operators.plus(), m, list.get(i)));
			
		}
				
		return listReturn;
		
	}
	
	
	
	/**
	 * Generates And Returns The Mult Operator
	 * 
	 * @return Mult Operator
	 * @throws WrongInputException 
	 * @throws WrongCalculationException 
	 * @throws WrongExpressionException 
	 */
	public static MathExpr mult (MathExpr A, MathExpr B) throws WrongInputException, WrongExpressionException, WrongCalculationException {
		
		if (A == null) {
			
			throw new WrongInputException ("mult() - Null MathExpression");
			
		}
		
		if (B == null) {
			
			throw new WrongInputException ("mult() - Null MathExpression");
			
		}		
		
		ArrayList<MathExpr> list = new ArrayList<MathExpr>();
		
		list.add(A);
		
		list.add(B);
		
		return functionMathExpr.mult(list);
		
	}

	/**
	 * Generates And Returns The Mult Operator
	 * 
	 * @return Plus Operator
	 * @throws WrongInputException 
	 * @throws WrongCalculationException 
	 * @throws WrongExpressionException 
	 */
	public static MathExpr mult (ArrayList<MathExpr> list) throws WrongInputException, WrongExpressionException, WrongCalculationException {
		
		if (list == null) {
			
			throw new WrongInputException ("mult() - Null MathExpression List");
			
		}
		
		for (int i = 0; i < list.size(); i++) {
			
			if (list.get(i) == null) {
				
				throw new WrongInputException ("mult() - Null MathExpression " + i);
				
			}
			
		}
		
		return (new MathExpr (Operators.mult(), list));
		
	}
		
	/**
	 * Multiply Every MathExpression By m (Left Multiplication)
	 * 
	 * @return Result
	 * @throws WrongInputException 
	 * @throws WrongCalculationException 
	 * @throws WrongExpressionException 
	 */
	public static ArrayList<MathExpr> multAll (ArrayList<MathExpr> list, MathExpr m) throws WrongInputException, WrongExpressionException, WrongCalculationException {
		
		if (list == null) {
			
			throw new WrongInputException ("multAll() - Null MathExpression List");
			
		}
		
		for (int i = 0; i < list.size(); i++) {
			
			if (list.get(i) == null) {
				
				throw new WrongInputException ("multAll() - Null MathExpression " + i);
				
			}
			
		}
		
		if (m == null) {
			
			throw new WrongInputException("multAll() - Null MathExpression m");
			
		}
		
		// Return ArrayList
		ArrayList<MathExpr> listReturn = new ArrayList<MathExpr>(list.size());
		
		for (int i = 0; i < list.size(); i++) {
		
			listReturn.add(new MathExpr(Operators.mult(), m, list.get(i)));
			
		}
				
		return listReturn;
		
	}
	

	/**
	 * Generates And Returns The Mult Operator
	 * 
	 * @return Mult Operator
	 * @throws WrongInputException 
	 * @throws WrongCalculationException 
	 * @throws WrongExpressionException 
	 */
	public static MathExpr invert (MathExpr A) throws WrongInputException, WrongExpressionException, WrongCalculationException {
		
		if (A == null) {
			
			throw new WrongInputException ("invert() - Null MathExpression");
			
		}
		
		if (!A.getSymbolic()) {
			
			if (A.eval().getOperandDouble() == 0) {
				
				throw new WrongCalculationException ("invert() - The Expression Always Evaluate To Zero !!!");
				
			}
			
		}
		
		return (new MathExpr (Operators.div(), new MathExpr (new MathTokenOperand("1")), A));
		
	}
	
}
