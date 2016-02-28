package MatrixMathExpr;

import java.util.ArrayList;
import java.util.Hashtable;

import Exceptions.WrongCalculationException;
import Exceptions.WrongExpressionException;
import Exceptions.WrongInputException;
import MathExpr.MathExpr;
import MathExpr.functionMathExpr;
import MathToken.MathTokenOperand;
import MathToken.MathTokenSymbol;
import MathToken.Operators;
import Parser.MathParser;

public class Matrix implements Cloneable {
	
	/** Matrix Elements */
	protected MathExpr[][] matrix;
	
	/** Row Number */
	protected int r;
	
	/** Column Number */
	protected int c;
	
	/** Square Property Flag */
	protected boolean isSquare;
	
	/** Matrix Determinant */
	protected MathExpr det;
	
	/** Matrix Range */
	protected int range;
	
	/** Matrix Transposed */
	public Matrix transpost;
	
	/** Matrix Inverse */
	public Matrix inverse;
	
	/** Boolean Flag, TRUE If Symbolic Matrix*/
	protected boolean symbolic;
	
	/**
	 * Constructor With MathExpr ArrayList Parameter
	 * @param matrixElements
	 * @param rowNumbers
	 * @param columnNumbers
	 * @throws WrongInputException
	 * @throws WrongCalculationException 
	 */
 	public Matrix (ArrayList<MathExpr> matrixElements, int rowNumbers, int columnNumbers) throws WrongInputException, WrongCalculationException {
		
		// Input Checking
		if (rowNumbers <= 0) {
					
			throw new WrongInputException ("Matix()- RowNumbers Must Be A Positive Integer!!!");
					
		}
				
		if (columnNumbers <= 0) {
					
			throw new WrongInputException ("Matix()- ColumnNumbers Must Be A Positive Integer!!!");
					
		}
				
		if (matrixElements == null) {
					
			throw new WrongInputException ("Matix()- matrixElements[] Is Null!!!");
					
		}
				
		if (matrixElements.size() == 0) {
					
			throw new WrongInputException ("Matix()- matrixElements[] Is Empty!!!");
					
		}
				
				
		// Field Instantation And Assignment
		this.r = rowNumbers; // RowNumber
		this.c = columnNumbers; // ColumnNumber
				
		if (this.r == this.c) { // Square Matrix
					
			this.isSquare = true;
					
		} else { // Rectangular Matrix
					
				this.isSquare = false;
					
		}
				
		// Matrix Instantation
		this.matrix = new MathExpr[this.r][this.c];
				
		// Matrix Values Assignment
		for (int i = 0; i < this.r; i++) {
					
			for (int j = 0; j < this.c; j++) {
						
				if (matrixElements.get(j+(i*this.c)) == null) {
							
					throw new WrongInputException ("Matix()- matrixElements[" + i + "[" + j + "] Is Null!!!");
							
				}
				
				if (matrixElements.get(j+(i*this.c)).getSymbolic()) {
					
					this.matrix[i][j] = matrixElements.get(j+(i*this.c));
					
				} else {
					
					this.matrix[i][j] = matrixElements.get(j+(i*this.c)).eval();
					
				}
						
				this.matrix[i][j] = matrixElements.get(j+(i*this.c));
						
			}
					
		}
		
		// Transpose And Inverse Instantation
		this.transpost = null;
		this.inverse = null;
		
		// Symbolic Checking
		this.isSymbolic();
		
		System.out.println (this.toString());				
				
	}
	
	
	/**
	 * Constructor With MathExpr Array Parameter
	 * @param matrixElements
	 * @param rowNumbers
	 * @param columnNumbers
	 * @throws WrongInputException
	 * @throws WrongCalculationException 
	 */
	public Matrix (MathExpr[] matrixElements, int rowNumbers, int columnNumbers) throws WrongInputException, WrongCalculationException {
		
		// Input Checking
		if (rowNumbers <= 0) {
			
			throw new WrongInputException ("Matix()- RowNumbers Must Be A Positive Integer!!!");
			
		}
		
		if (columnNumbers <= 0) {
			
			throw new WrongInputException ("Matix()- ColumnNumbers Must Be A Positive Integer!!!");
			
		}
		
		if (matrixElements == null) {
			
			throw new WrongInputException ("Matix()- matrixElements[] Is Null!!!");
			
		}
		
		if (matrixElements.length == 0) {
			
			throw new WrongInputException ("Matix()- matrixElements[] Is Empty!!!");
			
		}
		
		
		// Field Instantation And Assignment
		this.r = rowNumbers; // RowNumber
		this.c = columnNumbers; // ColumnNumber
		
		if (this.r == this.c) { // Square Matrix
			
			this.isSquare = true;
			
		} else { // Rectangular Matrix
			
			this.isSquare = false;
			
		}
		
		// Matrix Instantation
		this.matrix = new MathExpr[this.r][this.c];
		
		// Matrix Values Assignment
		for (int i = 0; i < this.r; i++) {
			
			for (int j = 0; j < this.c; j++) {
				
				if (matrixElements[j+(i*this.c)] == null) {
					
					throw new WrongInputException ("Matix()- matrixElements[" + i + "[" + j + "] Is Null!!!");
					
				}
				
				if (matrixElements[j+(i*this.c)].getSymbolic()) {
				
					this.matrix[i][j] = matrixElements[j+(i*this.c)];
					
				} else {
					
					this.matrix[i][j] = matrixElements[j+(i*this.c)].eval();
					
				}		
				
			}
			
		}
		
		// Transpose And Inverse Instantation
		this.transpost = null;
		this.inverse = null;
		
		// Symbolic Checking
		this.isSymbolic();		
						
		System.out.println (this.toString());		
		
	}
	
	
	/**
	 * Constructor With Double Array Parameter
	 * @param matrixElements
	 * @param rowNumbers
	 * @param columnNumbers
	 * @throws WrongInputException
	 * @throws WrongCalculationException 
	 * @throws WrongExpressionException 
	 */
	public Matrix (Double[] matrixElements, int rowNumbers, int columnNumbers) throws WrongInputException, WrongCalculationException, WrongExpressionException {
		
		// Input Checking
		if (rowNumbers <= 0) {
			
			throw new WrongInputException ("Matix()- RowNumbers Must Be A Positive Integer!!!");
			
		}
		
		if (columnNumbers <= 0) {
			
			throw new WrongInputException ("Matix()- ColumnNumbers Must Be A Positive Integer!!!");
			
		}
		
		if (matrixElements == null) {
			
			throw new WrongInputException ("Matix()- matrixElements[] Is Null!!!");
			
		}
		
		if (matrixElements.length == 0) {
			
			throw new WrongInputException ("Matix()- matrixElements[] Is Empty!!!");
			
		}
		
		
		// Field Instantation And Assignment
		this.r = rowNumbers; // RowNumber
		this.c = columnNumbers; // ColumnNumber
		
		if (this.r == this.c) { // Square Matrix
			
			this.isSquare = true;
			
		} else { // Rectangular Matrix
			
			this.isSquare = false;
			
		}
		
		// Matrix Instantation
		this.matrix = new MathExpr[this.r][this.c];
		
		// Matrix Values Assignment
		for (int i = 0; i < this.r; i++) {
			
			for (int j = 0; j < this.c; j++) {
				
				if (matrixElements[j+(i*this.c)] == null) {
					
					throw new WrongInputException ("Matix()- matrixElements[" + i + "[" + j + "] Is Null!!!");
					
				}	
				
				this.matrix[i][j] = new MathExpr (matrixElements[j+(i*this.c)]);
					
			}
			
		}
		
		// Transpose And Inverse Instantation
		this.transpost = null;
		this.inverse = null;
		
		// Symbolic Checking
		this.symbolic = true;		
						
		System.out.println (this.toString());		
		
	}
	
	/**
	 * Constructor With Integer Array Parameter
	 * @param matrixElements
	 * @param rowNumbers
	 * @param columnNumbers
	 * @throws WrongInputException
	 * @throws WrongCalculationException 
	 * @throws WrongExpressionException 
	 */
	public Matrix (Integer[] matrixElements, int rowNumbers, int columnNumbers) throws WrongInputException, WrongCalculationException, WrongExpressionException {
		
		// Input Checking
		if (rowNumbers <= 0) {
			
			throw new WrongInputException ("Matix()- RowNumbers Must Be A Positive Integer!!!");
			
		}
		
		if (columnNumbers <= 0) {
			
			throw new WrongInputException ("Matix()- ColumnNumbers Must Be A Positive Integer!!!");
			
		}
		
		if (matrixElements == null) {
			
			throw new WrongInputException ("Matix()- matrixElements[] Is Null!!!");
			
		}
		
		if (matrixElements.length == 0) {
			
			throw new WrongInputException ("Matix()- matrixElements[] Is Empty!!!");
			
		}
		
		
		// Field Instantation And Assignment
		this.r = rowNumbers; // RowNumber
		this.c = columnNumbers; // ColumnNumber
		
		if (this.r == this.c) { // Square Matrix
			
			this.isSquare = true;
			
		} else { // Rectangular Matrix
			
			this.isSquare = false;
			
		}
		
		// Matrix Instantation
		this.matrix = new MathExpr[this.r][this.c];
		
		// Matrix Values Assignment
		for (int i = 0; i < this.r; i++) {
			
			for (int j = 0; j < this.c; j++) {
				
				if (matrixElements[j+(i*this.c)] == null) {
					
					throw new WrongInputException ("Matix()- matrixElements[" + i + "[" + j + "] Is Null!!!");
					
				}	
				
				this.matrix[i][j] = new MathExpr (matrixElements[j+(i*this.c)]);
					
			}
			
		}
		
		// Transpose And Inverse Instantation
		this.transpost = null;
		this.inverse = null;
		
		// Symbolic Checking
		this.symbolic = true;		
						
		System.out.println (this.toString());		
		
	}
	
	
	

	
	/**
	 * @return the matrix
	 */
	public MathExpr[][] getMatrix() {
		
		return this.matrix;
	
	}

	
	/**
	 * @return the r
	 */
	public int getR() {
		
		return this.r;
	
	}


	/**
	 * @return the c
	 */
	public int getC() {
		
		return this.c;
	
	}

	
	/**
	 * @return the det
	 */
	public MathExpr getDet() {
		
		return this.det;
	
	}
	

	/**
	 * @return the range
	 */
	public int getRange() {
		
		return this.range;

	}
	
	
	/**
	 * @return the transposed matrix
	 */
	public Matrix getTranspost() {
		
		return this.transpost;
	
	}
	
	
	/**
	 * @return the inverse matrix
	 */
	public Matrix getInverse() {
		
		return this.inverse;
	
	}
	
	
	
	/**
	 * 
	 * @return TRUE if square matrix
	 */
	public boolean isSquare() {
		
		return this.isSquare;
		
	}
	
	
	/**
	 * 
	 * @return TRUE if symbolic matrix
	 */
	public boolean isSymbolic() {
		
		boolean result = false;
		
		ArrayList<MathExpr> list = this.matrixElementsList();
		
		for (int i = 0; (i < list.size() && !result); i++) {
			
			if (list.get(i).getSymbolic()) {
				
				result = true;
				
			}
			
		}
		
		this.symbolic = result;
		
		return this.symbolic;
		
	}
	
	
	/**
	 * 
	 * @return The matrix elements as arraylist
	 */
	public ArrayList<MathExpr> matrixElementsList () {
		
		ArrayList<MathExpr> matEl = new ArrayList<MathExpr>();
		
		for (int i = 0; i < this.r; i++) {
			
			for (int j = 0; j < this.c; j++) {
				
				matEl.add(this.matrix[i][j]);
				
			}
			
		}
		
		return matEl;
		
	}
	
	
	/**
	 * 
	 * @param row row number
	 * @return row's element as arraylist
	 * @throws WrongInputException
	 */
	public ArrayList<MathExpr> getRowList (int row) throws WrongInputException {
		
		if (row < 0) {
			
			throw new WrongInputException ("Row Must Be Positive");
			
		}
		
		if (row >= this.r) {
			
			throw new WrongInputException ("Row Must Be An Actual Row Number");
			
		}
		
		ArrayList<MathExpr> matEl = new ArrayList<MathExpr>();
		
		for (int i = 0; i < this.r; i++) {
			
			if (i == row) {
			
				for (int j = 0; j < this.c; j++) {
				
					matEl.add(this.matrix[i][j]);
				
				}
				
			}
			
		}
		
		return matEl;
		
	}
	
	
	/**
	 * 
	 * @param column column number
	 * @return column's element as arraylist
	 * @throws WrongInputException
	 */
	public ArrayList<MathExpr> getColumnList (int column) throws WrongInputException {
		
		if (column < 0) {
			
			throw new WrongInputException ("Column Must Be Positive");
			
		}
		
		if (column >= this.c) {
			
			throw new WrongInputException ("Column Must Be An Actual Column Number");
			
		}
		
		ArrayList<MathExpr> matEl = new ArrayList<MathExpr>();
		
		for (int i = 0; i < this.r; i++) {
			
			for (int j = 0; j < this.c; j++) {
				
				if (j == column) {
					
					matEl.add(this.matrix[i][j]);
				
				}
				
			}
			
		}
		
		return matEl;
		
	}
	
	
	/**
	 * 
	 * @return The matrix elements as array
	 */
	public MathExpr[] matrixElementsArray () {
		
		return (MathExpr[]) this.matrixElementsList().toArray();
		
	}
	
	
	/**
	 * 
	 * @param row row number
	 * @return row's element as array
	 * @throws WrongInputException
	 */
	public MathExpr[] getRowArray (int row) throws WrongInputException {
		
		return (MathExpr[]) this.getRowList(row).toArray();
		
	}


	/**
	 * 
	 * @param column column number
	 * @return column's element as arrays
	 * @throws WrongInputException
	 */
	public MathExpr[] getColumnArray (int column) throws WrongInputException {
	
		return (MathExpr[]) this.getColumnList(column).toArray();
	
	}
	
	
	
	/**
	 * 
	 * @param row row number to exclude
	 * @param column column number to exclude
	 * @return the matrix without row row and column column
	 * @throws WrongInputException
	 * @throws WrongCalculationException 
	 */
 	public Matrix minor (int row, int column) throws WrongInputException, WrongCalculationException {
		
		if (row < 0) {
			
			throw new WrongInputException ("Row Must Be Positive");
			
		}
		
		
		if (column < 0) {
			
			throw new WrongInputException ("Column Must Be Positive");
			
		}
		
		if (row >= this.r) {
			
			throw new WrongInputException ("Row Must Be An Actual Row Number");
			
		}

		if (column >= this.c) {
			
			throw new WrongInputException ("Column Must Be An Actual Column Number");
			
		}
		
		
		ArrayList<MathExpr> matEl = new ArrayList<MathExpr>();
		
		for (int i = 0; i < this.r; i++) {
			
			if (i != row) { // Skip Row
			
				for (int j = 0; j < this.c; j++) {
				
					if (j != column) { // Skip Column
					
						matEl.add(this.matrix[i][j]);
						
					}
				
				}
				
			}
			
		}
		
		return (new Matrix (matEl, this.r-1, this.c-1));
		
	}
	
	
 	
 	/**
 	 * 
 	 * @param row1 row number of the first row to swap
 	 * @param row2 row number of the second row to swap
 	 * @return The new matrix with row1 and row2 swapped
 	 * @throws WrongInputException
 	 * @throws WrongCalculationException 
 	 */
	public Matrix swapRow (int row1, int row2) throws WrongInputException, WrongCalculationException {
		
		ArrayList<MathExpr> matEl = new ArrayList<MathExpr>();
		
		if (row1 == row2) {
			
			matEl = this.matrixElementsList();
			
		} else {
		
			ArrayList<MathExpr> row1List = this.getRowList(row1);
		
			ArrayList<MathExpr> row2List = this.getRowList(row2);
		
			for (int i = 0; i < this.r; i++) {
			
				if (i == row1) {
				
					matEl.addAll(row2List);
				
				} else if (i == row2) {
				
					matEl.addAll(row1List);
				
				} else {
			
					matEl.addAll(this.getRowList(i));
			
				}
				
			}
				
		}
		
		return (new Matrix (matEl, this.r, this.c));		
		
	}
	
	
	/**
	 * 
	 * @param row row number of the old row to substitute with the new row
	 * @param newRow new row to add to the matrix
	 * @return The new matrix with newRow in place of the old row
	 * @throws WrongInputException
	 * @throws WrongCalculationException 
	 */
	public Matrix substituteRow (int row, ArrayList<MathExpr> newRow) throws WrongInputException, WrongCalculationException {
		
		if (row < 0) {
			
			throw new WrongInputException ("Column Must Be Positive");
			
		}
		
		if (row >= this.r) {
			
			throw new WrongInputException ("Column Must Be An Actual Column Number");
			
		}
		
		if (newRow == null) {
			
			throw new WrongInputException ("Null New Row");
			
		}
		
		if (newRow.size() != this.r) {
			
			throw new WrongInputException ("New Row Has A Wrong Size");
			
		}
		
		for (int i = 0; i < newRow.size(); i++) {
			
			if (newRow.get(i) == null) {
				
				throw new WrongInputException ("NewRow(" + i + ") Is Null!!!");
				
			}				
			
		}	

		ArrayList<MathExpr> matEl = new ArrayList<MathExpr>();
		
		for (int i = 0; i < this.r; i++) {			
			
			if (i == row) {
				
				matEl.addAll(newRow);
				
			} else {
			
				matEl.addAll(this.getRowList(i));
			
			}
				
		}	
		
		return (new Matrix (matEl, this.r, this.c));		
		
	}
	
	
	/**
	 * 
	 * @param row row number of the old row to substitute with the new row
	 * @param newRow new row to add to the matrix
	 * @return The new matrix with newRow in place of the old row
	 * @throws WrongInputException
	 * @throws WrongCalculationException 
	 */
	public Matrix substituteRow (int row, MathExpr[] newRow) throws WrongInputException, WrongCalculationException {
		
		ArrayList<MathExpr> matEl = new ArrayList<MathExpr>();
		
		for (int i = 0; i < newRow.length; i++) {
			
			if (newRow[i] == null) {
				
				throw new WrongInputException ("Null Element " + i + " In Array New Column");
				
			}
			
			matEl.add(newRow[i]);
			
		}
		
		return this.substituteColumn(row, matEl);
		
	}
	
	
	/**
 	 * 
 	 * @param column1 column number of the first column to swap
 	 * @param column2 column number of the second column to swap
 	 * @return The new matrix with column1 and column2 swapped
 	 * @throws WrongInputException
	 * @throws WrongCalculationException 
 	 */
	public Matrix swapColumn (int column1, int column2) throws WrongInputException, WrongCalculationException {
		
		ArrayList<MathExpr> matEl = new ArrayList<MathExpr>();
		
		if (column1 == column2) {
			
			matEl = this.matrixElementsList();
			
		} else {
		
			ArrayList<MathExpr> column1List = this.getColumnList(column1);
		
			ArrayList<MathExpr> column2List = this.getColumnList(column2);
		
			for (int i = 0; i < this.r; i++) {
				
				for (int j = 0; j < this.c; j++) {
			
					if (j == column1) {
				
						matEl.add(column2List.get(i));
				
					} else if (j == column2) {
				
						matEl.add(column1List.get(i));
				
					} else {
			
						matEl.add(this.matrix[i][j]);
			
					}				
			
				}
				
			}
				
		}
		
		return (new Matrix (matEl, this.r, this.c));		
		
	}
	
	
	
	/**
	 * 
	 * @param column column number of the old column to substitute with the new column
	 * @param newColumn new column to add to the matrix
	 * @return The new matrix with newColumn in place of the old column
	 * @throws WrongInputException
	 * @throws WrongCalculationException 
	 */
	public Matrix substituteColumn (int column, ArrayList<MathExpr> newColumn) throws WrongInputException, WrongCalculationException {
		
		if (column < 0) {
			
			throw new WrongInputException ("Column Must Be Positive");
			
		}
		
		if (column >= this.c) {
			
			throw new WrongInputException ("Column Must Be An Actual Column Number");
			
		}
		
		if (newColumn == null) {
			
			throw new WrongInputException ("Null New Column");
			
		}
		
		if (newColumn.size() != this.c) {
			
			throw new WrongInputException ("New Column Has A Wrong Size");
			
		}
		
		for (int i = 0; i < newColumn.size(); i++) {
			
			if (newColumn.get(i) == null) {
				
				throw new WrongInputException ("NewColumn(" + i + ") Is Null!!!");
				
			}				
			
		}	

		ArrayList<MathExpr> matEl = new ArrayList<MathExpr>();
		
		for (int i = 0; i < this.r; i++) {
			
			for (int j = 0; j < this.c; j++) {
			
				if (j == column) {
				
						matEl.add(newColumn.get(i));
				
				} else {
			
					matEl.add(this.matrix[i][j]);
			
				}
				
			}	
				
		}
		
		return (new Matrix (matEl, this.r, this.c));		
		
	}
	
	
	/**
	 * 
	 * @param column column number of the old column to substitute with the new column
	 * @param newColumn new column to add to the matrix
	 * @return The new matrix with newColumn in place of the old column
	 * @throws WrongInputException
	 * @throws WrongCalculationException 
	 */
	public Matrix substituteColumn (int column, MathExpr[] newColumn) throws WrongInputException, WrongCalculationException {
		
		ArrayList<MathExpr> matEl = new ArrayList<MathExpr>();
		
		for (int i = 0; i < newColumn.length; i++) {
			
			if (newColumn[i] == null) {
				
				throw new WrongInputException ("Null Element " + i + " In Array New Column");
				
			}
			
			matEl.add(newColumn[i]);
			
		}
		
		return this.substituteColumn(column, matEl);
		
	}
	
	
	/**
	 * 
	 * @return The matrix determinant
	 * @throws WrongExpressionException
	 * @throws WrongCalculationException
	 * @throws WrongInputException
	 */
	public MathExpr det () throws WrongExpressionException, WrongCalculationException, WrongInputException {
		
		MathExpr det = null;
		
		if (!this.isSquare) {
			
			det = null;	
			
		} else {
			
			if (this.r == 1) { // det = [a] -> Only Element Of The Matrix
				
				det = this.matrix[0][0];
				
			}
			
			if (this.r == 2) { // det = a*d - b*c
								
				MathExpr a = this.matrix[0][0];
				MathExpr b = this.matrix[0][1];
				MathExpr c = this.matrix[1][0];
				MathExpr d = this.matrix[1][1];
				
				MathExpr ad = new MathExpr (Operators.mult(), a, d);
				MathExpr bc = new MathExpr (Operators.mult(), b, c);
				
				det = (new MathExpr (Operators.minus_b(), ad, bc));
				
			}
			
			if (this.r > 2) {
				
				det = this.detLaplace();
				
			}
			
		}		
		
		if (!det.getSymbolic()) { // If Not Symbolic, Eval
			
			det = det.eval();
			
		}

		this.det = det;
		
		return det;
		
	}
	
	
	/**
	 * 
	 * @return The Determinant computed with laplace algorithm
	 * @throws WrongExpressionException
	 * @throws WrongCalculationException
	 * @throws WrongInputException
	 */
	private MathExpr detLaplace() throws WrongExpressionException, WrongCalculationException, WrongInputException {
		
		MathExpr det = null;
		
		ArrayList<MathExpr> arg = new ArrayList<MathExpr>();
		
		for (int j = 0; j < this.c; j++) {
		
			if (this.matrix[0][j].getType().equals("operand") && this.matrix[0][j].getOperandDouble() == 0) {
			
				arg.add(new MathExpr (new MathTokenOperand ("0")));
				
			} else {
				
				if (j % 2 == 0)
				
					arg.add(new MathExpr (Operators.mult(), this.matrix[0][j], this.minor(0, j).det()));
				
				else {
					
					arg.add(new MathExpr (Operators.minus_u(), new MathExpr (Operators.mult(), this.matrix[0][j], this.minor(0, j).det())));
					
				}
				
			}
		
		}
		
		det = new MathExpr (Operators.plus(), arg);
		
		return det;		
		
	}
	
	
	/**
	 * Transpose The Current Matrix
	 * @return the transposed matrix
	 * @throws WrongInputException
	 * @throws WrongCalculationException 
	 */
	public Matrix transpose () throws WrongInputException, WrongCalculationException {
		
		ArrayList<MathExpr> transposedList = new ArrayList<MathExpr>(this.r*this.c);
		
		ArrayList<MathExpr> colTmp = null;
		
		// Invert Columns With Rows
		for (int i = 0; i < this.c; i++) {
			
			colTmp = this.getColumnList(i);
			
			for (int j = 0; j < this.r; j++) {
				
				transposedList.add(colTmp.get(j));
				
			}
			
		}		
		
		// Swap Row Number With Column Number
		Matrix transposed = new Matrix(transposedList, this.c, this.r);
		
		this.transpost = transposed;
		
		return transposed;
		
	}
	
	
	/**
	 * Invert The Current Matrix
	 * @return the inverted matrix
	 * @throws WrongInputException
	 * @throws WrongCalculationException 
	 * @throws WrongExpressionException 
	 */
	public Matrix invert () throws WrongInputException, WrongExpressionException, WrongCalculationException {
		
		// Det
		MathExpr det = this.det();
		
		// Rectangular Matrix
		if (!this.isSquare) {
		
			throw new WrongCalculationException ("invert() - Rectangular Matrix");
			
		}
		
		// Singular Matrix
		if (!det.checkSymbolic()) {
				
			double detD = det.getOperandDouble();
			
			if (detD == 0) {
			
				throw new WrongCalculationException ("invert() - Singular Matrix");
				
			}
					
		}
		
		// Inverse Matrix Element List
		ArrayList<MathExpr> inverseList = new ArrayList<MathExpr>(this.r*this.c);
		
		// Inverse Of The Determinant
		MathExpr detInv = functionMathExpr.invert(det);
		
		// Compute Inverse Matrix
		if (this.r == 2) {
		
			//       |a   b|
			// A =   |     | 
			//       |c   d| 
			//
			//                         |d   -b|
			// Inv(A)    =  [1/Det(A)] |      | 
			//                         |-c   a| 
			
			
			ArrayList<MathExpr> list = this.matrixElementsList();
						
			ArrayList<MathExpr> tmpList = new ArrayList<MathExpr>(this.r*this.c);
			
			tmpList.add(list.get(3)); // Add d to first position
			tmpList.add(functionMathExpr.mult(list.get(1), new MathExpr(new MathTokenOperand("-1")))); // Add -b to second position
			tmpList.add(functionMathExpr.mult(list.get(2), new MathExpr(new MathTokenOperand("-1")))); // Add -c to third position
			tmpList.add(list.get(0)); // Add a to last position
			
			inverseList = functionMathExpr.multAll(tmpList, detInv);
			
			
		} else { // A.r > 2
			
			// Inv(A)[i][j]  =  [1/Det(A)]*Cof(A)[j][i] 
			//               = [(-1)^(i+j)] * det[M(A,j,i)] * [1/Det(A)]
			//  where M(A,j,i) is the minor of A without row j and column i
			
			MathExpr coeff; // leading coefficient, +1 or -1 times the inverse of the determinant
			
			for (int i = 0; i < this.r; i++) {
				
				for (int j = 0; j < this.c; j++) {
					
					if ( (i+j) % 2 == 0 ) {
						
						coeff = detInv;
						
					} else {
						
						coeff = new MathExpr (Operators.minus_u(), detInv);
						
					}
					
					inverseList.add(functionMathExpr.mult(coeff, this.minor(j, i).det()));
					
				}
				
			}
			
		}
		
		// Inverse Matrix
		Matrix inverse = new Matrix(inverseList, this.c, this.r);
		
		inverse.eval();
		
		this.inverse = inverse;
		
		return inverse;
		
	}
	
	
	/**
	 * Evaluate Every Expression Of The Matrix
	 * @return the evaluated matrix
	 * @throws WrongCalculationException
	 * @throws WrongInputException
	 */
	public Matrix eval () throws WrongInputException, WrongCalculationException {
		
		ArrayList<MathExpr> elTmp = this.matrixElementsList();
		
		ArrayList<MathExpr> el = new ArrayList<MathExpr> (elTmp.size());
				
		for (int i = 0; i < elTmp.size(); i++) {
				
			if (elTmp.get(i).getSymbolic()) {
				
				el.add(elTmp.get(i));
				
			} else {
				
				try {
					
					el.add(elTmp.get(i).eval());
					
				} catch (WrongCalculationException e) {
					
					e.printStackTrace();				
					
				}
				
			}
			
		}
		
		return (new Matrix (el, this.r, this.c));		
		
	}
	
	
	/**
	 * Evaluate Every Expression Of The Matrix
	 * @return the evaluated matrix
	 * @throws WrongCalculationException
	 * @throws WrongInputException
	 * @throws WrongExpressionException 
	 */
	public Matrix evalSymbolic (double y) throws WrongCalculationException, WrongInputException, WrongExpressionException {
		
		ArrayList<MathExpr> elTmp = this.matrixElementsList();
		
		ArrayList<MathExpr> el = new ArrayList<MathExpr> (elTmp.size());
		
		for (int i = 0; i < elTmp.size(); i++) {
			
			el.add(elTmp.get(i).evalSymbolic(y));
			
		}
		
		return (new Matrix (el, this.r, this.c));		
		
	}
	
	
	/**
	 * Evaluate Every Expression Of The Matrix
	 * @return the evaluated matrix
	 * @throws WrongCalculationException
	 * @throws WrongInputException
	 * @throws WrongExpressionException 
	 */
	public Matrix evalSymbolic (Hashtable<MathTokenSymbol, Double> hashTab) throws WrongCalculationException, WrongInputException, WrongExpressionException {
		
		ArrayList<MathExpr> elTmp = this.matrixElementsList();
		
		ArrayList<MathExpr> el = new ArrayList<MathExpr> (elTmp.size());
		
		for (int i = 0; i < elTmp.size(); i++) {
			
			el.add(elTmp.get(i).evalSymbolic(hashTab));
			
		}
		
		return (new Matrix (el, this.r, this.c));		
		
	}
	
	
	/**
	 * Evaluate Every Expression Of The Matrix
	 * @return the evaluated matrix
	 * @throws WrongCalculationException
	 * @throws WrongInputException
	 * @throws WrongExpressionException 
	 */
	public Matrix substituteSymbol (MathTokenSymbol symbol, double newVal) throws WrongCalculationException, WrongInputException, WrongExpressionException {
		
		return this.substituteSymbol(symbol, (new MathExpr (new MathTokenOperand ("" + newVal))));
		
	}
	
	
	/**
	 * 
	 * @return The Result From The Evaluation Process
	 * @throws WrongInputException 
	 * @throws WrongCalculationException 
	 * @throws WrongExpressionException 
	 */
	public Matrix substituteSymbol (String symbol, String newVal) throws WrongInputException, WrongCalculationException, WrongExpressionException {
		
		return this.substituteSymbol (new MathTokenSymbol (symbol), newVal);
		
	}
	
	
	/**
	 * 
	 * @return The Result From The Evaluation Process
	 * @throws WrongInputException 
	 * @throws WrongCalculationException 
	 * @throws WrongExpressionException 
	 */
	public Matrix substituteSymbol (MathTokenSymbol symbol, String newVal) throws WrongInputException, WrongCalculationException, WrongExpressionException {
		
		MathExpr expr = (new MathParser (newVal, "infix")).getMathExpr();
		
		return this.substituteSymbol (symbol, expr);
		
	}
	
	
	/**
	 * 
	 * @return The Result From The Evaluation Process
	 * @throws WrongInputException 
	 * @throws WrongCalculationException 
	 */
	public Matrix substituteSymbol (MathTokenSymbol symbol, MathExpr newVal) throws WrongInputException, WrongCalculationException {
		
		ArrayList<MathExpr> elTmp = this.matrixElementsList();
		
		ArrayList<MathExpr> el = new ArrayList<MathExpr> (elTmp.size());
		
		for (int i = 0; i < elTmp.size(); i++) {
			
			el.add(elTmp.get(i).substituteSymbol(symbol, newVal));
			
		}
		
		return (new Matrix (el, this.r, this.c));
		
	}
	
	
	/**
	 * Derive Every Mathematical Expression In Symbol
	 * 
	 * @param symbol Variabile In Which Derivating
	 * @return The Derivated Matrix
	 * @throws WrongExpressionException 
	 * @throws WrongInputException 
	 * @throws WrongCalculationException 
	 */
	public Matrix derive (MathTokenSymbol symbol) throws WrongExpressionException, WrongInputException, WrongCalculationException {
		
		ArrayList<MathExpr> elTmp = this.matrixElementsList();
		
		ArrayList<MathExpr> el = new ArrayList<MathExpr> (elTmp.size());
		
		for (int i = 0; i < elTmp.size(); i++) {
			
			el.add(elTmp.get(i).derive(symbol));
			
		}
		
		return (new Matrix (el, this.r, this.c));
		
	}
	
	
	/**
	 * Return The Elements As Double Array (If Numbers)
	 * 
	 * @return the elements list as array
	 * @throws WrongExpressionException 
	 * @throws WrongInputException 
	 * @throws WrongCalculationException 
	 */
	public double[] getOperandDouble () throws WrongExpressionException, WrongInputException, WrongCalculationException {
		
		ArrayList<MathExpr> elTmp = this.matrixElementsList();
		
		ArrayList<Double> el = new ArrayList<Double> (elTmp.size());
		
		for (int i = 0; i < elTmp.size(); i++) {
			
			el.add(elTmp.get(i).getOperandDouble());
			
		}
		
		double[] operands = new double[el.size()];
		
		for (int i = 0; i < el.size(); i++) {
			
			operands[i] = el.get(i);
			
		}
		
		return operands;
		
	}
	
	
	/**
	 * Return The Elements As Double Matrix (If Numbers)
	 * 
	 * @return the elements list as matrix
	 * @throws WrongExpressionException 
	 * @throws WrongInputException 
	 * @throws WrongCalculationException 
	 */
	public double[][] getOperandDoubleMatrix() throws WrongExpressionException, WrongInputException, WrongCalculationException {
		
		ArrayList<MathExpr> elTmp = this.matrixElementsList();
		
		ArrayList<Double> el = new ArrayList<Double> (elTmp.size());
		
		for (int i = 0; i < elTmp.size(); i++) {
			
			el.add(elTmp.get(i).getOperandDouble());
			
		}
		
		double[][] matrix = new double[this.r][this.c];
		
		for (int i = 0; i < this.r; i++) {
			
			for (int j = 0; j < this.c; j++) {
				
				matrix[i][j] = el.get(j+(i*this.c));
				
			}
			
		}
		
		return matrix;
		
	}
	
	
	/**
	 * Return The Elements As Double ArrayList (If Numbers)
	 * 
	 * @return the elements list as arraylist
	 * @throws WrongExpressionException 
	 * @throws WrongInputException 
	 * @throws WrongCalculationException 
	 */
	public ArrayList<Double> getOperandDoubleList () throws WrongExpressionException, WrongInputException, WrongCalculationException {
		
		ArrayList<MathExpr> elTmp = this.matrixElementsList();
		
		ArrayList<Double> el = new ArrayList<Double> (elTmp.size());
		
		for (int i = 0; i < elTmp.size(); i++) {
			
			el.add(elTmp.get(i).getOperandDouble());
			
		}
		
		return el;
		
	}
		
	
	/**
	 * Clone With Deep Copy
	 * @return The cloned matrix
	 */
	@Override
	public Matrix clone () {
		
		Matrix cloned = null;
		
		try {
			
			cloned = new Matrix (this.matrixElementsList(), this.r, this.c);
		
		} catch (WrongCalculationException | WrongInputException e) {
			
			e.printStackTrace();
		
		}
		
		return cloned;
		
	}
	
	
	/** 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString () {
		
		String toString = "";
		
		for (int i = 0; i < this.r; i++) {
			
			for (int j = 0; j < this.c; j++) {
				
				toString += this.matrix[i][j].toString() + "  ";
				
			}
			
			toString += "\n";
			
		}
		
		return toString;		
		
	}
	

}
