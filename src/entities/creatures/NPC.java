package entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JComboBox.KeySelectionManager;
import javax.swing.JFrame;

import display.Display;
import game.Game;
import game.Handler;
import graphics.Animation;
import graphics.Assets;
import states.State;
import tiles.Tile;

public class NPC extends Creature{
	
	private Animation animDown, animUp, animLeft, animRight;
	private int direction, ultimateDir;
	private int secondTimer;
	private float speed = 1.5f;
	private String name;
	private float startX, startY;
	private int boxSize;
	private boolean hasStopped = false;
	private int battlesStarted = 0;
	private Display battleDisplay;
	
	public NPC(Handler handler, String name, int boxSize, float x, float y) {

		
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
		
		
		
		Rectangle npcRect = new Rectangle((int) x - 10, (int) y, (int) width + 10, (int) height);
		Rectangle playerRect = new Rectangle((int) handler.getWorld().getEntityManager().getPlayer().getX(),
					(int) handler.getWorld().getEntityManager().getPlayer().getY(),
					(int) handler.getWorld().getEntityManager().getPlayer().getWidth(),
					(int) handler.getWorld().getEntityManager().getPlayer().getHeight());
		
		
		if(npcRect.intersects(playerRect)) {
			stopNPC();
			battlesStarted++;
			if (battlesStarted <= 1) {
//				System.out.println("true");
//				battleDisplay = new Display("Battle", 200, 200);
//				
//				battleDisplay.getFrame().setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/4 + 20, Toolkit.getDefaultToolkit().getScreenSize().height/4 + 20);
//				Graphics g = battleDisplay.getFrame().getGraphics();
//				g.setColor(Color.BLACK);
//				g.drawString("hi", 20, 20);
				
				handler.getKeyManager().battle = true;
				handler.getKeyManager().forceKeyChange(KeyEvent.VK_X, true);
//				JFrame frame = new JFrame();
//				frame.setSize(300, 300);
//				frame.setVisible(true);

			}
			//handler.getWorld().getEntityManager().getPlayer().unStopPlayer();
			
			handler.getWorld().getEntityManager().getPlayer().stopPlayer();

			if (handler.getWorld().getEntityManager().getPlayer().getDirection() == 1) {
				ultimateDir = 2;
			} else if (handler.getWorld().getEntityManager().getPlayer().getDirection() == 2) {
				ultimateDir = 1;
			} else if (handler.getWorld().getEntityManager().getPlayer().getDirection() == 3) {
				ultimateDir = 4;
			} else if (handler.getWorld().getEntityManager().getPlayer().getDirection() == 4) {
				ultimateDir = 3;
			}
			
//			if(!battleDisplay.getFrame().isActive()) {
//				handler.getWorld().getEntityManager().getPlayer().unStopPlayer();
//				if (handler.getWorld().getEntityManager().getPlayer().getxMove() > 0 || handler.getWorld().getEntityManager().getPlayer().getyMove() > 0) {
//					battleDisplay.getFrame().setVisible(false);
//				}
//			}
			
		} else {
			
			hasStopped = false;
			ultimateDir = 0;
		}
			
		
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
		if (ultimateDir == 0) {
			g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		} else {
			if(ultimateDir == 1) {
				g.drawImage(Assets.feng_left[0], (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
			} else if (ultimateDir == 2) {
				g.drawImage(Assets.feng_right[0], (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
			} else if (ultimateDir == 3) {
				g.drawImage(Assets.feng_up[0], (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
			} else if (ultimateDir == 4) {
				g.drawImage(Assets.feng_down[0], (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
			}
		}
		
		
		
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
