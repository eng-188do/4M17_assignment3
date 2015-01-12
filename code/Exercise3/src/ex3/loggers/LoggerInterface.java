/**
 * 
 */
package ex3.loggers;

/**
 * @author eng-188do
 * Interface for the logger classes. I want to ensure that they all print out in the same way.
 */
@FunctionalInterface
public interface LoggerInterface {
	/**
	 * Print out log to file specified.
	 * @param outputFile : name stub of file to output to (can append to it the name of the logger).
	 */
	public  void printResults(String outputFile);
}
