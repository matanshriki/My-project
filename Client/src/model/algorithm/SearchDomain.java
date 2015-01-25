package model.algorithm;

import java.util.HashMap;
//This interface Is a kind of "agreement" between the domain search algorithm Whereby, given the 3 "Terms" these concerns are resolved.
public interface SearchDomain {
	State getStartState();
	State getGoalState();
	HashMap<Action,State> getAllPossibleMoves(State current);
	
	String getProblemDescription();
}
