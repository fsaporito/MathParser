package MathExpr;


import java.util.ArrayList;
import java.util.Hashtable;

import MathToken.MathToken;
import MathToken.MathTokenOperand;
import MathToken.MathTokenOperator;
import MathToken.MathTokenSymbol;
import MathToken.Operators;
import Parser.MathLexer;
import Parser.MathParser;

import DataStructures.Queue;

import Exceptions.WrongInputException;
import Exceptions.WrongCalculationException;
import Exceptions.WrongExpressionException;

public class MathExpr {

	/** Precision */
	protected static double precision = 0.000000001;

	/** Single Operand Field */
	protected MathTokenOperand operand;

	/** Single Symbol Field */
	protected MathTokenSymbol symbol;

	/**
	 *  Expression Type:
	 *  - operand (No Operand)
	 *  - symbol (No Operand)
	 *  - expression (With Operand)
	 *
	 */
	protected String type;

	/** Boolean Value, TRUE IF The Expression Contains A Symbol */
	protected boolean symbolic;

	/** Arguments List */
	protected ArrayList<MathExpr> exprArgs;

	/** Symbols List Found In The Expression */
	protected ArrayList<MathTokenSymbol> symList;

	/** Operator */
	protected MathTokenOperator operator;

	/** Boolean, TRUE If Precision Computing Isn't Required - Default is FALSE*/
	protected boolean laskPrecision;


	/**
	 *
	 * Constructor That Creates An Expression Composed Only By An Operand (Double)
	 *
	 * @param value Double Number To Use As A MathExpr
	 * @throws WrongExpressionException
	 * @throws WrongInputException
	 */
	public MathExpr (double value) throws WrongExpressionException, WrongInputException {

		this( new MathTokenOperand (value) );

	}

	/**
	 *
	 * Constructor That Creates An Expression Composed Only By An Operand (Int)
	 *
	 * @param value Int Number To Use As A MathExpr
	 * @throws WrongExpressionException
	 * @throws WrongInputException
	 */
	public MathExpr (int value) throws WrongExpressionException, WrongInputException {

		this( new MathTokenOperand (value) );

	}


	/**
	 *
	 * Constructor That Creates An Expression Composed Only By An Operand
	 *
	 * @param tk Math Operand
	 * @throws WrongExpressionException
	 */
	public MathExpr (MathToken tk) throws WrongExpressionException {

		if (tk == null) {

			throw new WrongExpressionException ("MathExpr - Null Operand Input!!!");

		}

		if (tk.isOperand()) {

			this.operand =(MathTokenOperand) tk;

			this.type = "operand";

			this.symbolic = false;

			this.symList = new ArrayList<MathTokenSymbol>();

			this.laskPrecision = false;

		} else if (tk.isSymbol()) {

			this.symbol =(MathTokenSymbol) tk;

			this.type = "symbol";

			this.symbolic = true;

			this.symList = new ArrayList<MathTokenSymbol>();

			this.symList.add(this.symbol);

			this.laskPrecision = false;

		} else {

			String exceptionString = "MathExpr - An Expression Cannot Be Formed Only By A";

			if (tk.isOperator()) {

				exceptionString += "n Operator !!!";

			}

			if (tk.isParenthesis()) {

				exceptionString += "MathExpr - Parenthesis !!!";

			}

			throw new WrongExpressionException (exceptionString);

		}

	}

	/**
	 *
	 * Constructor That Creates An Expression Composed By An
	 * Operator And One Expression:
	 * - UNARY_MINUS
	 * - UNARY_SQRT
	 * - UNARY_LOG
	 * without lask precision
	 *
	 * @param operator Math Operator With Arity One
	 * @param operandExpr Expression Argument
	 * @throws WrongExpressionException
	 * @throws WrongCalculationException
	 * @throws WrongInputException
	 */
	public MathExpr (MathTokenOperator operator, MathExpr operandExpr) throws WrongExpressionException, WrongCalculationException, WrongInputException {

		this(operator, operandExpr, false);

	}



	/**
	 *
	 * Constructor That Creates An Expression Composed By An
	 * Operator And One Expression:
	 * - UNARY_MINUS
	 * - UNARY_SQRT
	 * - UNARY_LOG
	 *
	 * @param operator Math Operator With Arity One
	 * @param operandExpr Expression Argument
	 * @param laskPrec Boolean, TRUE if lask precision is required
	 * @throws WrongExpressionException
	 * @throws WrongCalculationException
	 * @throws WrongInputException
	 */
	public MathExpr (MathTokenOperator operator, MathExpr operandExpr, boolean laskPrec) throws WrongExpressionException, WrongCalculationException, WrongInputException {

		if (operator == null) {

			throw new WrongExpressionException ("MathExpr - Null Operator!!!");

		}

		System.out.println (operator.getName() + "  -  " + operandExpr);


		if (operator.getArgNum() != 1) {

			throw new WrongExpressionException ("MathExpr - Wrong Operator Number Operator!!!");

		}

		if (operandExpr == null) {

			throw new WrongExpressionException ("MathExpr - Null Operand!!!");

		}


		if (operator.getName().equals("UNARY_MINUS")
			|| operator.getName().equals("SQRT")
			|| operator.getName().equals("LOG")
			|| operator.getName().equals("EXP")
			|| operator.getName().equals("COS")
			|| operator.getName().equals("SIN")
			|| operator.getName().equals("TG")
			|| operator.getName().equals("ARCOS")
			|| operator.getName().equals("ARCSIN")
			|| operator.getName().equals("ARCTG")
			|| operator.getName().equals("COSH")
			|| operator.getName().equals("SINH")
			|| operator.getName().equals("TANH")
			|| operator.getName().equals("FACT")
			) {

			this.operator = operator;

			this.type = "expression";

			this.laskPrecision = laskPrec;

			ArrayList<MathExpr> tmpList = new ArrayList<MathExpr> ();

			tmpList.add (operandExpr);

			this.exprArgs = tmpList;

			this.symbolic = this.checkSymbolic();

			this.exprArgs = this.simplify (tmpList);

		}  else {

			throw new WrongExpressionException ("MathExpr - Unrecognised Operator!!!\n" + operator.toString());

		}

	}


	/**
	 *
	 * Constructor That Creates An Expression Composed An
	 * Operator And One Operand:
	 * - UNARY_MINUS
	 * - UNARY_SQRT
	 * - UNARY_LOG
	 *  without lask precision
	 *
	 * @param operator Math Operator With Arity One
	 * @param operand Math Operand, Operator Argument
	 * @throws WrongExpressionException
	 * @throws WrongCalculationException
	 * @throws WrongInputException
	 */
	public MathExpr (MathTokenOperator operator, MathTokenOperand operand) throws WrongExpressionException, WrongCalculationException, WrongInputException {

		this (operator, (new MathExpr (operand)), false);

	}


	/**
	 *
	 * Constructor That Creates An Expression Composed An
	 * Operator And One Operand:
	 * - UNARY_MINUS
	 * - UNARY_SQRT
	 * - UNARY_LOG
	 *
	 * @param operator Math Operator With Arity One
	 * @param operand Math Operand, Operator Argument
	 * @param laskPrec Boolean, TRUE if lask precision is required
	 * @throws WrongExpressionException
	 * @throws WrongCalculationException
	 * @throws WrongInputException
	 */
	public MathExpr (MathTokenOperator operator, MathTokenOperand operand, boolean laskPrec) throws WrongExpressionException, WrongCalculationException, WrongInputException {

		this (operator, (new MathExpr (operand)), laskPrec);

	}


	/**
	 *
	 * Constructor That Creates An Expression Composed An
	 * Operator And Two Expressions:
	 * - PLUS
	 * - BINARY MINUS
	 * - MULT
	 * - DIV
	 * without Lask precision
	 *
	 * @param operator Math Operator With Arity Two
	 * @param operandExpr1 First Argument
	 * @param operandExpr2 Second Argument
	 * @throws WrongExpressionException
	 * @throws WrongCalculationException
	 * @throws WrongInputException
	 */
	public MathExpr (MathTokenOperator operator, MathExpr operandExpr1, MathExpr operandExpr2) throws WrongExpressionException, WrongCalculationException, WrongInputException {

		this (operator, operandExpr1, operandExpr2, false);

	}



	/**
	 *
	 * Constructor That Creates An Expression Composed An
	 * Operator And Two Expressions:
	 * - PLUS
	 * - BINARY MINUS
	 * - MULT
	 * - DIV
	 *
	 * @param operator Math Operator With Arity Two
	 * @param operandExpr1 First Argument
	 * @param operandExpr2 Second Argument
	 * @param laskPrec Boolean, TRUE if lask precision is required
	 * @throws WrongExpressionException
	 * @throws WrongCalculationException
	 * @throws WrongInputException
	 */
	public MathExpr (MathTokenOperator operator, MathExpr operandExpr1, MathExpr operandExpr2, boolean laskPrec) throws WrongExpressionException, WrongCalculationException, WrongInputException {

		if (operator == null) {

			throw new WrongExpressionException ("MathExpr - Null Operator!!!");

		}

		if (operandExpr1 == null) {

			throw new WrongExpressionException ("MathExpr - Null Operand1!!!");

		}

		if (operandExpr2 == null) {

			throw new WrongExpressionException ("MathExpr - Null Operand2!!!");

		}

		if (operator.getName().equals("PLUS")
			|| operator.getName().equals("BINARY_MINUS")
			|| operator.getName().equals("MULT")
			|| operator.getName().equals("DIV")
			|| operator.getName().equals("POW")
			) {

			this.operator = operator;

			this.type = "expression";

			this.laskPrecision = laskPrec;

			ArrayList<MathExpr> tmpList = new ArrayList<MathExpr> ();

			tmpList.add (operandExpr1);

			tmpList.add (operandExpr2);

			this.exprArgs = tmpList;

			this.symbolic = this.checkSymbolic();

			this.exprArgs = this.simplify(tmpList);

		} else {

			throw new WrongExpressionException ("MathExpr - Unrecognised Operator!!!\n" + operator.toString());

		}

	}



	/**
	 *
	 * Constructor That Creates An Expression Composed An
	 * Operator And Two Operands:
	 * - PLUS
	 * - BINARY MINUS
	 * - MULT
	 * - DIV
	 * without lask precision
	 *
	 * @param operator Math Operator With Arity Two
	 * @param operand1 First Operand Argument
	 * @param operand2 Second Operand Argument
	 * @throws WrongExpressionException
	 * @throws WrongCalculationException
	 * @throws WrongInputException
	 */
	public MathExpr (MathTokenOperator operator, MathTokenOperand operand1, MathTokenOperand operand2) throws WrongExpressionException, WrongCalculationException, WrongInputException {

		this (operator, (new MathExpr (operand1)), (new MathExpr (operand2)), false);

	}


	/**
	 *
	 * Constructor That Creates An Expression Composed An
	 * Operator And Two Operands:
	 * - PLUS
	 * - BINARY MINUS
	 * - MULT
	 * - DIV
	 *
	 * @param operator Math Operator With Arity Two
	 * @param operand1 First Operand Argument
	 * @param operand2 Second Operand Argument
	 * @param laskPrec Boolean, TRUE if lask precision is required
	 * @throws WrongExpressionException
	 * @throws WrongCalculationException
	 * @throws WrongInputException
	 */
	public MathExpr (MathTokenOperator operator, MathTokenOperand operand1, MathTokenOperand operand2, boolean laskPrec) throws WrongExpressionException, WrongCalculationException, WrongInputException {

		this (operator, (new MathExpr (operand1)), (new MathExpr (operand2)), laskPrec);

	}


	/**
	 *
	 * Constructor That Creates An Expression Composed An
	 * Operator And N Operands:
	 *
	 * without lask precision
	 *
	 * @param operator Math Operator With Arity N
	 * @param operandExprList Arguments List
	 * @throws WrongExpressionException
	 * @throws WrongCalculationException
	 * @throws WrongInputException
	 */
	public MathExpr (MathTokenOperator operator, ArrayList<MathExpr> operandExprList) throws WrongExpressionException, WrongCalculationException, WrongInputException {

		this(operator, operandExprList, false);

	}



	/**
	 *
	 * Constructor That Creates An Expression Composed An
	 * Operator And N Operands:
	 *
	 * @param operator Math Operator With Arity N
	 * @param operandExprList Arguments List
	 * @param laskPrec Boolean, TRUE if lask precision is required
	 * @throws WrongExpressionException
	 * @throws WrongCalculationException
	 * @throws WrongInputException
	 */
	public MathExpr (MathTokenOperator operator, ArrayList<MathExpr> operandExprList, boolean laskPrec) throws WrongExpressionException, WrongCalculationException, WrongInputException {

		if (operator == null) {

			throw new WrongExpressionException ("MathExpr - Null Operator!!!");

		}

		if (operandExprList == null) {

			throw new WrongExpressionException ("MathExpr - Null Arguments List!!!");

		}

		if (operandExprList.size() == 0) {

			throw new WrongExpressionException ("MathExpr - Empty Arguments List!!!");

		}

		for (int i = 0; i < operandExprList.size(); i++) {

			if (operandExprList.get(i) == null) {

				throw new WrongExpressionException ("MathExpr - Null Argument " + i + "!!!");

			}

		}

		this.operator = operator;

		this.type = "expression";

		this.laskPrecision = laskPrec;

		this.exprArgs = operandExprList;

		this.symbolic = this.checkSymbolic();

		this.exprArgs = this.simplify(operandExprList);

	}


	/**
	 * Returns TRUE If Lask Precision Is On, False Otherwise
	 *
	 * @return The Lask Flag
	 */
	public boolean getLaskPrecision() {

		return this.laskPrecision;

	}

	/**
	 * Set The Lask Precision Flag
	 *
	 */
	public void setLaskPrecision (boolean laskPrec) {

		this.laskPrecision = laskPrec;

	}



	/**
	 * Returns The Expression's Operand
	 * In Case The Expression Is Of Type Operand,
	 * NULL Otherwise
	 *
	 * @return The Operand
	 */
	public MathTokenOperand getOperand() {

		if (this.type.equals("operand")) {

			return this.operand;

		} else {

			return null;

		}

	}


	/**
	 * Returns The Expression's Operand As Double
	 * In Case The Expression Is Of Type Operand,
	 *
	 * @return The Operand As Double
	 */
	public double getOperandDouble() {

		double returnValue = 0.0;

		try {

			if (this.type.equals("operand")) {

				returnValue = this.operand.getValueDouble();

			} else {

				throw new WrongCalculationException ("getOperandDouble - Not An Operand, Cannot Transsform To Double");

			}

		} catch (WrongCalculationException e) {

				e.printStackTrace();

		}

		return returnValue;

	}


	/**
	 * Returns The Expression's Symbol
	 * In Case The Expression Is Of Type Symbol,
	 * NULL Otherwise
	 *
	 * @return The Symbol
	 */
	public MathTokenSymbol getSymbol() {

		if (this.type.equals("symbol")) {

			return this.symbol;

		} else {

			return null;

		}

	}


	/**
	 * Returns TRUE If The Expression Is Symbolic,
	 * FALSE Otherwise
	 *
	 * @return The Symbolic Boolean Value
	 */
	public boolean getSymbolic() {

		return this.symbolic;

	}


	/**
	 * Returns The Expression's Type
	 *
	 * @return The Type
	 */
	public String getType() {

		return this.type;

	}


	/**
	 * Returns An ArrayList With The Expression Arguments
	 * NULL If The Expression Is An Operand Or A Symbol
	 *
	 * @return ArrayList With The Expression Arguments
	 */
	public ArrayList<MathExpr> getExprArgs() {

		if (this.type.equals("expression")) {

			return this.exprArgs;

		} else {

			return null;

		}

	}


	/**
	 * Returns The Expression's Operator
	 * NULL If The Expression Is An Operand
	 *
	 * @return The Operator
	 */
	public MathTokenOperator getOperator() {

		if (this.type.equals("expression")) {

			return this.operator;

		} else {

			return null;

		}

	}


	/**
	 * Returns The Expression' Symbols List
	 *
	 * @return The Expression' Symbols List
	 */
	public ArrayList<MathTokenSymbol> getSymbolList () {

		return this.symList;

	}


	/**
	 * Checks If The Expression Contains Symbolic Arguments
	 *
	 * @return TRUE IF Finds Symbols, FALSE Otherwise
	 */
	public boolean checkSymbolic () {

		boolean symbol = false;

		boolean symbolTMP = false;

		if (this.type.equals("operand")) {

			symbol = false;

		} else if (this.type.equals("symbol")) {

			symbol = true;

		} else if (this.type.equals("expression")) {

			this.symList = new ArrayList<MathTokenSymbol>();

			ArrayList<MathTokenSymbol> symListTMP = new ArrayList<MathTokenSymbol>();

			for (int i = 0; i < this.exprArgs.size(); i++) {

				symbolTMP = this.exprArgs.get(i).getSymbolic();

				symbol = symbol || symbolTMP; // Update symbol

				if (symbolTMP) { // Add Symbols Used In SubExpressions

					symListTMP = this.exprArgs.get(i).getSymbolList();

					for (int j = 0; j < symListTMP.size(); j++) {

						if (!this.symList.contains(symListTMP.get(j))) {

							this.symList.add(symListTMP.get(j));

						}

					}

				}

			}

		}

		return symbol;


	}



	/** Simplify The Expression
	 * @throws WrongCalculationException
	 * @throws WrongInputException
	 *
	 */
	private ArrayList<MathExpr> simplify (ArrayList<MathExpr> operandList) throws WrongCalculationException, WrongInputException {

		ArrayList<MathExpr> tmpList = new ArrayList<MathExpr>();

		for (int i = 0; i < operandList.size(); i++) {

			if (operandList.get(i).getType().equals("operand")) {

				tmpList.add(operandList.get(i));

			} else if (operandList.get(i).getType().equals("symbol")) {

				tmpList.add(operandList.get(i));

			} else {

				if (operandList.get(i).getSymbolic() &&
					this.operator.equals(operandList.get(i).getOperator()) && (
					this.operator.getName().equals("PLUS") ||
					this.operator.getName().equals("BINARY_MINUS") ||
					this.operator.getName().equals("MULT") ||
					this.operator.getName().equals("DIV"))) {

					for (int j = 0; j < operandList.get(i).getExprArgs().size(); j++) {

						tmpList.add(operandList.get(i).getExprArgs().get(j));

					}

				} else {

					tmpList.add(operandList.get(i));

				}

			}

		}

		if (this.symbolic && !this.getType().equals("symbol")) {

			tmpList = this.simplifySym(tmpList);

		}

		return tmpList;

	}


	/** Simplify The Symbolic Expression
	 * @throws WrongCalculationException
	 * @throws WrongInputException
	 *
	 */
	private ArrayList<MathExpr> simplifySym (ArrayList<MathExpr> operandList) throws WrongCalculationException, WrongInputException {

		ArrayList<MathExpr> tmpList = new ArrayList<MathExpr>();
		MathExpr exprTMP;

		for (int i = 0; i < operandList.size(); i++) {

			exprTMP = operandList.get(i);

			if (exprTMP.symbolic) { // Symbolic, Add To The List

				tmpList.add(exprTMP);

			} else { // Not Symbolic, Evaluate

				exprTMP = exprTMP.eval();

				// Multiplication With Zero, Set Whole Expression To Zero
				if (exprTMP.getOperandDouble() == 0 && this.getOperator().equals(Operators.mult())) {

					this.operand = new MathTokenOperand ("0");
					this.operator = null;
					this.symbol = null;
					this.symbolic = false;
					this.symList = null;
					this.type = "operand";

					return null; // No Arg List

				}

				if ((exprTMP.getOperandDouble() == 0 && this.getOperator().equals(Operators.plus())) || // Sum With Zero, Skip
					(exprTMP.getOperandDouble() == 0 && this.getOperator().equals(Operators.minus_b())) || // Subtraction With Zero, Skip
					(exprTMP.getOperandDouble() == 1 && this.getOperator().equals(Operators.mult())) || // Division With One, Skip
					(exprTMP.getOperandDouble() == 1 && this.getOperator().equals(Operators.div()))) { // Multiplication With One, Skip

					continue;

				} else {

					tmpList.add(exprTMP);

				}

			}

		}

		// If Only An Expr Remains For A MultiArg Operator, Lift It Up
		if ( tmpList.size() == 1 &&
			(this.operator.getName().equals("PLUS") ||
			this.operator.getName().equals("BINARY_MINUS") ||
			this.operator.getName().equals("MULT") ||
			this.operator.getName().equals("DIV"))) {

			this.operand = tmpList.get(0).getOperand();
			this.operator = tmpList.get(0).getOperator();
			this.symbol =  tmpList.get(0).getSymbol();
			this.symList =  tmpList.get(0).getSymbolList();
			this.symbolic = tmpList.get(0).getSymbolic();
			this.type = tmpList.get(0).getType();

			return tmpList.get(0).getExprArgs();

		}


		// Pow Semplification
		if ( tmpList.size() == 2 && this.operator.getName().equals("POW") ) {

			if ( !tmpList.get(0).getSymbolic() ) { // Base Is A Number

				if ( tmpList.get(0).getOperandDouble() == 0) { // Base = 0, Result Is A Zero

					this.operand = new MathTokenOperand ("0");
					this.operator = null;
					this.symbol = null;
					this.symbolic = false;
					this.symList = null;
					this.type = "operand";

					return null; // No Arg List

				}

				if ( tmpList.get(0).getOperandDouble() == 1) { // Base = 1, Result Is A 1

					this.operand = new MathTokenOperand ("1");
					this.operator = null;
					this.symbol = null;
					this.symbolic = false;
					this.symList = null;
					this.type = "operand";

					return null;

				}

			}


			if ( !tmpList.get(1).getSymbolic() ) { // Exponent Is A Number

				if ( tmpList.get(1).getOperandDouble() == 0) { // Exponent = 0, Result Is A 1

					this.operand = new MathTokenOperand ("1");
					this.operator = null;
					this.symbol = null;
					this.symbolic = false;
					this.symList = null;
					this.type = "operand";

					return null; // No Arg List

				}

				if ( tmpList.get(1).getOperandDouble() == 1) { // Exponent = 1, Result Is The Base

					this.operand = tmpList.get(0).getOperand();
					this.operator = tmpList.get(0).getOperator();
					this.symbol =  tmpList.get(0).getSymbol();
					this.symList =  tmpList.get(0).getSymbolList();
					this.symbolic = tmpList.get(0).getSymbolic();
					this.type = tmpList.get(0).getType();

					return tmpList.get(0).getExprArgs();

				}

			}

		}

		return tmpList;

	}



	/**
	 * Evaluate Recursively The Mathematical Expression
	 *
	 * @return The Result From The Evaluation Process
	 * @throws WrongCalculationException
	 * @throws WrongInputException
	 */
	public MathExpr eval () throws WrongCalculationException, WrongInputException {

		// Expression To Return
		MathExpr returnValue = null;

		// Evaluated Expression Arguments
		ArrayList<MathExpr> exprEvalList = new ArrayList<MathExpr> ();

		// TMP Operand To Pass To The Final Expression
		MathTokenOperand tmpOperand = null;

		if (this.type.equals("operand")) { // Only An Operand

			if (this.operand.getValue().equals("PI")) {

				tmpOperand = new MathTokenOperand (Double.toString(Math.PI));

			} else {

				tmpOperand = new MathTokenOperand (this.operand.getValue());

			}

		} else if (this.symbolic) {

			throw new WrongCalculationException ("MathExpr Eval()- Evaluating A Symbolic Expression!!!");

		} else { // Full Mathematical Expression

			if (this.exprArgs.size() == 1) { // Unary Operator

				tmpOperand = this.evalUnary();

			} else { // N-ary Operator

				for (int i = 0; i < this.exprArgs.size(); i++) {

					exprEvalList.add(this.exprArgs.get(i).eval());

				}

				tmpOperand = this.evalNary(exprEvalList);

			}

		}

		try {

			// If The Result Is An Integer, Removes The Ending .0
			if (tmpOperand.getValue().length() > 2) {

				if (tmpOperand.getValue().substring(tmpOperand.getValue().length()-2).equals(".0")) {

					tmpOperand = new MathTokenOperand (tmpOperand.getValue().substring(0, tmpOperand.getValue().lastIndexOf('.')));

				}

			}

			returnValue = new MathExpr (tmpOperand);

			this.operand = tmpOperand;

		} catch (WrongExpressionException e) {

			e.printStackTrace();

		}

		return returnValue;

	}


	/**
	 * Evaluates Expression Of The Type:
	 * operator expression_argument
	 *
	 * @return The Result From The Evaluation Process
	 * @throws WrongCalculationException
	 * @throws WrongInputException
	 */
	private MathTokenOperand evalUnary () throws WrongCalculationException, WrongInputException {

		MathExpr tmpValue = this.exprArgs.get(0).eval(); // Get Evaluated Argument Expression

		String tmpOperandValue = tmpValue.getOperand().getValue(); // Get Operand

		Double tmpDoubleValue = Double.parseDouble(tmpOperandValue); // Convert Argument To Double

		MathTokenOperand tmpResult; // Return Value

		if (this.operator.getName().equals("UNARY_MINUS")) { // UNARY MINUS

			tmpDoubleValue = tmpDoubleValue*(-1);

		} else if (this.operator.getName().equals("SQRT")) { // UNARY SQRT

			if (tmpDoubleValue >= 0) { // Argument Must Be Non Negative

				tmpDoubleValue = Math.sqrt(tmpDoubleValue);

			} else {

				throw new WrongCalculationException ("MathExpr Eval()- SQRT argument Must Be Non Negative!!!");

			}

		} else if (this.operator.getName().equals("LOG")) { // UNARY LOG

			if (tmpDoubleValue > 0) { // Argument Must Be Positive

				tmpDoubleValue = Math.log(tmpDoubleValue);

			} else {

				throw new WrongCalculationException ("tMathExpr Eval()- LOG argument Must Be Positive!!!");

			}

		} else if (this.operator.getName().equals("EXP")) { // EXP

			tmpDoubleValue = Math.exp(tmpDoubleValue);

		} else if (this.operator.getName().equals("COS")) { // COSIN

			tmpDoubleValue = Math.cos(tmpDoubleValue);

		} else if (this.operator.getName().equals("SIN")) { // SIN

			tmpDoubleValue = Math.sin(tmpDoubleValue);

		} else if (this.operator.getName().equals("TAN")) { // TAN

			tmpDoubleValue = Math.tan(tmpDoubleValue);

		} else if (this.operator.getName().equals("ARCOS")) { // ARCOS

			if (Math.abs(tmpDoubleValue) < 1) { // Argument Must Be In Range -1, +1

				tmpDoubleValue = Math.acos(tmpDoubleValue);

			} else {

				throw new WrongCalculationException ("MathExpr Eval()- ARCOS argument In Range -1, +1 !!!");

			}

		} else if (this.operator.getName().equals("ARCSIN")) { // ARCSIN

			if (Math.abs(tmpDoubleValue) < 1) {  // Argument Must Be In Range -1, +1

				tmpDoubleValue = Math.asin(tmpDoubleValue);

			} else {

				throw new WrongCalculationException ("MathExpr Eval()- ARCSIN argument In Range -1, +1 !!!");

			}

		} else if (this.operator.getName().equals("ARCTAN")) { // ARCTAN

			tmpDoubleValue = Math.atan(tmpDoubleValue);

		} else if (this.operator.getName().equals("COSH")) { // COSH

			tmpDoubleValue = Math.cosh(tmpDoubleValue);

		} else if (this.operator.getName().equals("SINH")) { // SINH

			tmpDoubleValue = Math.sinh(tmpDoubleValue);

		} else if (this.operator.getName().equals("TANH")) { // TANH

				tmpDoubleValue = Math.tanh(tmpDoubleValue);

		} else if (this.operator.getName().equals("FACT")) { // FACT

			if (tmpDoubleValue >= 0) {

				try {

					tmpDoubleValue =(double) CustomFunctions.fact(Math.round(tmpDoubleValue));

				} catch (WrongInputException e) {

					e.printStackTrace();

				}

			}

		} else {

			throw new WrongCalculationException ("MathExpr Eval()- Unrecognised Operator!!!\n" + this.operator.toString());

		}

		if (Math.abs(tmpDoubleValue) <= precision*Math.nextUp(1.0f) ) {

			if (this.laskPrecision) { // If Lask Precision Is Enabled Do This Approximation

				tmpDoubleValue = 0.;

			}

		}

		tmpResult = new MathTokenOperand (tmpDoubleValue.toString());

		return tmpResult;

	}


	/**
	 * Evaluates Expression Of The Type:
	 * operator expr1 expr2 expr3 ...
	 *
	 * @return The Result From The Evaluation Process
	 * @throws WrongCalculationException
	 * @throws WrongInputException
	 */
	private MathTokenOperand evalNary (ArrayList<MathExpr> exprEvalList) throws WrongCalculationException, WrongInputException {

		// TMP Operand To Pass To The Final Expression
		MathTokenOperand tmpOperand = null;

		// TMP Double Used For Actual Calculations
		Double tmpDoubleValue = 0.0;

		if (this.operator.getName().equals("PLUS")) {

			tmpDoubleValue = Double.parseDouble((exprEvalList.get(0).getOperand().getValue()));

			for (int i = 1; i < exprEvalList.size(); i++) {

				tmpDoubleValue += Double.parseDouble((exprEvalList.get(i).getOperand().getValue()));

			}

			tmpOperand = new MathTokenOperand (tmpDoubleValue.toString());

		}

		if (this.operator.getName().equals("BINARY_MINUS")) {

			tmpDoubleValue = Double.parseDouble((exprEvalList.get(0).getOperand().getValue()));

			for (int i = 1; i < exprEvalList.size(); i++) {

				tmpDoubleValue -= Double.parseDouble((exprEvalList.get(i).getOperand().getValue()));

			}

			System.out.println (tmpDoubleValue.toString());

			tmpOperand = new MathTokenOperand (tmpDoubleValue.toString());

		}

		if (this.operator.getName().equals("MULT")) {

			tmpDoubleValue = Double.parseDouble((exprEvalList.get(0).getOperand().getValue()));

			for (int i = 1; i < exprEvalList.size(); i++) {

				tmpDoubleValue *= Double.parseDouble((exprEvalList.get(i).getOperand().getValue()));

			}

			tmpOperand = new MathTokenOperand (tmpDoubleValue.toString());

		}

		if (this.operator.getName().equals("DIV")) {

			tmpDoubleValue = Double.parseDouble((exprEvalList.get(0).getOperand().getValue()));

			for (int i = 1; i < exprEvalList.size(); i++) {

				// Second Operand Must Be Non Zero
				if (Double.parseDouble((exprEvalList.get(i).getOperand().getValue())) != 0) {

					tmpDoubleValue /= Double.parseDouble((exprEvalList.get(i).getOperand().getValue()));

				} else {

					throw new WrongCalculationException ("MathExpr Eval() - DIV argument Must Be Non Zero!!!");

				}

			}

		}

		if (this.operator.getName().equals("POW")) { // POW

			double base = Double.parseDouble((exprEvalList.get(0).getOperand().getValue()));

			double exponent = Double.parseDouble((exprEvalList.get(1).getOperand().getValue()));

			tmpDoubleValue = Math.pow(base, exponent);

		}

		if (Math.abs(tmpDoubleValue) <= precision*Math.nextUp(1.0f) ) {

			if (this.laskPrecision) { // If Lask Precision Is Enabled Do This Approximation

				tmpDoubleValue = 0.;

			}

		}


		tmpOperand = new MathTokenOperand (tmpDoubleValue.toString());

		return tmpOperand;

	}


	/**
	 *
	 * @return The Result From The Evaluation Process
	 * @throws WrongCalculationException
	 * @throws WrongInputException
	 * @throws WrongExpressionException
	 */
	public MathExpr evalSymbolic (double val) throws WrongCalculationException, WrongInputException, WrongExpressionException {

		MathExpr exprTMP = null;

		if (this.symbolic) {

			Hashtable<MathTokenSymbol,Double> hashTab = new Hashtable<MathTokenSymbol,Double>();


			for (int i = 0; i < this.symList.size(); i++) {

				hashTab.put(this.symList.get(i), new Double(val));

			}

			exprTMP = this.evalSymbolic(hashTab);

		} else {

			exprTMP = this.eval();

		}

		return exprTMP;

	}


	/**
	 *
	 * @return The Result From The Evaluation Process
	 * @throws WrongCalculationException
	 * @throws WrongInputException
	 * @throws WrongExpressionException
	 */
	public MathExpr evalSymbolic (double val, MathTokenSymbol t) throws WrongCalculationException, WrongInputException, WrongExpressionException {

		Hashtable<MathTokenSymbol,Double> hashTab = new Hashtable<MathTokenSymbol,Double>();

		hashTab.put(t, val);

		return this.evalSymbolic(hashTab);

	}


	/**
	 *
	 * @return The Result From The Evaluation Process
	 * @throws WrongCalculationException
	 * @throws WrongInputException
	 * @throws WrongExpressionException
	 */
	public MathExpr evalSymbolic (Hashtable<MathTokenSymbol, Double> hashTab) throws WrongCalculationException, WrongInputException, WrongExpressionException {

		MathExpr exprTMP = null;

		if (this.symbolic) {

			if (hashTab == null) {

				throw new WrongCalculationException ("MathExpr EvalSymbolic()- Null Input Table");

			}

			if (hashTab.size() == 0) {

				throw new WrongCalculationException ("MathExpr EvalSymbolic()- Empty Input Table");

			}

			exprTMP = this;

			for (int i = 0; i < this.symList.size(); i++) {

				double val =(double) hashTab.get(this.symList.get(i));

				exprTMP = exprTMP.substituteSymbol (this.symList.get(i), val);

			}

			if (exprTMP.getSymbolic()) {

				throw new WrongCalculationException ("MathExpr EvalSymbolic()- Missing Values In The Map" + exprTMP.getSymbolList().toString());

			} else {

				exprTMP = exprTMP.eval();

			}

		} else {

			exprTMP = this.eval();

		}

		return exprTMP;

	}


	/**
	 *
	 * @return The Result From The Evaluation Process
	 * @throws WrongInputException
	 * @throws WrongCalculationException
	 * @throws WrongExpressionException
	 */
	public MathExpr substituteSymbol (String symbol, double newVal) throws WrongInputException, WrongCalculationException, WrongExpressionException {

		return this.substituteSymbol(new MathTokenSymbol (symbol), (new MathExpr (new MathTokenOperand ("" + newVal))));

	}


	/**
	 *
	 * @return The Result From The Evaluation Process
	 * @throws WrongInputException
	 * @throws WrongCalculationException
	 * @throws WrongExpressionException
	 */
	public MathExpr substituteSymbol (MathTokenSymbol symbol, double newVal) throws WrongInputException, WrongCalculationException, WrongExpressionException {

		return this.substituteSymbol(symbol, (new MathExpr (new MathTokenOperand ("" + newVal))));

	}

	/**
	 *
	 * @return The Result From The Evaluation Process
	 * @throws WrongInputException
	 * @throws WrongCalculationException
	 * @throws WrongExpressionException
	 */
	public MathExpr substituteSymbol (String symbol, String newVal) throws WrongInputException, WrongCalculationException, WrongExpressionException {

		return this.substituteSymbol (new MathTokenSymbol (symbol), newVal);

	}


	/**
	 *
	 * @return The Result From The Evaluation Process
	 * @throws WrongInputException
	 * @throws WrongCalculationException
	 * @throws WrongExpressionException
	 */
	public MathExpr substituteSymbol (MathTokenSymbol symbol, String newVal) throws WrongInputException, WrongCalculationException, WrongExpressionException {

		MathExpr expr = (new MathParser (newVal, "infix")).getMathExpr();

		return this.substituteSymbol (symbol, expr);

	}


	/**
	 *
	 * @return The Result From The Evaluation Process
	 * @throws WrongInputException
	 * @throws WrongCalculationException
	 */
	public MathExpr substituteSymbol (MathTokenSymbol symbol, MathExpr newVal) throws WrongInputException, WrongCalculationException {

		MathExpr exprRes = null;

		if (symbol == null) {

			throw new WrongInputException ("MathExpr deSym()- Null Symbol");

		}

		String tmpExprString = this.toStringInfix();

		try {

			if (tmpExprString.contains(symbol.getValue())) {

				MathLexer lexer = new MathLexer (tmpExprString, "infix");

				Queue<MathToken> tokenListTMP = lexer.getTokenList();

				MathToken tokenTMP;

				String parserExpr = new String();

				while (!tokenListTMP.emptyQueue()) {

					tokenTMP = tokenListTMP.deQueue();

					if (tokenTMP.equals(symbol)) {

						parserExpr += " ( " + newVal.toStringInfix() + " ) ";

					} else {

						parserExpr += tokenTMP.getValue();

					}

				}

				MathParser parser = new MathParser (parserExpr, "infix");

				exprRes = parser.getMathExpr();

			} else {

				exprRes = (MathExpr) this.clone();

			}

		} catch (WrongExpressionException e) {

			e.printStackTrace();

		}

		return exprRes;

	}


	/**
	 * Derive The Mathematical Expression In Symbol
	 *
	 * @param symbol Variabile In Which Derivating
	 * @return The Derivated Math Expr
	 * @throws WrongExpressionException
	 * @throws WrongInputException
	 * @throws WrongCalculationException
	 */
	public MathExpr derive (MathTokenSymbol symbol) throws WrongExpressionException, WrongInputException, WrongCalculationException {

		if (symbol == null) {

			throw new WrongInputException ("MathExpr- Derive(): Null Input!!!");

		}

		MathExpr deriv;

		if (!this.symbolic) { // Not Symbolic, Return 0

			deriv = new MathExpr (new MathTokenOperand ("0"));

		} else { // Symbolic Expression, Return 0

			if (this.getType().equals("symbol")) { // Expression Is Only A Symbol

				if (this.getSymbol().equals(symbol)) { // Same Symbol, Return 1 (dx/dx = 1)

					deriv = new MathExpr (1);

				} else {  // Different Symbols, Return 0 (dy/dx = 0)

					deriv = new MathExpr (0);

				}

			} else { // Full Symbolic Expression

				if (this.getSymbolList().contains(symbol)) { // Actual Derivation

					if (this.getExprArgs().size() == 1) { // Unary Operator

						deriv = this.deriveUnary (symbol);

					} else { // N-ary Operator

						deriv = this.deriveNary (symbol);

					}

				} else { // Expression Doesn't Contain The Symbol, Return 0

					deriv = new MathExpr (0);

				}

			}

		}

		if (!deriv.symbolic) { // Derivate Not Symbolic, Evaluate It

			deriv = deriv.eval();

		}

		return deriv;

	}



	/**
	 * Derivation Of Unary Operator Expression
	 *
	 * @param symbol Variabile In Which Derivating
	 * @return The Derivated Math Expr
	 * @throws WrongInputException
	 * @throws WrongCalculationException
	 * @throws WrongExpressionException
	 */
	private MathExpr deriveUnary (MathTokenSymbol symbol) throws WrongExpressionException, WrongCalculationException, WrongInputException {

		MathExpr resExpr; // Return Value
		MathExpr tmpExpr; // Tmp Value

		if (this.operator.getName().equals("UNARY_MINUS")) { // UNARY MINUS

			resExpr = new MathExpr (this.operator, this.exprArgs.get(0).derive(symbol));

		} else if (this.operator.getName().equals("SQRT")) { // UNARY SQRT

			// D sqrt( f(x) ) = f'(x) * (1 / 2sqrt(f(x)))

			MathTokenOperator mult = Operators.mult(); // Multiplication Operator
			MathTokenOperator div = Operators.div(); // Division Operator

			MathExpr two = new MathExpr (new MathTokenOperand ("2")); // Two Operand

			tmpExpr = new MathExpr (mult, two, this); // 2* sqrt(expr)

			resExpr = new MathExpr (div, this.exprArgs.get(0).derive(symbol), tmpExpr); // f'(x) * (1 / 2sqrt(f(x)))

		} else if (this.operator.getName().equals("LOG")) { // UNARY LOG

			// D log( f(x) ) = f'(x) / f(x)

			MathTokenOperator div = Operators.div(); // Division Operator

			resExpr = new MathExpr (div, this.exprArgs.get(0).derive(symbol), this.exprArgs.get(0)); // f'(x) / f(x)

		} else if (this.operator.getName().equals("EXP")) { // EXP

			// D exp( f(x) ) = f'(x) * exp( f(x) )

			MathTokenOperator mult = Operators.mult(); // Multiplication Operator

			resExpr = new MathExpr (mult, this.exprArgs.get(0).derive(symbol), this); // f'(x) * exp( f(x) )

		} else if (this.operator.getName().equals("COS")) { // COSIN

			// D cos( f(x) ) = - f'(x) * sin( f(x) )

			MathTokenOperator mult = Operators.mult(); // Multiplication Operator
			MathTokenOperator sin = Operators.sin(); // Sin Operator
			MathTokenOperator umin = Operators.minus_u(); // Unary Minus Operator

			tmpExpr = new MathExpr (sin, this.exprArgs.get(0)); // sin ( f(x) )

			tmpExpr = new MathExpr (mult, this.exprArgs.get(0).derive(symbol), tmpExpr); // f'(x) * sin( f(x) )

			resExpr = new MathExpr (umin, tmpExpr); // - f'(x) * sin( f(x) )

		} else if (this.operator.getName().equals("SIN")) { // SIN

			// D sin ( f(x) ) = f'(x) * cos( f(x) )

			MathTokenOperator mult = Operators.mult(); // Multiplication Operator
			MathTokenOperator cos = Operators.cos(); // Cos Operator

			tmpExpr = new MathExpr (cos, this.exprArgs.get(0)); // cos ( f(x) )

			resExpr = new MathExpr (mult, this.exprArgs.get(0).derive(symbol), tmpExpr); // f'(x) * cos( f(x) )

		} else if (this.operator.getName().equals("TAN")) { // TAN

			// D tan( f(x) ) = f'(x) * (1 + tg^2 ( f(x) ))

			MathTokenOperator pow = Operators.pow(); // Power Operator
			MathTokenOperator plus = Operators.plus(); // Plus Operator
			MathTokenOperator mult = Operators.mult(); // Multiplication Operator

			MathExpr one = new MathExpr (new MathTokenOperand ("1")); // One Operand
			MathExpr two = new MathExpr (new MathTokenOperand ("2")); // Two Operand

			tmpExpr = new MathExpr (pow, this, two); // tg^2 ( f(x) ))

			tmpExpr = new MathExpr (plus, one, tmpExpr); // 1 + tg^2 ( f(x) )

			resExpr = new MathExpr (mult, this.exprArgs.get(0).derive(symbol), tmpExpr); // f'(x) * (1 + tg^2 ( f(x) ))

		} else if (this.operator.getName().equals("ARCOS")) { // ARCOS

			// D arcos( f(x) ) = - f'(x) / sqrt (1 - f(x)^2 )

			MathTokenOperator pow = Operators.pow(); // Power Operator
			MathTokenOperator min_u = Operators.minus_u(); // Unary Minus Operator
			MathTokenOperator min_b = Operators.minus_b(); // Binary Minus Operator
			MathTokenOperator div = Operators.mult(); // Division Operator
			MathTokenOperator sqrt = Operators.sqrt(); // Sqrt Operator

			MathExpr one = new MathExpr (new MathTokenOperand ("1")); // One Operand
			MathExpr two = new MathExpr (new MathTokenOperand ("2")); // Two Operand

			tmpExpr = new MathExpr (pow, this.exprArgs.get(0), two); // f(x)^2

			tmpExpr = new MathExpr (min_b, one, tmpExpr); // 1 - f(x)^2

			tmpExpr = new MathExpr (sqrt, tmpExpr); // sqrt (1 - f(x)^2)

			tmpExpr = new MathExpr (div, this.exprArgs.get(0).derive(symbol), tmpExpr); // f'(x) / sqrt (1 - f(x)^2)

			resExpr = new MathExpr (min_u, tmpExpr); // - f'(x) / sqrt (1 - f(x)^2)

		} else if (this.operator.getName().equals("ARCSIN")) { // ARCSIN

			// D arcsin( f(x) ) = f'(x) / sqrt (1 - f(x)^2 )

			MathTokenOperator pow = Operators.pow(); // Power Operator
			MathTokenOperator min = Operators.minus_b(); // Binary Minus Operator
			MathTokenOperator div = Operators.div(); // Division Operator
			MathTokenOperator sqrt = Operators.sqrt(); //Sqrt Operator

			MathExpr one = new MathExpr (new MathTokenOperand ("1")); // One Operand
			MathExpr two = new MathExpr (new MathTokenOperand ("2")); // Two Operand

			tmpExpr = new MathExpr (pow, this.exprArgs.get(0), two); // f(x)^2

			tmpExpr = new MathExpr (min, one, tmpExpr); // 1 - f(x)^2

			tmpExpr = new MathExpr (sqrt, tmpExpr); // sqrt (1 - f(x)^2)

			resExpr = new MathExpr (div, this.exprArgs.get(0).derive(symbol), tmpExpr); // f'(x) / sqrt (1 - f(x)^2 )

		} else if (this.operator.getName().equals("ARCTAN")) { // ARCTAN

			// D arctg( f(x) ) = f'(x) / (1 + f(x)^2 )

			MathTokenOperator pow = Operators.pow(); // Power Operator
			MathTokenOperator plus = Operators.plus(); // Plus Operator
			MathTokenOperator div = Operators.div(); // Division Operator

			MathExpr one = new MathExpr (new MathTokenOperand ("1")); // One Operand
			MathExpr two = new MathExpr (new MathTokenOperand ("2")); // Two Operand

			tmpExpr = new MathExpr (pow, this.exprArgs.get(0), two); // f(x)^2

			tmpExpr = new MathExpr (plus, one, tmpExpr); // 1 + f(x)^2

			resExpr = new MathExpr (div, this.exprArgs.get(0).derive(symbol), tmpExpr); // f'(x) / (1 + f(x)^2 )

		} else if (this.operator.getName().equals("COSH")) { // COSH

			// D cosh ( f(x) ) = f'(x) * sinh( f(x) )

			MathTokenOperator mult = Operators.mult(); // Multiplication Operator
			MathTokenOperator sinh = Operators.sinh(); // Sinh Operator

			tmpExpr = new MathExpr (sinh, this); // sinh ( f(x) )

			resExpr = new MathExpr (mult, this.exprArgs.get(0).derive(symbol), tmpExpr); // f'(x) * sinh( f(x) )

		} else if (this.operator.getName().equals("SINH")) { // SINH

			// D sinh ( f(x) ) = f'(x) * cosh( f(x) )

			MathTokenOperator mult = Operators.mult(); // Multiplication Operator
			MathTokenOperator cosh = Operators.sinh(); // Cosh Operator

			tmpExpr = new MathExpr (cosh, this); // cosh ( f(x) )

			resExpr = new MathExpr (mult, this.exprArgs.get(0).derive(symbol), tmpExpr); // f'(x) * cosh( f(x) )

		} else if (this.operator.getName().equals("TANH")) { // TANH

			// D tan( f(x) ) = f'(x) * (1 - tanh^2 ( f(x) ))

			MathTokenOperator pow = Operators.pow(); // Power Operator
			MathTokenOperator minus = Operators.minus_b(); // Binary Minus Operator
			MathTokenOperator mult = Operators.mult(); // Multiplication Operator

			MathExpr one = new MathExpr (new MathTokenOperand ("1")); // One Operand
			MathExpr two = new MathExpr (new MathTokenOperand ("2")); // Two Operand

			tmpExpr = new MathExpr (pow, this, two); // tanh^2 ( f(x) ))

			tmpExpr = new MathExpr (minus, one, tmpExpr); // 1 - tanh^2 ( f(x) )

			resExpr = new MathExpr (mult, this.exprArgs.get(0).derive(symbol), tmpExpr); // f'(x) * (1 - tanh^2 ( f(x) ))

		} else {

			throw new WrongCalculationException ("MathExpr- DerivateUnary(): Unrecognised Operator!!!\n" + this.operator.toString());

		}

		return resExpr;

	}



	/**
	 * Derivation Of N-ary Operator Expression
	 *
	 * @param symbol Variabile In Which Derivating
	 * @return The Derivated Math Expr
	 * @throws WrongCalculationException
	 * @throws WrongInputException
	 * @throws WrongExpressionException
	 */
	private MathExpr deriveNary (MathTokenSymbol symbol) throws WrongCalculationException, WrongExpressionException, WrongInputException {

		MathExpr resExpr = null; // Return Value

		System.out.println ();
		System.out.println ("D/D" + symbol.toStringValue() + "  " + this.toString());
		System.out.println ("Operator: " + this.operator.getName());
		System.out.println ("Arg Size: " + this.exprArgs.size());
		System.out.println ();

		if (this.operator.getName().equals("PLUS")
			|| this.operator.getName().equals("BINARY_MINUS")) {

			// D # (f, g, h, ...) = # (Df, Dg, Dh, ...)

			ArrayList<MathExpr> exprList = new ArrayList<MathExpr>();

			for (int i = 0; i < this.getExprArgs().size(); i++) {

				exprList.add(this.getExprArgs().get(i).derive(symbol));

			}

			resExpr = new MathExpr (this.operator, exprList);

		} else if (this.operator.getName().equals("MULT")) {

			MathTokenOperator plus = Operators.plus(); // Plus Operator
			MathExpr f = this.getExprArgs().get(0); // First Argument
			MathExpr Df = f.derive(symbol); // First Argument Derivative

			if (this.getExprArgs().size() == 2) {

				MathExpr g = this.getExprArgs().get(1); // Second Argument
				MathExpr Dg = g.derive(symbol); // Second Argument Derivative

				MathExpr tmp1 = new MathExpr (this.operator, Df, g); // f'*g
				MathExpr tmp2 = new MathExpr (this.operator, f, Dg); // f*g'

				resExpr = new MathExpr (plus, tmp1, tmp2); // f'*g + f*g'

			} else {

				ArrayList<MathExpr> tmpList = new ArrayList<MathExpr>(this.getExprArgs().size() - 1);

				for (int i = 1; i < this.getExprArgs().size(); i++) {

					tmpList.add(this.getExprArgs().get(i));

				}

				MathExpr tmp = new MathExpr (this.operator, tmpList);
				MathExpr Dtmp = tmp.derive(symbol);

				MathExpr tmp1 = new MathExpr (this.operator, Df, tmp); // f'*g
				MathExpr tmp2 = new MathExpr (this.operator, f, Dtmp); // f*g'

				resExpr = new MathExpr (plus, tmp1, tmp2); // f'*g + // f*g'

			}

		} else if (this.operator.getName().equals("DIV")) {

			MathTokenOperator minus = Operators.minus_b(); // Binary Minus Operator
			MathTokenOperator div = Operators.div(); // Division Operator
			MathTokenOperator pow = Operators.pow(); // Power Operator

			MathExpr two = new MathExpr (new MathTokenOperand ("2")); // One Operand

			MathExpr f = this.getExprArgs().get(0); // First Argument
			MathExpr Df = f.derive(symbol); // First Argument Derivative

			if (this.getExprArgs().size() == 2) {

				MathExpr g = this.getExprArgs().get(1); // Second Argument
				MathExpr Dg = g.derive(symbol); // Second Argument Derivative

				MathExpr tmp1 = new MathExpr (this.operator, Df, g); // f'*g
				MathExpr tmp2 = new MathExpr (this.operator, f, Dg); // f*g'
				MathExpr tmp3 = new MathExpr (minus, tmp1, tmp2); // f'*g - f*g'
				MathExpr tmp4 = new MathExpr (pow, g, two); // g^2

				resExpr = new MathExpr (div, tmp3, tmp4); // ( f'*g - f*g' ) / g^2

			} else {

				ArrayList<MathExpr> tmpList = new ArrayList<MathExpr>(this.getExprArgs().size() - 1);

				for (int i = 1; i < this.getExprArgs().size(); i++) {

					tmpList.add(this.getExprArgs().get(i));

				}

				MathExpr tmp = new MathExpr (this.operator, tmpList);
				MathExpr Dtmp = tmp.derive(symbol);

				MathExpr tmp1 = new MathExpr (this.operator, Df, tmp); // f'*g
				MathExpr tmp2 = new MathExpr (this.operator, f, Dtmp); // f*g'
				MathExpr tmp3 = new MathExpr (minus, tmp1, tmp2); // f'*g - f*g'
				MathExpr tmp4 = new MathExpr (pow, tmp, two);  // g^2

				resExpr = new MathExpr (div, tmp3, tmp4); // ( f'*g - f*g' ) / g^2

			}

		} else if (this.operator.getName().equals("POW")) { // POW

			// D a^g = a^g * (Dg)*ln(a)
			if (this.exprArgs.get(0).getType().equals("operand")) {

				MathTokenOperator pow = Operators.pow(); // Power Operator
				MathTokenOperator log = Operators.log(); // Log Operator
				MathTokenOperator mult = Operators.mult(); // Mult Operator

				MathExpr base = this.exprArgs.get(0); // Base, a
				MathExpr exp = this.exprArgs.get(1); // Exponent, g
				MathExpr Dexp = exp.derive(symbol); // Exponent Derivate, Dg

				MathExpr tmp1 = new MathExpr (pow, base, exp); // a^g
				MathExpr tmp2 = new MathExpr (log, base);  // ln(a)
				tmp2 = new MathExpr (mult, Dexp, tmp2);  // (Dg)*ln(a)

				resExpr = new MathExpr (mult, tmp1, tmp2); // a^g * (Dg)*ln(a)

			} else if (this.exprArgs.get(1).getType().equals("operand")) {

				// D f^n = n * f^n-1 * (Df)

				MathTokenOperator pow = Operators.pow(); // Power Operator
				MathTokenOperator minus = Operators.minus_b(); // Binary Minus Operator
				MathTokenOperator mult = Operators.mult(); // Multiplication Operator

				MathExpr f = this.exprArgs.get(0); // Base, f
				MathExpr Df = f.derive(symbol); // Base Derivate, Df
				MathExpr n = this.exprArgs.get(1); // Exponent, n

				if ( (n.getOperandDouble() - 1) < precision ) { // n = 1, Return Df

					resExpr = Df;

				} else {

					MathExpr one = new MathExpr (new MathTokenOperand ("1")); // One Operand

					MathExpr tmp = new MathExpr (minus, n, one); // n-1
					tmp = new MathExpr (pow, f, tmp);  // f^n-1
					tmp = new MathExpr (mult, n, tmp);  // n*f^n-1

					resExpr = new MathExpr (mult, tmp, Df); // n * f^n-1 * (Df)

				}

			} else { // General Case

				// D f^g = f^g * ((Df) * (g/f) + (Dg)*ln(f))

				MathExpr f = this.exprArgs.get(0); // Base, f
				MathExpr Df = f.derive(symbol); // Exponent Derivate, Df
				MathExpr g = this.getExprArgs().get(1); // Exponent, g
				MathExpr Dg = g.derive(symbol); // Exponent Derivate, Dg

				MathTokenOperator plus = Operators.plus(); // Plus Operator
				MathTokenOperator mult = Operators.mult(); // Multiplication Operator
				MathTokenOperator div = Operators.div(); // Div Operator
				MathTokenOperator pow = Operators.pow(); // Power Operator
				MathTokenOperator log = Operators.log(); // Log Operator


				MathExpr tmp1 = new MathExpr (pow, f, g); // f^g

				MathExpr tmp2 = new MathExpr (div, g, f);  // g/f
				tmp2 = new MathExpr (mult, Df, tmp2);  // (Df)*(g/f)

				MathExpr tmp3 = new MathExpr (log, f);  // ln(f)
				tmp3 = new MathExpr (mult, Dg, tmp3);  // (Dg)*ln(f)

				MathExpr tmp4 = new MathExpr (plus, tmp2, tmp3); // (Df) * (g/f) + (Dg)*ln(f)

				resExpr = new MathExpr (mult, tmp1, tmp4); // f^g * ((Df) * (g/f) + (Dg)*ln(f))

			}

		} else {

			throw new WrongCalculationException ("MathExpr- DerivateNary(): Unrecognised Operator!!!\n" + this.operator.toString());

		}


		return resExpr;

	}


	/**
	 * Return A Cloned MathExpr
	 *
	 * @return clone MathExpr
	 */
	public MathExpr clone() {

		MathExpr cloned = null;

		try {

			if (this.type.equals("operand")) {	// Operand

				cloned = new MathExpr (this.operand);

			} else if (this.type.equals("symbol")) { // Symbol

				cloned = new MathExpr (this.symbol);

			} else { // Expression

				cloned = new MathExpr (this.operator, this.exprArgs);

			}

		} catch (WrongInputException | WrongExpressionException | WrongCalculationException e) {

			e.printStackTrace();

		}

		return cloned;

	}


	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		String returnString = new String();

		if (this.type.equals("operand")) {

			returnString = this.operand.getValue();



		} else if (this.type.equals("symbol")) {

			returnString = this.symbol.getValue();

		} else {

			if (this.operator.equals(Operators.plus())
				|| this.operator.equals(Operators.minus_u())
				|| this.operator.equals(Operators.minus_b())
				|| this.operator.equals(Operators.mult())
				|| this.operator.equals(Operators.div())
				|| this.operator.equals(Operators.pow())) {

				returnString ="(";

				for (int i = 0; i < this.exprArgs.size(); i++) {

					returnString += " " + this.exprArgs.get(i).toString() + " " + this.operator.getValue();

				}

				returnString = returnString.substring(0, (returnString.length()-2)); // Removes Last Superfluous Operator

			} else {

				returnString = this.operator.getValue() + "( ";

				if ( this.exprArgs.size() == 1) {

					returnString +=	this.exprArgs.get(0).toString();

				} else {

					for (int i = 0; i < this.exprArgs.size(); i++) {

						returnString +=	this.exprArgs.get(i).toString() + ", ";

					}

				}

			}

			returnString += " )";

		}

		return returnString;

	}


	/**
	 * Returns The Whole Expression In Infix Notation
	 *
	 * @return Infix String
	 */
	public String toStringInfix() {

		return this.toString();

	}


	/**
	 * Returns The Whole Expression In Prefix Notation
	 *
	 * @return Prefix String
	 */
	public String toStringPrefix() {

		String returnString = new String();

		if (this.type.equals("operand")) {

			returnString = this.operand.getValue();

		} else if (this.type.equals("symbol")) {

			returnString = this.symbol.getValue();

		} else {

			returnString = this.operator.getValue() + "( ";

			for (int i = 0; i < this.exprArgs.size(); i++) {

				returnString +=	this.exprArgs.get(i).toStringPrefix() + ", ";

			}

			returnString = returnString.substring(0, (returnString.length()-2));

			returnString += " )";

		}

		return returnString;

	}


	/**
	 * Returns The Whole Expression In Postfix Notation
	 *
	 * @return Postfix String
	 */
	public String toStringPostfix() {

		String returnString = new String();

		if (this.type.equals("operand")) {

			returnString = this.operand.getValue();

		} else if (this.type.equals("symbol")) {

			returnString = this.symbol.getValue();

		} else {

			returnString = "( ";

			for (int i = 0; i < this.exprArgs.size(); i++) {

				returnString +=	this.exprArgs.get(i).toStringPostfix() + ", ";

			}

			returnString = returnString.substring(0, (returnString.length()-2));

			returnString += " )" + this.operator.getValue();

		}

		return returnString;

	}



}
