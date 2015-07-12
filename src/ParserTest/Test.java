package ParserTest;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Exceptions.MismatchedParenthesisException;
import Exceptions.WrongCalculationException;
import Exceptions.WrongExpressionException;
import Exceptions.WrongInputException;

import Gui.JavaCalcGui;
import Parser.MathEvaluator;


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
		
		String res = "";
		
		MathEvaluator eval= new MathEvaluator ("3*x + 2*log(x)", "infix", 51);
			
		res = eval.getResultString();
			
		System.out.println (res);
				
		
	}

}
