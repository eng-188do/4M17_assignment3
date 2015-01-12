package ex3.control_parameters;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;

/**
 * 
 */

/**
 * @author eng-188do
 * Base class for reading in control parameters.
 */
public abstract class ControlParams {
	/**
	 * Reads in csv file containing control parameters
	 * @param fileName
	 */
	public void readInFile(String fileName){
		BufferedReader br = null;
		String line = ""; //temp variable for holding lines
		String splitter = ","; //what splits our files up
	 
		try {	 //try reading in file
			br = new BufferedReader(new FileReader(fileName));
			while ((line = br.readLine()) != null) {
				String[] lineData = line.split(splitter);	//Split up inputs 
				sortLineData(lineData);	 
			}	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally { //try closing the file.
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	 
		System.out.println("Read in " + fileName);
	}
	
	/**
	 * Deals with the line data - specific to the kind of optimisation
	 * @param lineData
	 */
	protected abstract void sortLineData(String[] lineData);
}
