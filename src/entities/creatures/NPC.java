package entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.Game;
import game.Handler;
import graphics.Animation;
import graphics.Assets;
import states.State;
import tiles.Tile;

public class NPC extends Creature{
	
	private Animation animDown, animUp, animLeft, animRight;
	private int direction;
	private int secondTimer;
	private float speed = 1.5f;
	private String name;
	private float startX, startY;
	private int boxSize;
	private boolean hasStopped = false;
	
	public NPC(Handler handler, String name, int boxSize,float x, float y) {

		
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		startX = x;
		startY = y;
		this.name = name;
		this.boxSize = boxSize;
		
		// SPECIFIC TO A STANDARD NPC
		bounds.x = 5;
		bounds.y = 10;
		bounds.width = 28;
		bounds.height = 32;
		
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
		
		
		
		
		
		if(x + bounds.x < handler.getWorld().getEntityManager().getPlayer().getX() + 20 && x + bounds.x  + 20 > handler.getWorld().getEntityManager().getPlayer().getX()) {
			if(y + bounds.y < handler.getWorld().getEntityManager().getPlayer().getY() + 20 && y + bounds.y  + 20 > handler.getWorld().getEntityManager().getPlayer().getY()) {
				State.setState(handler.getGame().battleState);
				handler.getGame().startBattle();
				System.out.println("NPC: " + x + ", " + y);
				System.out.println("PLAYER " + handler.getWorld().getEntityManager().getPlayer().getX() + ", " + handler.getWorld().getEntityManager().getPlayer().getY());
			}
		}
			
//			if ((handler.getWorld().getEntityManager().getPlayer().getX()) {
//				System.out.println("COLLIDED");
//			}
		
		
			
		//System.out.println(bounds.x);
		
		if (x < startX){
			x = startX;
		} else if (x > startX + boxSize) {
			x = startX + boxSize;
		}
		
		if (y < startY){
			y = startY;
		} else if (y > startY + boxSize) {
			y = startY + boxSize;
		}
		
		// Movement
		secondTimer++;
		if (secondTimer > 20) {
			getInput();
			secondTimer = 0;
		}
		if(!hasStopped) {
			move();
		}
		//handler.getGameCamera().centerOnEntity(this);
	}
	
	private void getInput() {
		
		xMove = 0;
		yMove = 0;
		
		if(x >= startX && x <= startX + boxSize && y >= startY && y <= startY + boxSize) {
		
			int rand = (int) Math.ceil(Math.random() * 8);
			//System.out.println(x + ", " + y);
			
				if(rand == 1) {
					yMove = -speed;
				}
				else if(rand == 2) {
					yMove = speed;
				}
				else if(rand == 3) {
					xMove = -speed;
				}
				else if(rand == 4) {
					xMove = speed;
				} else {
					xMove = 0;
					yMove = 0;
				}
		}
		
		
		
		
		
		
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
		if (y == handler.getWorld().getHeight() * Tile.TILE_HEIGHT + 3) {
			y = handler.getWorld().getHeight() * Tile.TILE_HEIGHT; 
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
	
	public void stopNPC() {
		hasStopped = true;
	}

}
