/**
 * 
 */
package ex3.swarm_specifics;


import java.util.*;

import ex3.control_parameters.ControlParamsS;
import ex3.exceptions.FunctionEvalLimitException;
import ex3.loggers.*;

/**
 * @author eng-188do
 * Class to hold the flock details.
 */
public class Flock {
	//Constructor.
	/**
	 * Constructor
	 * @param rndIn : random number generator
	 * @param cntrlIn : control parameters
	 * @param logIn : logger for the birds.
	 * @param fnIn : logger for function and gateway for evaluating functions ensuring that the evaluation limit is adhered to.
	 */
	public Flock(Random rndIn, ControlParamsS cntrlIn, BirdLogger logIn, FunctionCallLogger fnIn){
		rnd=rndIn;
		cntrl=cntrlIn;
		log=logIn;
		fn=fnIn;
		flockSize=cntrl.getFlockSize();
	}
	
	//Members
	private final int flockSize; //number of birds
	private Random rnd; //random number generator
	private ControlParamsS cntrl; //control parameters
	private BirdLogger log; //log for bird positions
	private FunctionCallLogger fn; //function logger and caller.
	
	private List<Bird> flock=new ArrayList<Bird>(); //for storing the flock.
	 
	//Methods
	/**
	 * Initialising each bird randomly in the space and with the set velocities.
	 * @throws FunctionEvalLimitException
	 */
	public void initialiseFlock() throws FunctionEvalLimitException{
		for(int i=0 ; i<flockSize; i++){
			flock.add(new Bird(fn,0,0,rnd,log,cntrl,i)); //add bird to flock.
			flock.get(i).initialise(); //Initialise the bird randomly.
		}
	}
	
	/**
	 * Updates the whole flock by getting them to move on one iteration.
	 * @throws FunctionEvalLimitException
	 */
	public void updateFlock() throws FunctionEvalLimitException{
		ArrayList<Bird> otherBirds=new ArrayList<Bird>();
		for(Bird eachBird: flock){ //go through each bird and update it
			otherBirds=shallowCopyFlock(); 
			otherBirds.remove(eachBird); 
			eachBird.updateBird(otherBirds);
		}
	}
	
	/**
	 * Performs a shallow copy of the flock (shallow in the sense that the references to the objects in the list remain constant)
	 * @return
	 */
	private ArrayList<Bird> shallowCopyFlock(){
		ArrayList<Bird> flockCopy=new ArrayList<Bird>();
		for(Bird eachBird: flock){ //go through each bird and also put it in the new list.
			flockCopy.add(eachBird);
		}
		return flockCopy;
	}

}
