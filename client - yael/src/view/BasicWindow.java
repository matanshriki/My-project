package view;

import java.util.Observable;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public abstract class BasicWindow extends Observable implements View, Runnable{

	protected Display display;
	protected Shell shell;
	
	public BasicWindow(int width, int height,String title) {
		display=new Display();
		shell=new Shell(display);
		shell.setSize(width, height);
		shell.setText(title);
	}
	
	abstract void initWidgets();
	abstract public String getUserAction();
	@Override
	public void run() {
		initWidgets();
		shell.open();
		
		// main event loop
		 while(!shell.isDisposed()){ // while window isn't closed

		    // 1. read events, put then in a queue.
		    // 2. dispatch the assigned listener
		    if(!display.readAndDispatch()){ // if the queue is empty
		       display.sleep(); // sleep until an event occurs 
		    }

		 } // shell is disposed

		 display.dispose(); // dispose OS components	
		 

}}
