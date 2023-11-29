/**
 * 
 */
package com.promineotech.person.dao;

/**
 * This class simply makes the generic RuntimeException specific to the application. The rule of exceptions is throw
 * specific, catch generic. This exception can be caught in a catch block catching MemDbException, RuntimeException,
 * Exception, or (a really bad idea) Throwable. Catching Throwable is **really** bad because it will also catch Errors,
 * which means that the JVM is toast.
 * 
 * @author Promineo
 *
 */
public class MemDbException extends RuntimeException {

  /*
   * This is completely the wrong use for the serialVersionID. It should be a valid version number used in serialization
   * and deserialization. Since we're not doing that (hardly anyone's doing that - there are better ways) this is
   * completely unnecessary. However, Eclipse complains if it's not there...
   */
  private static final long serialVersionUID = -6846676582809947759L;

  /**
   * Creates an exception from the given cause. This turns a checked exception into an unchecked exception.
   * 
   * @param cause The underlying checked exception
   */
  public MemDbException(Throwable cause) {
    super(cause);
  }

  /**
   * Creates an exception with a message and a cause.
   * @param message The message
   * @param cause The cause
   */
  public MemDbException(String message, Throwable cause) {
    super(message, cause);
  }
}
