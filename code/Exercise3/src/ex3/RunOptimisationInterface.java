/**
 * 
 */
package ex3;

/**
 * @author eng-188do
 * This is the interface for my code. Program should implement this contract.
 */
@FunctionalInterface
public interface RunOptimisationInterface {
	

	
	/**
	 * Runs the optimisation scheme in question
	 * @param seed
	 * @param inputFile
	 * @param outputFile
	 */
	public void run(Long seed, String inputFile, String outputFile);
		
	
}
