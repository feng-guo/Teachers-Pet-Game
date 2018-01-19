package game;

import battle.ListOfMoves;
import characters.ListOfCharacters;
import characters.Squad;
import graphics.GameCamera;
import input.KeyManager;
import input.MouseManager;
import items.Inventory;
import items.ListOfInventoryItems;
import states.*;
import worlds.World;

public class Handler {
	
	private Game game;
	private World world;
	
	public Handler (Game game) {
		this.game = game;
	}
	
	public GameCamera getGameCamera() {
		return game.getGameCamera();
	}
	
	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}
	
	public MouseManager getMouseManager() {
		return game.getMouseManager();
	}

	public int getWidth() {
		return game.getWidth();
	}
	
	public int getHeight() {
		return game.getHeight();
	}
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public ListOfCharacters getListOfCharacters() {
		return game.getListOfCharacters();
	}

	public ListOfInventoryItems getListOfInventoryItems() {
		return game.getListOfInventoryItems();
	}

	public ListOfMoves getListOfMoves() {
		return game.getListOfMoves();
	}

	public Inventory getInventory() {
		return game.getInventory();
	}

	public Squad getSquad() {
		return game.getSquad();
	}
}
