/**
 * 
 */
package ex3.coordinate_holder;

/**
 * @author eng-188do
 * Stores pairs of values which are double
 */
public final class PairDouble {	
	//Constructors
	public PairDouble(double x, double y) { 
		this.x = x; 
		this.y = y; 			
	}

	public PairDouble(PairDouble copy) { //Copy constructor
		this.x=new Double(copy.x);
		this.y=new Double(copy.y);
	} 
	
	//Members
	public double x; 
	public double y;

}

