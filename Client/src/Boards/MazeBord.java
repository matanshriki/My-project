package Boards;

import java.util.ArrayList;
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
	GameCharacter myplayer;
	Timer timer;
	TimerTask task;
	int s ; 
	ArrayList<String> mazeSolution = null;
	boolean isShowSolution = false;
	String description;
	
	public MazeBord(Composite parent, int style, String description) {
		super(parent, style);
		myplayer = new GameCharacter(0,0);
		this.description = description;
		Image mazeBackround = new Image(getDisplay(), "resources/water.jpg");
		setBackgroundImage(mazeBackround);
		
		int mazesize = description.split(",").length;
		double size = Math.sqrt(mazesize);
		s = ((int)size);
		mazeData = new int[s][s];

		split(description);

		addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				Image Walls = new Image(getDisplay(), "resources/GrayWall.jpg");
				Image player = new Image(getDisplay(), "resources/start.jpg");
				Image goal = new Image(getDisplay(), "resources/goal.jpg");
				Image rightArrow = new Image(getDisplay(), "resources/right_arrow.png");
				Image leftArrow = new Image(getDisplay(), "resources/left_arrow.png");
				Image upArrow = new Image(getDisplay(), "resources/up_arrow.png");
				Image downArrow = new Image(getDisplay(), "resources/down_arrow.png");
				
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
								e.gc.drawImage(Walls, 0, 0, Walls.getImageData().width, Walls.getImageData().height, x, y, w, h);	
							}
							{
								if(i == (s-1) && j == (s-1))
								e.gc.drawImage(goal,0, 0, goal.getImageData().width, goal.getImageData().height, x, y, w, h);	
							}
					}
				
				if(!isShowSolution){
					e.gc.drawImage(player, 0, 0, player.getImageData().width, player.getImageData().height, myplayer.x*w, myplayer.y*h, w-5, h-5);		
				}
				
				// Print solution
				if(mazeSolution != null && isShowSolution){
					int current_x = myplayer.x;
					int current_y = myplayer.y;
					
					
					Image currentArraw = null;
					for(String s : mazeSolution){
						int x_delta = 0;
						int y_delta = 0;
						if(s.equals("right")){
							currentArraw = rightArrow;
							x_delta = 1;
						}else if(s.equals("down")){
							currentArraw = downArrow;
							y_delta = 1;
						}else if(s.equals("left")){
							currentArraw = leftArrow;
							x_delta = -1;
						}else if(s.equals("up")){
							currentArraw = upArrow;
							y_delta = -1;
						}
						e.gc.drawImage(currentArraw, 0, 0, currentArraw.getImageData().width, currentArraw.getImageData().height, current_x*w, current_y*h, w-5, h-5);
						current_x += x_delta;
						current_y += y_delta;
					}
				}
			}

		});
		
		
		addDisposeListener(new DisposeListener() {

			@Override
			public void widgetDisposed(DisposeEvent arg0) {
				stop();
			}
		});
	}
	
	
	
	public void split(String description){
		String[] arr = description.split("\n");
		//System.out.println("i size is: "+arr.length);
		String[] arrRow;
		for(int i=0;i<arr.length;i++) {
			arrRow = arr[i].split(",");
			//System.out.println("j size is: "+arrRow.length);
			for (int j=0; j<arrRow.length; j++) {
				//System.out.println("("+i+","+j+") = "+arrRow[j]);
				mazeData[i][j] = Integer.parseInt(arrRow[j]);
				//System.out.println("mazeData is: "+mazeData[i][j]);
			}
		}
	}
	/////////////////////
	public int getRowNumber(String s) {
		int sum=0;
		for (int i=1; i<s.length(); i++) {
			if (s.charAt(i) == ',')
				break;
			sum = sum*10 + Character.getNumericValue(s.charAt(i));
		}
		return sum;
	}
	
	public int getColumnNumber(String s) {
		int sum=0;
		int lastChar = s.length()-1;
		int firstChar = s.indexOf(',');
		for (int i=firstChar+1; i<lastChar; i++) {
			sum = sum*10 + Character.getNumericValue(s.charAt(i));
		}
		return sum;
	}
	//////////////
	public String getGamePlayerCordinate() {
		
		double width = (getSize().x / s) - 1;				//(double)
		double height = getSize().y / s;
		
		int y = (int)(myplayer.getX() / width);
		int x = (int)(myplayer.getY() / height);
		return ("("+x+","+y+")");
	}
	
	
	public void setGamePlayer(String cordinate) {
		int y = getRowNumber(cordinate);  		//x of the new regular Move
		int x = getColumnNumber(cordinate);		//y of the new regular Move
		
		double width = (getSize().x / s) - 1;				//(double)
		double height = getSize().y / s;
	
		myplayer.setX(x * (int)width);
		myplayer.setY(y * (int)height);
		redraw();
	}
	
	public void moveGamePlayer(String direction){
		int next_x = myplayer.x; 
		int next_y = myplayer.y;
		if(direction.equals("right")){
			next_x += 1;
		}else if(direction.equals("down")){
			next_y += 1;
		}else if(direction.equals("left")){
			next_x -= 1;
		}else if(direction.equals("up")){
			next_y -= 1;
		}
		if( next_x > -1 && next_x < mazeData[0].length &&
			next_y > -1 && next_y < mazeData.length &&	
			mazeData[next_y][next_x] != 1){
			myplayer.x = next_x;
			myplayer.y = next_y;
		}
	}

	private void printMazeData(){
		for(int i = 0; i < mazeData[0].length; ++i){
			for(int j = 0; j < mazeData.length; ++j){
				System.out.print(mazeData[i][j] + " ");
			}
			System.out.println("");
		}
	}
	
	public void stop() {
		//task.cancel();
	//	timer.cancel();
	}
	
	public void setIsShowSolution(boolean isShowSolution){
		this.isShowSolution = isShowSolution;
	}
	
	public void setSolution(ArrayList<String> mazeSolution) {
		this.mazeSolution = mazeSolution;
	}
	public String getMazeAsString() {
		return myplayer.y + "-" + myplayer.x + "-" + description;
	}
}