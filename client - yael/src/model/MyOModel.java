package model;


import java.util.ArrayList;
import java.util.Observable;

import communication.Client;

public class MyModel extends Observable implements Model {
	
	protected ArrayList<Thread> threads = new ArrayList<Thread>();
	//protected HashMap<Problem,Solution> solutions1 = new  HashMap<Problem,Solution>();
	protected ArrayList<Solution> solutions = new ArrayList<Solution>();
	

	
	public Thread getT(int i){
		return threads.get(i);
	}
	
	private Problem problem;
	private Solution solution;
	
	//C'tor
	public MyModel()
	{
		problem = new Problem();
	}

	@Override
	public void selectDomain(String domain) {
		// domain = domainFactory.createDomain(domainName)
//		String[] arr = DomainName.split("-");
//		String domainName = arr[0];
		problem.setDomain(domain);
	}

	@Override
	public void selectAlgorithm(String algorithmName) {
		problem.setAlgorithmName(algorithmName);
	}

	@Override
	public void solveDomain()
	{
		Client client = new Client();
		//add to Clients?
		Solution solution = client.getSolution(problem);
		//System.out.println("step1 "+problem.toString());

		solutions.add(solution);
		System.out.println(solutions.size());	 		

		UpdateChange("DisplaySolution " );
		//UpdateChange("DisplaySolution " + solutions.size());

	}
		
//	public Solution getSolution(int i) {
//			System.out.println("test");
//			solutions.get(i);
//			System.out.println("test-problem "+ i);
//			Solution solution = solutions.get(i-1);
//			System.out.println(solutions.get(i-1));
//			System.out.println("present solution "+ solution);
//			return solutions.get(i-1);
//		}
		@Override
	public Solution getSolution() {
			// TODO Auto-generated method stub
			return solution;
		}
	
	public void doTask() {
		solveDomain();	
	}


//////////////////
	public void UpdateChange(String string) {
		this.setChanged();
		this.notifyObservers(string);			
	}
	
	@Override
	public void Saftyexit()
	{
		for (Thread t : threads)
		{
			t.interrupt();
		}
	}
	@Override
	public int addAndStart(Thread thread)
	{
		threads.add(thread);
		thread.start();
		return threads.size();
	}

}
