/**
 * 
 */
package ex3.tabu_search_specifics;

import ex3.coordinate_holder.Vector2;

/**
 * @author eng-188do
 *
 */
public final class ShortTermMemory extends ShorterTermMemories {
	//Constructor
	/**
	 * Constructor
	 * @param numPoints: memory capacity ie max number of points it can hold.
	 */
	public ShortTermMemory(int numPoints) {
		super(numPoints);

	}
	
	//Methods
	/* (non-Javadoc)
	 * @see ex3.TSSpecificClasses.ShorterTermMemories#addPoint(ex3.CoordinateHolder.Vector2)
	 * For Short term memory we add this point to the memory replacing the first stored memory. ie first in first out
	 * Note you technically can get the same Methods in here. This should be stopped by the LocalSearch class not coming back to a point that is taboo.
	 */
	@Override
	public void addPoint(Vector2 point) {
		//Shift array up (deleting last member). if nothing there will just shift zeros up, which is fine.
		System.arraycopy(memory, 0, memory, 1, memory.length-1);
		
		memory[0]=new Vector2(point); //store a copy
		
		if (itemsInMemory<memorySize)
			itemsInMemory++;
	}
	


}
