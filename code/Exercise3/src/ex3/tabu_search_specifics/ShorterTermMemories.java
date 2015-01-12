/**
 * 
 */
package ex3.tabu_search_specifics;

import ex3.coordinate_holder.Vector2;

/**
 * @author eng-188do
 * This is the base class for the shorter term memories: ie the short term memory and the medium term memory.
 */
public abstract class ShorterTermMemories {
	//Constructor
	/**
	 * Constructor
	 * @param numPoints :max number of points in memory
	 */
	ShorterTermMemories(int numPoints){
		memorySize=numPoints;
		memory= new Vector2[numPoints];
		itemsInMemory=0;
	}
	
	//Members
	protected final int memorySize; //memory capacity - number of points we can have.
	protected int itemsInMemory=0; //current number of items in memory
	protected Vector2[] memory; //memory items.

	//Methods
	/**
	 * Adds a point to memory
	 * @param point : point to add to the memory
	 */
	public abstract void addPoint(Vector2 point);  
	
	/**
	 * Empties the memory
	 */
	public void clearMemory(){
		itemsInMemory=0;
		memory= new Vector2[memorySize];
	}
	
	
	/**
	 * Checks if point is a member (ie same coords) of the short term memory.
	 * @param point
	 * @return true if it is a member
	 */
	public boolean checkIfMember(Vector2 point){
		double xCoord=point.getCoordX();
		double yCoord=point.getCoordY();
		Vector2 memoryItem;
		
		for (int i=0; i<itemsInMemory; i++){ //loop through and check whether the x and y coords already exist in memory
			memoryItem=memory[i];
			if ( memoryItem.getCoordX()==xCoord && memoryItem.getCoordY()==yCoord){
				//TODO: we want to check above works and we don't need to use tolerance instead. like Math.abs() rather than ==
				return true;
			}
		}
		
		return false; //if got here then it's not a member of the memory			
	}
}
