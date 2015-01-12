/**
 * 
 */
package ex3.control_parameters;

import ex3.exceptions.UnknownInputParameterException;

/**
 * @author eng-188do
 * Class to hold and read in the control parameters for tabu search
 */
public final class ControlParamsTS extends ControlParams {

	//Members
	private int intensifyCounter; //after how long do we intensify
	private int diversifyCounter; //after how long do we diversify
	private int reduceCounter; //when to reduce the increment size
	private double intensificationFactor; //how much to reduce the increment size by
	private double initialIncrement; //initial increment size.
	private int stmSize; //short term memory size
	private int mtmSize; //medium term memory size
	private int diversify2; //counter for diversifying the second time. can set negative if don't want to use.
	private int diversifyStop; //counter for when to stop diversifying. if do not want to use just set really big.

	
	//Getters	
	/**
	 * @return the diversify2
	 */
	public int getDiversify2() {
		return diversify2;
	}


	/**
	 * @return the diversifyStop
	 */
	public int getDiversifyStop() {
		return diversifyStop;
	}


	/**
	 * @return the stmSize
	 */
	public int getStmSize() {
		return stmSize;
	}


	/**
	 * @return the mtmSize
	 */
	public int getMtmSize() {
		return mtmSize;
	}


	
	

	/**
	 * @return the intensifyCounter
	 */
	public int getIntensifyCounter() {
		return intensifyCounter;
	}


	/**
	 * @return the diversifyCounter
	 */
	public int getDiversifyCounter() {
		return diversifyCounter;
	}


	/**
	 * @return the reduceCounter
	 */
	public int getReduceCounter() {
		return reduceCounter;
	}


	/**
	 * @return the intensificationFactor
	 */
	public double getIntensificationFactor() {
		return intensificationFactor;
	}




	/**
	 * @return the initialIncrement
	 */
	public double getInitialIncrement() {
		return initialIncrement;
	}

	//Methods
	/* (non-Javadoc)
	 * @see ex3.ControlParameters.ControlParams#sortLineData(java.lang.String[])
	 */
	@Override
	protected void sortLineData(String[] lineData) {
		switch( lineData[0].toLowerCase() ){ 
			case "intensify":
				this.intensifyCounter=Integer.parseInt(lineData[1]);
				break;
			case "diversify":
				this.diversifyCounter=Integer.parseInt(lineData[1]);				
				break;
			case "reduce":
				this.reduceCounter=Integer.parseInt(lineData[1]);
				break;
			case "intensification factor":
				this.intensificationFactor=Double.parseDouble(lineData[1]);
				break;
			case "initial increment":
				this.initialIncrement=Double.parseDouble(lineData[1]);
				break;
			case "medium term memory":
				this.mtmSize=Integer.parseInt(lineData[1]);
				break;
			case "short term memory":
				this.stmSize=Integer.parseInt(lineData[1]);
				break;
			case "diversify two":
				this.diversify2=Integer.parseInt(lineData[1]);
				break;
			case "diversify stop":
				this.diversifyStop=Integer.parseInt(lineData[1]);
				break;
			default:
				throw new UnknownInputParameterException("Cannot yet take in that input");		
		}

	}
	
	/**
	 * Checks that the control parameters makes sense
	 * @return true if they do make sense, false otherwise
	 */
	public boolean checkValid(){
		boolean returnVal=true;
		if (intensifyCounter>=diversifyCounter)
			returnVal=false;
		if (reduceCounter<diversifyCounter)
			returnVal=false;
		if (intensificationFactor>1 || intensificationFactor<0)
			returnVal=false;
		if (initialIncrement<0)
			returnVal=false;
		if (diversifyStop<0)
			returnVal=false;
		
		return returnVal;
	}

}
