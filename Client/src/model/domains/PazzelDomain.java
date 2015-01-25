package model.domains;

import java.util.ArrayList;
import java.util.HashMap;

import model.algorithm.Action;
import model.algorithm.SearchDomain;
import model.algorithm.State;
import model.domains.PazzelState;


public class PazzelDomain implements SearchDomain {
	public int  a=0;
	PazzelState start,goal;
	
	//C'tor - Getting open string (representing the puzzle)
	public PazzelDomain() 
	{
		String start = new String ("");
		ArrayList<Integer> number = new ArrayList<Integer>() ;
				
		for (int i = 0; i < 8; i++)	{number.add(i+1);}
		
		int num=0;
		for (int i = 0; i<8; i++)
		{
			num = ((int)(Math.random()*(8-i)));
			start = (start+number.get(num)+",");
			number.remove(num);
		}
		start+="0";
		this.goal = new PazzelState("1,2,3,4,5,6,7,8,0");
		this.start = new PazzelState(start);
		printThePazzel();
	}
	
	//Default C'tor - does not accept values (an "opening") and defines the domain random
		public PazzelDomain(String start) 
		{
			this.start = new PazzelState(start);
			this.goal = new PazzelState("1,2,3,4,5,6,7,8,0");
			printThePazzel();	
		}
	
	@Override
	//Defining actions Pazzel
	public HashMap<Action, State> getAllPossibleMoves(State current) 
	{
		HashMap<Action, State> moves=new HashMap<>();
		int [] Board = current.split();
		
		int i=0;
		for (; Board[i]!=0; i++)	{}
		
		//Specifications "steps"  in this Pazzel (Based on data from the external function that checks availability Location)
		if (isAdmissibleLocation(i-3)) 
		{
			Action down = new Action("down");
			down.setPrice(1);a++;
			current=new PazzelState (Builder(i,(-3),Board));
			moves.put(down, current);
		}
		if (isAdmissibleLocation(i+3)) 
		{
			Action up = new Action("up");
			up.setPrice(1);a++;
			current=new PazzelState (Builder(i,3,Board));

			moves.put(up, current);
		}
		if (isAdmissibleLocation(i-1) && i!=0 && i!=3 && i!=6 ) 
		{
			Action right = new Action("right");
			right.setPrice(1);a++;
			current= new PazzelState (Builder(i,(-1),Board));
			moves.put(right,current);
		}
		if (isAdmissibleLocation(i+1) && i!=2 && i!=5 && i!=8) 
		{
			Action left = new Action("left");
			left.setPrice(1);a++;
			current=new PazzelState (Builder(i,1,Board));
			moves.put(left, current);
		}
		return moves;
	}
	
	private String Builder(int zero, int move, int[] numBoard)
	{
		String state = new String ("");
		int i=0;
		numBoard[zero]= numBoard[zero+move];
		numBoard[zero+move]= 0;
		for (; i < numBoard.length-1; i++)
		{
			state = state+numBoard[i]+",";
		}
		state = state+numBoard[i];
		numBoard[zero+move]= numBoard[zero];
		numBoard[zero]= 0;
		return state;
	}
	
	// Function that checks if a qualifying position
	private Boolean isAdmissibleLocation (int move)
	{
		if (move>=0 && move <=8)
		{
			return true;
		}
		return false;
	}
	
	
	//Function that prints the puzzle 
	public void printThePazzel()
	{
		int [] Board = start.split();
		int i = 0;
		for (; i < 3; i++)	
			{System.out.print(Board[i]+",");}	System.out.println();
		for (; i < 6; i++)	
			{System.out.print(Board[i]+",");}	System.out.println();
		for (; i < 9; i++)	
			{System.out.print(Board[i]+",");}	System.out.println("\n");	
	}
	
	@Override
	public State getStartState()
	{
		return start;
	}

	@Override
	public State getGoalState()
	{
		return goal;
	}

	@Override
	public String getProblemDescription() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

