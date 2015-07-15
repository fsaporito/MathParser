package ParserTest;


import java.util.Hashtable;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Exceptions.MismatchedParenthesisException;
import Exceptions.WrongCalculationException;
import Exceptions.WrongExpressionException;
import Exceptions.WrongInputException;

import Gui.JavaCalcGui;
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
		
		JavaCalcGui windowCalc = new JavaCalcGui();
		
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		String res = "";
		
		Hashtable<MathTokenSymbol,Double> hashTab = new Hashtable<MathTokenSymbol,Double>();
		hashTab.put(new MathTokenSymbol("t"), new Double(1));
		hashTab.put(new MathTokenSymbol("y"), new Double(2));
		
		MathEvaluator eval= new MathEvaluator ("3*t + 2*y*log(t)", "infix", hashTab);
			
		res = eval.getResultString();
			
		System.out.println (res);
				
		
		
	}

}
