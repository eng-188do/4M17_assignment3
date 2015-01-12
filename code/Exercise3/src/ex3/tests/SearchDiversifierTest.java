/**
 * 
 */
package ex3.tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import ex3.tabu_search_specifics.SearchDiversifier;

/**
 * @author eng-188do
 *
 */
public class SearchDiversifierTest {

	/**
	 * Test method for {@link ex3.tabu_search_specifics.SearchDiversifier#genNewPoint()}.
	 */
	@Test
	public void testGenNewPoint() {
		//initialise
		Random rnd = new Random(1);
		SearchDiversifier diversifier=new SearchDiversifier(rnd);
		
		//run
		//run by adding new points in every location but one and getting new location
		/*   6
		 * 	 4	_|_|_|_|_|_
		 * 	 2	_|_|_|_|_|_
		 * 	 0	_|_|_|_|_|_
		 * 	-2	_|_|_|_|_|_
		 * 	-4	_|_|_|_|_|_
		 * 	-6	 | | | | |
		 * 	 -6,-4,-2,0,2,4,6
		 */
		diversifier.addPt(-4.5, 5);
		diversifier.addPt(-2.5, 5);
		diversifier.addPt(-0.5, 5);
		diversifier.addPt(0.5, 5);
		diversifier.addPt(2.5, 5);
		diversifier.addPt(4.5, 5);
		diversifier.addPt(-4.5, 3);
		diversifier.addPt(-2.5, 3);
		diversifier.addPt(-0.5, 3);
		diversifier.addPt(0.5, 3);
		diversifier.addPt(2.5, 3);
		diversifier.addPt(4.5, 3);
		diversifier.addPt(-4.5, 1);
		diversifier.addPt(-2.5, 1);
		diversifier.addPt(-0.5, 1);
		diversifier.addPt(0.5, 1);
		diversifier.addPt(2.5, 1);
		diversifier.addPt(4.5, 1);
		//diversifier.addPt(-4.5, -1); so point we generate should be between -4>x>-6 & -2<y<0
		diversifier.addPt(-2.5, -1);
		diversifier.addPt(-0.5, -1);
		diversifier.addPt(0.5, -1);
		diversifier.addPt(2.5, -1);
		diversifier.addPt(4.5, -1);
		diversifier.addPt(-4.5, -3);
		diversifier.addPt(-2.5, -3);
		diversifier.addPt(-0.5, -3);
		diversifier.addPt(0.5, -3);
		diversifier.addPt(2.5, -3);
		diversifier.addPt(4.5, -3);
		diversifier.addPt(-4.5, -5);
		diversifier.addPt(-2.5, -5);
		diversifier.addPt(-0.5, -5);
		diversifier.addPt(0.5, -5);
		diversifier.addPt(2.5, -5);
		diversifier.addPt(4.5, -5);
		double coord[]=diversifier.genNewPoint();
		
		//assert
		boolean xInRange=(-4>=coord[0] && coord[0]>=-6);
		boolean yInRange=(0>=coord[1] && coord[1]>=-2);
		assertTrue(xInRange);
		assertTrue(yInRange);
		
	}

}
