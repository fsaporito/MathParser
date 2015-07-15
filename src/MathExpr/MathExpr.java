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

import Exceptions.WrongCalculationException;
import Exceptions.WrongExpressionException;
import Exceptions.WrongInputException;

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
			
		} else if (tk.isSymbol()) {
			
			this.symbol =(MathTokenSymbol) tk;
			
			this.type = "symbol";
			
			this.symbolic = true;
			
			this.symList = new ArrayList<MathTokenSymbol>();
			
			this.symList.add(this.symbol);
			
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
	 * 
	 * @param operator Math Operator With Arity One
	 * @param operandExpr Expression Argument
	 * @throws WrongExpressionException
	 */
	public MathExpr (MathTokenOperator operator, MathExpr operandExpr) throws WrongExpressionException {
		
		if (operator == null) {
			
			throw new WrongExpressionException ("MathExpr - Null Operator!!!");
						
		}
		
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
			
			ArrayList<MathExpr> tmpList = new ArrayList<MathExpr> ();
			
			tmpList.add (operandExpr);			
			
			this.exprArgs = this.simplify (tmpList);		
			
			this.symbolic = this.checkSymbolic();
			
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
	 * 
	 * @param operator Math Operator With Arity One
	 * @param operand Math Operand, Operator Argument
	 * @throws WrongExpressionException
	 */
	public MathExpr (MathTokenOperator operator, MathTokenOperand operand) throws WrongExpressionException {
		
		this (operator, (new MathExpr (operand)));
		
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
	 * @throws WrongExpressionException
	 */
	public MathExpr (MathTokenOperator operator, MathExpr operandExpr1, MathExpr operandExpr2) throws WrongExpressionException {
		
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
			
			ArrayList<MathExpr> tmpList = new ArrayList<MathExpr> ();
			
			tmpList.add (operandExpr1);
		
			tmpList.add (operandExpr2);	
			
			this.exprArgs = this.simplify (tmpList);
			
			this.symbolic = this.checkSymbolic();
			
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
	 * 
	 * @param operator Math Operator With Arity Two
	 * @param operand1 First Operand Argument
	 * @param operand2 Second Operand Argument
	 * @throws WrongExpressionException
	 */
	public MathExpr (MathTokenOperator operator, MathTokenOperand operand1, MathTokenOperand operand2) throws WrongExpressionException {
		
		this (operator, (new MathExpr (operand1)), (new MathExpr (operand2)));
		
	}
	
	
	
	/**
	 * 
	 * Constructor That Creates An Expression Composed An
	 * Operator And N Operands:
	 * 
	 * @param operator Math Operator With Arity N
	 * @param operandExprList Arguments List
	 * @throws WrongExpressionException
	 */
	public MathExpr (MathTokenOperator operator, ArrayList<MathExpr> operandExprList) throws WrongExpressionException {
		
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
		
		System.out.println ("------------------------");
			
		this.exprArgs = this.simplify(operandExprList);	
		
		this.symbolic = this.checkSymbolic();
				
		System.out.println (this.toStringPostfix());
		
	}
	
	
	/** Simplify The Expression
	 * 
	 */
	private ArrayList<MathExpr> simplify (ArrayList<MathExpr> operandList) {
		
		ArrayList<MathExpr> tmpList = new ArrayList<MathExpr>();
		
		for (int i = 0; i < operandList.size(); i++) {
		
			if (operandList.get(i).getType().equals("operand")) {
				
				tmpList.add(operandList.get(i));
				
			} else if (operandList.get(i).getType().equals("symbol")) {
				
				tmpList.add(operandList.get(i));
				
			} else {
				
				if (this.operator.equals(operandList.get(i).getOperator()) && (			
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
		
		return tmpList;		
		
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


	
	/**
	 * Evaluate Recursively The Mathematical Expression
	 * 
	 * @return The Result From The Evaluation Process
	 * @throws WrongCalculationException
	 */
	public MathExpr eval () throws WrongCalculationException {
		
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
	 */
	private MathTokenOperand evalUnary () throws WrongCalculationException {
		
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
		
		if (tmpDoubleValue <= precision*Math.nextUp(1.0f) ) {
			
			tmpDoubleValue = 0.;
			
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
	 */
	private MathTokenOperand evalNary (ArrayList<MathExpr> exprEvalList) throws WrongCalculationException {
		
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
			
		tmpOperand = new MathTokenOperand (tmpDoubleValue.toString());					
			
		return tmpOperand;
		
	}

	
	/**
	 * 
	 * @return The Result From The Evaluation Process
	 * @throws WrongCalculationException 
	 * @throws WrongInputException 
	 */
	public MathExpr evalSymbolic (double val) throws WrongCalculationException, WrongInputException {
		
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
	 */
	public MathExpr evalSymbolic (Hashtable<MathTokenSymbol,Double> hashTab) throws WrongCalculationException, WrongInputException {
		
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
			
				exprTMP = this.deSym (exprTMP, this.symList.get(i), val);
				
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
	 */
	public MathExpr deSym (MathExpr exprTMP, MathTokenSymbol symbol, double val) throws WrongInputException {
		
		MathExpr exprRes = null;
		
		if (exprTMP == null) {
			
			throw new WrongInputException ("MathExpr deSym()- Null Math Expression");
			
		}
		
		if (symbol == null) {
			
			throw new WrongInputException ("MathExpr deSym()- Null Symbol");
			
		}
		
		String tmpExprString = exprTMP.toStringInfix();
		
		if (tmpExprString.contains(symbol.getValue())) {
			
			try {
				
				MathLexer lexer = new MathLexer (tmpExprString, "infix");
				
				Queue<MathToken> tokenListTMP = lexer.getTokenList();
				
				MathToken tokenTMP;
				
				String parserExpr = new String();
				
				while (!tokenListTMP.emptyQueue()) {
					
					tokenTMP = tokenListTMP.deQueue();
					
					if (tokenTMP.equals(symbol)) {
						
						tokenTMP = new MathTokenOperand ("" + val);
						
					}
					
					parserExpr += tokenTMP.getValue();					
				}
				
				MathParser parser = new MathParser (parserExpr, "infix");
				
				exprRes = parser.getMathExpr();
			
			} catch (WrongExpressionException e) {

				e.printStackTrace();
				
			}
			
		} else {
			
			exprRes = exprTMP;
			
		}
		
		return exprRes;
		
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
					
				for (int i = 0; i < this.exprArgs.size(); i++) {
				
					returnString +=	this.exprArgs.get(i).toString() + ", ";
				
				}
			
				returnString = returnString.substring(0, (returnString.length()-2));
			
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
