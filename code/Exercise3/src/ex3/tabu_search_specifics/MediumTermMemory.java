/**
 * 
 */
package ex3.tabu_search_specifics;

import ex3.coordinate_holder.Vector2;
import ex3.exceptions.AttributeNotCalulatedException;
import ex3.exceptions.FunctionEvalLimitException;

import java.util.*;

/**
 * @author eng-188do
 * Class to hold the medium term memory (mtm).
 */
public final class MediumTermMemory extends ShorterTermMemories {
	//Constructor
	/**
	 * Constructor
	 * @param numPoints : is the number of points in the memory
	 * @param rndIn : random number generator
	 */
	public MediumTermMemory(int numPoints, Random rndIn) {
		super(numPoints);
		rnd=rndIn;
	}

	//Members
	private Random rnd; //random number generator.

	//Methods

	/* (non-Javadoc)
	 * @see ex3.TSSpecificClasses.ShorterTermMemories#addPoint(ex3.CoordinateHolder.Vector2)
	 * Adds (copy of) point into the medium term memory if its value displaces that already there.
	 */
	@Override
	public void addPoint(Vector2 point) {
		//Note I have thought about the fact that we are using a Vector2 array rather than a map or some other structure to make searching more efficient.
		//this is because medium term memory is likely to be small notes say 4 items so array searching is unlikely to be significant and it is simpler to code and
		//understand.

		if (itemsInMemory==0){ //if memory is empty add it straight away and return
			memory[0]=new Vector2(point); //add a copy
			itemsInMemory++;
		} else{			
			if (checkIfMember(point)==true) // check if already in memory, if it is, return
				return;

			if(itemsInMemory==memorySize){// is memory full?				
				try{
					int index=findMax();
					if (memory[index].getValue()>point.getValue()) //is is smaller than current worst?
						memory[index]=new Vector2(point); //replace current biggest	
				}  catch (FunctionEvalLimitException e) { //All the functions should have been evaluated by here.			
					e.printStackTrace();
					throw new AttributeNotCalulatedException("Points values should have been calulated by this point.");
				}				
			} else{ //memory isn't full so add it to the end and increment counter.
				memory[itemsInMemory++]=new Vector2(point); //add a copy				
			}
		}
	}

	/**
	 * Returns the average from all the stored points
	 * (will be interesting to see what this ends up doing if the bests are in the two equal optimums)
	 * @return
	 */
	public double[] averageBest(){
		double xPoints=0, yPoints=0;

		//go through all memory points and calulate average
		int counter;
		for (counter=0; counter<itemsInMemory; counter++){
			xPoints+=memory[counter].getCoordX();
			yPoints+=memory[counter].getCoordY();
		}
		xPoints/= (double) counter;
		yPoints/= (double) counter;

		double[] coord={xPoints, yPoints};
		return coord;
	}

	/**
	 * Finds the index of the maximum value in the array.
	 * if several it will return one randomly.
	 * If nothing in memory it will return 0.
	 * @return index of largest
	 */
	private int findMax() {
		//NOTE: this is a rather clunky function, in the fact that it looks through the whole array twice.
		// we could perhaps use information in the first search to narrow down our second search.
		// as we are only dealing with small arrays doesn't really matter, but still...
		try{	
			double max=memory[0].getValue();
			List<Integer> indices = new ArrayList<Integer>();

			//search through and find the maximum
			for(int i=0; i<itemsInMemory; i++){
				if(memory[i].getValue() > max){
					max=memory[i].getValue();
				}
			}

			//next find the index
			for(int i=0; i<itemsInMemory; i++){
				if(memory[i].getValue() == max){
					indices.add(i);
				}
			}		
			//now return a random entry from this list
			int randomEntry=rnd.nextInt(indices.size());		
			return indices.get(randomEntry);

		}  catch (FunctionEvalLimitException e) { //All the functions should have been evaluated by here.			
			e.printStackTrace();
			throw new AttributeNotCalulatedException("Points values should have been calulated by this point.");
		}	


	}
	
	/**
	 * gets the vaue of the minimum in the memory.
	 * @return
	 */
	public double getMin(){
		try{	
			double min=memory[0].getValue();

			//search through and find the maximum
			for(int i=0; i<itemsInMemory; i++){
				if(memory[i].getValue() < min){
					min=memory[i].getValue();
				}
			}
			
			return min;

		}  catch (FunctionEvalLimitException e) { //All the functions should have been evaluated by here.			
			e.printStackTrace();
			throw new AttributeNotCalulatedException("Points values should have been calulated by this point.");
		}	
	
	}

}
