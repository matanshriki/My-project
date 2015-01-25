package model.domains;

import model.algorithm.Heuristic;
import model.algorithm.State;

public class MazeH implements Heuristic {

	@Override
	public double getEvaluation(State state, State goal) {
		int gx = ((MazeState)goal).getX();
		int gy = ((MazeState)goal).getY();
		int sx = ((MazeState)state).getX();
		int sy = ((MazeState)state).getY();

		double xDistance = Math.pow(sx-gx,2);
		double yDistance = Math.pow(sy-gy,2);
		
		return Math.sqrt(xDistance+yDistance);

	}
}

	

	
	
	
