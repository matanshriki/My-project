package model.domains;

import model.algorithm.Heuristic;
import model.algorithm.State;

public class PazzelH implements Heuristic
{
	@Override
	public double getEvaluation(State state, State goal)
	{
		int sum=0;
		int [] Numstate = state.split();
		int [] goalNum = goal.split();
		for(int i=0;i<(Numstate.length);i++)
			if(Numstate[i]==goalNum[i])
			{sum++;}
		return (-sum*sum*sum*sum);
	}
}