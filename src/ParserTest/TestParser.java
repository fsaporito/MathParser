package ParserTest;


import Exceptions.MismatchedParenthesisException;
import Exceptions.WrongInputException;
import Parser.MathParser;


public class TestParser {

	private boolean debug;
	
	private String type;
	
	
	public TestParser (boolean debug, String type) throws MismatchedParenthesisException, WrongInputException {
		
		this.debug = debug;
		
		if (type != null) {
			
			if (type.equals("infix") || type.equals("prefix") || type.equals("postfix")) {
				
				this.type = type;
				
			} else {
				
				throw new WrongInputException ("Wrong Type!!!");
				
			}
			
		} else {
			
			throw new WrongInputException ("Null Type!!!");
			
		}
		
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
			this.test10();
			this.test11();
			this.test12();
		
		} catch (WrongInputException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	
	public void test (String test, String infix, String prefix, String postfix) throws WrongInputException, MismatchedParenthesisException {
		
		System.out.print ("\n" + this.type + ": "+ test);
		
		MathParser parser = null;
		
		if (this.type.equals("infix")) {
			
			parser = new MathParser (infix, this.type);
			
		} else if (this.type.equals("prefix")) {
			
			parser = new MathParser (prefix, this.type);
			
		} else if (this.type.equals("postfix")) {
			
			parser = new MathParser (postfix, this.type);
			
		}
		
		if (this.debug) {
			
			System.out.println ();
			
			System.out.println ("Infix: " + parser.getInfixString().equals(infix));	
			System.out.println ("Infix           (Lenght=" + infix.length() + "): " + infix);
			System.out.println ("InfixCalculated (Lenght=" + parser.getInfixString().length() + "): " + parser.getInfixString());
			
		
			System.out.println ("Prefix: " + parser.getPrefixString().equals(prefix));	
			System.out.println ("Prefix           (Lenght=" + prefix.length() + "): " + prefix);
			System.out.println ("PrefixCalculated (Lenght=" + parser.getPrefixString().length() + "): " + parser.getPrefixString());
			
		
			System.out.println ("Postfix: " + parser.getPostfixString().equals(postfix));	
			System.out.println ("Postfix          (Lenght=" + postfix.length() + "): " + postfix);
			System.out.println ("PostixCalculated (Lenght=" + parser.getPostfixString().length() + "): " + parser.getPostfixString());
			
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
				
				System.out.println (" - Calculated Postfix: " + parser.getPostfixString());
				
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
	 * @throws MismatchedParenthesisException 
	 */
	public void test1() throws WrongInputException, MismatchedParenthesisException {
		
		String test = "Test 1 - One Digit Numbers Four Operation";
		
		String infix = "2 + 1";
		
		String prefix = "+ 1 2";
		
		String postfix = "2 1 +";
		
		this.test (test, infix, prefix, postfix);
		
		
		
	}
	
	
	/**
	 * Test 2
	 * 2 - 1 + 2 - 3
	 * @throws WrongInputException
	 * @throws MismatchedParenthesisException 
	 */
	public void test2() throws WrongInputException, MismatchedParenthesisException {
		
		String test = "Test 2 - One Digit Numbers Four Operation";
		
		String infix = "2 - 1 + 3";
		
		String prefix = "+ 3 - 1 2";
		
		String postfix = "2 1 - 3 +";
		
		this.test (test, infix, prefix, postfix);
		
	}
	
	
	/**
	 * Test 3
	 * 3 - 4 * 2
	 * @throws WrongInputException
	 * @throws MismatchedParenthesisException 
	 */
	public void test3() throws WrongInputException, MismatchedParenthesisException {
		
		String test = "Test 3 - One Digit Numbers Four Operation";
		
		String infix = "3 - 4 * 2";
		
		String prefix = "- * 2 4 3";
		
		String postfix = "3 4 2 * -";
		
		this.test (test, infix, prefix, postfix);
		
	}
	
	
	/**
	 * Test 4
	 * 3 + 4 + 5 + 6 * 2
	 * @throws WrongInputException
	 * @throws MismatchedParenthesisException 
	 */
	public void test4() throws WrongInputException, MismatchedParenthesisException {
		
		String test = "Test 4 - One Digit Numbers Four Operation";
		
		String infix = "3 + 4 + 5 + 6 * 2";
		
		String prefix = "+ * 2 6 + 5 + 4 3";
		
		String postfix = "3 4 + 5 + 6 2 * +";
		
		this.test (test, infix, prefix, postfix);
		
	}
	
	
	/**
	 * Test 5
	 * 3 / 2 + 4 - 5 + 6 * 2
	 * @throws WrongInputException
	 * @throws MismatchedParenthesisException 
	 */
	public void test5() throws WrongInputException, MismatchedParenthesisException {
		
		String test = "Test 5 - One Digit Numbers Four Operation";
		
		String infix = "3 / 2 + 4 - 5 + 6 * 2";
		
		String prefix = "+ * 2 6 - 5 + 4 / 2 3";
		
		String postfix = "3 2 / 4 + 5 - 6 2 * +";
		
		this.test (test, infix, prefix, postfix);
		
	}
	
	
	/**
	 * Test 6
	 * 34 * 43 + 131
	 * @throws WrongInputException
	 * @throws MismatchedParenthesisException 
	 */
	public void test6() throws WrongInputException, MismatchedParenthesisException {
		
		String test = "Test 6 - Every Rational Numbers Four Operation";
		
		String infix = "34 * 43 + 131";
		
		String prefix = "+ 131 * 43 34";
		
		String postfix = "34 43 * 131 +";
		
		this.test (test, infix, prefix, postfix);
	}
	
	
	/**
	 * Test 7
	 * 1.1 + 2.2 + 3.3 + 4.4 * 2
	 * @throws WrongInputException
	 * @throws MismatchedParenthesisException 
	 */
	public void test7() throws WrongInputException, MismatchedParenthesisException {
		
		String test = "Test 7 - Every Rational Numbers Four Operation";
		
		String infix = "1.1 + 2.2 + 3.3 + 4.4 * 2";
		
		String prefix = "+ * 2 4.4 + 3.3 + 2.2 1.1";
		
		String postfix = "1.1 2.2 + 3.3 + 4.4 2 * +";
		
		this.test (test, infix, prefix, postfix);
		
	}
	
	
	/**
	 * Test 8
	 * 1.1 * 2.2 + 50 + 34.43 - 3 * 10.0
	 * @throws WrongInputException
	 * @throws MismatchedParenthesisException 
	 */
	public void test8() throws WrongInputException, MismatchedParenthesisException {
		
		String test = "Test 8 - Every Rational Numbers Four Operation";
		
		String infix = "1.1 * 2.2 + 50 + 34.43 - 3 * 10.0";
		
		String prefix = "- * 10.0 3 + 34.43 + 50 * 2.2 1.1";
		
		String postfix = "1.1 2.2 * 50 + 34.43 + 3 10.0 * -";
		
		this.test (test, infix, prefix, postfix);
		
	}
	
	
	/**
	 * Test 9
	 * (3 - 4) * 2
	 * @throws WrongInputException
	 * @throws MismatchedParenthesisException 
	 */
	public void test9() throws WrongInputException, MismatchedParenthesisException {
		
		String test = "Test 9 - Parenthesis";
		
		String infix = "( 3 - 4 ) * 2";
		
		String prefix = "* 2 - 4 3";
		
		String postfix = "3 4 - 2 *";
		
		this.test (test, infix, prefix, postfix);
		
	}
	
	
	/**
	 * Test 10
	 * ( 3.1 - 4 ) * 2.5 / ( 3 + 3 * 2 ) * 84.54
	 * @throws WrongInputException
	 * @throws MismatchedParenthesisException 
	 */
	public void test10() throws WrongInputException, MismatchedParenthesisException {
		
		String test = "Test 10 - Parenthesis";
		
		String infix = "( 3.1 - 4 ) * 2.5 / ( 3 + 3 * 2 ) * 84.54";
		
		String prefix = "* 84.54 / + * 2 3 3 * 2.5 - 4 3.1";
		
		String postfix = "3.1 4 - 2.5 * 3 3 2 * + / 84.54 *";
		
		this.test (test, infix, prefix, postfix);
		
	}
	
	
	/**
	 * Test 11
	 * ( (3 + 4) * 2 + (3 - 4) * 2)
	 * @throws WrongInputException
	 * @throws MismatchedParenthesisException 
	 */
	public void test11() throws WrongInputException, MismatchedParenthesisException {
		
		String test = "Test 11 - Nested Parenthesis";
		
		String infix = "( ( 3 + 4 ) * 2 + ( 3 - 4 ) * 2 )";
		
		String prefix = "+ * 2 - 4 3 * 2 + 4 3";
		
		String postfix = "3 4 + 2 * 3 4 - 2 * +";
		
		this.test (test, infix, prefix, postfix);
		
	}
	
	
	/**
	 * Test 12
	 * ( 2 * ( 3 + 4 * (5 + 6 * (7 + 8) ) ) )
	 * @throws WrongInputException
	 * @throws MismatchedParenthesisException 
	 */
	public void test12() throws WrongInputException, MismatchedParenthesisException {
		
		String test = "Test 12 - Nested Parenthesis";
		
		String infix = "( 22 * ( 33 + 4 * ( 5 + 6 * ( 7 + 8 ) ) ) )";
		
		String prefix = "* + * + * + 8 7 6 5 4 33 22";
		
		String postfix = "22 33 4 5 6 7 8 + * + * + *";
		
		this.test (test, infix, prefix, postfix);
		
	}
	
}
