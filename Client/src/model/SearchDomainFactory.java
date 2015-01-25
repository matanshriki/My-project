package model;

import java.util.HashMap;

import model.algorithm.SearchDomain;
import model.domains.MazeDomain;
import model.domains.PuzzleDomain;
import model.domains.WGDomain;


public class SearchDomainFactory {
	
	private HashMap<String, DomainsCreator> Domains;
	private String[] arr;
	
	//C'tor (that make HashMap of the domains & in this case Sets 2 domains)
	public SearchDomainFactory()
	{
		Domains = new HashMap<String, DomainsCreator>();
		Domains.put("MazeDomain", new MazeCreator());
		Domains.put("PuzzleDomain", new PuzzleCreator());
		Domains.put("WGDomain", new WGCreator());
	}
	//Here we create The domains (again, in this case this c'tor create 2 domains)
	public SearchDomain createDomain(String domainName)
	{
		arr= domainName.split("-");					
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
			return new MazeDomain(arr[1], arr[2],Integer.valueOf(arr[3]), Integer.valueOf(arr[4]),Integer.valueOf(arr[5]));
		}		
	}

	private class PuzzleCreator implements DomainsCreator
	{
		@Override
		public SearchDomain create()
		{
			if (arr.length>1) {
				return new PuzzleDomain(arr[1]);
			}
			return new PuzzleDomain();
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



