package ParserTest;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Exceptions.MismatchedParenthesisException;
import Exceptions.WrongExpressionException;
import Exceptions.WrongInputException;
import Gui.JavaCalcGui;


public class Test {
	
	@SuppressWarnings("unused")
	public static void main (String[] args) throws MismatchedParenthesisException, WrongInputException, WrongExpressionException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		
		TestMathExprEval testMathExprEval = new TestMathExprEval (false);	
	
		TestLexer testLexer = new TestLexer (false);
		
		TestParser testParserInfix = new TestParser (false, "infix");
		
	//	TestParser testParserPrefix = new TestParser (false, "prefix");
		
	//	TestParser testParserPostfix = new TestParser (false, "postfix");
		
		JavaCalcGui window = new JavaCalcGui();
		
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		
	}

}
