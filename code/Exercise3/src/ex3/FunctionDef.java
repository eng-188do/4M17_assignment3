package ex3;

import java.util.Random;

/**
 * 
 */

/**
 * @author eng-188do
 * Class that holds the definition of the objective function
 */
public final class FunctionDef {
	//Members
	public static final double xlow=-6; //x lower bound
	public static final double xhigh=6; //x higher bound
	public static final double ylow=-6; //y lower bound
	public static final double yhigh=6; //y higher bound
	public static final double[] centre={0,0}; //marks out the centre of the region.
	
	//Methods
	/**
	 * Evaluates the function at x and y
	 * @param x this is x_1
	 * @param y this is x_2
	 * @return f(x)
	 */
	static public double evalFunc(double x, double y){
		//check in range
		if (!checkInRange( x,  y))
			throw new IllegalArgumentException("The input value is out of range");
		
		//Now calculate the actual function
		//Three terms defined on assignment sheet.
		double result;
		result= Math.sin(x)*Math.exp( Math.pow( (1-Math.cos(y)),2 ) );
		result+= Math.cos(y)*Math.exp( Math.pow( (1-Math.sin(x)),2 ) );
		result+= Math.pow( (x-y), 2);
		return result;
	}
	
	/**
	 * Checks input is in the allowed range
	 * @param x this is x_1
	 * @param y this is x_2
	 * @return true if in range, false if outside.
	 */
	public static boolean checkInRange(double x, double y){
		boolean val=true; //start off with true and disprove this (if applicable)
		if (x<xlow || x>xhigh)
			val=false;
		if (y<ylow || y>yhigh)
			val=false;
		return val;
	}
	
	/**
	 * Returns a random x in the allowed range
	 * @param rnd is the seed
	 * @return the random x.
	 */
	public static double genRandomX(Random rnd){
		double randomX=rnd.nextDouble()*(xhigh-xlow)+xlow;
		return randomX;
	}
	
	
	/**
	 * Return a random y in the allowed range
	 * @param rnd is the seed
	 * @return the random y
	 */
	public static double genRandomY(Random rnd){
		double randomY=rnd.nextDouble()*(yhigh-ylow)+ylow;
		return randomY;
	}
}
