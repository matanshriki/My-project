package model.domains;

import model.algorithm.State;

public class MazeState extends State {

	public MazeState(String state) {
		super(state);
	}
	//Definition of a divided values(x,y), to calculate the moving domain function Heuristic
	public int getY()
	{
		String s = this.toString().split(",")[0];
		return Integer.valueOf(s.substring(1));
	
	}
	public int getX()
	{
		String s = this.toString().split(",")[1];
		return Integer.valueOf(s.substring(0, s.length() - 1));
	}

}
