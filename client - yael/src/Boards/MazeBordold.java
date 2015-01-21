package Boards;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
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

		Image mazeBackround = new Image(getDisplay(), "resources/cover1.jpg");
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
//				e.gc.setForeground(new Color(null, 0, 0, 0));
//				e.gc.setBackground(new Color(null, 0, 0, 0));
				int width = getSize().x;
				System.out.println(width);
				int height = getSize().y;
				System.out.println(height);
				int w = width / mazeData[0].length;
				System.out.println(w);
				int h = height / mazeData.length;
				System.out.println(h);

				
				for (int i = 0; i < mazeData.length; i++)
					for (int j = 0; j < mazeData[0].length; j++) {
						int x = j * w;
						System.out.println("x= "+ x);
						int y = i * h;
						System.out.println("y= "+ y);
						System.out.println("hr : ");
						System.out.println("("+x+","+y+") = "+mazeData[i][j]);

						if (mazeData[i][j] != 0){
							e.gc.drawImage(tmp, 0, 0, tmp.getImageData().width, tmp.getImageData().height, x, y, w-5, h-5);	
						}
						//e.gc.drawImage(tmp1, x, y); 
					} e.gc.drawImage(tmp1, 296, 212); 
		
				//e.gc.drawImage(tmp1, mazeData.length, mazeData[0].length); 
				c.paint(e, w, h); }
		});
		
		
		
		addDisposeListener(new DisposeListener() {

			@Override
			public void widgetDisposed(DisposeEvent arg0) {
				stop();
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
			System.out.println("j size is: "+arrRow.length);
			for (int j=0; j<arrRow.length; j++) {
				//System.out.println("("+i+","+j+") = "+arrRow[j]);
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