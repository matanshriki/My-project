package model.domains;

import java.util.HashMap;

import model.algorithm.SearchDomain;
import model.domains.MazeDomain;
import model.domains.PuzzleDomain;
import model.domains.WGDomain;


public class SearchDomainFactory {
	
	private HashMap<String, DomainsCreator> Domains;
	private String[] arr;
	
	//C'tor (that maka HashMap of the domains & in this case Sets 2 domains)
	public SearchDomainFactory()
	{
		Domains = new HashMap<String, DomainsCreator>();
		Domains.put("MazeDomain", new MazeCreator());
		Domains.put("PuzzleDomain", new PuzzleCreator());
		Domains.put("WGDomain", new WGCreator());
	}
	//Here we create The domains (again, in this case this c'tor create 2 domains)
	public SearchDomain createDomain(String domain_string)
	{
		arr = domain_string.split("-");					
		DomainsCreator creator = Domains.get(arr[0]);
		
		SearchDomain domain = null;
		if (creator != null)  {
			domain = creator.create();			
		} 
		return domain;
	}
	
	
	//create DomainsCreatorInterface & define Specifically Exercise for each Domain
	private interface DomainsCreator
	{
		SearchDomain create();
	}
	
	private class MazeCreator implements DomainsCreator
	{
		public SearchDomain create() 
		{
			String maze_string = arr[3];
			String[] maze_rows = maze_string.split("\n");
			int maze_length = maze_rows.length;
			String[] maze_row_splitted = maze_rows[0].split(",");
			int maze_width = maze_row_splitted.length;
			int[][] maze = new int[maze_length][maze_width];
			
			for(int i = 0; i < maze_length; ++i){
				maze_row_splitted = maze_rows[i].split(",");
				for(int j = 0; j < maze_row_splitted.length; ++j){
					if(maze_row_splitted[j].equals("1")){
						maze[i][j] = 1;
					}else{
						maze[i][j] = 0;
					}
				}
			}
			
			return new MazeDomain(Integer.valueOf(arr[1]), Integer.valueOf(arr[2]), maze);
		}		
	}

	private class PuzzleCreator implements DomainsCreator
	{
		@Override
		public SearchDomain create()
		{
			return new PuzzleDomain(arr[1]);
		}
//private void PazzelDomain(String string) { } 		
	}
	
	private class WGCreator implements DomainsCreator
	{
		@Override
		public SearchDomain create() 
		{
		return new WGDomain(arr[1], arr[2]);
		}	
	}
	/*
	private class PazzelCreator implements DomainsCreator
	{
		@Override
		public SearchDomain create()
		{
			return new PazzelDomain();
		}
		
		public SearchDomain create(String stringPazzel)
		{
			if (stringPazzel!=null)
			{
				return new PazzelDomain(stringPazzel);
			}
			return new PazzelDomain();
		}
	}
*/
	
	
}



