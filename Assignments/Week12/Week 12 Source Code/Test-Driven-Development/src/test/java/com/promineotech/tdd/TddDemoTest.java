/**
 * 
 */
package com.promineotech.tdd;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import java.util.Objects;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * This class demonstrates how to use Test-Driven Development (TDD) to produce a test and some application code. Please
 * refer to the accompanying video for a demonstration of the complete process.
 * 
 * The test was developed to produce an application method that would do the following:
 * <ol>
 * <li>Trim all leading zeros off of a numeric value (i.e., "0050" --> "50")
 * <li>If the resulting value is empty, a single zero is returned (i.e., "00000" --> "0")
 * <li>If the value to be trimmed is null, a {@link NullPointerException} is thrown.
 * <li>If the value to be trimmed contains non-numeric characters, a {@link TddParseException} is thrown.
 * </ol>
 * 
 * The process worked like this:
 * <ol>
 * <li>The first test was written to show that trimming "05000" resulted in "5000"
 * <li>A second test was written to show that trimming "000000" resulted in "0";
 * <li>A third test was written to show that trimming "84959" resulted in no change.
 * </ol>
 * At this point it became apparent that tests were being added in which most of the code was duplicated. The only
 * values changing were the initial value and the expected value. It was decided to refactor the tests into a single
 * parameterized test. Once done, the code was cleaned up and additional tests for illegal values were added. This code
 * reflects the end of the process and does not show the evolution of the test. Please refer to the accompanying video
 * for that process.
 * 
 * @author Promineo
 *
 */
class TddDemoTest {

  private TddDemo tddDemo;

  /**
   * @throws java.lang.Exception
   */
  @BeforeEach
  void setUp() {
    tddDemo = new TddDemo();
  }

  /**
   * This parameterized test is for the method {@link TddDemo#leftTrim(String)}. It shows that the leftTrim method
   * correctly handles legal and illegal values.
   * 
   * @param stringToTest The value to trim
   * @param expected The expected result
   * @param exceptionClass If not {@code null}, the test expects an exception of this class to be thrown.
   */
  @ParameterizedTest
  @MethodSource("com.promineotech.tdd.TddDemoTest#argumentsForZeroTrim")
  void assertThatLeftTrimRemovesZerosOnTheLeft(String stringToTest, String expected,
      Class<? extends Exception> exceptionClass) {
    // Given: a numeric string to test

    if (Objects.isNull(exceptionClass)) {
      // When: the method to remove zeros is called
      String actual = tddDemo.leftTrim(stringToTest);

      // Then: the zeros are removed properly
      assertThat(actual).isEqualTo(expected);
    } else {
      // When: the method to remove zeros is called
      // Then: an exception is thrown.
      assertThatThrownBy(() -> tddDemo.leftTrim(stringToTest)).isInstanceOf(exceptionClass);
    }

  }

  /**
   * These arguments are for {@link #assertThatLeftTrimRemovesZerosOnTheLeft(String, String, Class)}. JUnit expects a
   * static method with no parameters to supply the test arguments.
   * 
   * @return A Stream of test arguments
   */
  static Stream<Arguments> argumentsForZeroTrim() {
    // @formatter:off
    return Stream.of(
        arguments("05000", "5000", null),
        arguments("000000", "0", null),
        arguments("84959", "84959", null),
        arguments(null, "", NullPointerException.class),
        arguments("abc", "", TddParseException.class),
        arguments("-15", "", TddParseException.class)
    );
    // @formatter:on
  }

}
