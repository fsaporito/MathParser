package ParserTest;


import Exceptions.WrongInputException;
import Parser.MathLexer;


public class TestLexer {

	private boolean debug;
	
	
	public TestLexer (boolean debug) {
		
		this.debug = debug;
		
		try {
			
			this.test1();			
			this.test2();			
			this.test3();	
			this.test4();	
		
		} catch (WrongInputException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	
	public void test (String test, String input, String type) throws WrongInputException {
		
		System.out.print ("\nLexer [" + type + "]:" + test);
		
		MathLexer lexer = new MathLexer (input, type);
		
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
		
		String input = "( (3 + 4) * 2 + (3 - 4) * 2)";
		
		this.test (test, input, "infix");
		
	}
	
}
	