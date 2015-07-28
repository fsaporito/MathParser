package ParserTest;

import Exceptions.WrongCalculationException;
import Exceptions.WrongExpressionException;
import Exceptions.WrongInputException;
import MathExpr.MathExpr;
import MathToken.MathTokenOperand;
import MathToken.MathTokenSymbol;
import MathToken.Operators;

public class TestDer {
	
	private boolean debug;	
	private MathTokenSymbol t = new MathTokenSymbol ("t");
	private MathTokenSymbol x = new MathTokenSymbol ("x");
	private MathTokenSymbol y = new MathTokenSymbol ("y");
		
	public TestDer (boolean debug) {
			
		this.debug = debug;
			
		try {			
				
			this.test1();			
			this.test2();			
			this.test3();	
			this.test4();
		/*	this.test5();
			this.test6();
			this.test7();
			this.test8();
			this.test9();
			this.test10();
			this.test11();
			this.test12();
			this.test13(); */
			
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

	public void test (String test, MathExpr expr, String result, MathTokenSymbol symbol) throws WrongInputException, WrongCalculationException, WrongExpressionException {
			
		System.out.print ("\nDerivate:" + test + " Derivate(" + symbol.getValue() + "):");
			
		if (this.debug) {
				
			System.out.println ();
			
			System.out.println ("Input: " + expr.toString() + "  Result: " + expr.derive(symbol).toString() + " (Expected " + result + ")");
				
		} else {
				
			boolean error = false;
				
			if (!expr.derive(symbol).toString().equals(result)) {	
					
				System.out.println ("\n");
					
				System.out.println ("!!! ERROR !!!");
					
				System.out.println (" - Expr: (Lenght=" + expr.toString().length() + "): " + expr.toString());
					
				System.out.println (" - Calculated Result (Lenght=" + result.length() + "): " + expr.derive(symbol).toString());
				
				System.out.println (" - Correct Result (Lenght=" + result.length() + "): " + result);
					
				error = true;
					
			}
				
			if (!error) {
						
				System.out.println (" = " + result + "  ... Ok");
					
			}
				
		}
			
	}
	
	
	/**
	 * Test 1
	 * 2
	 * @throws WrongInputException
	 * @throws WrongExpressionException 
	 * @throws WrongCalculationException 
	 */
	public void test1() throws WrongInputException, WrongExpressionException, WrongCalculationException {
		
		String test = "Test 1 -> ";
		
		MathExpr finalExpr = new MathExpr (new MathTokenOperand ("2.0"));
		
		String result = "0";
		
		test += finalExpr.toString();
		
		this.test (test, finalExpr, result, this.x);
		
		
		
	}
		
		
		
	/**
	 * Test 2
	 * x + 1
	 * @throws WrongInputException
	 * @throws WrongExpressionException 
	 * @throws WrongCalculationException 
	 */
	public void test2() throws WrongInputException, WrongExpressionException, WrongCalculationException {
		
		String test = "Test 2 -> ";
		
		MathExpr expr1 = new MathExpr (new MathTokenSymbol ("x"));
		MathExpr expr2 = new MathExpr (new MathTokenOperand ("1"));
		
		MathExpr finalExpr = new MathExpr (Operators.plus(), expr1, expr2);		
		
		String result = "1";
		
		test += finalExpr.toString();
		
		this.test (test, finalExpr, result, this.x);
		
		
		
	}
	
	
	/**
	 * Test 3
	 * sin(x)
	 * @throws WrongInputException
	 * @throws WrongExpressionException 
	 * @throws WrongCalculationException 
	 */
	public void test3() throws WrongInputException, WrongExpressionException, WrongCalculationException {
		
		String test = "Test 3 -> ";
		
		MathExpr finalExpr = new MathExpr (Operators.sin(), new MathExpr (this.x));
		
		System.out.println (finalExpr.toString());
		
		String result = "cos(x)";
		
		test += finalExpr.toString();
		
		this.test (test, finalExpr, result, this.x);
		
		
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
		MathExpr expr3 = new MathExpr (Operators.plus(), expr1, expr2);
		
		MathExpr expr4 = new MathExpr (new MathTokenOperand ("3.3"));		
		MathExpr expr5 = new MathExpr (Operators.plus(), expr3, expr4);
		
		
		MathExpr expr6 = new MathExpr (new MathTokenOperand ("4.4"));
		MathExpr expr7 = new MathExpr (new MathTokenOperand ("2"));		
		MathExpr expr8 = new MathExpr (Operators.mult(), expr6, expr7);
		
		
		MathExpr finalExpr = new MathExpr (Operators.plus(), expr5, expr8);
		
		String result = "15.4";
		
		test += finalExpr.toString();
		
		this.test (test, finalExpr, result, this.t);
		
		
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
		MathExpr expr3 = new MathExpr (Operators.minus_b(), expr1, expr2);
		
		MathExpr expr4 = new MathExpr (new MathTokenOperand ("2"));
		
		MathExpr finalExpr = new MathExpr (Operators.mult(), expr3, expr4);
		
		String result = "-2";
		
		test += finalExpr.toString();
		
		this.test (test, finalExpr, result, this.x);
		
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
		MathExpr expr3 = new MathExpr (Operators.minus_b(), expr1, expr2);
		
		MathExpr expr4 = new MathExpr (new MathTokenOperand ("2"));
		MathExpr expr5 = new MathExpr (Operators.mult(), expr3, expr4);
		
		MathExpr finalExpr = new MathExpr (Operators.minus_u(), expr5);
		
		String result = "2";
		
		test += finalExpr.toString();
		
		this.test (test, finalExpr, result, this.x);
		
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
		MathExpr expr3 = new MathExpr (Operators.plus(), expr1, expr2);
		
		MathExpr expr4 = new MathExpr (new MathTokenOperand ("2"));
		MathExpr expr5 = new MathExpr (Operators.mult(), expr3, expr4);
		
		
		MathExpr expr6 = new MathExpr (new MathTokenOperand ("3"));
		MathExpr expr7 = new MathExpr (new MathTokenOperand ("4"));	
		MathExpr expr8 = new MathExpr (Operators.minus_b(), expr6, expr7);
		
		MathExpr expr9 = new MathExpr (new MathTokenOperand ("2"));
		MathExpr expr10 = new MathExpr (Operators.mult(), expr8, expr9);
		
		MathExpr finalExpr = new MathExpr (Operators.plus(), expr5, expr10);
		
		String result = "12";
		
		test += finalExpr.toString();
		
		this.test (test, finalExpr, result, this.x);
		
		
		
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
		MathExpr expr3 = new MathExpr (Operators.mult(), expr1, expr2);
		
		MathExpr expr4 = new MathExpr (new MathTokenOperand ("8"));
		MathExpr expr5 = new MathExpr (Operators.mult(), expr3, expr4);
		
		MathExpr expr6 = new MathExpr (new MathTokenOperand ("2"));
		
		
		MathExpr finalExpr = new MathExpr (Operators.div(), expr5, expr6);
		
		String result = "128";
		
		test += finalExpr.toString();
		
		this.test (test, finalExpr, result, this.x);		
		
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
		MathExpr expr2 = new MathExpr (Operators.plus(), expr1, expr1);
		
		MathExpr expr3 = new MathExpr (new MathTokenOperand ("16"));
		MathExpr expr4 = new MathExpr (Operators.mult(), expr2, expr3);
		
		MathExpr finalExpr = new MathExpr (Operators.sqrt(), expr4);
		
		String result = "16";
		
		test += finalExpr.toString();
		
		this.test (test, finalExpr, result, this.x);		
		
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
		MathExpr expr2 = new MathExpr (Operators.plus(), expr1, expr1);
		
		MathExpr expr3 = new MathExpr (new MathTokenOperand ("17"));
		MathExpr expr4 = new MathExpr (Operators.minus_b(), expr2, expr3);
		
		MathExpr expr5 = new MathExpr (Operators.minus_u(), expr4);
		
		MathExpr finalExpr = new MathExpr (Operators.log(), expr5);
		
		String result = "0";
		
		test += finalExpr.toString();
		
		this.test (test, finalExpr, result, this.x);		
		
	}
	
	
	/**
	 * Test 11
	 * pow( 10, 100))
	 * @throws WrongInputException
	 * @throws WrongExpressionException 
	 * @throws WrongCalculationException 
	 */
	public void test11() throws WrongInputException, WrongExpressionException, WrongCalculationException {
		
		String test = "Test 11 -> ";		

		MathExpr expr1 = new MathExpr (new MathTokenOperand ("4"));
		MathExpr expr2 = new MathExpr (new MathTokenOperand ("2"));
		MathExpr finalExpr = new MathExpr (Operators.pow(), expr1, expr2);
		
		String result = "16";
		
		test += finalExpr.toString();
		
		this.test (test, finalExpr, result, this.x);		
		
	}
	
	
	/**
	 * Test 12
	 * exp( log (1))
	 * @throws WrongInputException
	 * @throws WrongExpressionException 
	 * @throws WrongCalculationException 
	 */
	public void test12() throws WrongInputException, WrongExpressionException, WrongCalculationException {
		
		String test = "Test 12 -> ";		

		MathExpr expr1 = new MathExpr (new MathTokenOperand ("1"));
		MathExpr expr2 = new MathExpr (Operators.log(), expr1);
		
		MathExpr finalExpr = new MathExpr (Operators.exp(), expr2);
		
		String result = "1";
		
		test += finalExpr.toString();
		
		this.test (test, finalExpr, result, this.x);		
		
	}
	
	
	/**
	 * Test 13
	 * fact( +( 4, 1))
	 * @throws WrongInputException
	 * @throws WrongExpressionException 
	 * @throws WrongCalculationException 
	 */
	public void test13() throws WrongInputException, WrongExpressionException, WrongCalculationException {
		
		String test = "Test 13 -> ";		

		MathExpr expr1 = new MathExpr (new MathTokenOperand ("4"));
		MathExpr expr2 = new MathExpr (new MathTokenOperand ("1"));
		MathExpr expr3 = new MathExpr (Operators.plus(), expr1, expr2);
		
		MathExpr finalExpr = new MathExpr (Operators.fact(), expr3);
		
		String result = "120";
		
		test += finalExpr.toString();
		
		this.test (test, finalExpr, result, this.x);		
		
	}
	
	
	
	
}

