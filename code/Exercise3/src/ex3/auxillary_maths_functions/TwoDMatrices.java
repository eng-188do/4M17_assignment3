package ex3.auxillary_maths_functions;
/**
 * 
 */

/**
 * @author eng-188do
 *	Class to perform the matrix operations we need
 */
final public class TwoDMatrices {
	
	/**
	 * Naive (ie O(N^3)) Matrix multiplication
	 * @param one matrix 1
	 * @param two matrix 2
	 * @return product
	 */
	static public double[][] matrixMultiplication(double[][] one, double[][] two){
		int heightO=one.length; //height of matrix one
		int widthO=one[0].length;
		int heightT=two.length; //height of matrix 2
		int widthT=two[0].length;
		double[][] output=new double[heightO][widthT]; //MxN x NxL=MxL matrix
		
		if (widthO!=heightT) //check can multiply them
			throw new IllegalArgumentException("Matrix dimensions do not agree");
		
		//NOTE: as only multiply 2by 2 using simple, not Strassen, algorithm
		for(int i=0;i<heightO;i++){ //next rows
			for(int j=0; j<widthT; j++){ //next columns
				output[i][j]=0;
				for(int ii=0; ii<widthO; ii++){ //down row &col
					output[i][j]+=one[i][ii]*two[ii][j];
				}
			}
		}
		
		return output;
	}
	
	/**
	 * Inverse of two by two matrix 
	 * @param matrix
	 * @return inverse
	 */
	static public double[][] twoByTwoMatrixInversion(double[][] matrix){
		double det=calcDeterminantTwobyTwo(matrix);
		double[][] output=new double[2][2];
		
		//Next move the elements around/times by negative 1 and diveide by determinant
		output[0][0]=matrix[1][1]/det;
		output[1][1]=matrix[0][0]/det;
		output[0][1]=-matrix[0][1]/det;
		output[1][0]=-matrix[1][0]/det;
		
		return output;
		
	}
	
	
	/**
	 * Calculates the determinant of a two by two matrix
	 * @param matrix
	 * @return determinant
	 */
	public static double calcDeterminantTwobyTwo(double[][] matrix){
		if(matrix.length!=2 || matrix[0].length!=2)
			throw new IllegalArgumentException("Matrix is not 2 by 2.");
		
		double det=matrix[0][0]*matrix[1][1]-matrix[1][0]*matrix[0][1];
		//NOTE: we could have numerical issues here
		
		return det;
	}	
	
	/**
	 * Converts an 1d array to a 2d one where values get put in the first column.
	 * @param array 1d ie { , }
	 * @return 2d array ie { { , }, { , }}
	 */
	public static double[][] convertArrayToColVector(double[] array){
		double[][] vector=new double[array.length][1];
		for(int i=0; i<array.length; i++){
			vector[i][0]=array[i];
		}
		return vector;
	}
	
	/**
	 * Converts a 2d array with values only in its column to a 1d array
	 * @param vector 2d array ie { { , }, { , }}
	 * @return 1d array ie { , }
	 */
	public static double[] convertColVectorToArray(double[][] vector){
		if (vector[0].length>1)
			throw new IllegalArgumentException("Col vector has more than one column");
		
		double[] array= new double[vector.length];
		
		for(int i=0; i<vector.length; i++){
			array[i]=vector[i][0];
		}
		return array;
	}
	
}
