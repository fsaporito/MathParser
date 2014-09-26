package ParserTest;

import Exceptions.WrongCalculationException;
import Exceptions.WrongExpressionException;
import Exceptions.WrongInputException;
import MathExpr.MathExpr;
import MathToken.MathTokenOperand;
import MathToken.MathTokenOperator;

public class TestMathExprEval {
	
	private MathTokenOperator plus;
	private MathTokenOperator minus_u;
	private MathTokenOperator minus_b;
	private MathTokenOperator molt;
	private MathTokenOperator div;
	private MathTokenOperator sqrt_u;
	private MathTokenOperator log_u;
	

	private boolean debug;		
		
	public TestMathExprEval (boolean debug) {
			
		this.debug = debug;
			
		try {
			
			this.plus = new MathTokenOperator ("PLUS", "+", 2, 2);
			this.minus_u = new MathTokenOperator ("UNARY_MINUS", "-", 4, 1);
			this.minus_b = new MathTokenOperator ("BINARY_MINUS", "-", 2, 2);
			this.molt = new MathTokenOperator ("MOLT", "*", 3, 2);
			this.div = new MathTokenOperator ("DIV", "/", 3, 2);
			this.sqrt_u = new MathTokenOperator ("UNARY_SQRT", "sqrt", 5, 1);
			this.log_u = new MathTokenOperator ("UNARY_LOG", "log", 5, 1);
				
			this.test1();			
			this.test2();			
			this.test3();	
			this.test4();
			this.test5();
			this.test6();
			this.test7();
			this.test8();
			this.test9();
			this.test10();
			
		} catch (WrongInputException e) {
				
			System.out.println ("WrongInputException!!!");
			e.printStackTrace();
				
		} catch (WrongExpressionException e) {
			
			System.out.println ("WrongExpressionException!!!");
			e.printStackTrace();
		
		} catch (WrongCalculationException e) {
			
			System.out.println ("WrongECalculationException!!!");
			e.printStackTrace();
		}
			
	}
		
		
	public void test (String test, MathExpr expr, String result) throws WrongInputException, WrongCalculationException {
			
		System.out.print ("\nMathExpr:" + test);
			
		if (this.debug) {
				
			System.out.println ();
			
			System.out.println ("Input: " + expr.toString() + "  Result: " + expr.eval().toString() + " (Expected " + result + ")");
				
		} else {
				
			boolean error = false;
				
			if (!expr.eval().toString().equals(result)) {	
					
				System.out.println ("\n");
					
				System.out.println ("!!! ERROR !!!");
					
				System.out.println (" - Expr: (Lenght=" + expr.toString().length() + "): " + expr.toString());
					
				System.out.println (" - Calculated Result (Lenght=" + result.length() + "): " + expr.eval().toString());
				
				System.out.println (" - Correct Result (Lenght=" + result.length() + "): " + result);
					
				error = true;
					
			}
				
			if (!error) {
						
				System.out.println (" ... Ok");
					
			}
				
		}
			
	}
	
	
	/**
	 * Test 1
	 * 2 + 1
	 * @throws WrongInputException
	 * @throws WrongExpressionException 
	 * @throws WrongCalculationException 
	 */
	public void test1() throws WrongInputException, WrongExpressionException, WrongCalculationException {
		
		String test = "Test 1 -> ";
		
		MathExpr finalExpr = new MathExpr (new MathTokenOperand ("2.0"));
		
		String result = "2";
		
		test += finalExpr.toString();
		
		this.test (test, finalExpr, result);
		
		
		
	}
		
		
		
	/**
	 * Test 2
	 * 2 + 1
	 * @throws WrongInputException
	 * @throws WrongExpressionException 
	 * @throws WrongCalculationException 
	 */
	public void test2() throws WrongInputException, WrongExpressionException, WrongCalculationException {
		
		String test = "Test 2 -> ";
		
		MathExpr expr1 = new MathExpr (new MathTokenOperand ("2"));
		MathExpr expr2 = new MathExpr (new MathTokenOperand ("1"));
		
		MathExpr finalExpr = new MathExpr (plus, expr1, expr2);		
		
		String result = "3";
		
		test += finalExpr.toString();
		
		this.test (test, finalExpr, result);
		
		
		
	}
	
	
	/**
	 * Test 3
	 * 34 * 43 + 131
	 * @throws WrongInputException
	 * @throws WrongExpressionException 
	 * @throws WrongCalculationException 
	 */
	public void test3() throws WrongInputException, WrongExpressionException, WrongCalculationException {
		
		String test = "Test 3 -> ";
		
		MathExpr expr1 = new MathExpr (new MathTokenOperand ("34"));
		MathExpr expr2 = new MathExpr (new MathTokenOperand ("43"));		
		MathExpr expr3 = new MathExpr (molt, expr1, expr2);
		
		MathExpr expr4 = new MathExpr (new MathTokenOperand ("131"));		
		
		MathExpr finalExpr = new MathExpr (plus, expr3, expr4);
		
		String result = "1593";
		
		test += finalExpr.toString();
		
		this.test (test, finalExpr, result);
		
		
	}
	
	
	/**
	 * Test 4
	 * 1.1 + 2.2 + 3.3 + 4.4 * 2
	 * @throws WrongInputException
	 * @throws WrongExpressionException 
	 * @throws WrongCalculationException 
	 */
	public void test4() throws WrongInputException, WrongExpressionException, WrongCalculationException {
		
		String test = "Test 4 -> ";
		
		MathExpr expr1 = new MathExpr (new MathTokenOperand ("1.1"));
		MathExpr expr2 = new MathExpr (new MathTokenOperand ("2.2"));		
		MathExpr expr3 = new MathExpr (plus, expr1, expr2);
		
		MathExpr expr4 = new MathExpr (new MathTokenOperand ("3.3"));		
		MathExpr expr5 = new MathExpr (plus, expr3, expr4);
		
		
		MathExpr expr6 = new MathExpr (new MathTokenOperand ("4.4"));
		MathExpr expr7 = new MathExpr (new MathTokenOperand ("2"));		
		MathExpr expr8 = new MathExpr (molt, expr6, expr7);
		
		
		MathExpr finalExpr = new MathExpr (plus, expr5, expr8);
		
		String result = "15.4";
		
		test += finalExpr.toString();
		
		this.test (test, finalExpr, result);
		
		
	}
	
	
	/**
	 * Test 5
	 * (3 - 4) * 2
	 * @throws WrongInputException
	 * @throws WrongExpressionException 
	 * @throws WrongCalculationException 
	 */
	public void test5() throws WrongInputException, WrongExpressionException, WrongCalculationException {
		
		String test = "Test 5 -> ";
		
		MathExpr expr1 = new MathExpr (new MathTokenOperand ("3"));
		MathExpr expr2 = new MathExpr (new MathTokenOperand ("4"));	
		MathExpr expr3 = new MathExpr (minus_b, expr1, expr2);
		
		MathExpr expr4 = new MathExpr (new MathTokenOperand ("2"));
		
		MathExpr finalExpr = new MathExpr (molt, expr3, expr4);
		
		String result = "-2";
		
		test += finalExpr.toString();
		
		this.test (test, finalExpr, result);
		
	}
	
	
	/**
	 * Test 6
	 * - (3 - 4) * 2
	 * @throws WrongInputException
	 * @throws WrongExpressionException 
	 * @throws WrongCalculationException 
	 */
	public void test6() throws WrongInputException, WrongExpressionException, WrongCalculationException {
		
		String test = "Test 6 -> ";
		
		MathExpr expr1 = new MathExpr (new MathTokenOperand ("3"));
		MathExpr expr2 = new MathExpr (new MathTokenOperand ("4"));	
		MathExpr expr3 = new MathExpr (minus_b, expr1, expr2);
		
		MathExpr expr4 = new MathExpr (new MathTokenOperand ("2"));
		MathExpr expr5 = new MathExpr (molt, expr3, expr4);
		
		MathExpr finalExpr = new MathExpr (minus_u, expr5);
		
		String result = "2";
		
		test += finalExpr.toString();
		
		this.test (test, finalExpr, result);
		
	}
	
	
	/**
	 * Test 7
	 * ( (3 + 4) * 2 + (3 - 4) * 2)
	 * @throws WrongInputException
	 * @throws WrongExpressionException 
	 * @throws WrongCalculationException 
	 */
	public void test7() throws WrongInputException, WrongExpressionException, WrongCalculationException {
		
		String test = "Test 7 -> ";		

		MathExpr expr1 = new MathExpr (new MathTokenOperand ("3"));
		MathExpr expr2 = new MathExpr (new MathTokenOperand ("4"));	
		MathExpr expr3 = new MathExpr (plus, expr1, expr2);
		
		MathExpr expr4 = new MathExpr (new MathTokenOperand ("2"));
		MathExpr expr5 = new MathExpr (molt, expr3, expr4);
		
		
		MathExpr expr6 = new MathExpr (new MathTokenOperand ("3"));
		MathExpr expr7 = new MathExpr (new MathTokenOperand ("4"));	
		MathExpr expr8 = new MathExpr (minus_b, expr6, expr7);
		
		MathExpr expr9 = new MathExpr (new MathTokenOperand ("2"));
		MathExpr expr10 = new MathExpr (molt, expr8, expr9);
		
		MathExpr finalExpr = new MathExpr (plus, expr5, expr10);
		
		String result = "12";
		
		test += finalExpr.toString();
		
		this.test (test, finalExpr, result);
		
		
		
	}
	
	
	/**
	 * Test 8
	 * (( 8 * 4 ) * 8) / 2)
	 * @throws WrongInputException
	 * @throws WrongExpressionException 
	 * @throws WrongCalculationException 
	 */
	public void test8() throws WrongInputException, WrongExpressionException, WrongCalculationException {
		
		String test = "Test 8 -> ";		

		MathExpr expr1 = new MathExpr (new MathTokenOperand ("8"));
		MathExpr expr2 = new MathExpr (new MathTokenOperand ("4"));	
		MathExpr expr3 = new MathExpr (molt, expr1, expr2);
		
		MathExpr expr4 = new MathExpr (new MathTokenOperand ("8"));
		MathExpr expr5 = new MathExpr (molt, expr3, expr4);
		
		MathExpr expr6 = new MathExpr (new MathTokenOperand ("2"));
		
		
		MathExpr finalExpr = new MathExpr (div, expr5, expr6);
		
		String result = "128";
		
		test += finalExpr.toString();
		
		this.test (test, finalExpr, result);		
		
	}
	
	
	/**
	 * Test 9
	 * sqrt( 8 + 8 ) * 16)
	 * @throws WrongInputException
	 * @throws WrongExpressionException 
	 * @throws WrongCalculationException 
	 */
	public void test9() throws WrongInputException, WrongExpressionException, WrongCalculationException {
		
		String test = "Test 9 -> ";		

		MathExpr expr1 = new MathExpr (new MathTokenOperand ("8"));
		MathExpr expr2 = new MathExpr (plus, expr1, expr1);
		
		MathExpr expr3 = new MathExpr (new MathTokenOperand ("16"));
		MathExpr expr4 = new MathExpr (molt, expr2, expr3);
		
		MathExpr finalExpr = new MathExpr (sqrt_u, expr4);
		
		String result = "16";
		
		test += finalExpr.toString();
		
		this.test (test, finalExpr, result);		
		
	}
	
	
	/**
	 * Test 10
	 * log(-(( 8 + 8 ) - 17)))
	 * @throws WrongInputException
	 * @throws WrongExpressionException 
	 * @throws WrongCalculationException 
	 */
	public void test10() throws WrongInputException, WrongExpressionException, WrongCalculationException {
		
		String test = "Test 10 -> ";		

		MathExpr expr1 = new MathExpr (new MathTokenOperand ("8"));
		MathExpr expr2 = new MathExpr (plus, expr1, expr1);
		
		MathExpr expr3 = new MathExpr (new MathTokenOperand ("17"));
		MathExpr expr4 = new MathExpr (minus_b, expr2, expr3);
		
		MathExpr expr5 = new MathExpr (minus_u, expr4);
		
		MathExpr finalExpr = new MathExpr (log_u, expr5);
		
		String result = "0";
		
		test += finalExpr.toString();
		
		this.test (test, finalExpr, result);		
		
	}
	
	
	
	
}
