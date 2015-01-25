package Boards;

import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class PuzzleBord extends Canvas{
	
	String puzzleData;
	private	GameCharacter character;
	int puzzleSize;
	private static final int size = 3;
	Canvas startPuzzleCanvas;
//	Timer timer;
//	TimerTask task;
	int[][] board;
	
	
	Image puzzleImage1, puzzleImage2, puzzleImage3, puzzleImage4, puzzleImage5, puzzleImage6, puzzleImage7, puzzleImage8, puzzleImage0;
	
	private HashMap<Integer, Image> puzzleMap;

	public PuzzleBord(Composite parent, int style, String description) {
		super(parent, style);		

		puzzleData = description;
		//System.out.println("puzzlesize = " + (puzzlesize)); //puzzle size
		puzzleSize = size * size;
		//System.out.println("size = " + size); //num of r&c
		board = new int[size][size];
		
		if(puzzleData != null)
		{
			createPuzzle();
			creatBoardGame();
		}
	}
		
	private void createPuzzle() {
		String[] d = puzzleData.split(",");
		
		String c;
		int k=0;
		for(int i=0 ; i < size ; i++)
			for(int j=0 ; j < size ; j++){
				c = d[k];
				board[i][j] = Integer.parseInt(c);
				k++;
		}	
	}

	public void setImage(){
		
		 puzzleImage1 = new Image(getDisplay(), "resources/1.jpg");
		 puzzleImage2 = new Image(getDisplay(), "resources/2.jpg");
		 puzzleImage3 = new Image(getDisplay(), "resources/3.jpg");
		 puzzleImage4 = new Image(getDisplay(), "resources/4.jpg");
		 puzzleImage5 = new Image(getDisplay(), "resources/5.jpg");
		 puzzleImage6 = new Image(getDisplay(), "resources/6.jpg");
		 puzzleImage7 = new Image(getDisplay(), "resources/7.jpg");
		 puzzleImage8 = new Image(getDisplay(), "resources/8.jpg");
		 puzzleImage0 = new Image(getDisplay(), "resources/white.jpg");
		
		puzzleMap = new HashMap<Integer, Image>();
		
		puzzleMap.put(1,puzzleImage1);
		puzzleMap.put(2,puzzleImage2);
		puzzleMap.put(3,puzzleImage3);
		puzzleMap.put(4,puzzleImage4);
		puzzleMap.put(5,puzzleImage5);
		puzzleMap.put(6,puzzleImage6);
		puzzleMap.put(7,puzzleImage7);
		puzzleMap.put(8,puzzleImage8);
		puzzleMap.put(0,puzzleImage0);
}
	
	private void creatBoardGame() {
		setImage();
		setLayout(new GridLayout(3, true));
		setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));

		setBackground(new Color(null, 255, 255, 255));

		addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {

				if (board != null) {
					int width = getSize().x;
					int height = getSize().y;
					int w = (width / board[0].length);
					int h = (height / board.length);

					for (int i = 0; i < board.length; i++)
						for (int j = 0; j < board[0].length; j++) {
							int x = j * w;
							int y = i * h;
							Image numberImage = puzzleMap.get(board[i][j]);
							e.gc.drawImage(numberImage, 0, 0,
									numberImage.getImageData().width,
									numberImage.getImageData().height, x, y, w,
									h);
						}
				}
			}
		});
	}

	
	Integer current_x = 2, current_y = 2;
	public void findXYOfBlank(){
		for (int i = 0; i < board.length; i++){
			for (int j = 0; j < board[0].length; j++) {
				if(board[i][j] == 0){
					current_x = i;
					current_y = j;
				}
			}
		}
	}
	
	public void swapPlaces(int x1, int y1, int x2, int y2){
		int tmp = board[x1][y1];
		board[x1][y1] = board[x2][y2];
		board[x2][y2] = tmp;
	}
	
	public void moveGameBlank(String direction) {
		Integer next_x = null, next_y = null;
		findXYOfBlank();
		next_x = current_x;
		next_y = current_y;
		if(direction.equals("right")){
			next_y = current_y + 1;
		}else if(direction.equals("down")){
			next_x = current_x + 1;
		}else if(direction.equals("left")){
			next_y = current_y - 1;
		}else if(direction.equals("up")){
			next_x = current_x - 1;
		}
		if( next_x > -1 && next_x < board[0].length &&
			next_y > -1 && next_y < board.length){
			swapPlaces(next_x, next_y, current_x, current_y);
		}
	}
	
	protected GameCharacter getCharacter() {
		return character;
	}

	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}

	public String getPuzzleData() {
		return puzzleData;
	}

	public void setNewSize(String description) {
		puzzleData = description;
		puzzleSize = size * size;
		board = new int[size][size];

		if (puzzleData != null) {
			createPuzzle();
			creatBoardGame();
		}
		redraw();
	}

	public String getPuzzleAsString() {
		return puzzleData;
	}
}