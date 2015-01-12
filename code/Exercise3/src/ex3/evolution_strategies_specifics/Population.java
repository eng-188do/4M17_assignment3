/**
 * 
 */
package ex3.evolution_strategies_specifics;

import java.util.*;

import ex3.FunctionDef;
import ex3.control_parameters.ControlParamsES;
import ex3.exceptions.FunctionEvalLimitException;
import ex3.loggers.FunctionCallLogger;
import ex3.loggers.SurvivingPopLogger;

/**
 * @author eng-188do
 * Class to hold whole population of evolutionary strategy members
 */
public class Population {
	//Constructor.
	/**
	 * Constructor
	 * @param rndIn : random number generator
	 * @param fnin : function logger
	 * @param cntrlIn : control parameters
	 * @param logSurvPopIn : logger for the surviving population for each generation
	 */
	public Population(Random rndIn, FunctionCallLogger fnin, ControlParamsES cntrlIn, SurvivingPopLogger logSurvPopIn){
		rnd=rndIn;
		cntrl=cntrlIn;
		fn=fnin;
		logSurvPop=logSurvPopIn;
	}
	
	//Members
	private List<ESMember> pop= new ArrayList<ESMember>(); //holds population
	private List<ESMember> mutParents= new ArrayList<ESMember>(); //holds the parents after mutation
	private List<ESMember> children= new ArrayList<ESMember>(); //holds the children pre-selection.
	private ControlParamsES cntrl; //control parameters
	private Random rnd; //random number generator
	private FunctionCallLogger fn; //function logger (ensures we do not go over evaluation limit).
	private int gen=0; //stores generation count.
	private SurvivingPopLogger logSurvPop; //logs the surviving population at each generation.
	
	//Members
	/**
	 * Mutate the population and put it into the mutated parents list.
	 */
	public void mutate(){
		mutParents=Population.cloneList(pop);
		for(ESMember mem: mutParents){
			mem.mutate();
		}
	}
	
	/**
	 * Combine the mutated parents to form children, which go into the children list.
	 * @throws FunctionEvalLimitException
	 */
	public void recombineToFormChildren() throws FunctionEvalLimitException{
		//NOTE I am assuming that this only results in valid solutions - which is the case for my problem as valid space is square
		int numChildToForm=cntrl.getParentChildRatio()*cntrl.getNumParents();
		children.clear(); //clear old.
		gen++; //we are creating a new generation
		
		for(int counter=0;counter<numChildToForm;counter++){
			//Choose parents:
			int parent1=genRandomParent();
			int parent2=genRandomParent();
			
			//Choose new points:
			double x=mutParents.get(parent1).getCoordX();
			double y=mutParents.get(parent2).getCoordY();
			
			//Choose new strategy params:
			double[] vars=recombineVars(parent1,parent2);
			double[] alphas=recombineAlphas(parent1,parent2);
			
			//Make child!
			ESMember child=new ESMember( fn, x,  y, rnd, cntrl, vars, alphas, gen);
			child.getValue(); //make sure that it knows its value by this point.
			children.add(child);			
		}
	}
	
	/**
	 * We add the children back to the general population. note we leave the original (unmutated) population how it is.
	 * This is an elitist scheme.
	 * NOTE NOT CURRENTLY USED
	 */
	public void addChildrenToPopulationKeepParents(){
		for (ESMember child:children){
			pop.add(child);
		}		
	}
	
	/**
	 * We add the children back to the general population. and we remove the parents
	 *  @see Population#addChildrenToPopulationKeepParents()   elitist version
	 */
	public void addChildrenToPopulation(){
		pop.clear();
		for (ESMember child:children){
			pop.add(child);
		}		
	}
	
	
	/**
	 * We are just going to keep the best lambda of the population
	 */
	public void assessAndCullPop(){
		Collections.sort(pop);
		pop.subList(cntrl.getNumParents(),pop.size()).clear();	
		addToSurvivingPopLog();
		
	}
	
	
	/**
	 * Adds the surving population to the correct log.
	 */
	private void addToSurvivingPopLog(){
		//And add to the log
				for(ESMember survivingMember: pop){ //go through population
					double x,y,val = 0;
					
					//get coordinates
					x=survivingMember.getCoordX();
					y=survivingMember.getCoordY();
					
					//try getting the value - it should be calculated by this point in the program:
					try { 
						val=survivingMember.getValue();
					} catch (FunctionEvalLimitException e) { //they should all be calculated by now so we will write a message and quit
						e.printStackTrace();
						System.out.println("Program isn't working correctly, debug!");
						System.exit(1); //we're going to exit here as program is not working, as designned.
					}
					logSurvPop.add(x, y, val, gen);
				}
	}
	
	/**
	 * Clones a list (this is a deep copy)
	 * @param origList : original list to clone from
	 * @return the cloned list
	 */
	public static List<ESMember> cloneList(List<ESMember> origList) {
	    List<ESMember> clonedList = new ArrayList<ESMember>(origList.size());
	    for (ESMember mem : origList) { //look through and copy each member in original list.
	        clonedList.add(new ESMember(mem));
	    }
	    return clonedList;
	}
	
	/**
	 * @return a random parent's index
	 */
	private int genRandomParent(){
		int num=rnd.nextInt(mutParents.size());
		return num;
	}
	
	
	/**
	 * Perform recombination on strategy parameters by returning average of parents (intermediate recombination).
	 * @param strat1 strategy parameters of parent 1
	 * @param strat2 strategy parameters of parent 2
	 * @return the new strategy parameters of the child.
	 */
	private double[] recombineStrategy(double[] strat1, double[] strat2){		
		double[] stratNew=new double[strat1.length];
		
		for(int i=0;i<strat1.length;i++){
			stratNew[i]=0.5*(strat1[i]+strat2[i]);
		}
		return stratNew;
	}
	
	
	/**
	 * Recombination strategy on the variances
	 * @param parent1 index of parent 1
	 * @param parent2 index of parent 2
	 * @return  the new variance
	 */
	private double[] recombineVars(int parent1, int parent2){
		double[] vars1, vars2;		
		vars1=mutParents.get(parent1).vars;
		vars2=mutParents.get(parent2).vars;
		
		return recombineStrategy(vars1, vars2)	;	//(intermediate recombination)
	}
	
	/**
	 * Recombination strategy on the alphas
	 * @param parent1 index of parent 1
	 * @param parent2 index of parent 2
	 * @return  the new alpha
	 */
	private double[] recombineAlphas(int parent1, int parent2){
		double[] alpha1, alpha2;		
		alpha1=mutParents.get(parent1).alphas;
		alpha2=mutParents.get(parent2).alphas;
		
		return recombineStrategy(alpha1, alpha2);	//(intermediate recombination)	
	}
	
	/**
	 * Initialised the random population. X's are chosen randomly. Strategy parameters are defined deterministically.
	 * see my report for discussion on this point.
	 */
	public void initialisePopulation(){
		pop.clear();
		
		double x,y;
		double vars[]={cntrl.getVar(), cntrl.getVar()}; //start covariance functions are defined.
		double alphas[]={1,0,0,1}; //deterministic and no covariance to start off with.		
		
		for (int i=0; i<cntrl.getNumParents(); i++){
			x=FunctionDef.genRandomX(rnd);
			y=FunctionDef.genRandomY(rnd);
			pop.add(new ESMember(fn, x, y, rnd, cntrl, vars, alphas, 0));			
		}
		
		addToSurvivingPopLog();
	}
}
