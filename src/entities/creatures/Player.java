package entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Handler;
import graphics.Animation;
import graphics.Assets;
import tiles.Tile;

public class Player extends Creature{
	
	
	// Variables
	private Animation animDown, animUp, animLeft, animRight;
	private int direction;
	private boolean hasStopped;
	
	// Creates player
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		// SPECIFIC TO A STANDARD PLAYER
		bounds.x = 10;
		bounds.y = 20;
		bounds.width = 16;
		bounds.height = 22;
		
		// Animations
		animDown = new Animation(100, Assets.player_down);
		animUp = new Animation(100, Assets.player_up);
		animLeft = new Animation(100, Assets.player_left);
		animRight = new Animation(100, Assets.player_right);

		
		
	}

	@Override
	public void tick() {
		
		// Animations
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();
		
		// Movement
		getInput();
		if (!hasStopped) {
			move();
		}
		//hasStopped = false;
		handler.getGameCamera().centerOnEntity(this);
	}
	
	private void getInput() {
		
		xMove = 0;
		yMove = 0;
		
		//if(x <= handler.getWorld().getWidth() && x >= 0 && y <= handler.getWorld().getHeight() && y >= 0) {
			if(handler.getKeyManager().up) {
				yMove = -speed;
			}
			else if(handler.getKeyManager().down) {
				yMove = speed;
			}
			else if(handler.getKeyManager().left) {
				xMove = -speed;
			}
			else if(handler.getKeyManager().right) {
				xMove = speed;
			}
		//} 
		if (x == -3) {
			x = 0;
		}
		if (y == -3) {
			y = 0;
		}
		//System.out.println(handler.getWorld().getWidth() * Tile.TILE_WIDTH);
		if (x > (handler.getWorld().getWidth() - 1) * Tile.TILE_WIDTH) {
			x = (handler.getWorld().getWidth() - 1) * Tile.TILE_WIDTH; 
		}
		if (y > (handler.getWorld().getHeight() - 1.5f) * Tile.TILE_HEIGHT) {
			y = (handler.getWorld().getHeight() - 1.5f) * Tile.TILE_HEIGHT;
		}

		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		
		
//		g.setColor(Color.RED);
//		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//					(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
//					bounds.width, bounds.height);
//					
	}
	
	// Retrieves animation frame
	private BufferedImage getCurrentAnimationFrame() {
		
		if (hasStopped) {
			return returnStationarySprite(direction);
		}
		
		if (xMove < 0){ // going left
			direction = 1;
			return animLeft.getCurrentFrame();
		} else if (xMove > 0){ // going right
			direction = 2;
			return animRight.getCurrentFrame();
		} else if (yMove < 0) { // going up
			direction = 3;
			return animUp.getCurrentFrame();
		} else if (yMove > 0) { // going down
			direction = 4;
			return animDown.getCurrentFrame();
		} else if (xMove == 0 && yMove == 0){
			
			// Keeps them facing the current direction
			return returnStationarySprite(direction);
		}
		
		return Assets.player_down[0];
		
		
		
	}
	
	// Makes them stay still
	public BufferedImage returnStationarySprite(int direction) {
		if(direction == 1) {
			return Assets.player_left[0];
		} else if (direction == 2) {
			return Assets.player_right[0];
		} else if (direction == 3) {
			return Assets.player_up[0];
		} else {
			return Assets.player_down[0];
		}
	}

	public int getDirection() {
		return direction;
	}
	
	public void stopPlayer() {
		hasStopped = true;
	}
	
	public void unStopPlayer() {
		hasStopped = false;
	}

}
