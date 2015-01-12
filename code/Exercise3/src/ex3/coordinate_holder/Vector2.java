/**
 * 
 */
package ex3.coordinate_holder;

import ex3.exceptions.AttributeNotCalulatedException;
import ex3.exceptions.FunctionEvalLimitException;
import ex3.loggers.FunctionCallLogger;

/**
 * @author eng-188do
 * Class to store the coordinate data and value
 * Note: this class has a natural ordering that is inconsistent with equals.
 */
public class Vector2 implements Comparable<Vector2> {
	//Constructors
	/**
	 * Constructor
	 * @param logger : is the logger for making function calls and ensuring we don't go over the limit.
	 * @param x : x_1 coord (which we often call x)
	 * @param y : x_2 coord (which we often call y)
	 */
	public Vector2(FunctionCallLogger logger,double x, double y){
		fn=logger;
		coord= new PairDouble(x,y);
		haveEvaluated=false;
	}
	
	/**
	 * Copy constructor
	 * @param copy is the item to copy
	 */
	public Vector2(Vector2 copy){ 
		this.fn=copy.fn; //note same object
		this.coord=new PairDouble(copy.coord);
		this.value=new Double(copy.value);
		this.haveEvaluated= new Boolean(copy.haveEvaluated);
	}
	
	
	
	//Members
	protected FunctionCallLogger fn; //records all function calls
	private PairDouble coord; //coordinates 
	protected double value; // value at coordinates, evaluate last minute to avoid unnecessary function evals
	protected boolean haveEvaluated; //flag to say whether or not we have evaluated.
	
	//Methods
	/**
	 * Gets the function value at this point
	 * @return function value
	 * @throws FunctionEvalLimitException exception if function eval. limit has been met
	 */
	public double getValue() throws FunctionEvalLimitException {
		double val;
		if (haveEvaluated){
			val=value;	
		}	else {
			val=fn.getValue(getCoordX(), getCoordY());
			value=val; //store value for future reference
			haveEvaluated=true;
		}
		return val;			
	}

	
	/**
	 *  @return the x coordinate
	 */
	public double getCoordX(){
		return coord.x;
	}
	
	/**
	 * @return the y coordinate
	 */
	public double getCoordY(){
		return coord.y;
	}
	
	/**
	 * Method for comparing two Vector2's by their objective function values.
	 * This method assumes that the points values have been calculated by this point. if not it will throw an error.
	 */
	@Override
    public int compareTo(Vector2 other){
        // compareTo should return < 0 if this is supposed to be
        // less than other, > 0 if this is supposed to be greater than 
        // other and 0 if they are supposed to be equal
        int result;
		try {
			result = (this.getValue() < other.getValue()) ? -1 : ((this.getValue() > other.getValue()) ? 1: 0);
		} catch (FunctionEvalLimitException e) { //All the functions should have been evaluated by here			
			e.printStackTrace();
			throw new AttributeNotCalulatedException("Points values should have been calulated by this point.");
		}
        return result;
    }

	/**
	 * Sets new coordinated
	 * @param x : x_1 coord
	 * @param y : x_2 coord
	 */
	protected void setCoord(double x, double y){
		coord=new PairDouble(x,y);
		haveEvaluated=false;
	}

	
	
}
