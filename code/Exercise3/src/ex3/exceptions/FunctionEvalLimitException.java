/**
 * 
 */
package ex3.exceptions;

/**
 * @author eng-188do
 * Exception for when the limit on the number of function evaluations is met.
 * Use this to easily meet the limit of 1000 function evaluations given in the assignment.
 */
public class FunctionEvalLimitException extends Exception {
	public FunctionEvalLimitException() { super(); }
	public FunctionEvalLimitException(String message) { super(message); }
	public FunctionEvalLimitException(String message, Throwable cause) { super(message, cause); }
	public FunctionEvalLimitException(Throwable cause) { super(cause); }

}
