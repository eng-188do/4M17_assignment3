/**
 * 
 */
package ex3.tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import ex3.coordinate_holder.Vector2;
import ex3.exceptions.FunctionEvalLimitException;
import ex3.loggers.FunctionCallLogger;
import ex3.tabu_search_specifics.MediumTermMemory;

/**
 * @author eng-188do
 *
 */
public class MediumTermMemoryTest {

	/**
	 * Test method for {@link ex3.tabu_search_specifics.MediumTermMemory#averageBest()}.
	 */
	@Test
	public void testAverageBest() {
		//arrange
		FunctionCallLogger fn=new FunctionCallLogger(100);
		MediumTermMemory mem=new MediumTermMemory(20, new Random(1));
		Vector2 point1 = new Vector2(fn,0,2); 
		Vector2 point2 = new Vector2(fn,1,3); 
		Vector2 point3 = new Vector2(fn,4,1); 
		double[] expectedAvergageBest={1.6666666667, 2}; //from calculator.
		double [] actualAverageBest=new double[2];
		
		//run
		mem.addPoint(point1);
		mem.addPoint(point2);
		mem.addPoint(point3);
		actualAverageBest=mem.averageBest();
		
		//assert
		assertEquals("Incorrect average best x calulated"  ,expectedAvergageBest[0], actualAverageBest[0],TestConstants.DOUBLE_EPSILON);
		assertEquals("Incorrect average best y calulated"  ,expectedAvergageBest[1], actualAverageBest[1],TestConstants.DOUBLE_EPSILON);
	}

	/**
	 * Test method for {@link ex3.tabu_search_specifics.MediumTermMemory#getMin()}.
	 */
	@Test
	public void testGetMin1() {
		//arrange
		FunctionCallLogger fn=new FunctionCallLogger(100);
		MediumTermMemory mem=new MediumTermMemory(20, new Random(1));
		Vector2 point1 = new Vector2(fn,0,2); //val:2.868795616243186
		Vector2 point2 = new Vector2(fn,1,3); //val: 47.129226041767765
		Vector2 point3 = new Vector2(fn,4,1); //val: 19.896177372947569
		//need to ensure that values have been calculated.
		try {
			point1.getValue();
			point2.getValue();
			point3.getValue();
		} catch (FunctionEvalLimitException e) { //given it a 100 trials so should be fine.
			e.printStackTrace();
		}

		double expectedMin=2.868795616243186; //by hand on calc
		double actualMin=0;
		
		//run
		mem.addPoint(point1);
		mem.addPoint(point2);
		mem.addPoint(point3);
		actualMin=mem.getMin();
		
		//assert
		assertEquals("Incorrect min returned"  ,expectedMin, actualMin,TestConstants.DOUBLE_EPSILON);
	}
	
	/**
	 * Test method for {@link ex3.tabu_search_specifics.MediumTermMemory#getMin()}.
	 */
	@Test
	public void testGetMin2() {
		//arrange
		FunctionCallLogger fn=new FunctionCallLogger(100);
		MediumTermMemory mem=new MediumTermMemory(4, new Random(1)); //only 4 items in memory.
		Vector2 point1 = new Vector2(fn,0,2); //val:2.868795616243186
		Vector2 point2 = new Vector2(fn,1,3); //val: 47.129226041767765
		Vector2 point3 = new Vector2(fn,4,1); //val: 19.896177372947569
		Vector2 point4 = new Vector2(fn,-2,5); //val: 58.344893672224373
		Vector2 point5 = new Vector2(fn,5.9,-5.9); //val: 145
		Vector2 point6 = new Vector2(fn,-1.5,3); //val: -85.592787269845118
		Vector2 point7 = new Vector2(fn,1,3); //(identical to 2
		//need to ensure that values have been calculated.
		try {
			point1.getValue();
			point2.getValue();
			point3.getValue();
			point4.getValue();
			point5.getValue();
			point6.getValue();
			point7.getValue();
		} catch (FunctionEvalLimitException e) { //given it a 100 trials so should be fine.
			e.printStackTrace();
		}

		double expectedMin=-85.592787269845118; //by hand on calc
		double actualMin=0;
		
		//run
		mem.addPoint(point1);
		mem.addPoint(point2);
		mem.addPoint(point3);
		mem.addPoint(point4);
		mem.addPoint(point5);
		mem.addPoint(point6);
		mem.addPoint(point7);
		actualMin=mem.getMin();
		
		//assert
		assertEquals("Incorrect min returned"  ,expectedMin, actualMin,TestConstants.DOUBLE_EPSILON);
	}

}
