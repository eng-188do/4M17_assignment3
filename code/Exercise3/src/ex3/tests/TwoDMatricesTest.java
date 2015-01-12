package ex3.tests;
import static org.junit.Assert.*;

import org.junit.Test;

import ex3.auxillary_maths_functions.TwoDMatrices;

/**
 * 
 */

/**
 * @author eng-188do
 *
 */
public class TwoDMatricesTest {

	/**
	 * Test method for {@link TwoDMatrices#matrixMultiplication(double[][], double[][])}.
	 */
	@Test
	public void testMatrixMultiplication1() {
		//intialise
		double[][] mat1={ {1.30,	3.10},
						{-4.10,	2.25}};
		double[][] mat2={ {0.7,	0.1},
							{4,	-2.5}};
		double[][] expectedOut={ {13.31,	-7.62},
							{6.13,	-6.035}}; //from MATLAB
		double[][] actualOut={ {0,	0},
								{0,	0}};
		
		//run
		actualOut=TwoDMatrices.matrixMultiplication(mat1, mat2);
		
		//assert
		for(int i=0; i<2 ; i++){
			for(int j=0;j<2;j++){
				assertEquals("Eval function does not work",expectedOut[i][j],actualOut[i][j],TestConstants.DOUBLE_EPSILON);
			}
		}
		
	}
	
	
	/**
	 * Test method for {@link TwoDMatrices#matrixMultiplication(double[][], double[][])}.
	 */
	@Test
	public void testMatrixMultiplication2() {
		//intialise
		double[][] mat1={ {1.30,	3.10},
						{-4.10,	2.25}};
		double[][] mat2={ {	0.1},
							{	-2.5}};
		double[][] expectedOut={    {-7.6200},
				   					{-6.0350}}; //from MATLAB
		double[][] actualOut={ {	0},
								{	0}};
		
		//run
		actualOut=TwoDMatrices.matrixMultiplication(mat1, mat2);
		
		//assert
		for(int i=0; i<1 ; i++){			
			assertEquals("Eval function does not work",expectedOut[i][0],actualOut[i][0],TestConstants.DOUBLE_EPSILON);			
		}
		
	}

	/**
	 * Test method for {@link TwoDMatrices#twoByTwoMatrixInversion(double[][])}.
	 */
	@Test
	public void testTwoByTwoMatrixInversion() {
		double[][] mat={ {0.7,	0.1},
				{4,	-2.5}};
		double[][] expectedOut={ {1.16279069767442,	0.0465116279069767},
				{1.86046511627907,	-0.325581395348837}}; //from MATLAB  	   
		double[][] actualOut={ {0,	0},
				{0,	0}};

		//run
		actualOut=TwoDMatrices.twoByTwoMatrixInversion(mat);

		//assert
		for(int i=0; i<2 ; i++){
			for(int j=0;j<2;j++){
				assertEquals("Mat inv function does not work",expectedOut[i][j],actualOut[i][j],TestConstants.DOUBLE_EPSILON);
}
}
	}

	/**
	 * Test method for {@link TwoDMatrices#calcDeterminantTwobyTwo(double[][])}.
	 */
	@Test
	public void testCalcDeterminantTwobyTwo() {
		double[][] mat={ {0.7,	0.1},
				{4,	-2.5}};
		double expectedOut=-2.15; //from MATLAB  	   
		double actualOut=0;

		//run
		actualOut=TwoDMatrices.calcDeterminantTwobyTwo(mat);
		//assert		
		assertEquals("Mat det function does not work",expectedOut,actualOut,TestConstants.DOUBLE_EPSILON);
	}
	
	/**
	 * Test method for {@link TwoDMatrices#convertArrayToColVector(double[])}
	 */
	@Test
	public void testConvertArrayToColVector(){
		//intialise
		double[] mat1={7.35,	-3.25};
				
		double[][] expectedOut={    {7.35},
									{-3.25}}; 
		double[][] actualOut={ {	0},
				{	0}};

		//run
		actualOut=TwoDMatrices.convertArrayToColVector(mat1);

		//assert
		for(int i=0; i<1 ; i++){			
			assertEquals("Eval function does not work",expectedOut[i][0],actualOut[i][0],TestConstants.DOUBLE_EPSILON);			
		}
	}

	/**
	 * Test method for {@link TwoDMatrices#convertColVectorToArray(double[][])}
	 */
	@Test
	public void convertColVectorToArray(){
		//intialise
		double[][] mat1={{7.35},	{-3.25}};
				
		double[] expectedOut={    7.35,-3.25}; 
		double[] actualOut={ 	0,		0};

		//run
		actualOut=TwoDMatrices.convertColVectorToArray(mat1);

		//assert
		for(int i=0; i<1 ; i++){			
			assertEquals("Eval function does not work",expectedOut[i],actualOut[i],TestConstants.DOUBLE_EPSILON);			
		}
	}

}
