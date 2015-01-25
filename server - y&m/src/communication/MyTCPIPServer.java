package communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import tasks.TaskRunnable;

public class MyTCPIPServer {
	private ServerSocket server;
	private int port;
	private int numOfClients;
//	private ArrayList<Integer> Clients = new ArrayList<Integer>();
	private HashMap<Socket, Integer> Clients1 = new HashMap<Socket, Integer>();
	private int counter=0;

	private ExecutorService executor;
	private Thread thread;

	
	public MyTCPIPServer() {//מגדיר סרסב (פורט ומספר קליינטים מקסימלי)
		ServerProperties properties = HandleProperties.readProperties();
		this.port = properties.getPort();
		this.numOfClients = properties.getNumOfClients();
	
	}	
	
	public MyTCPIPServer(int port, int numOfClients) {
		this.port = port;
		this.numOfClients = numOfClients;
	}
	
	public void startServer() {
		try {
//			server = new ServerSocket(port);
			final ServerSocket server=new ServerSocket(port);// יוצר סרבר סוקט על סמך הפורט שהתקבל
			System.out.println("server side - connected");
			// wait for a client
			//int i = 0;
			server.setSoTimeout(1000000);
			
			executor = Executors.newFixedThreadPool(numOfClients);
			thread = new Thread(new Runnable() {

				@Override
				public void run() {
				
					while(!Thread.interrupted()) 
					//while(true)
					{
						try {
							Socket someClient = server.accept();
							counter++;
							Clients1.put(someClient, counter);
	
							System.out.println("Got new connection " + Clients1.size());
	//						System.out.println("Got new connection " + counter);
							
							//someClient.getInetAddress();
	
							
							if (someClient != null) {
								ClientHandler handler = new ClientHandler(someClient);
								executor.submit(new TaskRunnable(handler));
							}
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	catch (Exception e) {
							
						}
					}				
				}			
		});
		
		thread.start();
		}catch (IOException e1) {
					// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
	}
	
	public void stopServer() {		
		try {
			thread.interrupt();
			executor.shutdownNow();
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

