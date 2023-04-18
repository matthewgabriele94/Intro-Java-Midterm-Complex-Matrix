package complexmatrix;

import java.util.ArrayList;
import java.util.Arrays;

import java.io.*;

public class ComplexMatrix {
	
	private int m;
	private int n;
	private Complex[][] cmpMatrix;
	
	public int getNumRows() {
		return this.m;
	}
	
	public int getNumCols() {
		return this.n;
	}
	
	public ComplexMatrix(int m, int n) {
		this.m = m;
		this.n = n;
		this.cmpMatrix = new Complex[m][n];
		for(int i = 0; i < m; i++) {
			Arrays.fill(cmpMatrix[i], new Complex());
		}
	}
	
	public ComplexMatrix(Complex[][] input) {
		this.m = input.length;
		this.n = input[0].length;
		this.cmpMatrix = input;
	}
	
	public ComplexMatrix add(ComplexMatrix cm) throws MatrixDimensionException {
		
		if(this.m != cm.m || this.n != cm.n) {
			throw new MatrixDimensionException("Matrices dimensions are not compatible for this operation.");
		}
		
		ComplexMatrix retCmpMatrix = new ComplexMatrix(this.m, this.n);
		
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				retCmpMatrix.cmpMatrix[i][j] = this.cmpMatrix[i][j].add(cm.cmpMatrix[i][j]);
			}
		}
		return retCmpMatrix;
	}
	
	public ComplexMatrix mult(ComplexMatrix cm) throws MatrixDimensionException {
		
		if(this.n != cm.m) {
			throw new MatrixDimensionException("Matrices dimensions are not compatible for this operation.");
		}
		ComplexMatrix retCmpMatrix = new ComplexMatrix(this.m, cm.n);
		
		for(int i = 0; i < this.m; i++) {
			for(int j = 0; j < cm.n; j++) {
				Complex cmpBase = new Complex();
				int p = 0, q = 0;
				while(p < this.n && q < cm.m) {
					Complex newComp = this.cmpMatrix[i][p].multiply(cm.cmpMatrix[q][j]);
					cmpBase = cmpBase.add(newComp);
					p++;
					q++;
				}
				retCmpMatrix.cmpMatrix[i][j] = retCmpMatrix.cmpMatrix[i][j].add(cmpBase);
			}
		}
		return retCmpMatrix;
	}
	
	public String toString() {
		
		String retString = "";
		
		for(int i = 0; i < this.m; i++) {
			for(int j = 0; j < this.n; j++) {
				retString += this.cmpMatrix[i][j].toString();
				
				if(j != this.n - 1) {
					retString += "\t\t";
				}
				if(j == this.n - 1 && i != m - 1) {
					retString += "\n\n";
				}
			}
		}
		
		return retString;
	}
	
	public static ComplexMatrix read(String filename) throws IncompatibleMatrixException {
		int rowCount = 0;
		int colCount = 0;
		
		FileReader f = null;
		try {
			f = new FileReader(filename);
		} 
    	catch (FileNotFoundException e) {
			System.err.println("file not found: " + e.getMessage());
			System.exit(1);
		}
		
		BufferedReader in = new BufferedReader(f);
		ArrayList<ArrayList<Complex>> arrayRead = new ArrayList<ArrayList<Complex>>();
		try {
			String inString = in.readLine().trim(); 
			while (inString!=null) { 
				String[] nums = inString.split(" ");
				ArrayList<Complex> arrayComp = new ArrayList<Complex>();
				for(int i = 0; i < nums.length; i++) {
					String[] nums2 = nums[i].split("_");
					if(rowCount == 0) {
						colCount = nums.length;
					}
					else {
						if(nums.length != colCount) {
							throw new IncompatibleMatrixException("Matrix rows do not have consistent number of columns.");
						}
					}
					Complex cmpTry = null;
					try {
						cmpTry = new Complex(Double.parseDouble(nums2[0].trim()), Double.parseDouble(nums2[1].trim()));
					}
					catch (NumberFormatException nfe){
						System.err.println("number format problem: " + nfe.getMessage());
					}
					arrayComp.add(cmpTry);
				}
				arrayRead.add(arrayComp);	
				inString = in.readLine();
				rowCount++;
			}

			f.close();
		} 
		catch (IOException e) {
			System.err.println("error reading file: " + e.getMessage());
		}
		ComplexMatrix retCmpMat = new ComplexMatrix(rowCount, colCount);
		for(int i = 0; i < rowCount; i++) {
			for(int j = 0; j < colCount; j++) {
				retCmpMat.cmpMatrix[i][j] = arrayRead.get(i).get(j);
			}
		}
		return retCmpMat;
		
	}
	
	public void write(String filename) {
		PrintWriter out = null;
		try {
			System.out.println("Entering try statement");
			out = new PrintWriter(new FileWriter(filename));
			for(int i = 0; i < this.m; i++) {
				for(int j = 0; j < this.n; j++) {
					out.printf("%.2f_%.2f",this.cmpMatrix[i][j].getReal(),this.cmpMatrix[i][j].getImaginary());
					if(j != this.n - 1) {
						out.print(" ");
					}
					else if(j == this.n - 1 && i != this.m - 1) {
						out.print("\n");
					}
				}
			}
		}
		catch (IndexOutOfBoundsException e) {
            System.err.println("Caught IndexOutOfBoundsException: " +
                                 e.getMessage());
        } 
		catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        } 
		finally {
            if (out != null) {
                System.out.println("Closing PrintWriter");
                out.close();
            } else {
                System.out.println("PrintWriter not open");
            }
        }
	}
	
	public static void main(String[] args) {
		
		//TESTING TO CONFIRM METHODS ARE CORRECT
		
		/*
		
		System.out.println("Read and Write Tests:");
		ComplexMatrix cm1 = new ComplexMatrix(1, 1);
		ComplexMatrix cm2 = new ComplexMatrix(1, 1);
		Complex c1 = new Complex(3, 4);
		Complex c2 = new Complex(2, 3);
		cm1.cmpMatrix[0][0] = c1;
		cm2.cmpMatrix[0][0] = c2;
		ComplexMatrix cm3 = null;
		try {
			cm3 = cm2.add(cm1);
		} catch (MatrixDimensionException e) {
			System.err.println("error: " + e.getMessage());
		}
		//System.out.println(c1.toString());
		System.out.println(cm1.toString());
		System.out.println(cm2.toString());
		System.out.println(cm3.toString());
		
		ComplexMatrix testRead = null;
		try {
			testRead = read("complexmatrix3.txt");
		} catch (IncompatibleMatrixException e) {
			System.err.println("error: " + e.getMessage());
		}
		System.out.println(testRead.toString());
		testRead.write("complexmatrix4.txt");
		
		ComplexMatrix testRead2 = null;
		try {
			testRead2 = read("complexmatrix4.txt");
		} catch (IncompatibleMatrixException e) {
			System.err.println("error: " + e.getMessage());
		}
		System.out.println(testRead2.toString());
		
		System.out.println();
		System.out.println("Matrix Mult Check");
		
		Complex c3 = new Complex(3, 4);
		Complex c4 = new Complex(2, 3);
		Complex c5 = new Complex(1, 2);
		Complex c6 = new Complex(5, 4);
		Complex[][] input1 = new Complex[1][2];
		Complex[][] input2 = new Complex[2][1];
		
		input1[0][0] = c3;
		input1[0][1] = c4;
		input2[0][0] = c5;
		input2[1][0] = c6;
		
		ComplexMatrix cm5 = new ComplexMatrix(input1);
		ComplexMatrix cm6 = new ComplexMatrix(input2);
		
		System.out.println(cm5.toString());
		System.out.println(cm6.toString());
		System.out.println();
		ComplexMatrix cm7 = null;
		try {
			cm7 = cm5.mult(cm6);
		} 
		catch (MatrixDimensionException e) {
			System.err.println("error: " + e.getMessage());
		}
		
		System.out.println(cm7.toString());
		
		System.out.println("Matrix Mult Check #2");
		
		Complex[][] input3 = new Complex[2][2];
		
		input3[0][0] = c3;
		input3[0][1] = c4;
		input3[1][0] = c5;
		input3[1][1] = c6;
		
		ComplexMatrix cm8 = new ComplexMatrix(input3);
		ComplexMatrix cm9 = new ComplexMatrix(input2);
		
		System.out.println(cm8.toString());
		System.out.println(cm9.toString());
		System.out.println();
		ComplexMatrix cm10 = null;
		try {
			cm10 = cm8.mult(cm9);
		} 
		catch (MatrixDimensionException e) {
			System.err.println("error: " + e.getMessage());
		}
		
		System.out.println(cm10.toString());
		
System.out.println("Matrix Mult Check #3");
		
		Complex[][] input4 = new Complex[1][3];
		Complex[][] input5 = new Complex[3][1];
		
		input4[0][0] = c3;
		input4[0][1] = c4;
		input4[0][2] = c4;
		input5[0][0] = c5;
		input5[1][0] = c6;
		input5[2][0] = c6;
		
		ComplexMatrix cm11 = new ComplexMatrix(input4);
		ComplexMatrix cm12 = new ComplexMatrix(input5);
		
		System.out.println(cm8.toString());
		System.out.println(cm9.toString());
		System.out.println();
		ComplexMatrix cm13 = null;
		try {
			cm13 = cm11.mult(cm12);
		} 
		catch (MatrixDimensionException e) {
			System.err.println("error: " + e.getMessage());
		}
		
		System.out.println(cm13.toString());
		
		System.out.println();
		System.out.println("Matrix Mult Broken Check");
		ComplexMatrix cm14 = null;
		try {
			cm14 = cm8.mult(cm12);
		} 
		catch (MatrixDimensionException e) {
			System.err.println("error: " + e.getMessage());
		}
		
		System.out.println();
		System.out.println("Matrix Add Broken Check");
		
		ComplexMatrix cm15 = null;
		try {
			cm15 = cm8.add(cm6);
		} 
		catch (MatrixDimensionException e) {
			System.err.println("error: " + e.getMessage());
		}
		
		System.out.println();
		System.out.println("Constructor Check:");
		
		ComplexMatrix cm16 = new ComplexMatrix(4, 5);
		System.out.println(cm16.toString());
		
		System.out.println();
		System.out.println("Check Broken Read Operation:");
		
		try {
			ComplexMatrix testReadBroken = read("brokenmatrix.txt");
		} 
		catch (IncompatibleMatrixException e) {
			System.err.println("error: " + e.getMessage());
		}
		*/
		
	}
}
