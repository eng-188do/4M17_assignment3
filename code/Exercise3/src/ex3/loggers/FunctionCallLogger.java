/**
 * 
 */
package ex3.loggers;

import ex3.FunctionDef;
import ex3.exceptions.FunctionEvalLimitException;
import ex3.file_operations.FileArrayWriter;

/**
 * @author eng-188do
 * Holds all the function calls
 */
public class FunctionCallLogger implements LoggerInterface {
	//Constructor
	/**
	 * Constructor
	 * @param maxNumCallsIn : is the maximum number of calls allowed.
	 */
	public FunctionCallLogger(int maxNumCallsIn){
		maxNumCalls=maxNumCallsIn-1; //-1 is just as arrays start at 0 index
		vals= new double[maxNumCallsIn][4]; //1: x, 2: y, 3: value, 4: generation (if applicable)
	}
	
	//Members
	private int counter=-1; //for arrays starting at 0 index.
	private final int maxNumCalls; //maximum number of calls allowed
	private double[][] vals; //stores the log results
	
	
	//Methods
	/**
	 * Gets the fucntion's value and adds it to the log.
	 * Overloaded version of
	 * @see FunctionCallLogger#getValue(double, double, int)
	 * @param x : x coord (x_1)
	 * @param y : y coord (x_2)
	 * @param gen : generation number (only applicable to ES)
	 * @return the function's vallue
	 * @throws FunctionEvalLimitException : thrown when the function evaluation limit is met.
	 */
	public double getValue(double x, double y, int gen) throws FunctionEvalLimitException {
		//NOTE: as we are only calculating at max 1000 values perhaps for ones we have already calculated we can just look it up
		//will discuss this in the report.
		
		double val=getValue(x, y); //Get value and put other items in memory.
		
		//Put generation into memory		
		vals[counter][3]=gen;
		
		return val;		
	}
	

	/**
	 * prints out the log
	 */
	public  void printResults(String outputFile) {
		outputFile+="_FunctionCallLogger";
		FileArrayWriter.printOutArray(vals, outputFile);
		
	}

	/**
	 * Overloaded version of getValue
	 * @see FunctionCallLogger#getValue(double, double, int)
	 */
	public double getValue(double x, double y) throws FunctionEvalLimitException {
		counterIncrement();
		
		//calculate values
		double val=FunctionDef.evalFunc(x, y);
		
		//put values in memory
		vals[counter][0]=x;
		vals[counter][1]=y;
		vals[counter][2]=val;
		return val;
	}
	
	/**
	 * Increment the counter
	 * @throws FunctionEvalLimitException if we have used up our allowed function evaluations an exception is thrown.
	 */
	private void counterIncrement() throws FunctionEvalLimitException {
		if (++counter>maxNumCalls)
			throw new FunctionEvalLimitException();
	}
	


	

}
