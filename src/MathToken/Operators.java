package MathToken;

import Exceptions.WrongInputException;

public class Operators {

	/**
	 * Generates And Returns The Plus Operator
	 * 
	 * @return Plus Operator
	 */
	public static MathTokenOperator plus () {
		
		MathTokenOperator plus = null;
	
		try {
		
			plus = new MathTokenOperator ("PLUS", "+", 2, 2);
	
		} catch (WrongInputException e) {
		
			e.printStackTrace();
	
		}
	
		return plus;
		
	}
	
	
	/**
	 * Generates And Returns The Unary Minus Operator
	 * 
	 * @return Unary Minus Operator
	 */
	public static MathTokenOperator minus_u () {
		
		MathTokenOperator minus_u = null;
		
		try {
			
			minus_u = new MathTokenOperator ("UNARY_MINUS", "-", 4, 1);
		
		} catch (WrongInputException e) {
			
			e.printStackTrace();
		
		}
		
		return minus_u;
		
	}

	
	/**
	 * Generates And Returns The Binary Minus Operator
	 * 
	 * @return Binary Minus Operator
	 */
	public static MathTokenOperator minus_b () {
	
		MathTokenOperator minus_b = null;
	
		try {
		
			minus_b = new MathTokenOperator ("BINARY_MINUS", "-", 2, 2);
	
		} catch (WrongInputException e) {
		
			e.printStackTrace();
	
		}
	
		return minus_b;
		
	}

	
	/**
	 * Generates And Returns The Multiplication Operator
	 * 
	 * @return Multiplication Operator
	 */
	public static MathTokenOperator mult () {
	
		MathTokenOperator molt = null;
	
		try {
		
			molt = new MathTokenOperator ("MULT", "*", 3, 2);
	
		} catch (WrongInputException e) {
		
			e.printStackTrace();
	
		}
	
		return molt;
	
	}

	
	/**
	 * Generates And Returns The Division Operator
	 * 
	 * @return Division Operator
	 */
	public static MathTokenOperator div () {
	
		MathTokenOperator div = null;
	
		try {
		
			div = new MathTokenOperator ("DIV", "/", 3, 2);
	
		} catch (WrongInputException e) {
		
			e.printStackTrace();
	
		}
	
		return div;
	
	}
	
	
	/**
	 * Generates And Returns The Power Operator
	 * 
	 * @return Power Operator
	 */
	public static MathTokenOperator pow () {
	
		MathTokenOperator pow = null;
	
		try {
		
			pow = new MathTokenOperator ("POW", "^", 5, 2);
	
		} catch (WrongInputException e) {
		
			e.printStackTrace();
	
		}
	
		return pow;
	
	}

	
	/**
	 * Generates And Returns The Sqrt Operator
	 * 
	 * @return Sqrt Operator
	 */
	public static MathTokenOperator sqrt () {
	
		MathTokenOperator sqrt_u = null;
	
		try {
		
			sqrt_u = new MathTokenOperator ("SQRT", "sqrt", 5, 1);
	
		} catch (WrongInputException e) {
		
			e.printStackTrace();
	
		}
	
		return sqrt_u;
	
	}


	/**
	 * Generates And Returns The Unary Log Operator
	 * 
	 * @return Unary Log Operator
	 */
	public static MathTokenOperator log () {
	
		MathTokenOperator log_u = null;
	
		try {
		
			log_u = new MathTokenOperator ("LOG", "log", 5, 1);
	
		} catch (WrongInputException e) {
		
			e.printStackTrace();
	
		}
	
		return log_u;
	
	}


	/**
	 * Generates And Returns The Exponential Operator
	 * 
	 * @return Exponential Operator
	 */
	public static MathTokenOperator exp () {
	
		MathTokenOperator exp = null;
	
		try {
		
			exp = new MathTokenOperator ("EXP", "exp", 5, 1);
	
		} catch (WrongInputException e) {
		
			e.printStackTrace();
	
		}
	
		return exp;
	
	}

	
	/**
	 * Generates And Returns The Factorial Operator
	 * 
	 * @return Factorial Operator
	 */
	public static MathTokenOperator fact () {
	
		MathTokenOperator fact = null;
	
		try {
		
			fact = new MathTokenOperator ("FACT", "fact", 5, 1);
	
		} catch (WrongInputException e) {
		
			e.printStackTrace();
			
		}
	
		return fact;
	
	}


	/**
	 * Generates And Returns The Cos Operator
	 * 
	 * @return Cos Operator
	 */
	public static MathTokenOperator cos () {
	
		MathTokenOperator cos = null;
	
		try {
		
			cos = new MathTokenOperator ("COS", "cos", 5, 1);
	
		} catch (WrongInputException e) {
		
			e.printStackTrace();
	
		}
	
		return cos;
	
	}

	
	/**
	 * Generates And Returns The Sin Operator
	 * 
	 * @return Sin Operator
	 */
	public static MathTokenOperator sin () {
	
		MathTokenOperator sin = null;
	
		try {
		
			sin = new MathTokenOperator ("SIN", "sin", 5, 1);
	
		} catch (WrongInputException e) {
		
			e.printStackTrace();
			
		}
	
		return sin;
	
	}
	

	/**
	 * Generates And Returns The Tan Operator
	 * 
	 * @return Tan Operator
	 */
	public static MathTokenOperator tan () {
		
		MathTokenOperator tan = null;
	
		try {
		
			tan = new MathTokenOperator ("TAN", "tan", 5, 1);
	
		} catch (WrongInputException e) {
		
			e.printStackTrace();
			
		}
	
		return tan;
	
	}
	

	/**
	 * Generates And Returns The ArcCos Operator
	 * 
	 * @return ArcCos Operator
	 */
	public static MathTokenOperator acos () {
		
		MathTokenOperator acos = null;
	
		try {
		
			acos = new MathTokenOperator ("ACOS", "arcos", 5, 1);
	
		} catch (WrongInputException e) {
		
			e.printStackTrace();
			
		}
	
		return acos;
	
	}
	

	/**
	 * Generates And Returns The ArcSin Operator
	 * 
	 * @return ArcSin Operator
	 */
	public static MathTokenOperator asin () {
		
		MathTokenOperator asin = null;
	
		try {
		
			asin = new MathTokenOperator ("ASIN", "arcsin", 5, 1);
	
		} catch (WrongInputException e) {
		
			e.printStackTrace();
			
		}
	
		return asin;
	
	}	
	

	/**
	 * Generates And Returns The ArcTan Operator
	 * 
	 * @return ArcTan Operator
	 */
	public static MathTokenOperator atan () {
		
		MathTokenOperator atan = null;
	
		try {
		
			atan = new MathTokenOperator ("ATAN", "artan", 5, 1);
	
		} catch (WrongInputException e) {
		
			e.printStackTrace();
			
		}
	
		return atan;
	
	}
	

	/**
	 * Generates And Returns The Cosh Operator
	 * 
	 * @return Cosh Operator
	 */
	public static MathTokenOperator cosh () {
		
		MathTokenOperator cosh = null;
	
		try {
		
			cosh = new MathTokenOperator ("COSH", "cosh", 5, 1);
	
		} catch (WrongInputException e) {
		
			e.printStackTrace();
			
		}
	
		return cosh;
	
	}
	

	/**
	 * Generates And Returns The Sinh Operator
	 * 
	 * @return Sinh Operator
	 */
	public static MathTokenOperator sinh () {
		
		MathTokenOperator sinh = null;
	
		try {
		
			sinh = new MathTokenOperator ("SINH", "sinh", 5, 1);
	
		} catch (WrongInputException e) {
		
			e.printStackTrace();
			
		}
	
		return sinh;
	
	}
	

	/**
	 * Generates And Returns The Tanh Operator
	 * 
	 * @return Tanh Operator
	 */
	public static MathTokenOperator tanh () {
		
		MathTokenOperator tanh = null;
	
		try {
		
			tanh = new MathTokenOperator ("TANH", "tanh", 5, 1);
	
		} catch (WrongInputException e) {
		
			e.printStackTrace();
			
		}
	
		return tanh;
	
	}
	
	
	
}
