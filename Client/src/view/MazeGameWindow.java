package view;

import java.util.ArrayList;

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

import Boards.MazeBord;

public class MazeGameWindow extends BasicWindow {
	private String userAction;
	private List lstActions;
	static String userActoinD;
	private Button btnPlay;
	private String mazePlayerState;
	private String description;
	private MazeBord maze = null;
	int indexOfSelection= 0;
	
	public MazeGameWindow(int width, int height, String title) {
		super(width, height, title);
	}

	@Override
	void initWidgets() {
		shell.setLayout(new GridLayout(2, true));
		// /Group 1///
		Group group1 = new Group(shell, SWT.SHADOW_OUT);
		group1.setText("Choose level: ");
		group1.setLayout(new GridLayout(2, false));
		group1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));

		final Combo comboLevel = new Combo(group1, SWT.READ_ONLY);
		comboLevel
				.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));
		String itemsLevel[] = { "Easy", "Medium", "Hard" };
		comboLevel.setItems(itemsLevel);

		
		comboLevel.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				indexOfSelection = comboLevel.getSelectionIndex();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {

			}
		});

		Button btnPlay = new Button(group1, SWT.PUSH);
		btnPlay.setText("Play");
		btnPlay.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false,
				2, 1));

		btnPlay.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				DiagnosedMazeLevel(indexOfSelection);

				userAction = "createMaze";
				MazeGameWindow.this.setChanged();
				MazeGameWindow.this.notifyObservers("createMaze");
				buildMaze();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {

			}
		});
		// Button btnreplay = new Button(group1, SWT.PUSH);
		// btnreplay.setText("replace maze");
		// btnreplay.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true,
		// false,2, 1));
		// btnreplay.addSelectionListener(new SelectionListener() {
		//
		// @Override
		// public void widgetSelected(SelectionEvent arg0) {
		// userAction = "replay";
		// System.out.println(userAction);
		// MazeGameWindow.this.setChanged();
		// MazeGameWindow.this.notifyObservers("replay");
		// comboLevel.getSelectionIndex();
		// btnPlay.setSelection(true);
		// // btnPlay.getSelection();
		// //shell.dispose();
		// }
		//
		// @Override
		// public void widgetDefaultSelected(SelectionEvent arg0) {
		// // TODO Auto-generated method stub
		//
		// }
		// });

		// /Group 2///
		Group group2 = new Group(shell, SWT.SHADOW_OUT);
		group2.setText("Choose algorithm: ");
		group2.setLayout(new GridLayout(1, false));
		group2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));

		final Combo comboAlgo = new Combo(group2, SWT.READ_ONLY);
		comboAlgo
				.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		String itemsAlgo[] = { "BFS", "A-Star" };
		comboAlgo.setItems(itemsAlgo);
		comboAlgo.select(1);
		
		Button btnDisplay = new Button(group2, SWT.PUSH);
		btnDisplay.setText("Display Solution");
		btnDisplay.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false));

		btnDisplay.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Button btn = (Button)arg0.getSource();
				String buttonText = btn.getText();
				if(buttonText.equals("Display Solution")){
					btn.setText("Hide Solution");
					
					userAction = userActoinD;
					System.out.println("Domain sent to the server: " + userAction);
					
					userAction = "SetDomain MazeDomain-" + maze.getMazeAsString();
					MazeGameWindow.this.setChanged();
					MazeGameWindow.this.notifyObservers("SelectDomain");	
					
					userAction = "SelectAlgorithm " + comboAlgo.getText() + " mazeH";				
					MazeGameWindow.this.setChanged();
					MazeGameWindow.this.notifyObservers("SelectAlgorithm");	
					
					userAction = "SolveDomain";
					MazeGameWindow.this.setChanged();
					MazeGameWindow.this.notifyObservers("SolveDomain");
					
				}else{
					btn.setText("Display Solution");
					maze.setIsShowSolution(false);
					maze.redraw();
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {

			}
		});

	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	protected String DiagnosedMazeLevel(int indexOfSelection) {
		if (indexOfSelection == 0) {
			userAction = "SelectDomain MazeDomain-(0,0)-(6,6)-7-7-10";
			MazeGameWindow.this.setChanged();
			MazeGameWindow.this.notifyObservers();

		} else if (indexOfSelection == 1) {
			userAction = "SelectDomain MazeDomain-(0,0)-(8,8)-9-9-15";
			MazeGameWindow.this.setChanged();
			MazeGameWindow.this.notifyObservers();
		} else if (indexOfSelection == 2) {
			userAction = "SelectDomain MazeDomain-(0,0)-(10,10)-11-11-25";
			MazeGameWindow.this.setChanged();
			MazeGameWindow.this.notifyObservers();
		}
		return userAction;
	}

	public void updateMaze() {
		//maze.dispose();
		buildMaze();
	}
	
	private void buildMaze() {
		
		if(maze != null){
			maze.dispose();
		}
		setMaze(new MazeBord(shell, SWT.DOUBLE_BUFFERED, getDescription()));
		getMaze().moveBelow(btnPlay);
		
		getMaze().setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		getMaze().redraw();
		shell.layout();

		getMaze().setFocus();

		// getMaze
		maze.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				mazePlayerState = ((MazeBord) maze).getGamePlayerCordinate(); // (x,y)
																				// before
																				// move
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
					userAction = "selectMoves: " + direction + "-" + mazePlayerState;
					
					MazeGameWindow.this.setChanged();
					MazeGameWindow.this.notifyObservers(userAction);
					
					maze.moveGamePlayer(direction);
					maze.redraw();
					shell.layout();
				}
			}
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
		final ArrayList<String> mazeSolution = new ArrayList<String>();
		for (final Action a : solution.getActions()) {
			mazeSolution.add(a.getDescription());
		}
		this.shell.getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {
				maze.setSolution(mazeSolution);
				maze.setIsShowSolution(true);
				maze.redraw();
				shell.layout();
			}
		});
	}

	@Override
	public String getUserAction() {
		return userAction;
	}

	public MazeBord getMaze() {
		return maze;
	}

	public void setMaze(MazeBord maze) {
		this.maze = maze;
	}

}
