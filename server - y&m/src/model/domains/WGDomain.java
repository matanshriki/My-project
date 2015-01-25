package model.domains;

import java.util.HashMap;

import model.algorithm.Action;
import model.algorithm.Heuristic;
import model.algorithm.SearchDomain;
import model.algorithm.State;
import model.domains.WGH;

public class WGDomain implements SearchDomain {

	WGH	h;
	
	public Heuristic getH() {return h;}
	public void setH(Heuristic h) {this.h = (WGH)h;}

	WGState start,goal;
	
	//C'tor
	public WGDomain(String start,String goal) {
		h=new WGH();
		this.start=new WGState(start);
		this.goal=new WGState(goal);		
	}

	@Override
	public HashMap<Action, State> getAllPossibleMoves(State current) {
		
		HashMap<Action, State> moves=new HashMap<>();

		// char arrays to work on
		char[] chars=current.getState().toCharArray();
		char[] tmpChars=chars.clone();

		for(int i=0;i<chars.length;i++)
			for(int j=i+1;j<chars.length;j++){
								
				switchChars(tmpChars, i, j);
				
				// generate the new action and resulting state
				Action a=new Action("switch '"+chars[i]+"'("+i+") and '"+chars[j]+"'("+j+")");				
				WGState newState=new WGState(new String(tmpChars));
				// put them in the hash map
				moves.put(a, newState);
				
				// switch back to original order
				switchChars(tmpChars, i, j);
			}
		return moves;
	}
	
	//External function that prints two cell array
	private void switchChars(char[] chars,int i, int j){
		char tmp=chars[i];
		chars[i]=chars[j];
		chars[j]=tmp;
	}
	@Override
	public State getStartState() {
		return start;
	}

	@Override
	public State getGoalState() {
		return goal;
	}
	@Override
	public String getProblemDescription() {
		// TODO Auto-generated method stub
		return null;
	}
}
