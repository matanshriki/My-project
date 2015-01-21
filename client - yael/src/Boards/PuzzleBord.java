package Boards;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class PuzzleBord extends Canvas{
	
	Canvas startGameCanvas;
	private int[][] puzzleData;
	private	GameCharacter c;
	private int s; 
	
	public PuzzleBord(Composite parent, int style, String description) {
		super(parent, style);		
						
		startGameCanvas = new Canvas(getShell(), SWT.PUSH);
		startGameCanvas.setSize(500, 500);
		startGameCanvas.setLayout(new GridLayout(1, false));
		startGameCanvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,2,1));

		c = new GameCharacter(0,0);
				
			int puzzlesize = description.split(",").length;
			System.out.println("puzzlesize = " + (puzzlesize)); //mazesize
			double size = Math.sqrt(puzzlesize);
			s = ((int)size);
			System.out.println("size = " + s); //num of r&c
			puzzleData = new int[s][s];
			//split(description):
	
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
		
			e.gc.drawImage(puzzleImage1, 0,0);	
			e.gc.drawImage(puzzleImage2, 0,0);	
			e.gc.drawImage(puzzleImage3, 0,0);	
			e.gc.drawImage(puzzleImage4, 0,0);	
			e.gc.drawImage(puzzleImage5, 0,0);	
			e.gc.drawImage(puzzleImage6, 0,0);	
			e.gc.drawImage(puzzleImage7, 0,0);	
			e.gc.drawImage(puzzleImage8, 0,0);	
			e.gc.drawImage(puzzleImage9, 0,0);	
					
		c.paint(e, w, h);		
		}
	});
}	
//	public void split(String description){
//		
//
//		String[] arrRow= description.split(",");
//		int i = 0;
//		for (; i < 3; i++)	
//			{System.out.print(arrRow[i]+",");}	System.out.println();
//		for (; i < 6; i++)	
//			{System.out.print(arrRow[i]+",");}	System.out.println();
//		for (; i < 9; i++)	
//			{System.out.print(arrRow[i]+",");}	System.out.println("\n");	
//		
////		String[] arr = description.split(",");
////		
////		System.out.println("i size is: "+arr.length)
////		
////		int sizeof = arr.length*(s);
////		
////		String[] arrRow;
////		for(int i=0;i<arr.length*s;i++) {
////			
////			arrRow = arr[i].split(",");
////
////			System.out.println("j size is: "+arrRow.length);//////////
////			
////			for (int j=0; j<arrRow.length; j++) {
////				System.out.println("("+i+","+j+") = "+arrRow[j]);
////				puzzleData[i][j] = Integer.parseInt(arrRow[j]);
////				System.out.println("puzzleData is: "+puzzleData[i][j]);
////			}
////		}
//	}

}
