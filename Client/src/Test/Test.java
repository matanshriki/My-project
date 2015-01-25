package Test;
import java.util.ArrayList;

import model.algorithm.Action;
import model.algorithm.Astar;
import model.algorithm.BFS;
import model.algorithm.Heuristic;
import model.algorithm.SearchDomain;
import model.algorithm.Searcher;
import model.domains.MazeDomain;
import model.domains.MazeH;
import model.domains.PazzelDomain;
import model.domains.PazzelH;
import model.domains.WGDomain;
import model.domains.WGH;


public class Test {

	public static void main(String[] args) {
		
//define two variables to calculate the time function 
		long starttime;
		long endtime;
		
//Setting up the Domain you wish to review
		SearchDomain domain=new MazeDomain("(0,0)", "(5,5)" ,6,6,5);
		//SearchDomain domain=new PazzelDomain();//defult pazzel
		//SearchDomain domain=new PazzelDomain("2,4,6,8,0,1,3,5,7,");
		//SearchDomain domain=new WordGameDomain("veLo", "Love");

//Heuristic function definition relevant to the Domain we chose first
		MazeH h= new MazeH();
		//PazzelH h= new PazzelH();
		//WGH h= new WGH();
		
//Selection algorithm
		Searcher s=new Astar(h);
		//Searcher s=new BFS();
		
//Returns an array of operations that lead to a solution+ The Time taken
		starttime=System.currentTimeMillis();
		ArrayList<Action> actions=s.search(domain);
		endtime=System.currentTimeMillis();
		System.out.println("The time taken by the algorithm to find the solution:"+((endtime-starttime)%1000)+"ms");
		for(Action a : actions)
			System.out.println(a);
		
//Not a generic function call, showing us all the combinations of resolution algorithm Domains
		NonGenericTest();
		
}
		
	
	private static void NonGenericTest() {
		//define two variables to calculate the time function 
				long starttime;
				long endtime;
		
				//Algorithms Creators
				Searcher BFS= new BFS();


				Heuristic MazeH= new MazeH();
				Heuristic WGH=new WGH();
				Heuristic PazzelH=new PazzelH();
				
				Searcher AstarMaze= new Astar(MazeH);
				Searcher AstarPazzel= new Astar(PazzelH);
				Searcher AstarWG= new Astar(WGH);

				
				//solveMazeGame
				System.out.println("Maze solution Astar algorithm:");
				SearchDomain MazeDomain=new MazeDomain("(0,0)", "(5,5)" ,6,6,5);

				starttime=System.currentTimeMillis();
				ArrayList<Action> MazeAstaractions=AstarMaze.search(MazeDomain);
				endtime=System.currentTimeMillis();
				System.out.println("The time taken by the algorithm to find the solution:"+((endtime-starttime)%1000)+"ms");
				System.out.println("\n");

				for(Action a : MazeAstaractions)
					System.out.println(a);

				System.out.println("\n");

				System.out.println("Maze solution BFS algorithm:");
				starttime=System.currentTimeMillis();
				ArrayList<Action> MazeBFSactions=BFS.search(MazeDomain);
				endtime=System.currentTimeMillis();
				System.out.println("The time taken by the algorithm to find the solution:"+((endtime-starttime)%1000)+"ms");
				System.out.println("\n");

				for(Action a : MazeBFSactions)
					System.out.println(a);
				
				System.out.println("\n");

				
				//solvePazzelGame
				System.out.println("Pazzel solution Astar algorithm\n");
				SearchDomain PazzelDomain=new PazzelDomain("2,4,6,8,0,1,3,5,7");

				starttime=System.currentTimeMillis();
				ArrayList<Action> PazzelAstaractions=AstarPazzel.search(PazzelDomain);
				endtime=System.currentTimeMillis();
				System.out.println("The time taken by the algorithm to find the solution:"+((endtime-starttime)%1000)+"ms");
				System.out.println("\n");

				for(Action a : PazzelAstaractions)
					System.out.println(a);
				
				System.out.println("\n");

				
				System.out.println("Pazzel solution BFS algorithm:");
				starttime=System.currentTimeMillis();
				ArrayList<Action> PazzelBFSactions=BFS.search(PazzelDomain);
				endtime=System.currentTimeMillis();
				System.out.println("The time taken by the algorithm to find the solution:"+((endtime-starttime)%1000)+"ms");
				System.out.println("\n");

				for(Action a : PazzelBFSactions)
					System.out.println(a);
				
				System.out.println("\n");

				
				
				//solveWordGameGame
				System.out.println("WordGame solution Astar algorithm");
				SearchDomain WGDomain=new WGDomain("veLo", "Love");

				starttime=System.currentTimeMillis();
				ArrayList<Action> WGAstaractions=AstarWG.search(WGDomain);
				endtime=System.currentTimeMillis();
				System.out.println("The time taken by the algorithm to find the solution:"+((endtime-starttime)%1000)+"ms");
				System.out.println("\n");

				for(Action a : WGAstaractions)
				System.out.println(a);
				
				System.out.println("\n");

						
				System.out.println("WordGame solution BFS algorithm:");
				starttime=System.currentTimeMillis();
				ArrayList<Action> WGBFSactions=BFS.search(WGDomain);
				endtime=System.currentTimeMillis();
				System.out.println("The time taken by the algorithm to find the solution:"+((endtime-starttime)%1000)+"ms");
				System.out.println("\n");

				for(Action a : WGBFSactions)
					System.out.println(a);
						
				System.out.println("\n");

	}

}


/*
((Astar)s).setH(h);
s.getH(h);
*/
