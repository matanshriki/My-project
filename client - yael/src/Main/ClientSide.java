package Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientSide {
	static int numberOfCommand = 0;
	
//	public static void main(String[] args) {
//		String userCommand = null, resonseFromServer = null;
//		Socket server;
//		BufferedReader inFromServer = null;
//		PrintWriter outToServer = null;
//		char[] cbuf = new char[4000];
//		
//		try {
//			server = new Socket("127.0.0.1", 5001);
//			inFromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
//			outToServer = new PrintWriter(new OutputStreamWriter(server.getOutputStream()));
//
//			print_options();
//			
//			// open communication
//			while(true){
//				
//				//get user command.
//				userCommand = getUserCommand();
//			
//				//send command to the server
//				outToServer.println(userCommand + "\n\r");
//				outToServer.flush();
//				System.out.println("After writing to server");
//				
//				//Receive response from the server
//				resonseFromServer = inFromServer.readLine();
//				//resonseFromServer = cbuf.toString();
//				System.out.println("After reading from server: " + resonseFromServer);
//				
//				//Present response or exit if needed
//				if(resonseFromServer.equals("exit")){
//					inFromServer.close();
//					outToServer.close();
//					server.close();
//					break;
//				} 
//				else if (!resonseFromServer.equals("do nothing")){
//					System.out.println(resonseFromServer);
//				} 
//			}
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}		
//	}

	
	private static String getUserCommand() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter command: ");
		numberOfCommand++;
		System.out.println("Please Enter command #"+numberOfCommand+":");
		String command = scanner.nextLine();
		return command;
	}

	private static void print_options() {
		System.out.println("Welcome to my project\n");
		System.out.println("In this project, is demanded to choose a domain (game) from a multiple choice As well as the algorithm calculates the solution.\n");
		System.out.println("First - Select the domain (for example):");			
		System.out.println("SelectDomain MazeDomain-(0,0)-(5,5)-6-6-5/ PazzelDomain-2,4,5,6,7,8,1,3,0/ WGDomain-ovel-love\n");
		System.out.println("Then select an algorithm");
		System.out.println("SelectAlgorithm BFS (or) Astar-mazeH / Astar-PazzelH / Astar-WGH \n");
		System.out.println("For Solving, write : SolveDomain \n");
		System.out.println("If you want to check if there is a solution alrady, write : IsThereSolution #\n");
		System.out.println("Finelly , write : DisplySolution #\n");
		System.out.println(" write : exit \n");
	}
	
}



