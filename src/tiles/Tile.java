package tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {

	// STATIC STUFF

	public static Tile[] tiles = new Tile[256];
	public static Tile hallTile = new HallTile(0);
	public static Tile rockTile = new RockTile(1);
	public static Tile nullTile = new NullTile(2);
	public static Tile pathTile	= new PathTile(3);
	public static Tile grassTile = new FloorTile(4);
	public static Tile wallTile = new WallTile (5);
	public static Tile gymTile = new GymTile (6);

	// CLASS

	public static final int TILE_WIDTH = 32, TILE_HEIGHT = 32;

	protected BufferedImage texture;
	protected final int id;

	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;

		tiles[id] = this;
	}

	public void tick() {

	}

	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
	}

	public boolean isSolid() {
		return false;
	}

	public int getId() {
		return id;
	}
}
