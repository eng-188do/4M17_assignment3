/**
 * 
 */
package ex3.tests;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import ex3.FunctionDef;
import ex3.control_parameters.ControlParamsES;
import ex3.evolution_strategies_specifics.ESMember;
import ex3.exceptions.FunctionEvalLimitException;
import ex3.loggers.FunctionCallLogger;

/**
 * @author eng-188do
 *
 */
public class ESMemberTest {

	/**
	 * Test method for {@link ex3.evolution_strategies_specifics.ESMember#getValue()}.
	 */
	@Test
	public void testGetValue() {
		//initialise
		FunctionCallLogger fn=new FunctionCallLogger(10);
		ControlParamsES cntrl=new ControlParamsES();
		double[] dummyVars={1,1};
		double[] dummyAlphas={0,0,0,0};
		ESMember mem=new ESMember(fn, 0.2345, -4.561, new Random(1), cntrl, dummyVars, dummyAlphas, 0);
		double expectedOut=23.59857075; //on calulator
		double actualOut=0;
		
		
		//run
		try {
			actualOut=mem.getValue();
		} catch (FunctionEvalLimitException e) {
			e.printStackTrace();
		}
				
		//assert
		assertEquals("getVal function does not work",expectedOut,actualOut,TestConstants.DOUBLE_EPSILON);

	}



	/**
	 * Test method for {@link ex3.coordinate_holder.Vector2#compareTo(ex3.coordinate_holder.Vector2)}.
	 * I'm going to test this by sorting a simple list. As this is really what I want this method to help me do.
	 */
	@Test
	public void testCompareTo() {
		//initialise
		FunctionCallLogger fn=new FunctionCallLogger(7);
		ControlParamsES cntrl=new ControlParamsES();
		double[] dummyVars={1,1};
		double[] dummyAlphas={0,0,0,0};
		
			//Set up 5 members in a list
		ESMember mem1=new ESMember(fn, 0.2345, -4.561, new Random(1), cntrl, dummyVars, dummyAlphas, 0);//value 23.59857075
		ESMember mem2=new ESMember(fn, 0.2345, -4.561, new Random(1), cntrl, dummyVars, dummyAlphas, 0);  //value 23.59857075
		ESMember mem3=new ESMember(fn, 0.5, -2.4, new Random(1), cntrl, dummyVars, dummyAlphas, 0); //value: 17.2528
		ESMember mem4=new ESMember(fn, 0.6, 3.2, new Random(1), cntrl, dummyVars, dummyAlphas, 0); //value: 36.1723
		ESMember mem5=new ESMember(fn, -5.32, -0.1, new Random(1), cntrl, dummyVars, dummyAlphas, 0); //value: 29.0968
		List<ESMember> listMems=new ArrayList<ESMember>();
		listMems.add(mem1);
		listMems.add(mem2);
		listMems.add(mem3);
		listMems.add(mem4);
		listMems.add(mem5);
		double[] expectedXValsOut={0.5, 0.2345, 0.2345, -5.32, 0.6};
		double[] expectedValsOut={17.2528, 23.59857075, 23.59857075, 29.0968, 36.1723};
		
		for(ESMember member: listMems){
			try {
				member.getValue();
			} catch (FunctionEvalLimitException e) { //shouldn't happen as less than 10 evals				
				e.printStackTrace();
			} //make sure all the values have been calculated.
		}
		
		//run
		Collections.sort(listMems);
				
		//assert
		for(int i=0; i<listMems.size(); i++){
			try {
				assertEquals("Sorting function does not work",expectedValsOut[i],listMems.get(i).getValue(),TestConstants.DOUBLE_EPSILON);
			} catch (FunctionEvalLimitException e) { //shouldn't throw this exception as should no eval again - we're also checking this 
				e.printStackTrace();
			}
			assertEquals("Sorting function does not work",expectedXValsOut[i],listMems.get(i).getCoordX(),TestConstants.DOUBLE_EPSILON);
		}
	}

}
