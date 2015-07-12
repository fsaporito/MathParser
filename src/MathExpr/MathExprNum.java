package MathExpr;

import java.util.ArrayList;

import Exceptions.WrongExpressionException;
import MathToken.MathToken;
import MathToken.MathTokenOperand;
import MathToken.MathTokenOperator;

public class MathExprNum extends MathExpr {

	public MathExprNum(MathToken tk) throws WrongExpressionException {
		super(tk);
		// TODO Auto-generated constructor stub
	}

	public MathExprNum(MathTokenOperator operator, MathExpr operandExpr)
			throws WrongExpressionException {
		super(operator, operandExpr);
		// TODO Auto-generated constructor stub
	}

	public MathExprNum(MathTokenOperator operator, MathTokenOperand operand)
			throws WrongExpressionException {
		super(operator, operand);
		// TODO Auto-generated constructor stub
	}

	public MathExprNum(MathTokenOperator operator, MathExpr operandExpr1,
			MathExpr operandExpr2) throws WrongExpressionException {
		super(operator, operandExpr1, operandExpr2);
		// TODO Auto-generated constructor stub
	}

	public MathExprNum(MathTokenOperator operator, MathTokenOperand operand1,
			MathTokenOperand operand2) throws WrongExpressionException {
		super(operator, operand1, operand2);
		// TODO Auto-generated constructor stub
	}

	public MathExprNum(MathTokenOperator operator,
			ArrayList<MathExpr> operandExprList)
			throws WrongExpressionException {
		super(operator, operandExprList);
		// TODO Auto-generated constructor stub
	}

}
