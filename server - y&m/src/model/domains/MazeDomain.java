package model.domains;

import java.util.HashMap;

import model.algorithm.Action;
//import model.algorithm.Heuristic;
import model.algorithm.SearchDomain;
import model.algorithm.State;

public class MazeDomain implements SearchDomain {
	private int Maze[][];
	private int number_of_rows;
	private int number_of_columns;
	MazeState start, goal;
	
	public MazeState getStart() {
		return start;
	}

	public void setStart(MazeState start) {
		this.start = start;
	}
//C'tor that Creating a maze
	public MazeDomain(int start_x, int start_y, int[][] maze){
		number_of_rows = maze.length;
		number_of_columns = maze[0].length;
		Maze = new int[maze.length][maze[0].length];
		for (int i = 0; i < maze.length; i++)
		{
			for (int j = 0; j < maze[0].length; j++)
			{
				Maze[i][j] = maze[i][j];
			}
		}

		String state = "("+start_x+","+start_y+")";
		this.start = new MazeState(state);
		state = "(" + (maze.length-1) + "," + (maze[0].length - 1) + ")";
		this.goal = new MazeState(state);
		// /

		for (int l = 0; l < maze.length; l++)// לולאה שמדפיסה את המבוך
		{
			for (int j = 0; j < maze[0].length; j++)
			{
				System.out.print(Maze[l][j] + ",");
			}
			System.out.print("\n");
		}	
	}
	
	@Override
	//Defining actions maze
	public HashMap<Action, State> getAllPossibleMoves(State current) {
		
		HashMap<Action, State> moves=new HashMap<>();
		int x = ((MazeState)current).getX();
		int y =((MazeState)current).getY();
		MazeState nextState = null;	

//Specifications "steps"  in this maze
		
		//down
		//right
		if(x+1<number_of_columns)
		{
			if(Maze[y][x + 1]==0)
			{
				nextState=new MazeState("("+y+","+(x+1)+")");
				nextState.setPrice(current.getPrice()+1);
				nextState.setCamefrom(current);
				nextState.setCameWithAction(new Action("left"));
				moves.put(new Action("right"), nextState);
			}
		}
		//left
		//up
		if(y-1 >= 0)
		{
			if(Maze[y - 1][x]==0)	
			{
				nextState=new MazeState("("+(y-1)+","+x+")");
				nextState.setPrice(current.getPrice()+1);
				nextState.setCamefrom(current);
				nextState.setCameWithAction(new Action("down"));
				moves.put(new Action("up"), nextState);
			}
		}
		if(y+1<number_of_rows)
		{
			if(Maze[y+1][x]==0)	
			{		
				nextState=new MazeState("("+(y+1)+","+x+")");
				nextState.setPrice(current.getPrice()+1);						
				nextState.setCamefrom(current);
				nextState.setCameWithAction(new Action("up"));
				moves.put(new Action("down"), nextState);
			}
		}
		if(x-1 >= 0)
		{
			if(Maze[y][x - 1]==0)	
			{
				nextState=new MazeState("("+y+","+(x - 1)+")");
				nextState.setPrice(current.getPrice()+1);
				nextState.setCamefrom(current);
				nextState.setCameWithAction(new Action("right"));
				moves.put(new Action("left"), nextState);
			}
		}
				
		return moves;
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
		String maze = "";
		for(int y=0;y<number_of_rows;y++)//לולאה שמדפיסה את המבוך
		{
			for(int x=0;x<number_of_columns;x++)
			{
				maze += Maze[y][x] + ",";
			}
			maze += "\n";
		}
		return maze;
	
	}



}
