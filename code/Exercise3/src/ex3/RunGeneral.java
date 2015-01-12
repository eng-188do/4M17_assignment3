/**
 * 
 */
package ex3;

import ex3.evolution_strategies_specifics.RunES;
import ex3.exceptions.UnknownInputParameterException;
import ex3.swarm_specifics.RunSwarm;
import ex3.tabu_search_specifics.RunTS;

/**
 * @author eng-188do
 * Class that holds the main method and is the entry point of our program.
 */
public final class RunGeneral {

	/**
	 * Our main entry for program.
	 * @param args expects 
	 * -seed, (long) for random initialisation
	 * -input filename (csv format) @see ControlParams
	 * -output filename stub. (different logs have different endings ion this)
	 * -and the type of optimisation to perform!:
	 * 		--'ES' for evolutionary strategy 
	 * 		--'TS' for tabu search
	 * 		--'PS' for particle swarm search
	 */
	public static void main(String[] args) {
		RunOptimisationInterface optimiser;
		
		switch (args[3]){
		case "ES": //evolutionary strategy
			optimiser=new RunES();
			break;
		case "TS": //tabu search
			optimiser=new RunTS();
			break;
		case "PS": //particle swarm optimisation.
			optimiser=new RunSwarm();
			break;
		default:
			throw new UnknownInputParameterException("The input argument, " +args[3]+" is unknown.");
		}
		
		optimiser.run(Long.parseLong(args[0]), args[1], args[2]);	//runs the optimisation

		System.out.println("The optimisation has finished");
	}

}
