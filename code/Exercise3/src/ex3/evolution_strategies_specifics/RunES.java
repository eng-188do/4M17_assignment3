/**
 * 
 */
package ex3.evolution_strategies_specifics;

import java.util.*;

import ex3.RunOptimisationInterface;
import ex3.control_parameters.ControlParamsES;
import ex3.exceptions.FunctionEvalLimitException;
import ex3.loggers.FunctionCallLogger;
import ex3.loggers.SurvivingPopLogger;



/**
 * @author eng-188do
 * Class runs the evolution strategy program.
 */
public class RunES implements RunOptimisationInterface {



	/* (non-Javadoc)
	 * @see ex3.RunOptimisation#run(java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	public void run(Long seed, String inputFile, String outputFile) {
		//Set up loggers
		FunctionCallLogger log=new FunctionCallLogger(1000); //only 1000 function calls allowed
		SurvivingPopLogger logSurvPop=new SurvivingPopLogger();
		
		//set up then read in control parameters
		ControlParamsES cntrl=new ControlParamsES();
		cntrl.readInFile(inputFile);
		
		//set up random seed
		Random rnd=new Random(seed);
		
		//set up population
		Population population=new Population(rnd, log, cntrl,logSurvPop);
		
		//run optimisation
		try{
			runOptimisation(population);
		} catch (FunctionEvalLimitException e){//we've met our allowed func evals 
			//print out results:
			log.printResults(outputFile);
			logSurvPop.printResults(outputFile);
		}
	}
	
	private void runOptimisation(Population pop) throws FunctionEvalLimitException{
		pop.initialisePopulation();
			while (true){ //go through cycles 
				pop.mutate(); //mutate
				pop.recombineToFormChildren(); //recombine to generate offspring
				pop.addChildrenToPopulation(); //add these children to the population
				//pop.addChildrenToPopulationKeepParents(); //add these children to the population (elitist)
				pop.assessAndCullPop(); //assess and calualte the survivors of the population.
			}
	}
	
	
	

}
