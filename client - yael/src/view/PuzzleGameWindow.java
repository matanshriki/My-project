package view;

import model.Solution;
import model.algorithm.Action;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;

import Boards.PuzzleBord;

public class PuzzleGameWindow extends BasicWindow {
	private String userAction;
	private List lstActions;
	private String description;
	private PuzzleBord puzzle;
	
	public PuzzleGameWindow(int width, int height, String title) {
		super(width, height, title);
		
	}
	

	@Override
	void initWidgets() {
		
		shell.setLayout(new GridLayout(2, false));
		
		Button btnStart = new Button(shell, SWT.PUSH);
		btnStart.setText("Start");
		btnStart.setLayoutData(new GridData(SWT.CENTER, SWT.UP, false, false));
		
		btnStart.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {	
				
				Group group1 = new Group(shell, SWT.SHADOW_OUT);
			    group1.setText("Choose algorithm: ");
			    group1.setLayout(new GridLayout(1, false));
				group1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));

			    final Combo comboAlgo = new Combo(group1, SWT.READ_ONLY);	
			    comboAlgo.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));
			    String itemsAlgo[] = {"BFS", "A-Star" };
			    comboAlgo.setItems(itemsAlgo);

				Button btnDisplay = new Button(group1, SWT.PUSH);
				btnDisplay.setText("Display Solution");
				btnDisplay.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
			
//			lstActions = new List(shell, SWT.BORDER | SWT.V_SCROLL);
//			lstActions.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
					
				btnDisplay.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent arg0) {	
					userAction = "SelectDomain PuzzleDomain-" ;
					System.out.println(userAction);
					PuzzleGameWindow.this.setChanged();
					PuzzleGameWindow.this.notifyObservers();
		
					userAction = "SelectAlgorithm " + comboAlgo.getText();
					System.out.println(userAction);
					PuzzleGameWindow.this.setChanged();
					PuzzleGameWindow.this.notifyObservers();	
					
					userAction = "SolveDomain";
					PuzzleGameWindow.this.setChanged();
					PuzzleGameWindow.this.notifyObservers();
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
									
				}
			});
				
				userAction = "SelectDomain PuzzleDomain-" ;
				PuzzleGameWindow.this.setChanged();
				PuzzleGameWindow.this.notifyObservers();	

				userAction = "createPuzzle";
				PuzzleGameWindow.this.setChanged();
				PuzzleGameWindow.this.notifyObservers("createPuzzle");	
						
				
				puzzle = new PuzzleBord(shell, SWT.BORDER, getDescription());
				System.out.println("herh (several sep in BP "+ getDescription());
				puzzle.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true ,true));
				puzzle.redraw();
				shell.layout();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
								
			}
		});
	}

	


	@Override
	public String getUserAction() {
		return userAction;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
		for (Action a : solution.getActions()) {
			
			this.shell.getDisplay().syncExec(new Runnable() {
				
				@Override
				public void run() {					
					lstActions.add(a.toString());
				}
			});						
		}		
	}

}
