package complexmatrix;

import java.util.ArrayList;
import java.util.Collections;
/*Test*/
public class Complex implements Comparable<Complex>{
	private double a;
	private double b;
	
	public Complex() {
		this.a = 0;
		this.b = 0;
	}
	
	public Complex(double a) {
		this();
		this.a = a;
	}
	
	public Complex(double a, double b) {
		this();
		this.a = a;
		this.b = b;
	}
	
	public double getReal() {
		return this.a;
	}
	
	public void setReal(double a) {
		this.a = a;
	}
	
	public double getImaginary() {
		return this.b;
	}
	
	public void setImaginary(double b) {
		this.b = b;
	}
	
	public String toString() {
		String retString = this.a + " + i" + (this.b);
		return retString;
	}
	
	public double getMagnitude() {
		return Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2)));
	}
	
	public Complex add(Complex c) {
		double realSum = this.a + c.a;
		double imaginarySum = this.b + c.b;
		return new Complex(realSum, imaginarySum);
	}

	public Complex subtract(Complex c) {
		double realDiff = this.a - c.a;
		double imaginaryDiff = this.b - c.b;
		return new Complex(realDiff, imaginaryDiff);
	}
	
	public Complex multiply(Complex c) {
		double realProd = this.a * c.a - this.b * c.b;
		double imaginaryProd = this.a * c.b + this.b * c.a;
		return new Complex(realProd, imaginaryProd);
	}
	
	public Complex divide(Complex c) {
		double realQuotient = (this.a * c.a + this.b * c.b) / (Math.pow(c.a, 2) + Math.pow(c.b, 2));
		double imaginaryQuotient = (this.b * c.a - this.a * c.b) / (Math.pow(c.a, 2) + Math.pow(c.b, 2));
		return new Complex(realQuotient, imaginaryQuotient);
	}
	
	@Override
	public int compareTo(Complex o) {
		if(this.getMagnitude() > o.getMagnitude()){
			return 1;
		}
		else if(this.getMagnitude() < o.getMagnitude()) {
			return -1;
		}
		else {
			return 0;
		}
	
	}
	
	public static void main(String[] args) {
		
		//TESTING TO CONFIRM METHODS ARE CORRECT
		
		/*
		Complex cmp1 = new Complex(3, 4);
		Complex cmp2 = new Complex(3, 5);
		Complex cmp3 = new Complex(3, 2);
		Complex cmp4 = new Complex(3, 3);
		System.out.println(cmp1.toString());
		Complex newcmp = cmp2.add(cmp3);
		System.out.println(newcmp.toString());
		
		System.out.println();
		System.out.println("Magnitude check:");
		System.out.println(cmp1.getMagnitude());
		
		System.out.println();
		System.out.println("toString check and Comparable check:");
		
		ArrayList<Complex> cmpArray = new ArrayList<Complex>();
		cmpArray.add(cmp1);
		cmpArray.add(cmp2);
		cmpArray.add(cmp3);
		cmpArray.add(cmp4);
		
		Collections.sort(cmpArray);
		
		for(Complex i : cmpArray) {
			System.out.println(i.toString());
		}
		
		System.out.println();
		System.out.println("Subtract and Quotient Check");
		Complex cmp5 = cmp2.subtract(cmp3);
		System.out.println(cmp5.toString());
		
		Complex cmp6 = new Complex(5, 1);
		Complex cmp7 = new Complex(3, 1);
		
		Complex cmp8 = cmp6.divide(cmp7);
		
		System.out.println(cmp8.toString());
		
		System.out.println();
		System.out.println("Comp Check #2:");
		
		Complex cmp9 = new Complex(4, 4);
		Complex cmp10 = new Complex(1, 2);
		Complex cmp11 = new Complex(5, 9);
		Complex cmp12 = new Complex(2, 3);
		
		ArrayList<Complex> cmpArray2 = new ArrayList<Complex>();
		cmpArray2.add(cmp9);
		cmpArray2.add(cmp10);
		cmpArray2.add(cmp11);
		cmpArray2.add(cmp12);
		
		for(Complex i : cmpArray2) {
			System.out.println(i.toString());
		}
		System.out.println();
		
		Collections.sort(cmpArray2);
		
		for(Complex i : cmpArray2) {
			System.out.println(i.toString());
		}
		
		System.out.println();
		System.out.println("Getters/Setters Check:");
		
		Complex cmp13 = new Complex();
		Complex cmp14 = new Complex(4);
		Complex cmp15 = new Complex(4, 2);
		
		System.out.println(cmp13.toString());
		System.out.println(cmp14.toString());
		System.out.println(cmp15.toString());
		
		double getRealCheck = cmp15.getReal();
		double getImaginaryCheck = cmp15.getImaginary();
		
		System.out.println(getRealCheck + " " + getImaginaryCheck);
		
		cmp15.setReal(3);
		cmp15.setImaginary(3);
		System.out.println(cmp15.toString());
		
		getRealCheck = cmp15.getReal();
		getImaginaryCheck = cmp15.getImaginary();
		
		System.out.println(getRealCheck + " " + getImaginaryCheck);
		
		System.out.println();
		System.out.println("Other Constructors Check:");
		Complex cmp16 = new Complex();
		System.out.println(cmp16.getReal() + " " + cmp16.getImaginary());
		
		Complex cmp17 = new Complex(4);
		System.out.println(cmp17.getReal() + " " + cmp17.getImaginary());
		*/
	}
	
}
