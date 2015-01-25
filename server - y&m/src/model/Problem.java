package model;

import java.io.Serializable;

import model.algorithm.SearchDomain;

public class Problem implements Serializable {
	private String domain;
	private SearchDomain domain1;
	private String algorithmName;
	private String problemDescription;

	public SearchDomain getDomain1() {
		return domain1;
	}

	public void setDomain(SearchDomain domain) {
		this.domain1 = domain;
	}
	public String getDomain() {
		return domain;
	}


	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getAlgorithmName() {
		return algorithmName;
	}

	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	public String getProblemDescription() {
		return problemDescription;
	}

	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}

}
