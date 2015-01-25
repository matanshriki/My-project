package model;

import java.util.HashMap;

import model.algorithm.Heuristic;
import model.domains.MazeH;
import model.domains.PazzelH;
import model.domains.WGH;

public class HeuristicFactory {
	private HashMap<String,HeuristicsCreator> heuristics;
	
	//C'tor
	public HeuristicFactory()
	{
		heuristics= new HashMap<String,HeuristicsCreator>();
		heuristics.put("mazeH",new MazeCreator());
		heuristics.put("PazzelH",new PazzelCreator());
		heuristics.put("WGH",new WGCreator());

	}
	
	//generic function thet create heuristics Specific function
	public Heuristic creatHeuristics(String heuristicName)
	{
		HeuristicsCreator creator= heuristics.get(heuristicName);
		Heuristic h=null;
		if(creator!=null){
			h =creator.create();
		}
		return h;
	}
	
	
	//create HeuristicInterface & define Specifically Exercise for each domain
	private interface HeuristicsCreator
	{
		Heuristic create();
	}
	
	private class MazeCreator implements HeuristicsCreator
	{
		@Override
		public Heuristic create() {
			return new MazeH();
		}
		
	}
	
	private class PazzelCreator implements HeuristicsCreator
	{
		@Override
		public Heuristic create() {
			return new PazzelH();
		}
		
	}

	private class WGCreator implements HeuristicsCreator
	{
		@Override
		public Heuristic create() {
			return new WGH();
		}
		
	}


		
}

