/**
 * 
 */
package ex3.exceptions;

/**
 * @author eng-188do
 * Exception thrown when attribute not found.
 * It is a runtime exception so signifies a problem of my 9the programmer's fault). I have used this mostly to signal
 * when the value of a point has not been calculated when it should have been already and we have run out of function eavluations to
 * evaluate it.
 */
public class AttributeNotCalulatedException extends RuntimeException {
	public AttributeNotCalulatedException(){super();};
	public AttributeNotCalulatedException(String message){super( message);};
	public AttributeNotCalulatedException(String message, Throwable cause){super(message, cause);};
	public AttributeNotCalulatedException(Throwable cause){super( cause);};
}
