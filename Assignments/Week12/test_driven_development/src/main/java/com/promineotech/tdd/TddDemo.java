/**
 * 
 */
package com.promineotech.tdd;

/**
 * This class demonstrates the use of Test-Driven Development (TDD). The test TddDemoTest was written first and then
 * this class was written. As the test was refactored with different arguments (test cases) this class was modified to
 * address the new needs.
 * 
 * @author Promineo
 *
 */
public class TddDemo {

  /**
   * Trim the zeros off of the left side of a String.
   * 
   * @param valueToTest The value to trim
   * @return A value with the leftmost zeros trimmed off of the String, or "0" if the returned String would have been
   *         empty.
   */
  public String leftTrim(String valueToTest) {
    int pos = 0;

    for (; pos < valueToTest.length(); pos++) {
      char ch = valueToTest.charAt(pos);

      if (!Character.isDigit(ch)) {
        throw new TddParseException("Expected value to be numeric");
      }

      if (ch != '0') {
        break;
      }
    }

    String result = valueToTest.substring(pos);
    return result.isEmpty() ? "0" : result;
  }

}
