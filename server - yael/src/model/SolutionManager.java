package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class SolutionManager {
	
	public HashMap<String, Solution> solutionsMap;
	public static SolutionManager instance = null;
	private static final String FILE_NAME = "resource/solutions.dat";

	protected SolutionManager() {	
		solutionsMap = new HashMap<String, Solution>();
	}
	
	public static SolutionManager getInstance() {
		if (instance == null) {
			instance = new SolutionManager();			
		}
		return instance;
	}
	
	public void addSolution(Solution solution) {
		solutionsMap.put(solution.getProblemDescription(), solution);
	}
	
	public Solution getSolution(String problemDescription) {
		return solutionsMap.get(problemDescription);
	}
	
	public void saveSolutionsInFile() {
		FileOutputStream out = null;
		ObjectOutputStream oos = null;
		try {
			out = new FileOutputStream(FILE_NAME);
			oos = new ObjectOutputStream(out);
			oos.writeObject(solutionsMap);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}


//protected SolutionManager() {	
//	solutionsMap = new HashMap<String, Solution>();
//	File f = new File(FILE_NAME);
//	//readFromFile(f);
//	if(f.exists()) //load data from file - if 
//	{
//	    try
//	    {
//	    	FileInputStream file = new FileInputStream(FILE_NAME);
//			ObjectInputStream in = new ObjectInputStream(file);
//			solutionsMap = (HashMap<String, Solution>)in.readObject();
//	    	file.close();
//	    }
//	    catch (IOException e) {
//            e.printStackTrace();
//	    } catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} 
//	}	
//}
//
//public static SolutionManager getInstance() {
//	if (instance == null) {
//		instance = new SolutionManager();			
//	}
//	return instance;
//}
//
//public void addSolution(Solution solution) {
//	solutionsMap.put(solution.getProblemDescription(), solution);
//	saveSolutionsInFile();
//}
//
//public Solution getSolution(String problemDescription) {
//	return solutionsMap.get(problemDescription);
//}
//
//public void saveSolutionsInFile() {
//	FileOutputStream out = null;
//	ObjectOutputStream oos = null;
//	try {
//		out = new FileOutputStream(FILE_NAME);
//		oos = new ObjectOutputStream(out);
//		oos.writeObject(solutionsMap);
//		
//	} catch (FileNotFoundException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} finally {
//		if (out != null) {
//			try {
//				out.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//}
