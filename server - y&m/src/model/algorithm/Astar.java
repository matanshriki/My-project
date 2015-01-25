package model.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * this class is the algorithm A-star.
 * @author Matan Shriki
 *
 */

public class Astar extends CommonSearcher {
	
	/**
	 * Define heuristic function And creating it generically
	 */
	Heuristic h;
	public Astar (Heuristic h)
	{
		this.h=h;
	}

	/**
	 * Create a set of actions
	 */
	public ArrayList<Action> search(SearchDomain domain) 
	{
		openList = new PriorityQueue<State>();
		closedList = new PriorityQueue<State>(); 
		
		double newPathPrice=0;
		State goal=domain.getGoalState();
		HashMap<State,Double> g_score=new HashMap<>();
		State start=domain.getStartState();
        start.setCamefrom(null);
        g_score.put(start, 0.0);
        this.openList.add(domain.getStartState());
        
		
      start.setPrice(0+h.getEvaluation(start,goal)); // price is f(x) = g_score + h_score

      
		while (!openList.isEmpty())
		{		
			State state = openList.poll(); 
			
			if (state.equals(domain.getGoalState()))
			{
				return generatePathToGoal(state);
			}
			
			closedList.add(state);
		
			/**
			 * Depending on the activity algorithm, we will search at this time all my potential neighbors \ All possible actions
			 */
			HashMap<Action, State> neighbours=domain.getAllPossibleMoves(state);
			for (Action a : neighbours.keySet())
			{
				State nextState = neighbours.get(a);
				if (closedList.contains(nextState))
				{
					continue;
				}
				newPathPrice = g_score.get(state)+a.getPrice();

				/**
				 * Assuming we found the next best move for us, we set its values
				 */
				if (!openList.contains(nextState) || newPathPrice<nextState.getPrice())
				{
					nextState.setCamefrom(state); 
					nextState.setPrice(h.getEvaluation(nextState,goal)+newPathPrice);
					nextState.setCameWithAction(a);
					g_score.put(nextState, newPathPrice);
					if(!openList.contains(nextState) )
					openList.add(nextState);
				}	
			}
		}
		
		return null;
	}
}
