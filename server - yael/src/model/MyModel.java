package model;

import java.util.ArrayList;
import java.util.Observable;

import model.algorithm.Action;
import model.algorithm.SearchDomain;
import model.algorithm.Searcher;

public class MyModel extends Observable implements Model {
	
	private SearchDomain domain;
	private Searcher algorithm;
	//private SearchDomainFactory domainsFactory;
	private SearchAlgorithmsFactory algorithmsFactory;
	private Solution solution;
	private Problem problem;
	private SolutionManager solutionManager;
	
	//private ArrayList<Solution> solutions = new ArrayList<Solution>();
	protected ArrayList<Thread> threads = new ArrayList<Thread>();
	
	public Thread getT(int i){
		return threads.get(i);
	}
	
	//C'tor
	public MyModel()
	{
		algorithmsFactory = new SearchAlgorithmsFactory();
		//domainsFactory = new SearchDomainFactory();
		solutionManager = SolutionManager.getInstance();
	}

//	@Override
//	public void selectDomain(String domainName) {
//		//domain = domainName;	
//		domain.getProblemDescription();
//	}
	
	public String selectDomains(String domainName) {
		return domainName;
	}

	@Override
	public void selectAlgorithm(String algorithmName) {
		algorithm = algorithmsFactory.createAlgorithm(algorithmName);
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

	@Override
	public void solveDomain() {
				
		String problemDescription = domain.getProblemDescription();
		this.solution = solutionManager.getSolution(problemDescription);
		
		if (solution == null) {		
			ArrayList<Action> actions = algorithm.search(domain);
			solution = new Solution();
			solution.setActions(actions);
			solution.setProblemDescription(problemDescription);//
		
			solutionManager.addSolution(solution);
		}
		
//		String problemDescription = domain.getProblemDescription();
//		this.solution = solutionManager.getSolution(problemDescription);
//		
//		if (solution == null) {		
//			ArrayList<Action> actions = algorithm.search(domain);
//			solution = new Solution();
//			solution.setActions(actions);
//			solution.setProblemDescription(problemDescription);//
//		
//			solutionManager.addSolution(solution);
//		}
	}

	@Override
	public Solution getSolution() {
		System.out.println(solution);
		
		return solution;

	}

	@Override
	public void doTask() {
		solveDomain();
	}

	@Override
	public void UpdateChange(String string) {
		// TODO Auto-generated method stub
		
	}


	
}


//@Override
//public void solveDomain()
//{
//	String problemDescription = domain.getProblemDescription();
//	int i = solutions.size();
//	solutions.add(i, null);
//	Solution solution = solutionManager.getSolution(problemDescription);
//
//	if (solution == null)
//	{
//		System.out.println("Calculating new solution");
//		ArrayList<Action> actions = algorithm.search(domain);
//		if (actions == null)
//		{ // if the search was not successful
//			actions = new ArrayList<Action>();
//			actions.add(new Action("There is No solution"));
//		}
//		solution = new Solution();
//		solution.setActions(actions);
//		solution.setProblemDescription(problemDescription);
//		solutionManager.addSolution(solution);
//		solutions.add(i, solution);
//
//	}
//	else if (solutions != null)
//	{
//		System.out.println("Found a solution!");
//		solutions.add(i, solution);
//		this.setChanged();
//		this.notifyObservers("already");
//	}
//}
