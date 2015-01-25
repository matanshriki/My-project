package model;

import java.util.ArrayList;
import java.util.Observable;

import model.algorithm.SearchDomain;
import model.domains.MazeState;
import communication.Client;

////client side
public class MyModel extends Observable implements Model {

	private SearchDomain domain;
	private SearchDomainFactory domainsFactory;
	private Problem problem;
	private Solution solution;
	private String problemDescription;
	private String domainCreateDescription;
	private String location;
	private ArrayList<Solution> solutions = new ArrayList<Solution>();
	protected ArrayList<Thread> threads = new ArrayList<Thread>();

	public Thread getT(int i) {
		return threads.get(i);
	}

	// C'tor
	public MyModel() {
		setDomainsFactory(new SearchDomainFactory());
		problem = new Problem();

	}

	@Override
	public void selectDomain(String domainName) {
		domain = domainsFactory.createDomain(domainName);
		String pd = domain.getProblemDescription();
		//problem.setDomain(domain);
		problem.setDomain(domainName);
	
		problem.setProblemDescription(pd);

		
		domainCreateDescription = domainName;
		//problem.setProblemDescription(pd);
		problemDescription = pd;

	

		String domainDescription = domainCreateDescription;
		//System.out.println("domainDescription= "+domainDescription);//

		String[] domainValues = domainDescription.split("-");

//		String domainWalls = domainValues[3];
//		//System.out.println("domainWalls= "+ domainWalls);//
//		int limit = Integer.parseInt(domainWalls) - 1;
//		//System.out.println("limit= "+ limit);//
//

	}


	
	public void selectAlgorithm(String algorithmName) {
		problem.setAlgorithmName(algorithmName);
	}

	@Override
	public void solveDomain() {

		Client client = new Client();
		solution = client.getSolution(problem);
		problemDescription = solution.getProblemDescription();

		solutions.add(solution);
		UpdateChange("DisplaySolution");
	}

	@Override
	public Solution getSolution() {

		return solution;
	}

	public void doTask() {
		solveDomain();
	}

	@Override
	public void Saftyexit() {
		for (Thread t : threads) {
			t.interrupt();
		}
	}

	@Override
	public int addAndStart(Thread thread) {
		threads.add(thread);
		thread.start();
		return threads.size();
	}

	public SearchDomain getDomain() {
		return domain;
	}

	public String checksmoves(String args) {
		String[] move = args.split("-");
		String currentstate = move[1];

		String[] extractValues = currentstate.split(",");
		String extractY = extractValues[1];

		return extractY;
		
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
		System.out.println(domain + " 0");
	}

	@Override
	public void setcurrentValues(String args) {

		String[] arr = args.split("-");
		String move = arr[0];
		System.out.println("move "+move);
		location = arr[1];
		System.out.println("current location "+location);
		String[] startValue = location.split(",");		
		String startX = startValue[0];
		startX.toCharArray();
		//System.out.println("X= "+x);
		String startY = startValue[1];
		startY.toCharArray();
		//		current.setState(location);
//		System.out.println("the current state is "+current);
//		domain.getAllPossibleMoves(current);
//		
		if (domainCreateDescription.startsWith("MazeDomain")){
			MazeState currentstate = new MazeState(location);
			domain.getAllPossibleMoves(currentstate);

//			//right
//			if(x+1<limit)
//			{			
//				if(Maze[y][x + 1]==0)
//				{
////					nextState=new MazeState("("+y+","+(x+1)+")");
////					nextState.setPrice(current.getPrice()+1);
////					nextState.setCamefrom(current);
////					nextState.setCameWithAction(new Action("left"));
////					moves.put(new Action("right"), nextState);
//				}
//			}
//			//up
//			if(y-1 >= 0)
//			{
//				if(Maze[y - 1][x]==0)	
//				{
//
////					nextState=new MazeState("("+(y-1)+","+x+")");
////					nextState.setPrice(current.getPrice()+1);
////					nextState.setCamefrom(current);
////					nextState.setCameWithAction(new Action("down"));
////					moves.put(new Action("up"), nextState);
//				}
//			}
//			//down
//			if(y+1<limit)
//			{
//				if(Maze[y+1][x]==0)	
//				{		
////					nextState=new MazeState("("+(y+1)+","+x+")");
////					nextState.setPrice(current.getPrice()+1);						
////					nextState.setCamefrom(current);
////					nextState.setCameWithAction(new Action("up"));
////					moves.put(new Action("down"), nextState);
//				}
//			}
//			//left
//			if(x-1 >= 0)
//			{
//				if(Maze[y][x - 1]==0)	
//				{
////					nextState=new MazeState("("+y+","+(x - 1)+")");
////					nextState.setPrice(current.getPrice()+1);
////					nextState.setCamefrom(current);
////					nextState.setCameWithAction(new Action("right"));
////					moves.put(new Action("left"), nextState);
//				}
//			}
//					
//			return moves;
//		}

	
	      }

}

	public SearchDomainFactory getDomainsFactory() {
		return domainsFactory;
	}

	public void setDomainsFactory(SearchDomainFactory domainsFactory) {
		this.domainsFactory = domainsFactory;
	}

	@Override
	public void setDomainString(String args) {
		problem.setDomain(args);
	}}
