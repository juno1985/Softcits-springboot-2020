package org.demo.junit.bean;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestMyCalculator {
	
	MyCalculator myCalculator;
	
	@BeforeEach
	public void init() {
		myCalculator = new MyCalculator();
	}
	
	@AfterEach
	public void destroy(){
		System.out.println("Test case completed.");
	}
	
	@Test
	public void testAdd() {
		int result = myCalculator.add(2, 3);
		Assertions.assertEquals(5, result);
	}
	
	@Test
	public void testMinus() {
		int result = myCalculator.minus(5, 2);
		Assertions.assertEquals(3, result);
	}
	
	@Test
	public void testDiv1() {
		double result = myCalculator.div(15, 3);
		Assertions.assertEquals(5, result);
		
	}
	
	@Test
	public void testDiv2() {

		Exception exp = Assertions.assertThrows(RuntimeException.class, () -> myCalculator.div(15, 0));
		
		Assertions.assertEquals("b cannot be 0", exp.getMessage());
		
		//double result = myCalculator.div(15, 0);
	}

}
