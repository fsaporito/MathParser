package MathExpr;


import java.util.ArrayList;

import MathToken.MathToken;
import MathToken.MathTokenOperand;
import MathToken.MathTokenOperator;

import Exceptions.WrongExpressionException;

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
	 * Constructor That Creates An Expression Composed An
	 * Operator And One Expression:
	 * - UNITARY_MINUS
	 * 
	 * @param operator Math Operator With Arity One
	 * @param operand Expression Argument
	 * @throws WrongExpressionException
	 */
	public MathExpr (MathTokenOperator operator, MathExpr operandExpr) throws WrongExpressionException {
		
		if (operator == null) {
			
			throw new WrongExpressionException ("Null Operator!!!");
						
		}
		
		if (operandExpr == null) {
			
			throw new WrongExpressionException ("Null Operand!!!");
			
		}
		
		if (operator.getName().equals("UNARY_MINUS")) {
			
			this.operator = operator;
			
			this.exprArgs = new ArrayList<MathExpr> ();
			
			this.exprArgs.add (operandExpr);
			
			this.type = "expression";
			
			
		} else {
			
			throw new WrongExpressionException ("Wrong Operand Numbers For " + operator.getName() + " !!!");
			
		}
		
	}
	
	
	/**
	 * 
	 * Constructor That Creates An Expression Composed An
	 * Operator And One Operand:
	 * - UNITARY_MINUS
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
		
		this.operator = operator;
			
		this.exprArgs = new ArrayList<MathExpr> ();
			
		this.exprArgs.add (operandExpr1);
		
		this.exprArgs.add (operandExpr2);		
		
		this.type = "expression";
		
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
	
	
	
	
	public MathTokenOperand getOperand() {
		
		return operand;
	
	}


	public String getType() {
	
		return type;
	
	}


	public ArrayList<MathExpr> getExprArgs() {
	
		return exprArgs;
	
	}


	public MathTokenOperator getOperator() {
	
		return operator;
	
	}


	
	
	public MathExpr eval () {
		
		MathExpr returnValue = null;
		MathExpr tmpValue = null;
		ArrayList<MathExpr> exprEvalList = new ArrayList<MathExpr> ();
		MathTokenOperand tmpOperand = null;
		String tmpOperandValue = new String();
		Double tmpDoubleValue;
		
		if (this.type.equals("operand")) {
			
			tmpOperand = new MathTokenOperand (this.operand.getValue());
			
		} else {
			
			if (this.operator.getName().equals("UNARY_MINUS")) {
				
				tmpValue = this.exprArgs.get(0).eval();
				
				tmpOperandValue = tmpValue.getOperand().getValue();
				
				if (tmpOperandValue.charAt(0) == '-') {
					
					tmpOperand = (new MathTokenOperand (tmpOperandValue.substring(1)));
					
				} else {
					
					tmpOperand = (new MathTokenOperand ("- " + tmpOperandValue));
					
				}					
				
			} else {
				
				for (int i = 0; i < this.exprArgs.size(); i++) {
					
					exprEvalList.add(this.exprArgs.get(i).eval());
					
				}
				
				
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
					
						tmpDoubleValue += Double.parseDouble((exprEvalList.get(i).getOperand().getValue()));
							
					}
					
					tmpOperand = new MathTokenOperand (tmpDoubleValue.toString());					
					
				}	
				
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
	 * 
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
