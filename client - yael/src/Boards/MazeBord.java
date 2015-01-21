package Boards;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class MazeBord extends Canvas {

	int[][] mazeData; 
	GameCharacter c;
	Timer timer;
	TimerTask task;
	int s ; 
	
	public MazeBord(Composite parent, int style, String description) {
		super(parent, style);

		Image mazeBackround = new Image(getDisplay(), "resources/MazeBack.jpg");
		setBackgroundImage(mazeBackround);
		c = new GameCharacter(0,0);
		
			int mazesize = description.split(",").length;
			System.out.println("mazesize = " + (mazesize -1)); //mazesize
			double size = Math.sqrt(mazesize);
			s = ((int)size);
			System.out.println("size = " + s); //num of r&c
			mazeData = new int[s][s];
		
			split(description);

		addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				Image tmp = new Image(getDisplay(), "resources/walls1.jpg");
				Image tmp1 = new Image(getDisplay(), "resources/start.jpg");
				Image tmp2 = new Image(getDisplay(), "resources/goal2.jpg");
				
				int width = getSize().x;
				int height = getSize().y;
				int w = width / s;
				int h = height / s;
		
				for (int i = 0; i < s; i++)
					for (int j = 0; j < s; j++) {
						int x = j * w;
						int y = i * h;
							if (mazeData[i][j] == 1)
							{
								e.gc.drawImage(tmp, 0, 0, tmp.getImageData().width, tmp.getImageData().height, x, y, w, h);	
							}
							if(i == (s-1) && j == (s-1))
							{
								e.gc.drawImage(tmp2,0, 0, tmp2.getImageData().width, tmp2.getImageData().height, x, y, w, h);	
							}
					}
				
				int i=0;
				int j=0;
				int x = j * w;
				int y = i * h;
			
				e.gc.drawImage(tmp1, 0, 0, tmp1.getImageData().width, tmp1.getImageData().height, x, y, w, h);

			c.paint(e, w, h);
				
			}

		});
		
		
		
		addDisposeListener(new DisposeListener() {

			@Override
			public void widgetDisposed(DisposeEvent arg0) {
				stop();
			}
		});

	
	// exit Button:
	Button btnExit = new Button(getShell(), SWT.PUSH);
	btnExit.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false,2, 1));
	btnExit.setText("Exit Game");
	
	btnExit.addSelectionListener(new SelectionListener() {

		

		@Override
		public void widgetSelected(SelectionEvent arg0) {
			setCommandChange("exit");
			getShell().dispose();
		}

		private void setCommandChange(String string) {
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {
			// TODO Auto-generated method stub

		}
	});
}

	public void start() {
		timer = new Timer();
		task = new TimerTask() {

			@Override
			public void run() {

				getDisplay().syncExec(new Runnable() {

					@Override
					public void run() {
						Random r = new Random();
						c.x += -5 + r.nextInt(11);
						c.y += -5 + r.nextInt(11);
						redraw();
					}
				});
			}
		};

		timer.scheduleAtFixedRate(task, 0, 500);
		;
	}
	
	public void split(String description){
		String[] arr = description.split("\n");
		//System.out.println("i size is: "+arr.length);
		String[] arrRow;
		for(int i=0;i<arr.length;i++) {
			arrRow = arr[i].split(",");
			//System.out.println("j size is: "+arrRow.length);
			for (int j=0; j<arrRow.length; j++) {
				System.out.println("("+i+","+j+") = "+arrRow[j]);
				mazeData[i][j] = Integer.parseInt(arrRow[j]);
				System.out.println("mazeData is: "+mazeData[i][j]);
			}
		}
	}

	
	public void stop() {
		task.cancel();
		timer.cancel();
	}
}