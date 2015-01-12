/**
 * 
 */
package ex3.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import ex3.control_parameters.ControlParamsES;

/**
 * @author eng-188do
 *
 */
public class ControlParamsESTest {

	/**
	 * Test method for {@link ex3.control_parameters.ControlParams#readInFile(java.lang.String)}.
	 */
	@Test
	public void testReadInFile() {
		//initialise
		ControlParamsES cntrl = new ControlParamsES();
		double expectedBeta=1.056;
		double expectedTau=0.32;
		double expectedTauP=1.56;
		double expectedVariance=4.35;
		int expectedParents=5;
		int expectedPAndCratio=3;
		/* I have also made a file with the following contents:
		 	beta,1.056
			tau,0.32
			tauP,1.56
			variance,4.35
			parents,5
			parent child ratio,3*/
		String filename=System.getProperty("user.home") + "/Documents/uniwork/4M17/ex3/code/esTestInputs.csv";
		
		//run
		cntrl.readInFile(filename);
		
		
		//assert
		assertEquals("beta",expectedBeta,cntrl.getBeta(),TestConstants.DOUBLE_EPSILON);
		assertEquals("tau",expectedTau,cntrl.getTau(),TestConstants.DOUBLE_EPSILON);
		assertEquals("tau_p",expectedTauP,cntrl.getTauP(),TestConstants.DOUBLE_EPSILON);
		assertEquals("variance",expectedVariance,cntrl.getVar(),TestConstants.DOUBLE_EPSILON);
		assertEquals("parents number",expectedParents,cntrl.getNumParents());
		assertEquals("parent child ratio",expectedPAndCratio,cntrl.getParentChildRatio());
		
	}

}
