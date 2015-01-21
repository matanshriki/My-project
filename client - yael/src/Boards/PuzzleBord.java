package Boards;

import java.util.HashMap;
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
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class PuzzleBord extends Canvas{
	
	Canvas startGameCanvas;
	private int[][] puzzleData;
	private	GameCharacter c;
	private int s; 
	Timer timer;
	TimerTask task;
	private HashMap<Integer, Image> puzzleMap;
	Object[][] board;


	public PuzzleBord(Composite parent, int style, String description) {
		super(parent, style);		
						
		startGameCanvas = new Canvas(getShell(), SWT.PUSH);
		startGameCanvas.setSize(500, 500);
		startGameCanvas.setLayout(new GridLayout(1, false));
		startGameCanvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,2,1));

		c = new GameCharacter(0,0);
				
			int puzzlesize = description.split(",").length;
			System.out.println("puzzlesize = " + (puzzlesize-1)); //maze size
			double size = Math.sqrt(puzzlesize);
			s = ((int)size);
			System.out.println("size = " + s); //num of r&c
			puzzleData = new int[s][s];
	//		split(description);
	
		addPaintListener(new PaintListener() {
		
		@Override
		public void paintControl(PaintEvent e) {
	
		Image puzzleImage1 = new Image(getDisplay(), "resources/1.jpg");
		Image puzzleImage2 = new Image(getDisplay(), "resources/2.jpg");
		Image puzzleImage3 = new Image(getDisplay(), "resources/3.jpg");
		Image puzzleImage4 = new Image(getDisplay(), "resources/4.jpg");
		Image puzzleImage5 = new Image(getDisplay(), "resources/5.jpg");
		Image puzzleImage6 = new Image(getDisplay(), "resources/6.jpg");
		Image puzzleImage7 = new Image(getDisplay(), "resources/7.jpg");
		Image puzzleImage8 = new Image(getDisplay(), "resources/8.jpg");
		Image puzzleImage9 = new Image(getDisplay(), "resources/9.jpg");
		
		int width = getSize().x;
		int height = getSize().y;
		int w = (width / s);
		int h = (height / s);
		
		puzzleMap = new HashMap<Integer, Image>();
		
		puzzleMap.put(1,puzzleImage1);
		puzzleMap.put(2,puzzleImage2);
		puzzleMap.put(3,puzzleImage3);
		puzzleMap.put(4,puzzleImage4);
		puzzleMap.put(5,puzzleImage5);
		puzzleMap.put(6,puzzleImage6);
		puzzleMap.put(7,puzzleImage7);
		puzzleMap.put(8,puzzleImage8);
		puzzleMap.put(0,puzzleImage9);


	for (int i = 0; i < s; i++)
		for (int j = 0; j < s; j++) {
			int x = j * w;
			int y = i * h;
				if (puzzleData[i][j] != Integer.parseInt("0"))
					
					puzzleMap.get(board[i][j]);
					e.gc.drawImage(puzzleImage1, 0, 0, puzzleImage1.getImageData().width, puzzleImage1.getImageData().height, x, y, w-5, h-5);	
			}
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

//	public void split(String description){
//		String[] arr = description.split("\n");
//		//System.out.println("i size is: "+arr.length);
//		String[] arrRow;
//		for(int i=0;i<arr.length;i++) {
//			arrRow = arr[i].split(",");
//			//System.out.println("j size is: "+arrRow.length);
//			for (int j=0; j<arrRow.length; j++) {
//				System.out.println("("+i+","+j+") = "+arrRow[j]);
//				mazeData[i][j] = Integer.parseInt(arrRow[j]);
//				System.out.println("mazeData is: "+mazeData[i][j]);
//			}
//		}
//	}


	public void stop() {
		task.cancel();
		timer.cancel();
	}
}