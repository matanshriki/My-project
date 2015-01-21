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

public class SelectGameWindow extends BasicWindow implements View, Runnable, Observer {
	UIView window = null;
	SelectGameWindow thisInstance = this;
	
	public SelectGameWindow(int width, int height, String title) {
		super(width, height, title);	
		display.loadFont("resource/Walk-Around-the-Block.ttf");
	}
	
	
	@Override
	void initWidgets() {
		
		shell.setLayout(new GridLayout(1, true));
		GridLayout gridlayout = new GridLayout();
		gridlayout.numColumns=2;
		Font font = new Font(display, "Walk Around the Block",50,SWT.NORMAL);
		
		Label title = new Label (shell, SWT.CENTER);
		title.setText("\n\nSelect a Game");
		title.setFont(font);
		GridData gridData = new GridData(GridData.FILL, GridData.CENTER ,true, false);
		gridData.horizontalSpan=3;
		title.setLayoutData(gridData);
		
//////// Choose window according to the game (using a factory)
		
	//maze buttom
		shell.setLayout(new GridLayout(3, true));
		Image Image1 = new Image(display, "resource/Maze.jpg");
		Button btnSelectGame1 = new Button(shell, SWT.PUSH);
		btnSelectGame1.setImage(Image1);
		btnSelectGame1.setLayoutData(new GridData(GridData.CENTER, GridData.CENTER, true, true));
		
		btnSelectGame1.addSelectionListener(new SelectionListener() {
		@Override
		public void widgetSelected(SelectionEvent arg0) {
		UIView window  = new MazeGameWindow(400, 400, "Maze Game");
//		SelectGameWindow.this.setChanged();
//		SelectGameWindow.this.notifyObservers(window);
		//window.addObserver(thisInstance);

		window.run();
	
		
		shell.setVisible(false);	
		}
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
	//Word Game buttom
		shell.setLayout(new GridLayout(3, true));			
		Image image3 = new Image(display, "resource/WordGame.jpg");
		Button btnSelectGame3 = new Button(shell, SWT.PUSH);
		btnSelectGame3.setImage(image3);
		btnSelectGame3.setLayoutData(new GridData(GridData.CENTER, GridData.CENTER, true, true));

		btnSelectGame3.addSelectionListener(new SelectionListener(){ 			
		@Override
		public void widgetSelected(SelectionEvent arg0) {	
		UIView window = new WordGameWindow(400, 400, "Word Game");
		
	

		SelectGameWindow.this.setChanged();
		SelectGameWindow.this.notifyObservers(window);
		window.run();
		shell.setVisible(false);	
		}	
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
	//puzzle buttom
		shell.setLayout(new GridLayout(3, true));	
		Image image2 = new Image(display, "resource/eightPuzzle.jpg");
		Button btnSelectGame2 = new Button(shell, SWT.PUSH);
		btnSelectGame2.setImage(image2);
		btnSelectGame2.setLayoutData(new GridData(GridData.CENTER, GridData.CENTER, true, true));

		btnSelectGame2.addSelectionListener(new SelectionListener(){ 		
		@Override
		public void widgetSelected(SelectionEvent arg0) {
		UIView window = new PuzzleGameWindow(400, 400, "Puzzle Game");
	//	window.addObserver(thisInstance);

//		SelectGameWindow.this.setChanged();
//		SelectGameWindow.this.notifyObservers(window);
		window.run();
		shell.setVisible(false);	
		}
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {}
		});

	}

	public void start() {
		run();
	}
	
	@Override
	public void displayCurrentState() {}
	@Override
	public void displaySolution(Solution solution) {
		this.displaySolution(solution); }
	@Override
	public String getUserAction() {
		return this.getUserAction(); }


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
}

//package view;
//
//import java.util.Observable;
//import java.util.Observer;
//
//import model.Solution;
//
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.events.SelectionEvent;
//import org.eclipse.swt.events.SelectionListener;
//import org.eclipse.swt.layout.GridData;
//import org.eclipse.swt.layout.GridLayout;
//import org.eclipse.swt.widgets.Button;
//import org.eclipse.swt.widgets.Combo;
//
//public class SelectGameWindow extends BasicWindow implements View, Observer {
//	BasicWindow window = null;
//	SelectGameWindow thisInstance = this;
//	public SelectGameWindow(int width, int height, String title) {
//		super(width, height, title);		
//	}
//
//	@Override
//	void initWidgets() {
//		shell.setLayout(new GridLayout(1, false));
//		
//		final Combo combo = new Combo(shell, SWT.READ_ONLY);	
//		combo.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));
//	    String items[] = { "WordGame", "Maze" };
//	    combo.setItems(items);
//	    
//	    Button btnSelectModel = new Button(shell, SWT.PUSH);
//	    btnSelectModel.setText("Start");
//	    btnSelectModel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
//
//	    btnSelectModel.addSelectionListener(new SelectionListener() {
//			
//			@Override
//			public void widgetSelected(SelectionEvent arg0) {
//				// Choose window according to the game (using a factory)				
//				
//				
//				int indexOfSelection = combo.getSelectionIndex();
//				display.dispose();
//				if(indexOfSelection == 0){
//					window = new WordGameWindow(400, 300, "Word Game");
//				}else if(indexOfSelection == 1){
//					window = new MazeGameWindow(400, 300, "Maze Game");
//				}else if(indexOfSelection == 2){
//					window = new PuzzleGameWindow(400, 300, "Puzzle Game");
//				}
//				window.addObserver(thisInstance);
//				window.run();
//				
//			}
//			
//			@Override
//			public void widgetDefaultSelected(SelectionEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//	}
//
//	@Override
//	public void start() {		
//		run();
//	}
//
//	@Override
//	public void displayCurrentState() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void displaySolution(Solution solution) {
//		window.displaySolution(solution);
//		
//	}
//
//	@Override
//	public String getUserAction() {
//		return window.getUserAction();
//	}
//
//	@Override
//	public void update(Observable o, Object arg) {
//		this.setChanged();
//		this.notifyObservers();
//	}
//}
