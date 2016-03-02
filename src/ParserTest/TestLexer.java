package ParserTest;


import Exceptions.WrongInputException;
import Parser.MathLexer;


public class TestLexer {

	private boolean debug;
	
	
	public TestLexer (boolean debug) {
		
		this.debug = debug;
		
		try {
			
			this.test1(); // One Digit Numbers Four Operation			
			this.test2(); // One Digit Numbers Four Operation		
			this.test3(); // Every Rational Numbers Four Operation
			this.test4(); // Parenthesis
			this.test5(); // Nested Parenthesis
			this.test6(); // Unary Minus 1 (#)
			this.test7(); // COS
			this.test8(); // SIN
			this.test9(); // TAN
			this.test10(); // LOG (FACT)
			this.test11(); // EXP
			this.test12(); // SQRT (POW)
			this.test13(); // Unary Minus 2
		
		} catch (WrongInputException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	
	public void test (String test, String input, String type) throws WrongInputException {
		
		System.out.print ("\nLexer [" + type + "]:" + test);
		
		MathLexer lexer = new MathLexer (input, type);
		
		input = input.replace('#', '-');
		
		if (this.debug) {
			
			System.out.println ();
		
			System.out.println ("Input: " + lexer.getTokenString() + "  Equals: " + lexer.getTokenString().equals(input));
			
		} else {
			
			boolean error = false;
			
			if (!lexer.getTokenString().equals(input)) {	
				
				System.out.println ("\n");
				
				System.out.println ("!!! ERROR !!!");
				
				System.out.println (" - Tokenised Input(Lenght=" + lexer.getTokenString().length() + "): " + lexer.getTokenString());
				
				System.out.println (" - Correct String (Lenght=" + input.length() + "): " + input);
				
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
	 */
	public void test1() throws WrongInputException {
		
		String test = "Test 1 - One Digit Numbers Four Operation";
		
		String input = "2 + 1";
		
		this.test (test, input, "infix");
		
		
		
	}
	
	
	/**
	 * Test 2
	 * 34 * 43 + 131
	 * @throws WrongInputException
	 */
	public void test2() throws WrongInputException {
		
		String test = "Test 2 - One Digit Numbers Four Operation";
		
		String input = "34 * 43 + 131";
		
		this.test (test, input, "infix");
		
	}
	
	
	/**
	 * Test 3
	 * 1.1 + 2.2 + 3.3 + 4.4 * 2
	 * @throws WrongInputException
	 */
	public void test3() throws WrongInputException {
		
		String test = "Test 3 - Every Rational Numbers Four Operation";
		
		String input = "1.1 + 2.2 + 3.3 + 4.4 * 2";
		
		this.test (test, input, "infix");
		
	}
	
	
	/**
	 * Test 4
	 * (3 - 4) * 2
	 * @throws WrongInputException
	 */
	public void test4() throws WrongInputException {
		
		String test = "Test 4 - Parenthesis";
		
		String input = "( 3 - 4 ) * 2";
		
		this.test (test, input, "infix");
		
	}
	
	
	/**
	 * Test 5
	 * ( (3 + 4) * 2 + (3 - 4) * 2)
	 * @throws WrongInputException
	 */
	public void test5() throws WrongInputException {
		
		String test = "Test 5 - Nested Parenthesis";
		
		String input = "( ( 3 + 4 ) * 2 + ( 3 - 4 ) * 2 )";
		
		this.test (test, input, "infix");
		
	}
	
	
	/**
	 * Test 6
	 * ( # (3 + 4) )
	 * @throws WrongInputException
	 */
	public void test6() throws WrongInputException {
		
		String test = "Test 6 - Unary Minus (#)";
		
		String input = "( # ( 3 + 4 ) )";
		
		this.test (test, input, "infix");
		
	}
	
	/**
	 * Test 7
	 * sin(45+45)
	 * @throws WrongInputException
	 */
	public void test7() throws WrongInputException {
		
		String test = "Test 7 - COS";
		
		String input = "( cos ( 45 + 45 ) )";
		
		this.test (test, input, "infix");
		
	}
	
	
	/**
	 * Test 8
	 * sin(45+45)
	 * @throws WrongInputException
	 */
	public void test8() throws WrongInputException {
		
		String test = "Test 8 - SIN";
		
		String input = "( sin ( 45 + 45 ) )";
		
		this.test (test, input, "infix");
		
	}
	
	
	/**
	 * Test 9
	 * tan(45+45)
	 * @throws WrongInputException
	 */
	public void test9() throws WrongInputException {
		
		String test = "Test 9 - TAN";
		
		String input = "( tan ( 45 + 45 ) )";
		
		this.test (test, input, "infix");
		
	}
	
	
	/**
	 * Test 10
	 * LOG(FACT(3))
	 * @throws WrongInputException
	 */
	public void test10() throws WrongInputException {
		
		String test = "Test 10 - LOG (FACT)";
		
		String input = "( log ( fact ( 10 ) ) )";
		
		this.test (test, input, "infix");
		
	}
	
	
	/**
	 * Test 11
	 * exp(10)
	 * @throws WrongInputException
	 */
	public void test11() throws WrongInputException {
		
		String test = "Test 11 - EXP";
		
		String input = "( exp ( 10 ) )";
		
		this.test (test, input, "infix");
		
	}
	
	
	/**
	 * Test 12
	 * SQRT(EXP(2)
	 * @throws WrongInputException
	 */
	public void test12() throws WrongInputException {
		
		String test = "Test 12 - SQRT ( EXP )";
		
		String input = "( sqrt ( exp ( 2 ) )";
		
		this.test (test, input, "infix");
		
	}
	
	
	/**
	 * Test 13
	 * (-15 + 1)*y
	 * @throws WrongInputException
	 */
	public void test13() throws WrongInputException {
		
		String test = "Test 13 - (-15 + 1)*y";
		
		String input = "( - 15 + 1 ) * y";
		
		this.test (test, input, "infix");
		
	}
	
	
}
	