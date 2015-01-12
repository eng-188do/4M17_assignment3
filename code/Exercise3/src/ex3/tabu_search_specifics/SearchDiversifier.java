/**
 * 
 */
package ex3.tabu_search_specifics;

import java.util.*;

import ex3.FunctionDef;

/**
 * @author eng-188do
 * This class holds the long term memory and sorts out the whole diversification process.
 * This class stores a 6 by 6 frequency grid of the search space. And notes how often we have had successful moves in each area
 */
public class SearchDiversifier {
	//Constructor
	/**
	 * Constructor
	 * @param rndIn : random number generator
	 */
	public SearchDiversifier(Random rndIn){
		rnd=rndIn;
	}
	
	//Members
	private int[][] freqTable=new int[6][6]; //this stores the number of times we have visited each area of the space. 
											//this will initialise them all to zero
											//storing y's then x's 
	private Random rnd; //random number generator
	
	//Methods
	/**
	 * Adds a point to the frequency table
	 * @param x coord
	 * @param y coord (or x_2).
	 */
	public void addPt(double x, double y){
		freqTable[findYIndex(y)][findXIndex(x)]++;
	}
	
	/**
	 * generates a new value to search from that hasn't been searched before
	 * @return {x,y}
	 */
	public double[] genNewPoint(){
		int[] indexToPlacePointsIn=findMin(); //find in which square we should place the results in
		
		//now generate some results in that square
		double[] returnResults=new double[2];
		returnResults[0]=genX(indexToPlacePointsIn[0]);
		returnResults[1]=genY(indexToPlacePointsIn[1]);
		
		return returnResults;
	}
	
	
	/**
	 * Find where in the freq table to put the x value
	 * @param x coord
	 * @return
	 */
	private int findXIndex(double x){
		double location=freqTable[0].length*(x-FunctionDef.xlow)/(FunctionDef.xhigh-FunctionDef.xlow);  //convert range to 0-6		
		return (int) location; //round down
	}
	
	/**
	 * Find where in the freq table to put the y value
	 * @param y coord
	 * @return index value it fallsin
	 */
	private int findYIndex(double y){
		double location=freqTable.length*(y-FunctionDef.ylow)/(FunctionDef.yhigh-FunctionDef.ylow);  //convert range to 0-6		
		return (int) location; //round down
	}
	
	/**
	 * Finds the minimum indices of the frequency array
	 * Note: if multiple mins then it returns a random one of these.
	 * @return {x,y}
	 */
	private int[] findMin(){
		int min=0;
		List<int[]> indices=new ArrayList<int[]>(); //this expanding list is where we will store our potential candidates		

		//Find minimum
		for(int i=0; i<freqTable.length;i++){
			for(int j=0; j<freqTable[0].length; j++){
				if(min>freqTable[i][j]){
					min=freqTable[i][j];
				}
			}
		}
		
		//Now go through and find where it occurs (in case it occurs multiple times)
		int[] entry; //{x,y}'s of minimums	
		for(int i=0; i<freqTable.length;i++){
			for(int j=0; j<freqTable[0].length; j++){
				if(min==freqTable[i][j]){
					entry=new int[2];
					entry[0]=j; //x
					entry[1]=i; //y
					indices.add(entry);					
				}
			}
		}
		
		//Now generate random entry from this
		int randomIndex=rnd.nextInt(  indices.size() );
		return indices.get(randomIndex);	
	}
	
	/**
	 * generate a random x coord in the right place
	 * @param xIndex
	 * @return radnom x coord
	 */
	private double genX(int xIndex){
		double intervalWidth=(FunctionDef.xhigh-FunctionDef.xlow)/freqTable[0].length; //get how wide an interval is
		double intervalValue=rnd.nextDouble()*intervalWidth;//get where in the interval to place our value
		
		double xOffset=((double) xIndex)*intervalWidth+FunctionDef.xlow; //find lh edge of interval
		return xOffset+intervalValue;
	}
	
	/**
	 * Generate a random y value in the right place.
	 * @param yIndex
	 * @return random y coord
	 */
	private double genY(int yIndex){
		double intervalWidth=(FunctionDef.yhigh-FunctionDef.ylow)/freqTable.length; //get how wide an interval is
		double intervalValue=rnd.nextDouble()*intervalWidth;//get where in the interval to place our value
		
		double yOffset=((double) yIndex)*intervalWidth+FunctionDef.ylow; //find lh edge of interval
		return yOffset+intervalValue;
	}
}
