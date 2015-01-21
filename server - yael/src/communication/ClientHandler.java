package communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import model.Model;
import model.MyModel;
import model.Problem;
import model.Solution;
import tasks.Task;

public class ClientHandler implements Task {
	
	private Socket someClient;
	private ArrayList<Socket> Clients= new ArrayList<Socket>(); 
	//private SolutionManager solutionsMap;
	

	
	
	public ClientHandler(Socket socket)
	{
		this.someClient = socket;
		//int i = Clients.size();
		Clients.add(socket);

	}

	@Override
	public void doTask() {
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		
		try {
			in = new ObjectInputStream(someClient.getInputStream());
			out = new ObjectOutputStream(someClient.getOutputStream());
			Problem problem = (Problem)in.readObject();
			
			Model model = new MyModel();
			model.selectDomains(problem.getDomain());
			System.out.println(problem.getDomain());
			
			model.selectAlgorithm(problem.getAlgorithmName());
			System.out.println(problem.getAlgorithmName());

			model.solveDomain();
			System.out.println("3");

			Solution solution = model.getSolution();
			System.out.println("Found solution :)");
			
			out.writeObject(solution);
			
			String problemDescription = solution.getProblemDescription();
			out.writeObject(problemDescription);	

			
		

			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			try {
				out.close();
				in.close();
				someClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}	
	}	
}
