package ParserTest;


import Exceptions.WrongInputException;
import Parser.MathLexer;


public class TestLexer {

	private boolean debug;
	
	
	public TestLexer (boolean debug) {
		
		this.debug = debug;
		
		try {
			
			// One Digit Numbers Four Operation
			this.test1();			
			this.test2();			
			this.test3();	
			this.test4();	
		
		} catch (WrongInputException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	
	public void test (String test, String input) throws WrongInputException {
		
		System.out.print ("\n" + test);
		
		MathLexer lexer = new MathLexer (input);
		
		if (this.debug) {
			
			System.out.println ();
		
			System.out.println ("Input: " + lexer.getTokenString() + "  Equals: " + lexer.getTokenString().equals(input));
			
		} else {
			
			boolean error = false;
			
			if (!lexer.getTokenString().equals(input)) {	
				
				System.out.println ("\n");
				
				System.out.println ("!!! ERROR !!!");
				
				System.out.println (" - Tokenised Input: " + lexer.getTokenString());
				
				System.out.println (" - Correct String   : " + input);
				
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
		
		String test = "Test 1";
		
		String input = "2 + 1";
		
		this.test (test, input);
		
		
		
	}
	
	
	/**
	 * Test 2
	 * 34 * 43 + 131
	 * @throws WrongInputException
	 */
	public void test2() throws WrongInputException {
		
		String test = "Test 2";
		
		String input = "34 * 43 + 131";
		
		this.test (test, input);
		
	}
	
	
	/**
	 * Test 3
	 * 1.1 + 2.2 + 3.3 + 4.4 * 2
	 * @throws WrongInputException
	 */
	public void test3() throws WrongInputException {
		
		String test = "Test 3";
		
		String input = "1.1 + 2.2 + 3.3 + 4.4 * 2";
		
		this.test (test, input);
		
	}
	
	
	/**
	 * Test 4
	 * (3 - 4) * 2
	 * @throws WrongInputException
	 */
	public void test4() throws WrongInputException {
		
		String test = "Test 4";
		
		String input = "(3 - 4) * 2";
		
		this.test (test, input);
		
	}
	
}
	