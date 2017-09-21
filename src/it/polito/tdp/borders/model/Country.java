package it.polito.tdp.borders.model;

public class Country implements Comparable<Country>{
	
	private String stateAbb;
	private Integer cCode;
	private String stateName;
	private Integer neighbours;
	
	/**
	 * @param stateAbb
	 * @param cCode
	 * @param stateName
	 */
	public Country(String stateAbb, Integer cCode, String stateName) {
		super();
		this.stateAbb = stateAbb;
		this.cCode = cCode;
		this.stateName = stateName;
		this.neighbours = 0;
	}

	/**
	 * @return the stateAbb
	 */
	public String getStateAbb() {
		return stateAbb;
	}

	/**
	 * @param stateAbb the stateAbb to set
	 */
	public void setStateAbb(String stateAbb) {
		this.stateAbb = stateAbb;
	}

	/**
	 * @return the cCode
	 */
	public Integer getcCode() {
		return cCode;
	}

	/**
	 * @param cCode the cCode to set
	 */
	public void setcCode(Integer cCode) {
		this.cCode = cCode;
	}

	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * @param stateName the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cCode == null) ? 0 : cCode.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (cCode == null) {
			if (other.cCode != null)
				return false;
		} else if (!cCode.equals(other.cCode))
			return false;
		return true;
	}

	@Override
	public int compareTo(Country other) {
		return this.getcCode().compareTo(other.getcCode());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return stateAbb;
	}

	/**
	 * @return the neighbours
	 */
	public Integer getNeighbours() {
		return neighbours;
	}

	/**
	 * @param neighbours the neighbours to set
	 */
	public void setNeighbours(Integer neighbours) {
		this.neighbours = neighbours;
	}
	
	public String printInfo(){
		return this.stateAbb+" - "+this.stateName+" - "+this.neighbours;
	}

}
