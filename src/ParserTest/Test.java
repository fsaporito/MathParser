package ParserTest;

import Exceptions.MismatchedParenthesisException;
import Exceptions.WrongInputException;


public class Test {
	
	@SuppressWarnings("unused")
	public static void main (String[] args) throws MismatchedParenthesisException, WrongInputException {
		
	//	TestLexer testLexer = new TestLexer (false);
		
	//	TestParser testParserInfix = new TestParser (false, "infix");
		
	//	TestParser testParserPrefix = new TestParser (false, "prefix");
		
	//	TestParser testParserPostfix = new TestParser (false, "postfix");
		
		TestMathExprEval testMathExprEval = new TestMathExprEval (false);
		
	}

}
