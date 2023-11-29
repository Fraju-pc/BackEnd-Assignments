/**
 * 
 */
package com.promineotech.tdd;

/**
 * @author Promineo
 *
 */
public class TddParseException extends RuntimeException {

  /**
   * 
   */
  public TddParseException() {
  }

  /**
   * @param message
   */
  public TddParseException(String message) {
    super(message);
  }

  /**
   * @param cause
   */
  public TddParseException(Throwable cause) {
    super(cause);
  }

  /**
   * @param message
   * @param cause
   */
  public TddParseException(String message, Throwable cause) {
    super(message, cause);
    // TODO Auto-generated constructor stub
  }

}
