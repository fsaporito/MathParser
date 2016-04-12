package ParserTest;


import java.math.BigDecimal;
import java.util.Hashtable;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Exceptions.WrongInputException;
import Exceptions.MismatchedParenthesisException;
import Exceptions.WrongCalculationException;
import Exceptions.WrongExpressionException;

import Gui.JavaCalcGui;
import Gui.JavaDeriveGui;
import MathExpr.MathExpr;
import MathToken.MathTokenSymbol;
import Parser.MathEvaluator;
import Parser.MathParser;



public class Test {

	@SuppressWarnings("unused")
	public static void main (String[] args) throws MismatchedParenthesisException, WrongInputException, WrongExpressionException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, WrongCalculationException {

	//	TestMathExprEval testMathExprEval = new TestMathExprEval (false);

	//	TestLexer testLexer = new TestLexer (false);

	//	TestParser testParserInfix = new TestParser (false, "infix");

	//	TestParser testParserPrefix = new TestParser (false, "prefix");

	//	TestParser testParserPostfix = new TestParser (false, "postfix");

	//  TestDer testDer = new TestDer (false);

	JavaCalcGui windowCalc = new JavaCalcGui();

	JavaDeriveGui windowDer = new JavaDeriveGui();

	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

	// Parsing Numbers In Scientific Notation
	//	String func = "(-15 * ( -8.908001E-4 ) )";
	//  MathExpr expr = (new MathParser (func)).getMathExpr();
	//  System.out.println (expr.toString());
	//  System.out.println (expr.eval());




	}

}
