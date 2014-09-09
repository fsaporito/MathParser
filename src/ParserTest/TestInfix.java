package ParserTest;


import Exceptions.WrongInputException;
import Parser.MathParser;


public class TestInfix {

	private boolean debug;
	
	
	public TestInfix (boolean debug) {
		
		this.debug = debug;
		
		try {
			
			// One Digit Numbers Four Operation
			this.test1();			
			this.test2();			
			this.test3();			
			this.test4();			
			this.test5();
			
			
			// Every Rational Numbers Four Operation
			this.test6();			
			this.test7();			
			this.test8();			
						
			
			
			// Add Parenthesis			
			this.test9();			
			//this.test10();
		
		} catch (WrongInputException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	
	public void test (String test, String infix, String prefix, String postfix) throws WrongInputException {
		
		System.out.print ("\n" + test);
		
		MathParser parser = new MathParser (infix, "infix");
		
		if (this.debug) {
			
			System.out.println ();
		
			System.out.println ("Infix: " + parser.getInfixString() + "  Equals: " + parser.getInfixString().equals(infix));
		
			System.out.println ("Prefix: " + parser.getPrefixString() + "  Equals: " + parser.getPrefixString().equals(prefix));
		
			System.out.println ("Postfix: " + parser.getPostfixString() + "  Equals: " + parser.getPostfixString().equals(postfix));
			
		} else {
			
			boolean error = false;
			
			if (!parser.getInfixString().equals(infix)) {	
				
				System.out.println ("\n");
				
				System.out.println ("!!! ERROR !!!");
				
				System.out.println (" - Calculated Infix: " + parser.getInfixString());
				
				System.out.println (" - Correct Infix   : " + infix);
				
				error = true;
				
			}
			
			if (!parser.getPrefixString().equals(prefix)) {	
				
				System.out.println ("\n");
				
				System.out.println ("!!! ERROR !!!");
				
				System.out.println (" - Calculated Prefix: " + parser.getPrefixString());
				
				System.out.println (" - Correct Prefix   : " + prefix);
				
				error = true;
				
			}
			
			if (!parser.getPostfixString().equals(postfix)) {	
				
				System.out.println ("\n");
				
				System.out.println ("!!! ERROR !!!");
				
				System.out.println (" - Calculated Postefix: " + parser.getPostfixString());
				
				System.out.println (" - Correct Postfix   : " + postfix);
				
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
		
		String infix = "2 + 1";
		
		String prefix = "+ 1 2";
		
		String postfix = "2 1 +";
		
		this.test (test, infix, prefix, postfix);
		
		
		
	}
	
	
	/**
	 * Test 2
	 * 2 - 1 + 2 - 3
	 * @throws WrongInputException
	 */
	public void test2() throws WrongInputException {
		
		String test = "Test 2";
		
		String infix = "2 - 1 + 2 - 3";
		
		String prefix = "- 3 + 2 - 1 2";
		
		String postfix = "2 1 - 2 + 3 -";
		
		this.test (test, infix, prefix, postfix);
		
	}
	
	
	/**
	 * Test 3
	 * 3 - 4 * 2
	 * @throws WrongInputException
	 */
	public void test3() throws WrongInputException {
		
		String test = "Test 3";
		
		String infix = "3 - 4 * 2";
		
		String prefix = "- * 2 4 3";
		
		String postfix = "3 4 2 * -";
		
		this.test (test, infix, prefix, postfix);
		
	}
	
	
	/**
	 * Test 4
	 * 3 + 4 + 5 + 6 * 2
	 * @throws WrongInputException
	 */
	public void test4() throws WrongInputException {
		
		String test = "Test 4";
		
		String infix = "3 + 4 + 5 + 6 * 2";
		
		String prefix = "+ * 2 6 + 5 + 4 3";
		
		String postfix = "3 4 + 5 + 6 2 * +";
		
		this.test (test, infix, prefix, postfix);
		
	}
	
	
	/**
	 * Test 5
	 * 3 / 2 + 4 - 5 + 6 * 2
	 * @throws WrongInputException
	 */
	public void test5() throws WrongInputException {
		
		String test = "Test 5";
		
		String infix = "3 / 2 + 4 - 5 + 6 * 2";
		
		String prefix = "+ * 2 6 - 5 + 4 / 2 3";
		
		String postfix = "3 2 / 4 + 5 - 6 2 * +";
		
		this.test (test, infix, prefix, postfix);
		
	}
	
	
	/**
	 * Test 6
	 * 34 * 43 + 131
	 * @throws WrongInputException
	 */
	public void test6() throws WrongInputException {
		
		String test = "Test 6";
		
		String infix = "34 * 43 + 131";
		
		String prefix = "+ 131 * 43 34";
		
		String postfix = "34 43 * 131 +";
		
		this.test (test, infix, prefix, postfix);
	}
	
	
	/**
	 * Test 7
	 * 1.1 + 2.2 + 3.3 + 4.4 * 2
	 * @throws WrongInputException
	 */
	public void test7() throws WrongInputException {
		
		String test = "Test 7";
		
		String infix = "1.1 + 2.2 + 3.3 + 4.4 * 2";
		
		String prefix = "+ * 2 4.4 + 3.3 + 2.2 1.1";
		
		String postfix = "1.1 2.2 + 3.3 + 4.4 2 * +";
		
		this.test (test, infix, prefix, postfix);
		
	}
	
	
	/**
	 * Test 8
	 * 1.1 * 2.2 + 50 + 34.43 - 3 * 10.0
	 * @throws WrongInputException
	 */
	public void test8() throws WrongInputException {
		
		String test = "Test 8";
		
		String infix = "1.1 * 2.2 + 50 + 34.43 - 3 * 10.0";
		
		String prefix = "- * 10.0 3 + 34.43 + 50 * 2.2 1.1";
		
		String postfix = "1.1 2.2 * 50 + 34.43 + 3 10.0 * -";
		
		this.test (test, infix, prefix, postfix);
		
	}
	
	
	/**
	 * Test 9
	 * (3 - 4) * 2
	 * @throws WrongInputException
	 */
	public void test9() throws WrongInputException {
		
		String test = "Test 9";
		
		String infix = "(3 - 4) * 2";
		
		String prefix = "* 2 - 4 3";
		
		String postfix = "3 4 - 2 *";
		
		this.test (test, infix, prefix, postfix);
		
	}
	
	
	/**
	 * Test 10
	 * (3 - 4) * 2 / (3 + 3* (1 - 2))
	 * @throws WrongInputException
	 */
	public void test10() throws WrongInputException {
		
		String test = "Test 10";
		
		String infix = "(3 - 4) * 2 / (3 + 3* (1 - 2))";
		
		String prefix = "/ + * - 2 1 3 3 * 2 - 4 3";
		
		String postfix = "3 4 - 2 * 3 3 1 2 - * + /";
		
		this.test (test, infix, prefix, postfix);
		
	}
	
}
