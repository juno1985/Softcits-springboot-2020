package org.demo.junit.bean;

public class MyCalculator {
	
	public int add(int a, int b) {
		int result = a + b;
		return result;
		//System.out.println("a + b = " + result);
	}
	
	public int minus(int a, int b) {
		int result = a - b;
		return result;
		//System.out.println("a - b = " + result);
	}
	
	public double div(int a, int b) {
		if(b == 0 ) throw new RuntimeException();
		return (a * 1.0) / b;
	}
	

}
