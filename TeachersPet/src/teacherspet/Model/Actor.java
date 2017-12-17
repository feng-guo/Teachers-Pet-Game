package Model;

import com.badlogic.gdx.math.Interpolation;

public class Actor {
	
	private TileMap map;
	private int x;
	private int y;
	
	private float worldX, worldY;
	
	private int srcX, srcY;
	private int destX, destY;
	private float animationTimer;
	private float ANIMATION_TIME = 0.5f;
	
	private ACTOR_STATE state;
	
	public Actor(TileMap map, int x, int y) {
		this.map = map;
		this.x = x;
		this.y = y;
		this.worldX = x;
		this.worldY = y;
		map.getTile(x, y).setActor(this);
		this.state = ACTOR_STATE.STANDING;
	}
	
	public enum ACTOR_STATE {
		WALKING,
		STANDING,
		;
	}
	
	public void update(float delta) {
		if (state == ACTOR_STATE.WALKING) {
			animationTimer += delta;
			worldX = Interpolation.linear.apply(srcX, destX, animationTimer/ANIMATION_TIME);
			worldY = Interpolation.linear.apply(srcY, destY, animationTimer/ANIMATION_TIME);
			if (animationTimer > ANIMATION_TIME) {
				finishMove();
			}
		}
	}
	
	public float getWorldX() {
		return worldX;
	}

	public float getWorldY() {
		return worldY;
	}

	public boolean move(int xChange, int yChange) {
		
		if (state != ACTOR_STATE.STANDING) {
			return false;
		}
		
		if (x + xChange >= map.getWidth() || x + xChange < 0 || y + yChange >= map.getHeight() || y + yChange < 0) {
			return false;
		}
		
		if (map.getTile(x+xChange, y+yChange).getActor() != null) {
			return false;
		}
		
		initializeMove(x, y, xChange, yChange);
		
		map.getTile(x, y).setActor(null);
		
		x += xChange;
		y += yChange;
		
		map.getTile(x, y).setActor(this);
		
		return true;
	}

	private void initializeMove(int oldX, int oldY, int xChange, int yChange) {
		this.srcX = oldX;
		this.srcY = oldY;
		this.destX = oldX + xChange;
		this.destY = oldY + yChange;
		this.worldX = oldX;
		this.worldY = oldY;
		animationTimer = 0f;
		state = ACTOR_STATE.WALKING;
		
	}
	
	private void finishMove() {
		state = ACTOR_STATE.STANDING;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
}
