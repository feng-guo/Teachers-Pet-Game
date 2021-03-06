package entities.creatures;


import java.awt.Graphics;
import java.awt.Rectangle;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import game.Handler;
import graphics.Animation;

import tiles.Tile;

public class NPC extends Creature{
	
	// variables
	private Animation animDown, animUp, animLeft, animRight;
	private int direction, ultimateDir;
	private int secondTimer;
	private float speed = 1.5f;
	private float startX, startY;
	private int boxSize;
	private boolean hasStopped = false;
	private int battlesStarted = 0;
	private String name;
	private BufferedImage[] spriteSetDown, spriteSetUp, spriteSetLeft, spriteSetRight;
	private ArrayList<BufferedImage[]> spriteMoves;
	
	// constructor
	public NPC(String name, Handler handler, 
			BufferedImage[] spriteSetDown, BufferedImage[] spriteSetUp, BufferedImage[] spriteSetLeft, BufferedImage[] spriteSetRight,
			int boxSize, float x, float y) {

		
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		this.name = name;
		startX = x;
		startY = y;
		this.boxSize = boxSize;
		
		this.spriteSetDown = spriteSetDown;
		this.spriteSetUp = spriteSetUp;
		this.spriteSetLeft = spriteSetLeft;
		this.spriteSetRight = spriteSetRight;
		
		spriteMoves = new ArrayList<BufferedImage[]>();
		spriteMoves.add(spriteSetUp);
		spriteMoves.add(spriteSetDown);
		spriteMoves.add(spriteSetLeft);
		spriteMoves.add(spriteSetRight);

		
		
		
		// SPECIFIC TO A STANDARD NPC
		bounds.x = 5;
		bounds.y = 10;
		bounds.width = 20;
		bounds.height = 20;
		
		// Animations
		animDown = new Animation(100, spriteSetDown);
		animUp = new Animation(100, spriteSetUp);
		animLeft = new Animation(100, spriteSetLeft);
		animRight = new Animation(100, spriteSetRight);
	}

	@Override
	public void tick() {
		// Animations
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();
		
		Rectangle npcRect = new Rectangle((int) x, (int) y, (int) width, (int) height);
		Rectangle playerRect = new Rectangle((int) handler.getWorld().getEntityManager().getPlayer().getX(),
					(int) handler.getWorld().getEntityManager().getPlayer().getY(),
					(int) handler.getWorld().getEntityManager().getPlayer().getWidth(),
					(int) handler.getWorld().getEntityManager().getPlayer().getHeight());
		
		
		if(npcRect.intersects(playerRect)) {
			stopNPC();
			battlesStarted++;
			if (battlesStarted <= 1) {
				
				//handler.getKeyManager().battle = true;
				
				
				//UNCOMMENT THIS
				handler.getKeyManager().forceKeyChange(KeyEvent.VK_X, true);
				handler.getGame().setCurrentOpponentName(name);
				handler.getGame().setCurrentOpponentSprites(spriteMoves);

			} else if (battlesStarted > 1) {
				handler.getKeyManager().forceKeyChange(KeyEvent.VK_X, false);
				//unStopNPC();
				handler.getWorld().getEntityManager().getPlayer().unStopPlayer();
			}
			
			try {
				if (handler.getGame().getBattleState().getBattleTest().isBattleEnd()) {
				}
			} catch (NullPointerException e) {
			}
			
			//handler.getWorld().getEntityManager().getPlayer().stopPlayer();

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

			unStopNPC();
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
			unStopNPC();
			g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		} else {
			stopNPC();
			if(ultimateDir == 1) {
				g.drawImage(spriteSetLeft[0], (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
			} else if (ultimateDir == 2) {
				g.drawImage(spriteSetRight[0], (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
			} else if (ultimateDir == 3) {
				g.drawImage(spriteSetUp[0], (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
			} else if (ultimateDir == 4) {
				g.drawImage(spriteSetDown[0], (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
			}
		}
		
		
		
//		g.setColor(Color.RED);
//		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//					(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
//					bounds.width, bounds.height);
//				
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		
		if(hasStopped) {
			xMove = 0;
			yMove = 0;
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
			if(direction == 1) {
				return spriteSetLeft[0];
			} else if (direction == 2) {
				return spriteSetRight[0];
			} else if (direction == 3) {
				return spriteSetUp[0];
			}
		}
		
		return spriteSetDown[0];
		
		
		
	}
	
	public void stopNPC() {
		hasStopped = true;
	}
	
	public void unStopNPC() {
		hasStopped = false;
	}
	
	public ArrayList<BufferedImage[]> getSprites() {
		return spriteMoves;
	}
	

}
