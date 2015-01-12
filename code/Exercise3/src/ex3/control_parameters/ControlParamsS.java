/**
 * 
 */
package ex3.control_parameters;

import ex3.exceptions.UnknownInputParameterException;

/**
 * @author eng-188do
 * Control Params for particle swarm algorithm
 */
public final class ControlParamsS extends ControlParams {

	//Members
	private double initialXVel; //initial x velocity of birds
	private double initialYVel; //initial y velocity of birds
	private double sSocial; //see p.171 Schneider & Kirkpatrick "Stochastic Optimisation"
	private double sCognition; //see p.171 Schneider & Kirkpatrick "Stochastic Optimisation"
	private double sAvoid; //see my report
	private double stiffness; //see p.171 Schneider & Kirkpatrick "Stochastic Optimisation"
	private double sNeighbour; //see p.171 Schneider & Kirkpatrick "Stochastic Optimisation"
	private double craziness; //amount to multiply random number from standard gaussian see p.172 Schneider & Kirkpatrick "Stochastic Optimisation"
	private int flockSize; //number of birds in flock.
	
	
	//Getters	
	/**
	 * @return the flockSize
	 */
	public int getFlockSize() {
		return flockSize;
	}


	/**
	 * @return the craziness
	 */
	public double getCraziness() {
		return craziness;
	}


	/**
	 * @return the initialXVel
	 */
	public double getInitialXVel() {
		return initialXVel;
	}


	/**
	 * @return the initialYvel
	 */
	public double getInitialYvel() {
		return initialYVel;
	}


	/**
	 * @return the sSocial
	 */
	public double getsSocial() {
		return sSocial;
	}



	/**
	 * @return the sCognition
	 */
	public double getsCognition() {
		return sCognition;
	}



	/**
	 * @return the sAvoid
	 */
	public double getsAvoid() {
		return sAvoid;
	}



	/**
	 * @return the stiffness
	 */
	public double getStiffness() {
		return stiffness;
	}



	/**
	 * @return the sNeighbour
	 */
	public double getsNeighbour() {
		return sNeighbour;
	}



	//Methods.

	/* (non-Javadoc)
	 * @see ex3.control_parameters.ControlParams#sortLineData(java.lang.String[])
	 * input:
	 * initial x velocity,#
		initial y velocity,#
		social,#
		cognition,#
		avoid,#
		stiffness,#
		neighbour,#
		craziness,#
		flock size,#
	 */
	@Override
	protected void sortLineData(String[] lineData) {
		switch( lineData[0].toLowerCase() ){ //NOTE
		case "initial x velocity":
			this.initialXVel=Double.parseDouble(lineData[1]);
			break;
		case "initial y velocity":
			this.initialYVel=Double.parseDouble(lineData[1]);				
			break;
		case "social":
			this.sSocial=Double.parseDouble(lineData[1]);
			break;
		case "cognition":
			this.sCognition=Double.parseDouble(lineData[1]);
			break;
		case "avoid":
			this.sAvoid=Double.parseDouble(lineData[1]);
			break;
		case "stiffness":
			this.stiffness=Double.parseDouble(lineData[1]);
			break;
		case "neighbour":
			this.sNeighbour=Double.parseDouble(lineData[1]);
			break;
		case "craziness":
			this.craziness=Double.parseDouble(lineData[1]);
			break;
		case "flock size":
			this.flockSize=Integer.parseInt(lineData[1]);
			break;
		default:
			throw new UnknownInputParameterException("Cannot yet take in that input");		
	}

	}

}
