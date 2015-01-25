package view;

import model.Solution;
import model.algorithm.Action;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;

public class WordGameWindow extends BasicWindow implements View {
	private String userAction;
	private List lstActions;
	
	public WordGameWindow(int width, int height, String title) {
		super(width, height, title);		
	}

	@Override
	void initWidgets() {
		
		shell.setLayout(new GridLayout(2, false));
		
		Label lblStartWord = new Label(shell, SWT.NONE);
		lblStartWord.setText("Enter start word: ");
		
		final Text txtStartWord = new Text(shell, SWT.BORDER);
		txtStartWord.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label lblEndWord = new Label(shell, SWT.NONE);
		lblEndWord.setText("Enter end word: ");
		
		final Text txtEndWord = new Text(shell, SWT.BORDER);
		txtEndWord.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label lblAlgorithm = new Label(shell, SWT.NONE);
		lblAlgorithm.setText("Choose algorithm: ");
		
		final Combo combo = new Combo(shell, SWT.READ_ONLY);	
		combo.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));
	    String items[] = { "BFS", "A-Star" };
	    combo.setItems(items);
	    combo.select(1);
	    
		Button btnSearch = new Button(shell, SWT.PUSH);
		btnSearch.setText("Search");
		btnSearch.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		
		lstActions = new List(shell, SWT.BORDER | SWT.V_SCROLL);
		lstActions.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
				
		btnSearch.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {	
				String firstWord = txtStartWord.getText();
				String secondWord = txtEndWord.getText();
				if(firstWord.length() != secondWord.length()){
					display.asyncExec(new Runnable() {
						@Override
						public void run() {		
							lstActions.add("No possable solution");
							lstActions.add("----------------------------");
						}
					});
					return;
				}else if(firstWord.equals(secondWord)){
					display.asyncExec(new Runnable() {
						@Override
						public void run() {		
							lstActions.add("Same word ;)");
							lstActions.add("----------------------------");
						}
					});	
					return;
				}
				
				userAction = "SelectDomain WGDomain-" + firstWord +
						"-" + secondWord;
				
				
				WordGameWindow.this.setChanged();
				WordGameWindow.this.notifyObservers("SelectDomain");	
				
				userAction = "SelectAlgorithm " + combo.getText() + " WGH";
				WordGameWindow.this.setChanged();
				WordGameWindow.this.notifyObservers("SelectAlgorithm");	
				
				userAction = "SolveDomain";
				WordGameWindow.this.setChanged();
				WordGameWindow.this.notifyObservers("SolveDomain");
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
								
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
		if(solution.getActions() == null){
			display.asyncExec(new Runnable() {
				@Override
				public void run() {		
					lstActions.add("No possable solution");
					lstActions.add("----------------------------");
				}
			});
		}else{
			for (final Action a : solution.getActions()) {
				display.asyncExec(new Runnable() {
					@Override
					public void run() {					
						lstActions.add(a.toString());
						lstActions.add("----------------------------");
					}
				});						
			}
		}
	}

	@Override
	public String getUserAction() {		
		return userAction;
	}

}
