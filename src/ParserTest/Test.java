package ParserTest;

import Exceptions.MismatchedParenthesisException;


public class Test {
	
	@SuppressWarnings("unused")
	public static void main (String[] args) throws MismatchedParenthesisException {
		
		TestLexer testLexer = new TestLexer (false);
		
		TestInfix testInfix = new TestInfix (false);
		
	}

}
