package worlds;

import java.awt.Graphics;

import entities.EntityManager;
import entities.creatures.NPC;
import entities.creatures.Player;
import entities.statics.*;
import game.Game;
import game.Handler;
import tiles.Tile;
import utils.Utils;

public class World {

	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;

	// Entities
	private EntityManager entityManager;


	public World(Handler handler, String path) {
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, spawnX * Tile.TILE_WIDTH, spawnY * Tile.TILE_WIDTH));

		entityManager.addEntity(new NPC(handler, "Feng2", 100, 250, 250));
		//entityManager.addEntity(new Tree(handler, 100, 250));

		//HALLWAY
		//Walls and windows
		for (int x = 2; x < 9; x++) {
			entityManager.addEntity (new IndoorWindowClosed(handler, (40 * x), 10));
		}
		for (int x = 15; x < 26; x++) {
			entityManager.addEntity (new Locker(handler, (30 * x), 10));
		}
		for (int x = 28; x < 40; x++) {
			entityManager.addEntity (new Locker(handler, (30 * x), 10));
		}
		for (int x = 5; x < 38; x++) {
			entityManager.addEntity(new Bench(handler, (10 * x), 60));
		}
		//Doors
		entityManager.addEntity(new HallwayDoorOpen(handler, (40 * 10), 10));
		entityManager.addEntity(new HallwayDoorOpen(handler, (40 * 20 - 9), 10));
		entityManager.addEntity(new HallwayDoorOpen(handler, (40 * 30 + 10), 10));


		loadWorld(path);

		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);


	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void tick() {
		entityManager.tick();
	}

	public void render(Graphics g) {

		// SMART RENDERING
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILE_WIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILE_HEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);


		for(int y = yStart; y < yEnd; y++) {
			for(int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g,
						(int)(x * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()),
						(int)(y * Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()));
			}
		}

		entityManager.render(g);

	}

	public Tile getTile(int x, int y) {
		//System.out.println(x + "," + y);
		if(x < 0 || y < 0 || x >= width || y >= height) {
			return Tile.grassTile;
		}

		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null) {
			return Tile.nullTile;
		}
		return t;

	}

	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");

		// First 4 numbers in file are the values
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);

		tiles = new int[width][height];

		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}


}
