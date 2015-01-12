/**
 * 
 */
package ex3.file_operations;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

/**
 * @author eng-188do
 * Class to conveniently hold the code for outputting an array into a csv file.
 */
public class FileArrayWriter {
	/**
	 * Print out array information to a file csv format.
	 * @param distances distances to print out
	 * @param location which file to output to
	 */
	public static void printOutArray(double[][] array, String location){
		Writer writer = null;
		location+=".csv";
		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream( location), "utf-8"));
		    for(int i=0; i<array.length;i++){ //print out "i, $arrayValue"
		    	String line="";
		    	for(double item: array[i]){
		    		line+=Double.toString(item) +",";
		    	}
		    	writer.write(line +"\n");
		    }
		    
		} catch (IOException e) {
		  e.printStackTrace();
		} finally { //now close the file
		   try {
			   writer.close();
		   } catch (Exception e) {
			  e.printStackTrace(); 
		   }
		}
	}
	
	/**
	 * converts a list of fixed size double arrays to an fixed multi-dimensional double array.
	 * @param log : this is a list of double arrays (which should all be the same size (although I don't currently check this)
	 * @return the list in a 2d double array format.
	 */
	public static double[][] convertListToDouble(List<double[]> log){
		double[][] logArray = new double[log.size()][log.get(0).length];
		for(int i=0; i<log.size();i++){ 
			logArray[i]=log.get(i);
		}
		return logArray;
	}
	
	
	/*static void printOutArray(ArrayList<double[]> array, String location){
	 //TODO:remove this dupliacte code
		Writer writer = null;
		location+=".csv";
		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream( location), "utf-8"));
		    for(int i=0; i<array.size();i++){ 
		    	String line="";
		    	for(double item: array.get(i)){
		    		line+=Double.toString(item) +",";
		    	}
		    	writer.write(line +"\n");
		    }
		    
		} catch (IOException e) {
		  e.printStackTrace();
		} finally { //now close the file
		   try {
			   writer.close();
		   } catch (Exception e) {
			  e.printStackTrace(); 
		   }
		}
	}*/
	

}
