package model;

import java.util.ArrayList;
import java.util.Observable;

import model.algorithm.SearchDomain;
import communication.Client;

public class MyModel extends Observable implements Model {
	
	private SearchDomain domain;
	private SearchDomainFactory domainsFactory;
	private Problem problem;
	private Solution solution;
	private String problemDescription;
	
	private ArrayList<Solution> solutions = new ArrayList<Solution>();
	protected ArrayList<Thread> threads = new ArrayList<Thread>();
	
	public Thread getT(int i){
		return threads.get(i);
	}
	
	//C'tor
	public MyModel()
	{
		domainsFactory = new SearchDomainFactory();
		problem = new Problem();

	}

	@Override
	public void selectDomain(String domainName) {
		domain = domainsFactory.createDomain(domainName);	
		String pd = domain.getProblemDescription();
//		String[] arr = domainName.split("-");
//		String domainv = arr[0];
		problem.setDomain(domain);
		System.out.println("setDomain inproblem here " + domainName); /////
		problem.setProblemDescription(pd);
		problemDescription = pd; 


	}

	public void selectAlgorithm(String algorithmName) {
		problem.setAlgorithmName(algorithmName);
		System.out.println("setAlgo in problem here: " + problem.getAlgorithmName()); /////
		

	}
	
//	@Override
//	public void selectAlgorithm(String algorithmName) {
//		algorithm = algorithmsFactory.createAlgorithm(algorithmName);
//	}

	@Override
	public void solveDomain()
	{
		
		Client client = new Client();
		Solution solution = client.getSolution(problem);
		problemDescription = solution.getProblemDescription();
		System.err.println("here");

		solutions.add(solution);
		System.out.println(solutions.size());	 		

		UpdateChange("DisplaySolution " );
	}

	@Override
	public Solution getSolution() {

		return solution;
		}
	
	public void doTask() {
		solveDomain();	
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


	public SearchDomain getDomain() {
		return domain;
	}
	
	@Override
	public void UpdateChange(String string) {
		this.setChanged();
		this.notifyObservers(string);
	}

	@Override
	public String getProblemDescription() {
		return problemDescription;
	}
	
	public void setProblemDescription(String domain) {
		domain = problemDescription;
System.out.println(domain + " 0");	}
}

