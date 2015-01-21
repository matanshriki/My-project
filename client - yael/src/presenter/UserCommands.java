package presenter;

import java.util.HashMap;

import model.Model;
import tasks.TaskRunnable;

public class UserCommands {

	private HashMap<String, Command> commands = new HashMap<String,Command>();
	
	public UserCommands()
	{
		commands.put("SelectDomain", new SelectDomainCommand());
		//commands.put("SelectAlgorithm", new SelectAlgorithmCommand());
		commands.put("SelectAlgorithm", new SolveDomainCommand());
		commands.put("SolveDomain", new SolveDomainCommand());
		commands.put("IsThereSolution", new IsThereSolutionCommand());
		commands.put("DisplySolution", new DisplySolutionCommand());
		commands.put("exit", new SafeExitCommand());
		//
		commands.put("createMaze", new createMazeCommand());
		commands.put("createPuzzle", new createPuzzleCommand());

	}
	
	public interface Command
	{
		Model doCommand(Model model, String args);
	}
	
	public Command selectCommand(String commandName)
	{
		Command command = commands.get(commandName);
		return command;
	}
	
	private class SelectDomainCommand implements Command
	{
		@Override
		public Model doCommand(Model model, String args) {
			model.selectDomain(args);	
			return model;
		}		
	}
	
//	private class SelectAlgorithmCommand implements Command
//	{
//		@Override
//		public Model doCommand(Model model, String args) {
//			model.selectAlgorithm(args);
//			return model;
//		}
//		
//	 //model = new SolveDomainCommand(); 
//	}

	private class SolveDomainCommand implements Command
	{
		public Model doCommand(Model model, String args) {
			int i = model.addAndStart(new Thread(new TaskRunnable(model)));
			System.out.println("Solving in thread " + i);
			model.UpdateChange("SolveDomain "+i);
			return model;
		}
	}
	
	private class IsThereSolutionCommand implements Command//
	{
		public Model doCommand(Model model, String args) {
			model.UpdateChange("IsThereSolution "+args);
			//if(!model.getT().isAlive())	
			return model;
			
			//if(!model.getT().isAlive())

			//return null;
		}
	}
	
	private class DisplySolutionCommand implements Command
	{
		@Override
		public Model doCommand(Model model, String args) {
			model.UpdateChange("DisplySolution "+args);			
			return model;
		}
	}
	private class SafeExitCommand implements Command
	{
		@Override
		public Model doCommand(Model model, String args) {
			model.UpdateChange("SafeExit");			
			return model;
		}
	}
	
	private class createMazeCommand implements Command
	{
		@Override
		public Model doCommand(Model model, String args) {
			model.UpdateChange("createMaze");			
			return model;
		}
	}
	private class createPuzzleCommand implements Command
	{
		@Override
		public Model doCommand(Model model, String args) {
			model.UpdateChange("createPuzzle");			
			return model;
		}
	}
	
}

