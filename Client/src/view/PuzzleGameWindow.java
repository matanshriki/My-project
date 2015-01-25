package view;

import model.Solution;
import model.algorithm.Action;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.MessageBox;

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

			Group group1 = new Group(shell, SWT.SHADOW_OUT);
		    group1.setText("Choose algorithm: ");
		    group1.setLayout(new GridLayout(1, false));
			group1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));

		    final Combo comboAlgo = new Combo(group1, SWT.READ_ONLY);	
		    comboAlgo.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));
		    String itemsAlgo[] = {"BFS", "A-Star" };
		    comboAlgo.setItems(itemsAlgo);
		    comboAlgo.select(1);
		    
			Button btnSearch = new Button(group1, SWT.PUSH);
			btnSearch.setText("Display Solution");
			btnSearch.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		
//		lstActions = new List(shell, SWT.BORDER | SWT.V_SCROLL);
//		lstActions.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
				
		btnSearch.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {	
				userAction = "SetDomain PuzzleDomain-" + puzzle.getPuzzleAsString() ;
				PuzzleGameWindow.this.setChanged();
				PuzzleGameWindow.this.notifyObservers();
	
				userAction = "SelectAlgorithm " + comboAlgo.getText() + " PuzzleH";
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
		
		Button btnStart = new Button(shell, SWT.PUSH);
		btnStart.setText("Start");
		btnStart.setLayoutData(new GridData(SWT.LEFT, SWT.UP, false, false));
		
		btnStart.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {	
				
				userAction = "SelectDomain PuzzleDomain-" ;
				PuzzleGameWindow.this.setChanged();
				PuzzleGameWindow.this.notifyObservers();	
				
				userAction = "createPuzzle";
				PuzzleGameWindow.this.setChanged();
				PuzzleGameWindow.this.notifyObservers("createPuzzle");	
						
				if(puzzle != null){
					puzzle.dispose();
				}
				puzzle = new PuzzleBord(shell, SWT.BORDER, getDescription());
				puzzle.addKeyListener(new KeyListener() {
					@Override
					public void keyReleased(KeyEvent arg0) {
					}

					@Override
					public void keyPressed(KeyEvent arg0) {
						String direction = "";
						switch (arg0.keyCode) {
							case SWT.ARROW_RIGHT:
									direction = "right";
								break;
			
							case SWT.ARROW_LEFT:
								    direction = "left";
								break;
			
							case SWT.ARROW_UP:
									direction = "up";
								break;
			
							case SWT.ARROW_DOWN:
									direction ="down";
								break;
							default:
								direction = "BadKeyPressed";
						}
						
						if(!direction.equals("BadKeyPressed")){												
							puzzle.moveGameBlank(direction);
							puzzle.redraw();
							shell.layout();
						}
					}
				});
				puzzle.setFocus();
				
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
	public void displaySolution(final Solution solution) {
		this.shell.getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {
				shell.layout();
				MessageBox dialog = new MessageBox(shell, SWT.OK);
				dialog.setText("Solution");
				String res = "";
				for(Action action : solution.getActions()){
					res += " -> " + action.toString();
				}
				dialog.setMessage(res);	
				dialog.open();
				
			}
		});

	
	}

}
