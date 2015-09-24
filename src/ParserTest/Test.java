package ParserTest;


import java.util.Hashtable;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Exceptions.MismatchedParenthesisException;
import Exceptions.WrongCalculationException;
import Exceptions.WrongExpressionException;
import Exceptions.WrongInputException;

import Gui.JavaCalcGui;
import Gui.JavaDeriveGui;
import MathToken.MathTokenSymbol;
import Parser.MathEvaluator;



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
		
	//	JavaDeriveGui windowDer = new JavaDeriveGui();
		
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
	/*	String res = "";
		
		Hashtable<MathTokenSymbol,Double> hashTab = new Hashtable<MathTokenSymbol,Double>();
		hashTab.put(new MathTokenSymbol("t"), new Double(1));
		hashTab.put(new MathTokenSymbol("y"), new Double(2));
		
		//String func = "3*t + 2*y*log(t)";
		
		String func = "3*t + 2*y";
		
		MathEvaluator eval = new MathEvaluator (func, "infix", hashTab);
			
		res = eval.getResultString();
			
		System.out.println (res); 
		
		*/
		
	}

}
