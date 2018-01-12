package entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Game;
import game.Handler;
import graphics.Animation;
import graphics.Assets;
import tiles.Tile;

public class Player extends Creature{
	
	private Animation animDown, animUp, animLeft, animRight;
	private int direction;
	
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		// SPECIFIC TO A STANDARD PLAYER
		bounds.x = 10;
		bounds.y = 20;
		bounds.width = 16;
		bounds.height = 22;
		
		// Animations
		animDown = new Animation(100, Assets.feng_down);
		animUp = new Animation(100, Assets.feng_up);
		animLeft = new Animation(100, Assets.feng_left);
		animRight = new Animation(100, Assets.feng_right);

		
		
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
		move();
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
	
	private BufferedImage getCurrentAnimationFrame() {
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
			if(direction == 1) {
				return Assets.feng_left[0];
			} else if (direction == 2) {
				return Assets.feng_right[0];
			} else if (direction == 3) {
				return Assets.feng_up[0];
			}
		}
		
		return Assets.feng_down[0];
		
		
		
	}

}
