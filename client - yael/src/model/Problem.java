package model;

import java.io.Serializable;

import model.algorithm.SearchDomain;

public class Problem implements Serializable {
	//private String domain;
	private SearchDomain domain;
	private String algorithmName;
	private String problemDescription;
	
	
	public SearchDomain getDomain() {
		return domain;
	}
	public void setDomain(SearchDomain domain) {
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
