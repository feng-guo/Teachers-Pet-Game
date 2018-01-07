package Model;

public class TileMap {
	
	private int width, height;
	private Tile[][] tiles;
	
	public TileMap(int width, int height) {
		this.width = width;
		this.height = height;
				
		tiles = new Tile[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				
				if (Math.random() > 0.5) {
					tiles[i][j] = new Tile(TERRAIN.GRASS_1);
				} else {
					tiles[i][j] = new Tile(TERRAIN.GRASS_2);
				}
			}
		}
	}
	
	public Tile getTile(int x, int y) {
		return tiles[x][y];
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
