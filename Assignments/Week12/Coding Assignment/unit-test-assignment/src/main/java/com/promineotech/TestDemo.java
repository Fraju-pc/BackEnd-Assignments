package com.promineotech;

import java.util.Random;

public class TestDemo {

	
	public int addPositive(int a, int b) {
		if (a <= 0 || b <= 0) {
			throw new IllegalArgumentException("Both parameters must be positive!");
		}
		
		return a + b;
	}
	
	
	public int checkIfEven(int a) {
		if((a % 2) != 0 ) {
			throw new IllegalArgumentException("Parameter must be even!");
		} 
		
		return a % 2;
	}
	
	public int randomNumberSquared() {
		int num1 = getRandomInt();
		int result = num1 * num1;
		return result;
	}


	public int getRandomInt() {
		
		Random random = new Random();
	    return random.nextInt(10) + 1;
	}
}
