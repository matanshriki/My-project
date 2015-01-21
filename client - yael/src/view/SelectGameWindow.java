package view;

import java.util.Observable;
import java.util.Observer;

import model.Solution;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;

public class SelectGameWindow extends BasicWindow implements View, Observer {
	private BasicWindow window = null;
	SelectGameWindow thisInstance = this;
	
	public SelectGameWindow(int width, int height, String title) {
		super(width, height, title);	
		display.loadFont("resources/Walk-Around-the-Block.ttf");
	}

	@Override
	void initWidgets() {
		shell.setLayout(new GridLayout(1, false));
		
		Font font = new Font(display, "Walk Around the Block",50,SWT.NORMAL);
		Label title = new Label (shell, SWT.CENTER);
		title.setText("\n\nSelect a Game");
		title.setFont(font);
		GridData gridData = new GridData(GridData.FILL, GridData.CENTER ,true, false);
		gridData.horizontalSpan=3;
		title.setLayoutData(gridData);
		
		
		//puzzle bottom
		shell.setLayout(new GridLayout(3, true));	
		Image image2 = new Image(display, "resources/8Puzzle.gif");
		Button btnSelectGame2 = new Button(shell, SWT.PUSH);
		btnSelectGame2.setImage(image2);
		btnSelectGame2.setLayoutData(new GridData(GridData.CENTER, GridData.CENTER, true, true));
		
		//Word Game bottom
		shell.setLayout(new GridLayout(3, true));			
		Image image3 = new Image(display, "resources/wordGame.gif");
		Button btnSelectGame3 = new Button(shell, SWT.PUSH);
		btnSelectGame3.setImage(image3);
		btnSelectGame3.setLayoutData(new GridData(GridData.CENTER, GridData.CENTER, true, true));
		
		//maze bottom
		shell.setLayout(new GridLayout(3, true));
		Image Image1 = new Image(display, "resources/maze.gif");
		Button btnSelectGame1 = new Button(shell, SWT.PUSH);
		btnSelectGame1.setImage(Image1);
		btnSelectGame1.setLayoutData(new GridData(GridData.CENTER, GridData.CENTER, true, true));
	
	
	btnSelectGame1.addSelectionListener(new SelectionListener() {
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			display.dispose();
		    setWindow(new MazeGameWindow(400, 400, "Maze Game"));
			getWindow().notifyObservers();
			getWindow().addObserver(thisInstance);
			getWindow().run();
			//String windowOfMaze = window.getUserAction();
		}
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {}
	});
	
	   btnSelectGame2.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				display.dispose();
				setWindow(new PuzzleGameWindow(400, 400, "Puzzle Game"));
				getWindow().notifyObservers();
				getWindow().addObserver(thisInstance);
				getWindow().run();
			}	
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
	   btnSelectGame3.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					display.dispose();
				    setWindow(new WordGameWindow(400, 400, "WordGame Game"));
					getWindow().addObserver(thisInstance);
					getWindow().run();
				}
				
				
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {}
	   			});

		}
	

		
		
	@Override
	public void start() {		
		run();
	}

	@Override
	public void displayCurrentState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displaySolution(Solution solution) {
		getWindow().displaySolution(solution);
		
	}

	@Override
	public String getUserAction() {
		return getWindow().getUserAction();
	}

	@Override
	public void update(Observable o, Object arg) {
		this.setChanged();
		this.notifyObservers();
	}

	public BasicWindow getWindow() {
		return window;
	}

	public void setWindow(BasicWindow window) {
		this.window = window;
	}


}
