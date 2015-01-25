package Boards;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

class GameCharacter {
	int x, y;
	private Image player;

	public GameCharacter(int x, int y) {
		this.x = x;
		this.y = y;
		player = new Image(null, "resources/start.jpg");
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void paint(PaintEvent e, int w, int h) {
    	e.gc.setBackground(new Color(null, 210, 210, 210));
		e.gc.drawImage(player, 0, 0, player.getImageData().width, player.getImageData().height, x, y, w, h);
	}
}
