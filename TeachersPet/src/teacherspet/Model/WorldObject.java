package Model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import Model.YSortable;

public class WorldObject implements YSortable {
	
	private int x, y;
	
	private TextureRegion texture;
	private float sizeX, sizeY;
	
	private List<GridPoint2> tiles;
	private boolean walkable;
	
	/*
	 * Note: x and y represent the bottom left corner of an object an its size represents the tiles it occupies
	 */

	
	private Animation animation;
	private float animationTimer;
	
	public WorldObject(int x, int y, TextureRegion texture, float sizeX, float sizeY, GridPoint2[] tiles) {
		this.x = x;
		this.y = y;
		this.texture = texture;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.tiles = new ArrayList<GridPoint2>();
		for (GridPoint2 p : tiles) {
			this.tiles.add(p);
		}
		this.walkable = true;
	}
	
	public WorldObject(int x, int y, boolean walkable, TextureRegion texture, float sizeX, float sizeY, GridPoint2 tile) {
		this.x = x;
		this.y = y;
		this.texture = texture;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.tiles = new ArrayList<GridPoint2>();
		this.tiles.add(tile);
		this.walkable = walkable;
	}
	
	public WorldObject(int x, int y, boolean walkable, TextureRegion texture, float sizeX, float sizeY, GridPoint2[] tiles) {
		this.x = x;
		this.y = y;
		this.texture = texture;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.tiles = new ArrayList<GridPoint2>();
		for (GridPoint2 p : tiles) {
			this.tiles.add(p);
		}
		this.walkable = walkable;
	}
	
	public WorldObject(int x, int y, boolean walkable, Animation animation, float sizeX, float sizeY, GridPoint2[] tiles) {
		this.x = x;
		this.y = y;
		this.animation = animation;
		this.animationTimer = 0f;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.tiles = new ArrayList<GridPoint2>();
		for (GridPoint2 p : tiles) {
			this.tiles.add(p);
		}
		this.walkable = walkable;
	}
	
	public void update(float delta) {
		if (animation != null) {
			animationTimer += delta;
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public float getSizeX() {
		return sizeX;
	}
	
	public float getSizeY() {
		return sizeY;
	}
	
	public boolean isWalkable() {
		return walkable;
	}
	
	public boolean containsTile(int x, int y) {
		for (GridPoint2 point : tiles) {
			if (point.x+this.x == x && point.y+this.y == y) {
				return true;
			}
		}
		return false;
	}
	
	public TextureRegion getSprite() {
		if (texture != null) {
			return texture;
		} else {
			return animation.getKeyFrame(animationTimer);
		}
	}
	
	public List<GridPoint2> getTiles() {
		return tiles;
	}

	public float getWorldX() {
		return x;
	}

	public float getWorldY() {
		return y;
	}

}