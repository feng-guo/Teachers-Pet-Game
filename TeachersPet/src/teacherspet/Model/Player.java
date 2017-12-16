package Model;

public class Player {
	private int x;
	private int y;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void move(int xChange, int yChange) {
		x += xChange;
		y += yChange;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
}
