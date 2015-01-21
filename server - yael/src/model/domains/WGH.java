package model.domains;

import model.algorithm.Heuristic;
import model.algorithm.State;

public class WGH implements Heuristic {
		public double getEvaluation(State state, State goal) {
			int sum=0;
			for(int i=0;i<state.getState().length();i++)
				if(state.getState().charAt(i)!=goal.getState().charAt(i))
					sum++;
			return sum;
	}
}
