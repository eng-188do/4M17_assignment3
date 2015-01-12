package ex3.tests;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ex3.FunctionDef;


/**
 * 
 */

/**
 * @author eng-188do
 * Class to test functions in FunctionDef.
 */
public class FunctionDefTest {

	/**
	 * Test method for {@link FunctionDef#evalFunc(double, double)}.
	 */
	@Test
	public void testEvalFunc1() {
		//intialise
		double inputX=0.2345;
		double inputY=-4.561;
		double expectedOut=23.59857075;
		double actualOut=0;
		
		//run
		actualOut=FunctionDef.evalFunc(inputX, inputY);
		
		//assert
		assertEquals("Eval function does not work",expectedOut,actualOut,TestConstants.DOUBLE_EPSILON);
		
	}

	
	@Rule
	public ExpectedException ex = ExpectedException.none();
	
	/**
	 * Test method for {@link FunctionDef#evalFunc(double, double)}.
	 */
	@Test
	public void testEvalFunc2() {		
		//intialise
		double inputX=0.2345;
		double inputY=-7.2536;
		double actualOut;
		
		//run
		ex.expect(IllegalArgumentException.class);
		actualOut=FunctionDef.evalFunc(inputX, inputY);
		
	}
	
	
	
	/**
	 * Test method for {@link FunctionDef#checkInRange(double, double)}.
	 */
	@Test
	public void testCheckInRange1() {
		//intialise
		double inputX=6.0;
		double inputY=-5.9;
		boolean expectedOut=true;
		boolean actualOut=false;
		
		//run
		actualOut=FunctionDef.checkInRange(inputX, inputY);
		
		//assert
		assertEquals("Range function does not work",expectedOut,actualOut);
	}
	
	
	/**
	 * Test method for {@link FunctionDef#checkInRange(double, double)}.
	 */
	@Test
	public void testCheckInRange2() {
		//intialise
		double inputX=6.01;
		double inputY=-5.9;
		boolean expectedOut=false;
		boolean actualOut=true;
		
		//run
		actualOut=FunctionDef.checkInRange(inputX, inputY);
		
		//assert
		assertEquals("Range function does not work",expectedOut,actualOut);
	}

}
