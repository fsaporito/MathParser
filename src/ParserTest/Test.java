package ParserTest;


import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Exceptions.MismatchedParenthesisException;
import Exceptions.WrongCalculationException;
import Exceptions.WrongExpressionException;
import Exceptions.WrongInputException;

import Gui.JavaCalcGui;



public class Test {
	
	@SuppressWarnings("unused")
	public static void main (String[] args) throws MismatchedParenthesisException, WrongInputException, WrongExpressionException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, WrongCalculationException {
		
	//	TestMathExprEval testMathExprEval = new TestMathExprEval (false);	
	
	//	TestLexer testLexer = new TestLexer (false);
		
	//	TestParser testParserInfix = new TestParser (false, "infix");
		
	//	TestParser testParserPrefix = new TestParser (false, "prefix");
		
	//	TestParser testParserPostfix = new TestParser (false, "postfix");
		
		JavaCalcGui windowCalc = new JavaCalcGui();
		
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		
		
	}

}
