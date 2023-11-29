/**
 * 
 */
package com.promineotech.team.dao;

/**
 * This exception is thrown by the DAO object if an error occurs in a database CRUD operation.
 * 
 * @author Promineo
 *
 */
public class MemDbException extends RuntimeException {

  /**
   * This completely useless definition is "required" by Eclipse.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Create the exception from a checked exception.
   * 
   * @param cause The underlying cause
   */
  public MemDbException(Throwable cause) {
    super(cause);
  }

  /**
   * Create the exception with a message and a cause
   * 
   * @param message The message
   * @param cause The underlying cause
   */
  public MemDbException(String message, Throwable cause) {
    super(message, cause);
  }
}
