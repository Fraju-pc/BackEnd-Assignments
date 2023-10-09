package Week06Curriculum.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Week06Curriculum.Math;

class MathTest {
	Math math = new Math();

	@Test
	void testMultiplyReturnsCorrectValue() {
		int product = math.multiply(2, 5);
		assertEquals(10, product);
	}
	
	@Test
	void testDivideReturnsCorrectValue() {
		double result = math.divide(10, 5);
		assertEquals(2, result);
	}
	
	@Test
	void testIsPositiveReturnsTrueIfArgIsPositive() {
		int a = 5;
		assertTrue(math.isPositive(a));
	}

}
