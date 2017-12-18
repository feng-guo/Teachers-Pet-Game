package Model;

import com.badlogic.gdx.graphics.g2d.TextureRegion; 
import com.badlogic.gdx.math.Interpolation;

import Utility.AnimationSet;

public class Actor {
	
	private TileMap map;
	private int x;
	private int y;
	private DIRECTION facing;
	
	private float worldX, worldY;
	
	private int srcX, srcY;
	private int destX, destY;
	private float animationTimer;
	private float ANIMATION_TIME = 0.3f;
	
	private float walkTimer;
	private boolean moveRequestThisFrame;
	
	private ACTOR_STATE state;
	private AnimationSet animations;
	
	public Actor(TileMap map, int x, int y, AnimationSet animations) {
		this.map = map;
		this.x = x;
		this.y = y;
		this.worldX = x;
		this.worldY = y;
		this.animations = animations;
		map.getTile(x, y).setActor(this);
		this.state = ACTOR_STATE.STANDING;
		this.facing = DIRECTION.SOUTH;
	}
	
	public enum ACTOR_STATE {
		WALKING,
		STANDING,
		;
	}
	
	public void update(float delta) {
		if (state == ACTOR_STATE.WALKING) {
			
			animationTimer += delta;
			walkTimer += delta;
			
			worldX = Interpolation.linear.apply(srcX, destX, animationTimer/ANIMATION_TIME);
			worldY = Interpolation.linear.apply(srcY, destY, animationTimer/ANIMATION_TIME);
			
			if (animationTimer > ANIMATION_TIME) {
				walkTimer -= (animationTimer-ANIMATION_TIME);
				finishMove();
				if (moveRequestThisFrame) {
					move(facing);
				} else {
					walkTimer = 0f;
				}
			}
		}
		
		moveRequestThisFrame = false;
	}
	
	public float getWorldX() {
		return worldX;
	}

	public float getWorldY() {
		return worldY;
	}
	
	public TextureRegion getSprite() {
		
		if (state == ACTOR_STATE.WALKING) {
			return animations.getWalking(facing).getKeyFrame(walkTimer);
		} else if (state == ACTOR_STATE.STANDING) {
			return animations.getStanding(facing);
		}
		return animations.getStanding(DIRECTION.SOUTH);
	}

	public boolean move(DIRECTION dir) {
		
		if (state == ACTOR_STATE.WALKING) {
			
			
			if (facing == dir) {
				moveRequestThisFrame = true;
			}
			return false;
		}
		
		// If it is in bounds
		if (x + dir.getXChange() >= map.getWidth() || x + dir.getXChange() < 0 || y + dir.getYChange() >= map.getHeight() || y + dir.getYChange() < 0) {
			return false;
		}
		
		if (map.getTile(x + dir.getXChange(), y + dir.getYChange()).getActor() != null) {
			return false;
		}
		
		initializeMove(dir);
		
		map.getTile(x, y).setActor(null);
		
		x += dir.getXChange();
		y += dir.getYChange();
		
		map.getTile(x, y).setActor(this);
		
		return true;
	}

	private void initializeMove(DIRECTION dir) {
		
		// CHANGING THIS FIXES THE ERROR FOR SOME REASON??
		this.facing = dir;
		
		this.srcX = x;
		this.srcY = y;
		this.destX = x + dir.getXChange();
		this.destY = y + dir.getYChange();
		this.worldX = x;
		this.worldY = x;
		animationTimer = 0f;
		state = ACTOR_STATE.WALKING;
		
	}
	
	private void finishMove() {
		state = ACTOR_STATE.STANDING;
		
		this.worldX = destX;
		this.worldY = destY;
		this.srcX = 0;
		this.srcY = 0;
		this.destX = 0;
		this.destY = 0;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
}
