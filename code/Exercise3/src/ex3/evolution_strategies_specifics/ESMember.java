/**
 * 
 */
package ex3.evolution_strategies_specifics;

import java.util.Random;
import ex3.FunctionDef;
import ex3.auxillary_maths_functions.TwoDMatrices;
import ex3.control_parameters.ControlParamsES;
import ex3.coordinate_holder.Vector2;
import ex3.exceptions.FunctionEvalLimitException;
import ex3.loggers.FunctionCallLogger;

/**
 * @author eng-188do
 * This class holds a member of the evolutionary strategy population.
 */
public final class ESMember extends Vector2 {
	//Constructor
	/**
	 * Constructor
	 * @param logger : function call logger. for getting function value and for storing all calls to it
	 * @param x : x coord (x_1 on assignment sheet)
	 * @param y : y coord (x_2 on assignment sheet)
	 * @param seed : random number generator to use
	 * @param cntrlIn : control parameters
	 * @param varsIn : variances for mutation
	 * @param alphasIn : alphas (ie rotation angles) for mutation
	 * @param genIn : generation number
	 */
	public ESMember(FunctionCallLogger logger,double x, double y, Random seed, ControlParamsES cntrlIn, double[] varsIn, double[] alphasIn, int genIn){
		super( logger, x,  y);
		rnd=seed;
		cntrl=cntrlIn;
		vars=varsIn;
		alphas=alphasIn;
		gen=genIn;
	}
	
	/**
	 * Copy Constructor
	 * @param copy : ESMember to copy.
	 */
	public ESMember(ESMember copy){
		super(copy); //TODO: check this is working as i am expecting it to.
		
		this.vars=new double[copy.vars.length];
		System.arraycopy( copy.vars, 0, this.vars, 0, copy.vars.length);
		
		this.alphas=new double[copy.alphas.length];
		System.arraycopy( copy.alphas, 0, this.alphas, 0, copy.alphas.length);
		
		this.gen=new Integer(copy.gen);	
		this.rnd=copy.rnd; //want reference to same object
		this.cntrl=copy.cntrl; //want reference to same object
	}
	
	//Members
	
	/* Strategy Parameters, these are protected to allow population to access them if it needs to. */
	protected double[] vars; //variances
	protected double[] alphas; //define as {axx,axy,ayx,ayy}.
	
	Random rnd; //random number generaetor
	private int gen; //generation number
	private ControlParamsES cntrl; //control parameters
	
	
	/* (non-Javadoc)
	 * @see ex3.CoordinateHolder.Vector2#getValue()
	 * gets the value at the current coords
	 */
	@Override
	public double getValue() throws FunctionEvalLimitException {
		double val;
		if (haveEvaluated){
			val=value;	
		}	else {
			val=fn.getValue(getCoordX(), getCoordY(), gen);
			value=val; //store value for future reference
			haveEvaluated=true;
		}
		return val;			
	}
	
	/**
	 * Mutates the member of the population
	 * overloaded method 
	 * @see ESMember#mutate(int)
	 */
	public void mutate(){
		mutate(0);
	}
	
	/**
	 * Mutates the member of the population
	 * @param counter : records number of times function has been called
	 * Note: calls itself recursively
	 */
	public void mutate(int counter){
		
		//mutate strategy parameters
		mutateStratParams();	
		 
		 //mutate X
		 double[] c=calcMutateXChange(); //change in coords as the result of the mutation
		
		 double newX=getCoordX()+ c[0];
		 double newY=getCoordY()+c[1];
		 //Change x and y
		 if (!FunctionDef.checkInRange(newX, newY)){//if out of range try mutating again.
			 if (counter > 100) // I found that the variances could run off with themselves and get too big. So I would call this function until stack overflow.
				 resetVars();  //So I put this hack in to try to stop that happening. Will be discussed in the report.
			 mutate(++counter);
			 return;
		 }
		 setCoord(newX,newY);		
	}
	
	/**
	 * resets the variances to their initial values
	 */
	private void resetVars(){
		for(int i=0; i<vars.length; i++){
			vars[i]=cntrl.getVar();
		}
	}
	
	/**
	 * @return  the change in coordinates due to mutation
	 */
	private double[] calcMutateXChange(){
		 //form rotation matrix (see p.70 beck)
		 double[][] rot={ {Math.cos(alphas[0]),-Math.sin(alphas[1])},
				 {Math.sin(alphas[2]),Math.cos(alphas[3])} };
		 
		 //form matrix to multiply this with
		 double[][] multiplier={ {vars[0],0},
				 				{0,vars[1]} };
		 
		 //random vector
		 double[][] randVec={{rnd.nextGaussian()},{rnd.nextGaussian()}};
		 
		 //multiply it out
		 double[][] change=TwoDMatrices.matrixMultiplication(rot, multiplier);
		 change=TwoDMatrices.matrixMultiplication(change, randVec);
		 double [] c=TwoDMatrices.convertColVectorToArray(change);
		 return c;
	}
	
	/**
	 * Mutates the strategy parameters  (ie varaiances and alphas) see Evolution Strategy Notes for how it does this.
	 */
	private void mutateStratParams(){
		 double[] n=new double[7]; //array  {N_0, N_1,N_2,N_11,N_12,N_21,N_22}
		 
		 //find random numbers for Ns
		 for (int i=0; i<n.length;i++)
		 	n[i]=rnd.nextGaussian();
		 
		 //mutate variances
		 for (int j=0; j<vars.length; j++){
			 vars[j]*=Math.exp(cntrl.getTauP()*n[0]+cntrl.getTau()*n[j+1]);
		 }
		 
		 //mutate alpha's
		 for (int k=0; k<alphas.length; k++){
			 alphas[k]+=cntrl.getBeta()*n[3+k];
		 }
	}
	
	

}
