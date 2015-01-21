package model.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class BFS extends CommonSearcher {
	public ArrayList<Action> search(SearchDomain domain) 
	{
		openList = new PriorityQueue<State>();
		closedList = new PriorityQueue<State>();
		//adding to the open list start state
		this.openList.add(domain.getStartState()); 
		
		while (!openList.isEmpty())
		{
			State state = openList.poll();
			closedList.add(state);
			
			//If the current is equal to the target,return the track
			if (state.equals(domain.getGoalState()))
			{
				ArrayList<Action> actions = generatePathToGoal(state);
				return actions;
			}
			
			//Depending on the activity algorithm, we will search at this time all my potential neighbors \ All possible actions
			HashMap<Action, State> nextStates = domain.getAllPossibleMoves(state);
			for (Action a : nextStates.keySet())
			{
				State nextState = nextStates.get(a);
				double newPathPrice = state.getPrice() + a.getPrice();
				if (!openList.contains(nextState) && !closedList.contains(nextState))
				{
					nextState.setCamefrom(state);
					nextState.setCameWithAction(a);
					nextState.setPrice(newPathPrice);
					openList.add(nextState);
				}
				else
				{					
					if (newPathPrice < nextState.getPrice())
					{
						if (!openList.contains(nextState))
							openList.add(nextState);
						else
						{
							openList.remove(nextState);
							nextState.setPrice(newPathPrice);
							openList.add(nextState);
							
						}
					}
				}
					
			}
		}	
				
		return null;
	}
}
