/**
 * 
 */
package ex3.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import ex3.coordinate_holder.Vector2;
import ex3.loggers.FunctionCallLogger;
import ex3.tabu_search_specifics.ShortTermMemory;

/**
 * @author eng-188do
 *
 */
public class ShortTermMemoryTest {

	/**
	 * Test method for {@link ex3.tabu_search_specifics.ShorterTermMemories#checkIfMember(ex3.coordinate_holder.Vector2)}.
	 * This point checks tabu bit is working by checking it remembers the last members.
	 */
	@Test
	public void testCheckIfMember() {
		//initialise
		FunctionCallLogger fn=new FunctionCallLogger(100);
		ShortTermMemory mem= new ShortTermMemory(5);
		Vector2 point1 = new Vector2(fn,0,2); 
		Vector2 point2 = new Vector2(fn,1,3); 
		Vector2 point3 = new Vector2(fn,4,1); 
		Vector2 point4 = new Vector2(fn,-2,5); 
		Vector2 point5 = new Vector2(fn,5.9,-5.9); 
		Vector2 point6 = new Vector2(fn,-1.5,3); 
		Vector2 point7 = new Vector2(fn,1,3);
		
		//run and assert
		assertFalse(mem.checkIfMember(point1)); //should be nothing in memory.
		mem.addPoint(point1);
		mem.addPoint(point2);
		mem.addPoint(point3);
		mem.addPoint(point4);
		mem.addPoint(point5);
		assertTrue(mem.checkIfMember(point1)); //check points 1 and 3 went in.
		assertTrue(mem.checkIfMember(point3));
		mem.addPoint(point6);
		mem.addPoint(point7);
		assertFalse(mem.checkIfMember(point1)); //point1 should have been pushed out of memory now.
		assertTrue(mem.checkIfMember(point3)); //but point3 should still be there
		
	}

}
