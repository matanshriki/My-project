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
			HandleModelCommand(arg1);
			//solution =(((Model)observable).getSolution());
			if ((arg1).equals("DisplaySolution")){
				solution = (((Model)observable).getSolution());
				view.displaySolution(solution);
			}
		}
		else if (observable instanceof View)
		{
			String action = view.getUserAction();
			//String args = HandleViewCommand(action);

			String[] arr = action.split(" ");

			String commandName = arr[0];
			
			String args = "";
			if (arr.length > 1){
				String [] args_array = new String[arr.length - 1];
				for(int i = 1; i < arr.length; ++i){
					args += arr[i] + "-";
				}
				args = args.substring(0, args.length() - 1); // Remove last "-"
			}else{
				args = arr[0];
			}
			
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
	


	private void HandleModelCommand(Object arg1) {
		if (((arg1).equals("createMaze"))||((arg1).equals("replay"))) {
			String description = ((MyModel)model).getDomain().getProblemDescription();
			((MazeGameWindow)((SelectGameWindow)view).getWindow()).setDescription(description);
			
		}else if ((arg1).equals("createPuzzle")) {
			String description = ((MyModel)model).getProblemDescription();
			((PuzzleGameWindow)((SelectGameWindow)view).getWindow()).setDescription(description);
		}
		else if (((arg1).equals("right"))||((arg1).equals("left"))){
			System.out.println("back to pres");
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

	public Solution getSolution() {
		return solution;
	}

	public void setSolution(Solution solution) {
		this.solution = solution;
	}
	
}
