/**
 * 
 */
package ex3.loggers;

import java.util.ArrayList;
import java.util.List;

import ex3.file_operations.FileArrayWriter;

/**
 * @author eng-188do
 * Class for storing the Particle Swarm Optimisation results.
 */
public final class BirdLogger implements LoggerInterface {
	//Members
	private List<double[]> birds=new ArrayList<double[]>(); //log of all the birds
				//it will be a double of length 7:
				// with birdnum,x coord, y coord, value, x velocity, y velocity, counter
	
	
	//Methods
	/**
	 * Adds bird to log
	 * @param birdNum unique identifier of bird
	 * @param coords birds coordinates {x,y}
	 * @param val the bird's value
	 * @param vel the birds velocity {xvel,yvel}
	 * @param birdIndividualCounter how many steps has the bird in question taken.
	 */
	public void addMember(int birdNum, double[] coords, double val, double[] vel, int birdIndividualCounter){
		//add values raqther than bird as easier to make sure we aren't taking a reference
		//convert to double if applicable
		double birdNumDoub=(double)birdNum;
		double birdIndividualCounterDoub=(double) birdIndividualCounter;
		
		//group up into array
		double[] input={birdNumDoub, coords[0], coords[1], val, vel[0], vel[1], birdIndividualCounterDoub};
		
		//add to list
		birds.add(input);
	}
	
	/* (non-Javadoc)
	 * @see ex3.Loggers.Logger#printResults(java.lang.String)
	 */
	@Override
	public void printResults(String outputFile) {
		outputFile+="_BirdLogger";
		FileArrayWriter.printOutArray(FileArrayWriter.convertListToDouble(birds), outputFile);
	}

}
