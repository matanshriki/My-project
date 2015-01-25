package model;

import java.util.Observer;

import tasks.Task;

/**
 * This is the model.
 * @author Matan Shriki
 *
 */
public interface Model extends Task {
	
	/**
	 * method that let you choose the algorithm
	 * @param algorithmName This is the algorithm that you need to choose.
	 */
	void selectAlgorithm(String algorithmName);
	/**
	 * This method turn on the algorithm to solve the problem
	 */
	void solveDomain();
	/**
	 * This method actually return the solution problem
	 * @return The solution of the problem.
	 */
	Solution getSolution();
	
	void addObserver(Observer o);
	
	Thread getT(int i);
	void UpdateChange(String string);
	void Saftyexit();
	
	/**
	 * This method return the index of the new thread.
	 * @param thread This is the thread that the method get.
	 * @return The thread.
	 */
	int addAndStart(Thread thread);

	String selectDomains(String domainName);
	void selectDomain(String domainName);
}
