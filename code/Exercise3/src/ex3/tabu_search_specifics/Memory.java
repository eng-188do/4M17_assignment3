/**
 * 
 */
package ex3.tabu_search_specifics;

import java.util.*;

import ex3.control_parameters.ControlParamsTS;
import ex3.coordinate_holder.Vector2;
import ex3.exceptions.FunctionEvalLimitException;
import ex3.loggers.FunctionCallLogger;
import ex3.loggers.TabuLogger;

/**
 * @author eng-188do
 * This function controls the 3 memories and makes sure when making function evaluations that they are not tabu.
 */
public class Memory {
	//Constructor
	/**
	 * Constructor
	 * @param rndIn : random number generator
	 * @param logIn : tabu logger for base points
	 * @param cntrl : control paramerters
	 */
	Memory(Random rndIn, TabuLogger logIn, ControlParamsTS cntrl){
		rnd=rndIn;
		
		//& set up the memories:
		mtm=new MediumTermMemory(cntrl.getMtmSize(), rnd);
		stm=new ShortTermMemory(cntrl.getStmSize());
		ltm=new SearchDiversifier(rnd);
	}
	
	//Members
	private Random rnd; //random number generator
	private FunctionCallLogger fn; //function logger and evaluator
	private SearchDiversifier ltm; //long term memory
	private MediumTermMemory mtm; //medium term memory
	private ShortTermMemory stm; //short term memory

	
	//Methods
	/**
	 * This gets the value for a potential base point and adds it to the relevant memories.
	 * @param point we work on this point. 
	 * @return false if point is tabu and 
	 * @throws FunctionEvalLimitException thrown if the point is tabu!
	 * 
	 */
	public void addBasePt(Vector2 point) throws FunctionEvalLimitException{
		if (stm.checkIfMember(point))
			throw new IllegalArgumentException("this point should not be being made a base point: it is tabu!"); 
		
		point.getValue(); //make sure that the value is calculated
		stm.addPoint(point);
		mtm.addPoint(point); //should already have been added as part of the potential points but if not we can just add it again
		ltm.addPt(point.getCoordX(), point.getCoordY());
		
	}
	
	/**
	 * Adds potential point to the memory and if it is tabu it will return false
	 * @param point
	 * @return true if not tabu
	 * @throws FunctionEvalLimitException
	 */
	public boolean addPotentialPoint(Vector2 point) throws FunctionEvalLimitException {
		if(stm.checkIfMember(point))
			return false; //this is tabu
		
		point.getValue(); //make sure we evaluate the points value
		mtm.addPoint(point); //gets potentially added to the medium term memory regardless of whether we go there or not
		return true; //if we have got here then not tabu
	}
	
	/**
	 * Give coordinates for intensifying
	 * @return {x,y}
	 */
	public double[] intensify(){
		return mtm.averageBest();
	}
	
	
	/**
	 * Get new coordinates for diversification
	 * @return {x,y}
	 */
	public double[] diversify(){
		return ltm.genNewPoint();
	}
	
	/**
	 * Returns the best solution so far
	 * @return the value at the best solution so far.
	 */
	public double getBstSolution(){
		return mtm.getMin();
	}
	
	/**
	 * Clears the Short term memory.
	 */
	public void clearSTM(){
		stm.clearMemory();
	}

}
