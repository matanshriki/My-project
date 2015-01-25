package communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import model.Problem;
import model.Solution;

public class Client {
	private String serverAddress;
	private int port;
	private String problemDescription;
	private String domain;


	
	public Client(String serverAddress,int port) {
		this.serverAddress = serverAddress;
		this.port = port;
	}
	
	public Client() {
		this.serverAddress = "127.0.0.1";
		this.port = 5001;
	}
	
	@SuppressWarnings("resource")
	public Solution getSolution(Problem problem) {		
		Socket socket = null;
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		Solution solution = null;

		problem.getDomain();
		problem.getAlgorithmName();		
		
		String[] arr = problem.getDomain().split("-");
		String domainName = arr[0];

		try {
			socket = new Socket(serverAddress, port);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			out.writeObject(problem);	

			solution = (Solution)in.readObject();
			System.out.println("Found solution: " + solution.getActions());
			 
	//		problemDescription = (String) in.readObject();
			return solution;	
								
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally {
			
			try {
				out.close();
				in.close();
				//socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}	
		
		System.out.println("NO Found solution");

		return null;
		
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
}
