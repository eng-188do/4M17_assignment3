/**
 * 
 */
package ex3.exceptions;

import javax.lang.model.UnknownEntityException;

/**
 * @author eng-188do
 * Exception thrown when an unknown input parameter is entered.
 */
public class UnknownInputParameterException extends UnknownEntityException {
	public UnknownInputParameterException(String message){ super(message);};
}
