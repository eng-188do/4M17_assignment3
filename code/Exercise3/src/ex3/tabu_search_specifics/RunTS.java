/**
 * 
 */
package ex3.tabu_search_specifics;

import java.util.Random;

import ex3.RunOptimisationInterface;
import ex3.control_parameters.ControlParamsTS;
import ex3.evolution_strategies_specifics.Population;
import ex3.exceptions.FunctionEvalLimitException;
import ex3.loggers.*;


/**
 * @author eng-188do
 * Runs tabu search.
 */
public class RunTS implements RunOptimisationInterface {

	/* (non-Javadoc)
	 * @see ex3.RunOptimisation#run(java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	public void run(Long seed, String inputFile, String outputFile) {
		//Set up loggers
		FunctionCallLogger log=new FunctionCallLogger(1000); //only 1000 function calls allowed
		TabuLogger logTabu=new TabuLogger();
		
		//set up then read in control parameters
		ControlParamsTS cntrl=new ControlParamsTS();
		cntrl.readInFile(inputFile);
		
		//set up random seed
		Random rnd=new Random(seed);
		
		//set up tabu search member
		LocalSearch tabuSearch=new LocalSearch(rnd,cntrl,log,logTabu);
		
		//run optimisation
		try{
			runOptimisation(tabuSearch);
		} catch (FunctionEvalLimitException e){//we've met our allowed func evals 
			//print out results:
			log.printResults(outputFile);
			logTabu.printResults(outputFile);
		}
	}
	
	private void runOptimisation(LocalSearch tabuSearch) throws FunctionEvalLimitException{
		tabuSearch.initialise(); //Initialise randomly in space
			while (true){ //go through cycles 
				tabuSearch.performOneIterLocalSearch(); //perform one iteration
			}
	}

}
