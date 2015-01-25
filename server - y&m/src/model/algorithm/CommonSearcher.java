package model.algorithm;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class CommonSearcher implements Searcher {

	protected PriorityQueue<State> openList;
	protected PriorityQueue<State> closedList; 
	
	@Override
	public ArrayList<Action> search(SearchDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	//Array returned by the "retroactive" track
	protected ArrayList<Action> generatePathToGoal(State state) {
		ArrayList<Action> actions = new ArrayList<Action>();
		
		while (state.camefrom != null){
		actions.add(0, state.cameWithAction);
		state = state.camefrom;			
		} 
	
			return actions;
	}

}
