package MathExpr;

import Exceptions.WrongInputException;

public class CustomFunctions {

	/**
	 * Returns the log of the argument x in base a
	 * 
	 * Uses the Change of base property:
	 * 
	 * log_b (x) = log(x) / log (b)
	 * 
	 * @param base log base, must be positive and different from 1
	 * @param x log argument, must be positive
	 * 
	 * @return the result
	 * @throws WrongInputException 
	 */
	public static double log (double base, double x) throws WrongInputException {
		
		if (base <= 0) {
			
			throw new WrongInputException ("Logarithm Base Must Be Positive!!!");
			
		}
		
		if (base == 1) {
			
			throw new WrongInputException ("Logarithm Base Mustn't Be 1 !!!");
			
		}

		if (x <= 0) {
	
			throw new WrongInputException ("Logarithm Argument Must Be Positive!!!");
	
		}
		
		double result = Math.log(x) / Math.log(base);	
		
		return result;
		
	}
	
	
	
	/**
	 * Returns the factorial of the argument n:
	 * 
	 * fact(0) = 1
	 * fact(1) = 1
	 * fact(n) = n*fact(n-1)
	 * 
	 * For n > 20, Stirling approximation is used:
	 * 
	 * fact(n) = sqrt(2*PI*n)*(n/e)^n
	 * 
	 * @param n Positive Integer
	 * 
	 * @return the result
	 * @throws WrongInputException 
	 */
	public static long fact (long n) throws WrongInputException {
		
		if (n < 0) {
			
			throw new WrongInputException ("Argument Must Be Positive!!!");
			
		}
		
		if (n == 0) {
			
			return 1;
			
		} else if (n < 20) {
			
			return (n*fact(n-1));
			
		} else {
			
			double result = Math.sqrt(2*Math.PI*n)*Math.pow((n/Math.E), n);
			
			return Math.round(result);
			
			
		}
		
		
	}


}
