package entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Game;
import game.Handler;
import graphics.Animation;
import graphics.Assets;

public class Player extends Creature{
	
	private Animation animDown, animUp, animLeft, animRight;
	
	
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
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		
		/*
		g.setColor(Color.RED);
		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
					(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
					bounds.width, bounds.height);
					*/
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		if (xMove < 0){
			return animLeft.getCurrentFrame();
		} else if (xMove > 0){
			return animRight.getCurrentFrame();
		} else if (yMove < 0) {
			return animUp.getCurrentFrame();
		} else if (yMove > 0) {
			return animDown.getCurrentFrame();
		} else {
			return Assets.feng_down[0];
		}
		
		
		
	}

}
