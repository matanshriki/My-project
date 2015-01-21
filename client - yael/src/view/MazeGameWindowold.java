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

import Boards.MazeBord;


	public class MazeGameWindowold extends BasicWindow {
		private String userAction;
		private List lstActions;
		static String userActoinD;
		private String description;
		private MazeBord maze;
		
		public MazeGameWindowold(int width, int height, String title) {
			super(width, height, title);
		}
		@Override
		void initWidgets() {
			shell.setLayout(new GridLayout(2, false));
			Group group1 = new Group(shell, SWT.SHADOW_OUT);
			group1.setText("Choose level: ");
			group1.setLayout(new GridLayout(1, false));
			group1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
			
			final Combo comboLevel = new Combo(group1, SWT.READ_ONLY);	
			comboLevel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));
		    String itemsLevel[] = {"Easy", "Medium", "Hard" };
		    comboLevel.setItems(itemsLevel);
		    
		    comboLevel.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					
					int indexOfSelection = comboLevel.getSelectionIndex();
					//display.dispose();
					userActoinD = DiagnosedMazeLevel(indexOfSelection);
					System.out.println(userAction);
					
				}
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
									
				}
			});
		    
		    Button btnPlay = new Button(group1, SWT.PUSH);
			btnPlay.setText("Play");
			btnPlay.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));

			
			btnPlay.addSelectionListener(new SelectionListener() {
					@Override
					public void widgetSelected(SelectionEvent arg0) {
						
						userAction = "createMaze";
						System.out.println(userAction);
						MazeGameWindow.this.setChanged();
						MazeGameWindow.this.notifyObservers("createMaze");	
						
					
						maze = new MazeBord(shell, SWT.BORDER, getDescription());
						maze.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,2,1));
						maze.redraw();
						shell.layout();

						 
						System.out.println(" :) ");
					}
					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {
										
					}
				});
//			maze.addKeyListener(new KeyListener() {
//				
//				@Override
//				public void keyReleased(KeyEvent arg0) {
//					// TODO Auto-generated method stub
//					
//				}
//				
//				@Override
//				public void keyPressed(KeyEvent arg0) {
//					// TODO Auto-generated method stub
//					
//				}
//			});

		    Group group2 = new Group(shell, SWT.SHADOW_OUT);
		    group2.setText("Choose algorithm: ");
		    group2.setLayout(new GridLayout(1, false));
			group2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));

			
		    final Combo comboAlgo = new Combo(group2, SWT.READ_ONLY);	
		    comboAlgo.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));
		    String itemsAlgo[] = {"BFS", "A-Star" };
		    comboAlgo.setItems(itemsAlgo);

			Button btnSearch = new Button(group2, SWT.PUSH);
			btnSearch.setText("Display Solution");
			btnSearch.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
			
//			
//			lstActions = new List(shell, SWT.BORDER | SWT.V_SCROLL);
//			lstActions.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
					
			btnSearch.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent arg0) {	
					userAction = userActoinD;
					System.out.println("D sent to the server: "+userAction);
					
					userAction = "SelectAlgorithm " + comboAlgo.getText();
					System.out.println("A sent to the server: "+userAction);
					MazeGameWindow.this.setChanged();
					MazeGameWindow.this.notifyObservers("SelectAlgorithm");	
					
				}
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
									
				}
			});
			
		}


		protected String DiagnosedMazeLevel(int indexOfSelection) {
			if(indexOfSelection == 0){
				userAction = "SelectDomain MazeDomain-(0,0)-(4,4)-5-5-5" ;
				MazeGameWindow.this.setChanged();
				MazeGameWindow.this.notifyObservers();	
				
			}else if(indexOfSelection == 1){
				userAction = "SelectDomain MazeDomain-(0,0)-(6,6)-7-7-14" ;
				MazeGameWindow.this.setChanged();
				MazeGameWindow.this.notifyObservers();					
			}else if(indexOfSelection == 2){
				userAction = "SelectDomain MazeDomain-(0,0)-(9,9)-10-10-30" ;
				MazeGameWindow.this.setChanged();
				MazeGameWindow.this.notifyObservers();	
				}
			return userAction;			
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
	

		
		
		
		
	}
