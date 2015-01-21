package model.algorithm;

import java.util.ArrayList;

public interface Searcher {
	//Returns an array steps to resolve
	public ArrayList<Action> search(SearchDomain domain);
}
