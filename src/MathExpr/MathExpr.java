package MathExpr;


import java.util.ArrayList;

import MathToken.MathToken;
import MathToken.MathTokenOperand;
import MathToken.MathTokenOperator;

import Exceptions.WrongCalculationException;
import Exceptions.WrongExpressionException;
import Exceptions.WrongInputException;

public class MathExpr {
	
	/** Single Operand Field*/
	private MathTokenOperand operand;
	
	/**
	 *  Expression Type:
	 *  - operand (No Operand)
	 *  - expression (With Operand)
	 *   
	 */
	private String type;
	
	/** Arguments List */
	private ArrayList<MathExpr> exprArgs;
	
	/** Operator */
	private MathTokenOperator operator;
	
	
	
	/**
	 * 
	 * Constructor That Creates An Expression Composed Only By An Operand
	 * 
	 * @param tk Math Operand
	 * @throws WrongExpressionException
	 */
	public MathExpr (MathToken tk) throws WrongExpressionException {
		
		if (tk == null) {
			
			throw new WrongExpressionException ("Null Operand Input!!!");
			
		}
		
		if (tk.isOperand()) {
			
			this.operand =(MathTokenOperand) tk;
			
			this.type = "operand";
			
		} else {
			
			String exceptionString = "An Expression Cannot Be Formed Only By A";
			
			if (tk.isOperator()) {
				
				exceptionString += "n Operator !!!";
				
			}
			
			if (tk.isParenthesis()) {
				
				exceptionString += "Parenthesis !!!";
				
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
			
			throw new WrongExpressionException ("Null Operator!!!");
						
		}
		
		if (operator.getArgNum() != 1) {
			
			throw new WrongExpressionException ("Wrong Operator Number Operator!!!");
						
		}
		
		if (operandExpr == null) {
			
			throw new WrongExpressionException ("Null Operand!!!");
			
		}
		
		
		if (operator.getName().equals("UNARY_MINUS")
			|| operator.getName().equals("UNARY_SQRT")
			|| operator.getName().equals("UNARY_LOG")
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
			
			this.exprArgs = new ArrayList<MathExpr> ();
			
			this.exprArgs.add (operandExpr);
			
			this.type = "expression";			
			
		}  else {
			
			throw new WrongExpressionException ("Unrecognised Operator!!!\n" + operator.toString());
			
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
	 * - MOLT
	 * - DIV
	 * 
	 * @param operator Math Operator With Arity Two
	 * @param operandExpr1 First Argument
	 * @param operandExpr2 Second Argument
	 * @throws WrongExpressionException
	 */
	public MathExpr (MathTokenOperator operator, MathExpr operandExpr1, MathExpr operandExpr2) throws WrongExpressionException {
		
		if (operator == null) {
			
			throw new WrongExpressionException ("Null Operator!!!");
						
		}
		
		if (operandExpr1 == null) {
			
			throw new WrongExpressionException ("Null Operand1!!!");
			
		}
		
		if (operandExpr2 == null) {
			
			throw new WrongExpressionException ("Null Operand2!!!");
			
		}
		
		if (operator.getName().equals("PLUS")
			|| operator.getName().equals("BINARY_MINUS")
			|| operator.getName().equals("MOLT")
			|| operator.getName().equals("DIV")
			|| operator.getName().equals("BINARY_SQRT")
			|| operator.getName().equals("BINARY_LOG")
			|| operator.getName().equals("POW")
			) {
		
			this.operator = operator;
			
			this.exprArgs = new ArrayList<MathExpr> ();
			
			this.exprArgs.add (operandExpr1);
		
			this.exprArgs.add (operandExpr2);		
		
			this.type = "expression";
			
		} else {
			
			throw new WrongExpressionException ("Unrecognised Operator!!!\n" + operator.toString());
			
		}
		
	}
	
	
	
	/**
	 * 
	 * Constructor That Creates An Expression Composed An
	 * Operator And Two Operands:
	 * - PLUS
	 * - BINARY MINUS
	 * - MOLT
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
			
			throw new WrongExpressionException ("Null Operator!!!");
						
		}
		
		if (operandExprList == null) {
			
			throw new WrongExpressionException ("Null Arguments List!!!");
			
		}
		
		if (operandExprList.size() == 0) {
			
			throw new WrongExpressionException ("Empty Arguments List!!!");
			
		}
		
		this.operator = operator;
			
		this.exprArgs = new ArrayList<MathExpr> ();
		
		for (int i = 0; i < operandExprList.size(); i++) {
			
			this.exprArgs.add (operandExprList.get(i));

		}		
		
		this.type = "expression";
		
	}
	
	
	
	/**
	 * Returns The Expression's Operand
	 * In Case The Expression Is Of Type Operand,
	 * NULL Otherwise
	 * 
	 * @return The Operator
	 */
	public MathTokenOperand getOperand() {
		
		if (this.type.equals("operand")) {
		
			return operand; 
			
		} else {
			
			return null;
			
		}
	
	}


	/**
	 * Returns The Expression's Type
	 * 
	 * @return The Type
	 */
	public String getType() {
	
		return type;
	
	}


	/**
	 * Returns An ArrayList With The Expression Arguments
	 * NULL If The Expression Is An Operand
	 * 
	 * @return ArrayList With The Expression Arguments
	 */
	public ArrayList<MathExpr> getExprArgs() {
	
		if (this.type.equals("expression")) {
		
			return exprArgs;
			
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
			
			return operator;
			
		} else {
			
			return null;
			
		}
	
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
			
			tmpOperand = new MathTokenOperand (this.operand.getValue());
			
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
			
		} else if (this.operator.getName().equals("UNARY_SQRT")) { // UNARY SQRT
			
			if (tmpDoubleValue >= 0) { // Argument Must Be Non Negative
				
				tmpDoubleValue = Math.sqrt(tmpDoubleValue);
				
			} else {
				
				throw new WrongCalculationException ("SQRT argument Must Be Non Negative!!!");
				
			}					
	
		} else if (this.operator.getName().equals("UNARY_LOG")) { // UNARY LOG
			
			if (tmpDoubleValue > 0) { // Argument Must Be Positive
				
				tmpDoubleValue = Math.log(tmpDoubleValue);
				
			} else {
				
				throw new WrongCalculationException ("LOG argument Must Be Positive!!!");
				
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
				
				throw new WrongCalculationException ("ARCOS argument In Range -1, +1 !!!");
				
			}
	
		} else if (this.operator.getName().equals("ARCSIN")) { // ARCSIN
			
			if (Math.abs(tmpDoubleValue) < 1) {  // Argument Must Be In Range -1, +1
			
				tmpDoubleValue = Math.asin(tmpDoubleValue);
				
			} else {
				
				throw new WrongCalculationException ("ARCSIN argument In Range -1, +1 !!!");
				
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
			
			throw new WrongCalculationException ("Unrecognised Operator!!!\n" + this.operator.toString());
			
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
		
		if (this.operator.getName().equals("MOLT")) {
			
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
					
					throw new WrongCalculationException ("DIV argument Must Be Non Zero!!!");
					
				}
				
			}
					
		} 
		
		if (this.operator.getName().equals("BINARY_SQRT")) { // BINARY SQRT
				
			double exponent = Double.parseDouble((exprEvalList.get(0).getOperand().getValue()));
					
			double radical = Double.parseDouble((exprEvalList.get(1).getOperand().getValue()));
			
			if (exponent >= 0) { // Exponent Must Be Non Negative
					
				if (exponent%2 == 0) { // Even Exponent
					
					if (radical >= 0) {				
						
						tmpDoubleValue = Math.pow(radical, (1/exponent));
						
					} else {
						
						throw new WrongCalculationException ("SQRT Argument (The Radical) Must Be Non Negative For Even Exponents!!!");
						
					}
				
				} else { // Odd Exponent
					
					tmpDoubleValue = Math.pow(radical, (1/exponent));
					
				}
					
			} else {
					
				throw new WrongCalculationException ("SQRT exponent Must Be Non Negative!!!");
					
			}					
		
		} 

		if (this.operator.getName().equals("BINARY_LOG")) { // BINARY LOG
			
			double base = Double.parseDouble((exprEvalList.get(0).getOperand().getValue()));
			
			double arg = Double.parseDouble((exprEvalList.get(1).getOperand().getValue()));
				
			if (base > 0) { // Base Must Be Positive
				
				if (base != 1) {
					
					if (arg > 0) {
						
						try {
							
							tmpDoubleValue = CustomFunctions.log(base, arg);
						
						} catch (WrongInputException e) {
							
							e.printStackTrace();
						
						}
						
					} else {
						
						throw new WrongCalculationException ("Log Argument Must Be Positive!!!");
						
					}
				
				} else {
					
					throw new WrongCalculationException ("Log Base Mustn't Be 1 !!!");
					
				}				
					
			} else {
					
				throw new WrongCalculationException ("Log Base Must Be Positive!!!");
					
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		String returnString = new String();
		
		if (this.type.equals("operand")) {
		
			returnString = this.operand.getValue();
			
		} else {
			
			returnString = this.operator.getValue() + "( ";
			
			for (int i = 0; i < this.exprArgs.size(); i++) {
				
				returnString +=	this.exprArgs.get(i).toString() + ", ";
				
			}
			
			returnString = returnString.substring(0, (returnString.length()-2));
			
			returnString += " )";
			
		}
		
		return returnString;
	}
	
	

}
