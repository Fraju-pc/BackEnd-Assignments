package com.promineotech;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import org.junit.jupiter.params.provider.MethodSource;

class TestDemoJUnitTest {

	private TestDemo testDemo;

	@BeforeEach
	void setUp() throws Exception {
		testDemo = new TestDemo();
	}

	//Testing that our method adds 2 positive integers correctly
	@ParameterizedTest
	@MethodSource("com.promineotech.TestDemoJUnitTest#argumentsForAddPositive")
	protected void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, boolean expectException) {

		//We are expecting the method to not throw and exception and run the test
		if (!expectException) {
			assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
		} 
		//Exception to catch a number that is not a positive integer
		else {
			assertThatThrownBy(() -> testDemo.addPositive(a, b)).isInstanceOf(IllegalArgumentException.class);
		}

	}

	// Arguments for add Positive
	static Stream<Arguments> argumentsForAddPositive() {
		// @formatter:off
	    return Stream.of(
	        arguments(2, 4, 6, false),
	        arguments(4, 6, 10, false), 
	        arguments(1000, 1000, 2000, false),
	        arguments(500, 600, 1100, false),
	        arguments(9, 1000000000, 1000000009, false),
	        arguments(2, -1, 6, true)
	    );
	    // @formatter:on
	}

	@Test
	void assertThatPairsOfPositiveNumbersAreAddedCorrectly() {

		assertThat(testDemo.addPositive(4, 5)).isEqualTo(9);

		assertThat(testDemo.addPositive(40, 50)).isEqualTo(90);
	}

	// Testing whether or not an integer is even
	@ParameterizedTest
	@MethodSource("com.promineotech.TestDemoJUnitTest#argumentsForassertThatAnIntegerIsEvenOrOdd")
	protected void assertThatAnIntegerIsEvenOrOdd(int a, boolean notExpectingException) {

		// Were expecting it to be true, if not it will throw an exception.
		if (notExpectingException) {
			assertThat(testDemo.checkIfEven(a)).isEqualTo(0);

		} else {
			// Exception catch if a % 2 is other than 0
			assertThatThrownBy(() -> testDemo.checkIfEven(a)).isInstanceOf(IllegalArgumentException.class);
		}

	}

	// Arguments for assertThatAnIntegerIsEvenOrOdd test method
	static Stream<Arguments> argumentsForassertThatAnIntegerIsEvenOrOdd() {
		// @formatter:off
	    return Stream.of(
	        arguments(2, true),
	        arguments(4, true), 
	        arguments(1000, true),
	        arguments(500, true),
	        arguments(9, false)
	    );
	    // @formatter:on
	}
	
	//Method to check if an integer is being properly squared
	@Test
	void assertThatNumberSquaredIsCorrect() {
		
		//Spy the class so we can mock the methods
		TestDemo mockDemo = spy(testDemo);
		
		//force the getRandomInt method to use a particular value so we can test properly
		doReturn(5).when(mockDemo).getRandomInt();	
		int fiveSquared = mockDemo.randomNumberSquared();
	
		//Check if 5 squared returns 25
		assertThat(fiveSquared).isEqualTo(25);
		
		
		
	}
}
