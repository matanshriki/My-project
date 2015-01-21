package presenter;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import model.Model;
import model.MyModel;
import model.Solution;
import presenter.UserCommands.Command;
import view.MazeGameWindow;
import view.PuzzleGameWindow;
import view.SelectGameWindow;
import view.View;

public class Presenter implements Observer {
	
	private Model model; // the current model
	private View view;
	private UserCommands commands;
	private ArrayList<Model> models; // all running models
	
	private Solution solution;
	
	public Presenter(Model model, View view)
	{
		this.model = model;
		this.view = view;
		commands = new UserCommands();
		models = new ArrayList<Model>();
		models.add(model);
	}

	@Override
	public void update(Observable observable, Object arg1) {
		if (observable instanceof Model)
		{	
			
			if ((arg1).equals("createMaze")) {
			//if (((arg1).equals("createMaze"))||((arg1).equals("createPuzzle"))) {

				String description = ((MyModel)model).getDomain().getProblemDescription();
				((MazeGameWindow)((SelectGameWindow)view).getWindow()).setDescription(description);
				
			}else if ((arg1).equals("createPuzzle")) {
				String description = ((MyModel)model).getProblemDescription();
				((PuzzleGameWindow)((SelectGameWindow)view).getWindow()).setDescription(description);
			}
			
			solution =(((Model)observable).getSolution());
			//view.displaySolution(solution);
		}
		else if (observable instanceof View)
		{
			String action = view.getUserAction();

			String[] arr = action.split(" ");
			
			String commandName = arr[0];
			
			if (action.startsWith("SelectAlgorithm")){
				((MyModel) model).selectAlgorithm(arr[1]);
				
				commandName = "SolveDomain";
			}
			
				
			String args = null;
			if (arr.length > 1)
				args = arr[1];
			else args = arr[0];
			
			Command command = commands.selectCommand(commandName);
			Model m = command.doCommand(model, args);
			
			// Check if we got a new model from the command
			if (m != model) {
				this.model = m;
				//models.add(m);
				m.addObserver(this);
			}
		 
		}
	}
	
	public static void main(String[] args) {
		

		MyModel model = new MyModel();
		
		SelectGameWindow view = new SelectGameWindow(1000, 520, "Select Game");
		
		Presenter presenter = new Presenter(model, view);
		
		model.addObserver(presenter);
		view.addObserver(presenter);
		
		view.start();
	}

//	public Solution getSolution() {
//		return solution;
//	}
//
//	public void setSolution(Solution solution) {
//		this.solution = solution;
//	}
	
}
