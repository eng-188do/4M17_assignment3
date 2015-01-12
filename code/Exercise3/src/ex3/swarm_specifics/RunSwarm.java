/**
 * 
 */
package ex3.swarm_specifics;

import java.util.Random;

import ex3.RunOptimisationInterface;
import ex3.control_parameters.ControlParamsS;
import ex3.exceptions.FunctionEvalLimitException;
import ex3.loggers.*;


/**
 * @author eng-188do
 * Class to run the swarm optimisation algorithm.
 */
public class RunSwarm implements RunOptimisationInterface {

	/* (non-Javadoc)
	 * @see ex3.RunOptimisationInterface#run(java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	public void run(Long seed, String inputFile, String outputFile) {
		//Set up loggers
		FunctionCallLogger log=new FunctionCallLogger(1000); //only 1000 function calls allowed
		BirdLogger logBird=new BirdLogger();
		
		//set up then read in control parameters
		ControlParamsS cntrl=new ControlParamsS();
		cntrl.readInFile(inputFile);
		
		//set up random seed
		Random rnd=new Random(seed);
		
		//set up flock
		Flock flock=new Flock(rnd,cntrl,logBird,log);
		
		//run optimisation
		try{
			runOptimisation(flock);
		} catch (FunctionEvalLimitException e){//we've met our allowed func evals 
			//print out results:
			log.printResults(outputFile);
			logBird.printResults(outputFile);
		}

	}
	
	private void runOptimisation(Flock flock) throws FunctionEvalLimitException{
		flock.initialiseFlock(); //initialise randomly in space
			while (true){ //go through cycles 
				flock.updateFlock(); //perform one iteration - update all points and velocities.
			}
	}

}
