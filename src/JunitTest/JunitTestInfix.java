package JunitTest;


import static org.junit.Assert.*;
import org.junit.Test;

import Exceptions.WrongInputException;
import Parser.MathParser;


public class JunitTestInfix {

	/**
	 * Test 1
	 * 2 + 1
	 * @throws WrongInputException
	 */
	@Test
	public void test1() throws WrongInputException {
		
		String infix = "2 + 1";
		
		String prefix = "+ 1 2";
		
		String postfix = "2 1 +";
		
		MathParser parser = new MathParser (infix, "infix");
		
		System.out.println ("\nTest 1");
		
		System.out.println ("Infix: " + parser.getInfixString() + "  Equals: " + parser.getInfixString().equals(infix));
		
		System.out.println ("Prefix: " + parser.getPrefixString() + "  Equals: " + parser.getPrefixString().equals(prefix));
		
		System.out.println ("Postfix: " + parser.getPostfixString() + "  Equals: " + parser.getPostfixString().equals(postfix));
		
		assertTrue("Test Infix " + infix, parser.getInfixString().equals(infix));
		
		assertTrue("Test Prefix " + infix, parser.getPrefixString().equals(prefix));
		
		assertTrue("Test Postfix " + infix, parser.getPostfixString().equals(postfix));
		
	}
	
	
	/**
	 * Test 2
	 * 2 - 1 + 2 - 3
	 * @throws WrongInputException
	 */
	@Test
	public void test2() throws WrongInputException {
		
		String infix = "2 - 1 + 2 - 3";
		
		String prefix = "- 3 + 2 - 1 2";
		
		String postfix = "2 1 - 2 + 3 -";
		
		MathParser parser = new MathParser (infix, "infix");
		
		System.out.println ("\nTest 2");
		
		System.out.println ("Infix: " + parser.getInfixString() + "  Equals: " + parser.getInfixString().equals(infix));
		
		System.out.println ("Prefix: " + parser.getPrefixString() + "  Equals: " + parser.getPrefixString().equals(prefix));
		
		System.out.println ("Postfix: " + parser.getPostfixString() + "  Equals: " + parser.getPostfixString().equals(postfix));
		
		assertTrue("Test Infix " + infix, parser.getInfixString().equals(infix));
		
		assertTrue("Test Prefix " + infix, parser.getPrefixString().equals(prefix));
		
		assertTrue("Test Postfix " + infix, parser.getPostfixString().equals(postfix));
		
	}
	
	
	/**
	 * Test 3
	 * 3 - 4 * 2
	 * @throws WrongInputException
	 */
	@Test
	public void test3() throws WrongInputException {
		
		String infix = "3 - 4 * 2";
		
		String prefix = "- * 2 4 3";
		
		String postfix = "3 4 2 * -";
		
		MathParser parser = new MathParser (infix, "infix");
		
		System.out.println ("\nTest 3");
		
		System.out.println ("Infix: " + parser.getInfixString() + "  Equals: " + parser.getInfixString().equals(infix));
		
		System.out.println ("Prefix: " + parser.getPrefixString() + "  Equals: " + parser.getPrefixString().equals(prefix));
		
		System.out.println ("Postfix: " + parser.getPostfixString() + "  Equals: " + parser.getPostfixString().equals(postfix));
		
		assertTrue("Test Infix " + infix, parser.getInfixString().equals(infix));
		
		assertTrue("Test Prefix " + infix, parser.getPrefixString().equals(prefix));
		
		assertTrue("Test Postfix " + infix, parser.getPostfixString().equals(postfix));
		
	}
	
	
	/**
	 * Test 4
	 * 3 + 4 + 5 + 6 * 2
	 * @throws WrongInputException
	 */
	@Test
	public void test4() throws WrongInputException {
		
		String infix = "3 + 4 + 5 + 6 * 2";
		
		String prefix = "+ * 2 6 + 5 + 4 3";
		
		String postfix = "3 4 + 5 + 6 2 * +";
		
		MathParser parser = new MathParser (infix, "infix");
		
		System.out.println ("\nTest 4");
		
		System.out.println ("Infix: " + parser.getInfixString() + "  Equals: " + parser.getInfixString().equals(infix));
		
		System.out.println ("Prefix: " + parser.getPrefixString() + "  Equals: " + parser.getPrefixString().equals(prefix));
		
		System.out.println ("Postfix: " + parser.getPostfixString() + "  Equals: " + parser.getPostfixString().equals(postfix));
		
		assertTrue("Test Infix " + infix, parser.getInfixString().equals(infix));
		
		assertTrue("Test Prefix " + infix, parser.getPrefixString().equals(prefix));
		
		assertTrue("Test Postfix " + infix, parser.getPostfixString().equals(postfix));
		
	}
	
	
	/**
	 * Test 5
	 * 3 / 2 + 4 - 5 + 6 * 2
	 * @throws WrongInputException
	 */
	@Test
	public void test5() throws WrongInputException {
		
		String infix = "3 / 2 + 4 - 5 + 6 * 2";
		
		String prefix = "+ * 2 6 - 5 + 4 / 2 3";
		
		String postfix = "3 2 / 4 + 5 - 6 2 * +";
		
		MathParser parser = new MathParser (infix, "infix");
		
		System.out.println ("\nTest 5");
		
		System.out.println ("Infix: " + parser.getInfixString() + "  Equals: " + parser.getInfixString().equals(infix));
		
		System.out.println ("Prefix: " + parser.getPrefixString() + "  Equals: " + parser.getPrefixString().equals(prefix));
		
		System.out.println ("Postfix: " + parser.getPostfixString() + "  Equals: " + parser.getPostfixString().equals(postfix));
		
		assertTrue("Test Infix " + infix, parser.getInfixString().equals(infix));
		
		assertTrue("Test Prefix " + infix, parser.getPrefixString().equals(prefix));
		
		assertTrue("Test Postfix " + infix, parser.getPostfixString().equals(postfix));
		
	}
	
	
	
	/**
	 * Test 6
	 * (3 - 4) * 2
	 * @throws WrongInputException
	 */
	@Test
	public void test6() throws WrongInputException {
		
		String infix = "(3 - 4) * 2";
		
		String prefix = "* 2 - 4 3";
		
		String postfix = "3 4 - 2 *";
		
		MathParser parser = new MathParser (infix, "infix");
		
		System.out.println ("\nTest 6");
		
		System.out.println ("Infix: " + parser.getInfixString() + "  Equals: " + parser.getInfixString().equals(infix));
		
		System.out.println ("Prefix: " + parser.getPrefixString() + "  Equals: " + parser.getPrefixString().equals(prefix));
		
		System.out.println ("Postfix: " + parser.getPostfixString() + "  Equals: " + parser.getPostfixString().equals(postfix));
		
		assertTrue("Test Infix " + infix, parser.getInfixString().equals(infix));
		
		assertTrue("Test Prefix " + infix, parser.getPrefixString().equals(prefix));
		
		assertTrue("Test Postfix " + infix, parser.getPostfixString().equals(postfix));
		
	}
	
	
	/**
	 * Test 7
	 * (3 - 4) * 2 / (3 + 3* (1 - 2))
	 * @throws WrongInputException
	 */
	@Test
	public void test7() throws WrongInputException {
		
		String infix = "(3 - 4) * 2 / (3 + 3* (1 - 2))";
		
		String prefix = "/ + * - 2 1 3 3 * 2 - 4 3";
		
		String postfix = "3 4 - 2 * 3 3 1 2 - * + /";
		
		MathParser parser = new MathParser (infix, "infix");
		
		System.out.println ("\nTest 7");
		
		System.out.println ("Infix: " + parser.getInfixString() + "  Equals: " + parser.getInfixString().equals(infix));
		
		System.out.println ("Prefix: " + parser.getPrefixString() + "  Equals: " + parser.getPrefixString().equals(prefix));
		
		System.out.println ("Postfix: " + parser.getPostfixString() + "  Equals: " + parser.getPostfixString().equals(postfix));
		
		assertTrue("Test Infix " + infix, parser.getInfixString().equals(infix));
		
		assertTrue("Test Prefix " + infix, parser.getPrefixString().equals(prefix));
		
		assertTrue("Test Postfix " + infix, parser.getPostfixString().equals(postfix));
		
	}
	
	
	public static void main (String[] args) {
		
		JunitTestInfix testClass = new JunitTestInfix ();
		
		try {
			
			// One Digit Numbers Four Operation
			testClass.test1();			
			testClass.test2();			
			testClass.test3();			
			testClass.test4();			
			testClass.test5();
			
			
			// Add Parenthesis			
			//testClass.test6();			
			//testClass.test7();
		
		} catch (WrongInputException e) {
			
			e.printStackTrace();
			
		}
		
	}

}
