package model;

import java.util.Observer;

import tasks.Task;

public interface Model extends Task {
	
	void selectDomain(String domainName);//method that let u choose one game by a string parameter
	void selectAlgorithm(String algorithmName);                 //choose Algorithm
	void solveDomain();                                         //turn on the algorithm to solve the problem
	//Solution getSolution(int i);//return the solution problem
	//Solution getSolution(Object problem);//return the solution problem

	
	void addObserver(Observer o);
	
	Thread getT(int i);
	
	void UpdateChange(String string);
	void Saftyexit();
	/*
	 * return the index of the new thread
	 */
	int addAndStart(Thread thread);
	
	Solution getSolution();
	String getProblemDescription();
}
