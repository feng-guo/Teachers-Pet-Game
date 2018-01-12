package graphics;

import entities.Entity;
import game.Game;
import game.Handler;
import tiles.Tile;

public class GameCamera {
	
	private float xOffset, yOffset;
	private Handler handler;
	
	public GameCamera(Handler handler, float xOffset, float yOffset) {
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	// Smart Camera movement
//	public void checkBlankSpace() {
//		if(xOffset < 0) {
//			xOffset = 0;
//		} else if (xOffset > handler.getWorld().getWidth() * Tile.TILE_WIDTH - handler.getWidth()) {
//			xOffset = handler.getWorld().getWidth() * Tile.TILE_WIDTH - handler.getWidth();
//		}
//		if(yOffset < 0) {
//			yOffset = 0;
//		} else if(yOffset > handler.getWorld().getHeight() * Tile.TILE_HEIGHT - handler.getHeight()) {
//			yOffset = handler.getWorld().getHeight() * Tile.TILE_HEIGHT - handler.getHeight();
//		}
//	}
	
	public void centerOnEntity(Entity e) {
		xOffset = e.getX() - handler.getWidth() / 2 + e.getWidth() / 2;
		yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;
		//checkBlankSpace();
	}
	
	public void move(float xAmt, float yAmt) {
		xOffset += xAmt;
		yOffset += yAmt;
		//checkBlankSpace();
	}

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}
	
}
