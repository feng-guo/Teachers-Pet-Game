package Model;

public class Actor {
	
	private TileMap map;
	private int x;
	private int y;
	
	public Actor(TileMap map, int x, int y) {
		this.map = map;
		this.x = x;
		this.y = y;
		map.getTile(x, y).setActor(this);
	}
	
	public boolean move(int xChange, int yChange) {
		
		if (x + xChange >= map.getWidth() || x + xChange < 0 || y + yChange >= map.getHeight() || y + yChange < 0) {
			return false;
		}
		
		if (map.getTile(x+xChange, y+yChange).getActor() != null) {
			return false;
		}
		
		map.getTile(x, y).setActor(null);
		
		x += xChange;
		y += yChange;
		
		map.getTile(x, y).setActor(this);
		
		return true;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
}
