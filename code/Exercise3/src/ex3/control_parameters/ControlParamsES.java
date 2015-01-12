/**
 * 
 */
package ex3.control_parameters;

import ex3.exceptions.UnknownInputParameterException;


/**
 * @author eng-188do
 *
 */
public final class ControlParamsES extends ControlParams {

	//Members
	private double tauP; //defined in notes
	private double tau; //defined in notes
	private double beta; //defined in notes
	private int parentChildRatio; //number of children for each parent
	private int numParents; //number of parents (and in a population post selection)
	private double var; //variances
	
	//getters/Setters
	/**
	 * @return the parentChildRatio
	 */
	public int getParentChildRatio() {
		return parentChildRatio;
	}


	/**
	 * @return the numParents
	 */
	public int getNumParents() {
		return numParents;
	}


	/**
	 * @param var the var to set
	 */
	public double getVar() {
		return var;
	}
	
	
	/**
	 * @return the tauP
	 */
	public double getTauP() {
		return tauP;
	}


	/**
	 * @return the tau
	 */
	public double getTau() {
		return tau;
	}


	/**
	 * @return the beta
	 */
	public double getBeta() {
		return beta;
	}



	

	/* (non-Javadoc)
	 * @see ex3.ControlParams#sortLineData(java.lang.String[])
	 */
	@Override
	protected void sortLineData(String[] lineData) {
		switch( lineData[0].toLowerCase() ){ //NOTE
			case "beta":
				this.beta=Double.parseDouble(lineData[1]);
				break;
			case "tau":
				this.tau=Double.parseDouble(lineData[1]);
				break;
			case "taup":
				this.tauP=Double.parseDouble(lineData[1]);
				break;
			case "variance":
				this.var=Double.parseDouble(lineData[1]);
				break;
			case "parents":
				this.numParents=Integer.parseInt(lineData[1]);
				break;
			case "parent child ratio":
				this.parentChildRatio=Integer.parseInt(lineData[1]);
				break;
			default:
				throw new UnknownInputParameterException("Cannot yet take in that input");		
		}

	}




}
