package Boards;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class MazeGameBoard extends GameBoard {

	String mazeString;
	int[][] maze;
	int mazeSize;
	Image mazeBackround;
	GameCharacter start;
	GameCharacter end;
	
	public MazeGameBoard(Composite parent, int style, String description) {
		super(parent, style, description);
		mazeBackround = new Image(getDisplay(), "resources/wall3.jpg");
		mazeString = description;
		if (mazeString != null) {
			buildMaze();
			createBoardGame();
		}
	}


	public void createBoardGame() {
		
		setLayout(new GridLayout(2, false));									//Layout of Board itself (in side)
		setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));		//Layout (out Side)
		setBackgroundImage(mazeBackround);
		
		addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent e) {
				Image tmp = new Image(getDisplay(), "resources/wall2.jpg");


						if (maze != null) {
							int width = getSize().x;
							int height = getSize().y;
							int w = width / maze[0].length;
							int h = height / maze.length;
							for (int i = 0; i < maze.length; i++)
								for (int j = 0; j < maze[0].length; j++) {
									int x = j * w;
									int y = i * h;
									if (maze[i][j] == 0) {
									e.gc.drawImage(tmp, 0, 0, tmp.getImageData().width, tmp.getImageData().height, x, y, w-5, h-5);
									e.gc.drawRectangle(x, y, w-5, h-5);
									}
								}
						}
					}
				});
	}

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
	
	public void extractSizeOfMaze(String s) {
		String[] a = s.split("Size ");
		String[] b = a[1].split("X");
		
		mazeSize = Integer.parseInt(b[0]);
	}
	
	public void setWallsOfMaze(String s) {
		String[] a = s.split(": ");
		String b = a[1];
		String[] arrWalls = b.split(" ");
	
		for(int k=0; k<arrWalls.length; k++) {
			int row =getRowNumber(arrWalls[k]); 
			int col =getColumnNumber(arrWalls[k]);
		
			maze[row][col] = 0;
		}
		
	}
	
	public void buildMaze() {
	
		extractSizeOfMaze(mazeString);
		maze = new int[mazeSize][mazeSize];
		for(int i=0; i<mazeSize; i++)
			for(int j=0; j<mazeSize; j++) {
				maze[i][j] = 1;
			}
		System.out.println();
		setWallsOfMaze(mazeString);

}
		
