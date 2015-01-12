/**
 * 
 */
package ex3.loggers;

import java.util.*;

import ex3.file_operations.FileArrayWriter;

/**
 * @author eng-188do
 * class that logs the surviving population at each generation
 */
public class SurvivingPopLogger implements LoggerInterface {
	
	//Members
	private List<double[]> log=new ArrayList<double[]>(); //arrays will be 4 by 4.

	
	//Methods
	/**
	 * Adds point to log
	 * @param x : x coord (x_1)
	 * @param y : y coord (x_2)
	 * @param val : value found at those coordinates
	 * @param gen : generation number of the point.
	 */
	public void add(double x, double y, double val, int gen){
		double genDoub=(double) gen;
		double[] listEntry={x,y,val,genDoub};
		log.add(listEntry);
	}
	
	/* (non-Javadoc)
	 * @see ex3.Logger#printResults(java.lang.String)
	 */
	@Override
	public void printResults(String outputFile) {
		outputFile+="_SurvivingPopulationLogger";
		FileArrayWriter.printOutArray(FileArrayWriter.convertListToDouble(log), outputFile);

	}
	


}
