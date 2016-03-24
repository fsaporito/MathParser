package Parser;

import java.util.Hashtable;

import Exceptions.WrongInputException;
import Exceptions.MismatchedParenthesisException;
import Exceptions.WrongCalculationException;
import Exceptions.WrongExpressionException;
import MathExpr.MathExpr;
import MathToken.MathTokenSymbol;

public class MathEvaluator {

	/** Input Math Expression */
	private MathExpr expr;

	/** Evaluated Math Expression */
	private MathExpr result;



	/**
	 * Constructor:
	 * - Evaluate The MathExpr
	 *
	 * @param expression Mathematical Expression
	 * @throws WrongCalculationException
	 * @throws WrongInputException
	 */
	public MathEvaluator (MathExpr expression) throws WrongCalculationException, WrongInputException {

		if (expression == null) {

			throw new NullPointerException ("Null Expression!!!");

		}

		this.expr = expression;

		this.result = this.expr.eval();

	}


	/**
	 * Constructor:
	 * - Evaluate The MathExpr With Symbolic Argument (One)
	 *
	 * @param expression Mathematical Expression
	 * @param val double value for symbolic variable
	 * @throws WrongCalculationException
	 * @throws WrongInputException
	 * @throws WrongExpressionException
	 */
	public MathEvaluator (MathExpr expression, double val) throws WrongCalculationException, WrongInputException, WrongExpressionException {

		if (expression == null) {

			throw new NullPointerException ("Null Expression!!!");

		}

		this.expr = expression;

		this.result = this.expr.evalSymbolic(val);

	}


	/**
	 * Constructor:
	 * - Evaluate The MathExpr With Symbolic Arguments (Multiplex)
	 *
	 * @param expression Mathematical Expression
	 * @param tableVal HashTable With <Sym Variable, Value> Pairs
	 * @throws WrongCalculationException
	 * @throws WrongInputException
	 * @throws WrongExpressionException
	 */
	public MathEvaluator (MathExpr expression, Hashtable<MathTokenSymbol,Double> tableVal) throws WrongCalculationException, WrongInputException, WrongExpressionException {

		if (expression == null) {

			throw new NullPointerException ("MathEvaluator- Null Expression!!!");

		}

		if (tableVal == null) {

			throw new NullPointerException ("MathEvaluator- Null Table!!!");

		}

		this.expr = expression;

		this.result = this.expr.evalSymbolic(tableVal);

	}




	/**
	 * Constructor:
	 * - Calls MathParser To Create A MathExpr
	 * - Evaluate The MathExpr
	 *
	 *
	 * @param input Mathematical Expression
	 * @param type input Notation Type (infix, prefix, postfix)
	 * @throws WrongInputException The Input Isn't A Correct Mathematical Expression
	 * @throws MismatchedParenthesisException
	 * @throws WrongExpressionException
	 * @throws WrongCalculationException
	 */
	public MathEvaluator (String input, String type) throws WrongInputException, MismatchedParenthesisException, WrongExpressionException, WrongCalculationException {

		if (input == null) { // Input Mustn't Be Null

			throw new NullPointerException ("Input is null!!!");

		}

		if (input.length() == 0) { // Input Mustn't Be Empty

			throw new NullPointerException ("Input is empty!!!");

		}


		if (type == null) { // type Mustn't Be Null

			throw new NullPointerException ("type is null!!!");

		}

		if (!type.equals("infix") && !type.equals("prefix") && !type.equals("postfix")) {

			throw new WrongInputException ("field type must be either infix, prefix or postfix");

		}


		MathParser parser = new MathParser (input, type);

		this.expr = parser.getMathExpr();

		this.result = this.expr.eval();

	}


	/**
	 * Constructor:
	 * - Calls MathParser To Create A MathExpr
	 * - Evaluate The MathExpr With Symbolic Variable
	 *
	 *
	 * @param input Mathematical Expression
	 * @param type input Notation Type (infix, prefix, postfix)
	 * @param tableVal HashTable With <Sym Variable, Value> Pairs
	 * @throws WrongInputException The Input Isn't A Correct Mathematical Expression
	 * @throws MismatchedParenthesisException
	 * @throws WrongExpressionException
	 * @throws WrongCalculationException
	 */
	public MathEvaluator (String input, String type, Hashtable<MathTokenSymbol,Double> tableVal) throws WrongInputException, MismatchedParenthesisException, WrongExpressionException, WrongCalculationException {

		if (input == null) { // Input Mustn't Be Null

			throw new NullPointerException ("Input is null!!!");

		}

		if (input.length() == 0) { // Input Mustn't Be Empty

			throw new NullPointerException ("Input is empty!!!");

		}


		if (type == null) { // type Mustn't Be Null

			throw new NullPointerException ("type is null!!!");

		}

		if (!type.equals("infix") && !type.equals("prefix") && !type.equals("postfix")) {

			throw new WrongInputException ("field type must be either infix, prefix or postfix");

		}

		if (tableVal == null) {

			throw new NullPointerException ("MathEvaluator- Null Table!!!");

		}


		MathParser parser = new MathParser (input, type);

		this.expr = parser.getMathExpr();

		this.result = this.expr.evalSymbolic(tableVal);

	}



	/**
	 * Constructor:
	 * - Calls MathParser To Create A MathExpr
	 * - Evaluate The MathExpr With Symbolic Variable
	 *
	 *
	 * @param input Mathematical Expression
	 * @param type input Notation Type (infix, prefix, postfix)
	 * @param val double value for symbolic variable
	 * @throws WrongInputException The Input Isn't A Correct Mathematical Expression
	 * @throws MismatchedParenthesisException
	 * @throws WrongExpressionException
	 * @throws WrongCalculationException
	 */
	public MathEvaluator (String input, String type, double val) throws WrongInputException, MismatchedParenthesisException, WrongExpressionException, WrongCalculationException {

		if (input == null) { // Input Mustn't Be Null

			throw new NullPointerException ("Input is null!!!");

		}

		if (input.length() == 0) { // Input Mustn't Be Empty

			throw new NullPointerException ("Input is empty!!!");

		}


		if (type == null) { // type Mustn't Be Null

			throw new NullPointerException ("type is null!!!");

		}

		if (!type.equals("infix") && !type.equals("prefix") && !type.equals("postfix")) {

			throw new WrongInputException ("field type must be either infix, prefix or postfix");

		}


		MathParser parser = new MathParser (input, type);

		this.expr = parser.getMathExpr();

		this.result = this.expr.evalSymbolic(val);

	}




	/**
	 *
	 * @return the Mathematical Expression
	 */
	public MathExpr getExpr() {

		return this.expr;

	}






	/**
	 *
	 * @return The Evaluated Mathematical Expression
	 */
	public MathExpr getResult() {

		return this.result;

	}


	/**
	 *
	 * @return The Evaluated Mathematical Expression
	 */
	public String getResultString() {

		return this.result.toString();

	}




}
