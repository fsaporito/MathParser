package ParserTest;

import java.util.ArrayList;

import Exceptions.WrongCalculationException;
import Exceptions.WrongExpressionException;
import Exceptions.WrongInputException;
import MathExpr.MathExpr;
import MathToken.MathTokenOperand;
import MathToken.MathTokenSymbol;
import MatrixMathExpr.Matrix;
import MatrixMathExpr.MatOp;


public class TestMatrix {

	private static int SIZE = 3;
	
	public static void main(String[] args) throws WrongExpressionException, WrongInputException, WrongCalculationException {
		
		MathExpr[] matEl = new MathExpr[SIZE*SIZE];
		
		for (int i = 0; i < SIZE*SIZE; i++) {
			
			matEl[i] = new MathExpr (new MathTokenOperand("" + (i)));
			
		}
		
		Matrix m = new Matrix (matEl, SIZE, SIZE);
		
		// Row Print
		for (int i = 0; i < SIZE; i++) {
		
			System.out.print ("Row(" + i + "): ");
			
			for (int j = 0; j < SIZE; j++) {
				
				System.out.print(" " + m.getRowList(i).get(j));
				
			}
			
			System.out.println ();
				
		}
		
		System.out.println ();
		
		// Column Print
		for (int i = 0; i < SIZE; i++) {
				
			System.out.print ("Column(" + i + "): ");
					
			for (int j = 0; j < SIZE; j++) {
						
				System.out.print(" " + m.getColumnList(i).get(j));
						
			}
					
			System.out.println ();
						
		}
		
		System.out.println ();
		System.out.println ();
		
		//System.out.println ("Det: " + m.det());
		
		System.out.println ("Swap Row 1 and 2");
		m.swapRow(1, 2).toString();
		
		System.out.println ("Swap Column 1 and 2");
		m.swapColumn(1, 2).toString();
		
		MathExpr[] zeroEl = new MathExpr[SIZE*SIZE];
		for (int i = 0; i < SIZE*SIZE; i++) {
			
			zeroEl[i] = new MathExpr (new MathTokenOperand("" + (0)));
			
		}
		
		Matrix zero = new Matrix (zeroEl, SIZE, SIZE);
		
		System.out.println ("Substitute Row 1 with 0 0 0");
		m.substituteRow(0, zero.getRowList(0));
		
		System.out.println ("Substitute Column 0 with 0 0 0");
		m.substituteColumn(0, zero.getColumnList(0));
		
		
		// Determinant
		System.out.println ("#############################");
		System.out.println ("Determinant Numeric");
		System.out.println ("#############################\n");
		
		matEl = new MathExpr[SIZE*SIZE];
		
		for (int i = 0; i < SIZE*SIZE; i++) {
			
			matEl[i] = new MathExpr (new MathTokenOperand("" + (i)));
			
		}
		
		m = new Matrix (matEl, SIZE, SIZE);
		
		System.out.println ("Det: " + m.det() + "\n\n");
		
		System.out.println ("#############################");
		System.out.println ("Determinant Symbolic");
		System.out.println ("#############################\n");
		
		matEl[0] = new MathExpr (new MathTokenSymbol ("x"));
		matEl[4] = new MathExpr (new MathTokenSymbol ("x"));
		matEl[8] = new MathExpr (new MathTokenSymbol ("x"));
		
		m = new Matrix (matEl, SIZE, SIZE);
		
		System.out.println ("Det: " + m.det());
		
		System.out.println ("\n###########################\n\n");
		System.out.println ("Transpose Square Matrix");
		
		m = new Matrix (matEl, SIZE, SIZE);
		
		m.transpose();
		
		System.out.println ("Transpose Rectangular Matrix");
		
		MathExpr[] matElR = new MathExpr[12];
		
		for (int i = 0; i < 12; i++) {
			
			matElR[i] = new MathExpr (new MathTokenOperand("" + (i)));
			
		}
		
		m = new Matrix (matElR, 4, 3);
		
		m.transpose();
		
		System.out.println ("\n###########################\n\n");
		System.out.println ("Matrix Multiply Square");
		
		SIZE = 2;
		
		MathExpr[] matElM = new MathExpr[SIZE*SIZE];
		
		for (int i = 0; i < SIZE*SIZE; i++) {
			
			matElM[i] = new MathExpr (new MathTokenOperand("" + (i)));
			
		}
		
		Matrix m1 = new Matrix (matElM, SIZE, SIZE);
		
		MatOp.multMM(m1, m1).eval();
		
		
		System.out.println ("Matrix Multiply Square");
		
		MathExpr[] matElM1 = new MathExpr[12];
		
		for (int i = 0; i < 12; i++) {
			
			matElM1[i] = new MathExpr (new MathTokenOperand("" + (i)));
			
		}
		
		Matrix mr1 = new Matrix (matElM1, 4, 3);
		
		MathExpr[] matElM2 = new MathExpr[12];
		
		for (int i = 0; i < 12; i++) {
			
			matElM2[i] = new MathExpr (new MathTokenOperand("" + (i)));
			
		}
		
		Matrix mr2 = new Matrix (matElM1, 3, 4);
		
		MatOp.multMM(mr1, mr2).eval();
		
		
		System.out.println ("Matrix Invert");
		
		ArrayList<MathExpr> matElList = new ArrayList<MathExpr>(); 
		
		matElList.add(new MathExpr(1));
		matElList.add(new MathExpr(2));
		matElList.add(new MathExpr(-1));
		matElList.add(new MathExpr(1));
		
		Matrix mi1 = new Matrix (matElList, 2, 2);
		
		System.out.println ("Is Symbolic: " + mi1.isSymbolic());
		
		System.out.println("Inverse: ");
		mi1.invert();
		
		
	}

}