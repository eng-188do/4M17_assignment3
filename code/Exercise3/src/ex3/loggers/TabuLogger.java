/**
 * 
 */
package ex3.loggers;

import ex3.file_operations.FileArrayWriter;

import java.util.*;

/**
 * @author eng-188do
 *	This class logs the base points in the Tabu search
 */
public class TabuLogger implements LoggerInterface {
	
	//Members
	private List<double[]> basePts=new ArrayList<double[]>(); //log of all the base points
				//this will be a double of length 7:
				// with x,y,val,globalCounter,counter, increment, pattern move (boolean)
	
	
	//Methods
	/**
	 * Adds base point to list
	 * @param x value : x coord (x_1)
	 * @param y value : y coord (x_2)
	 * @param val : (f(x,y))
	 * @param globalCounter which base point is it
	 * @param counter which stage of the local search are we on
	 * @param increment how much local search is moving
	 * @param patternMove was this base point the result of a pattern move?
	 */
	public void addMember(double x, double y, double val, int globalCounter, int counter, double increment, boolean patternMove){
		//convert to double if applicable
		double globalCounterDoub=(double)globalCounter;
		double counterDoub=(double) counter;
		double patternMoveDoub = patternMove ? 1.0 : 0.0;
		
		//group up into array
		double[] input={x, y, val, globalCounterDoub, counterDoub, increment, patternMoveDoub};
		
		//add to list
		basePts.add(input);
	}
	
	/* (non-Javadoc)
	 * @see ex3.Loggers.Logger#printResults(java.lang.String)
	 */
	@Override
	public void printResults(String outputFile) {
		outputFile+="_TabuSearchLogger";
		FileArrayWriter.printOutArray(FileArrayWriter.convertListToDouble(basePts), outputFile);
	}

}
