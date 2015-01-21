package model;

import java.util.HashMap;

import model.algorithm.Astar;
import model.algorithm.BFS;
import model.algorithm.Heuristic;
import model.algorithm.Searcher;

public class SearchAlgorithmsFactory {
	private HashMap<String, AlgorithmCreator> algorithms;
	private String[] arr;
	
	//C'tor (that maka HashMap of the algorithms & in this case Sets 2 algorithms)
	public SearchAlgorithmsFactory()
	{
		algorithms = new HashMap<String, AlgorithmCreator>();
		algorithms.put("BFS", new BFSCreator());
		algorithms.put("Astar", new AstarCreator());
	}
	
	//Here we create The algorithms (again, in this case this c'tor create 2 algorithms)
	public Searcher createAlgorithm(String algorithmName)
	{
		arr=algorithmName.split("-");

		AlgorithmCreator creator = algorithms.get(arr[0]);
		Searcher searcher = null;
		if (creator != null)  {
			searcher = creator.create();			
		}
		return searcher;
	}
	
	
	//create AlgorithmCreatorInterface & define Specifically Exercise for each algorithm
	private interface AlgorithmCreator
	{
		Searcher create();
	}
	
	private class BFSCreator implements AlgorithmCreator
	{
		@Override
		public Searcher create() {
			return new BFS();
		}		
	}
	
	private class AstarCreator implements AlgorithmCreator
	{
		public Searcher create() {
			 
			HeuristicFactory heuristicFactory=new HeuristicFactory();
			Heuristic h = heuristicFactory.creatHeuristics(arr[1]);		
			 return new Astar(h);
		}		
	}
}
