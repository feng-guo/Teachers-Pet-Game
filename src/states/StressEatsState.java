package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import game.Handler;
import graphics.Assets;

public class StressEatsState extends State {

	
	private int clockTimer = 60;
	private int tempTimer = 0;
	
	private int x = 300;
	private int y = 300;
	
	private Rectangle playerRect;
	private Rectangle[] tempRect;
	private boolean[] sendRect, caughtRect;
	private BufferedImage[] rectPic;
	
	private int score = 0;
	
	public StressEatsState(Handler handler) {
		super(handler);
		
			playerRect = new Rectangle(x, y, 20, 20);
			tempRect = new Rectangle[200];
			sendRect = new boolean[200];
			rectPic = new BufferedImage[200];
			caughtRect = new boolean[200];
			
			for (int i = 0; i < 200; i++) {
				caughtRect[i] = false;
				tempRect[i] = new Rectangle((int) (Math.random()*460), - 200 * i, 50, 50);
				sendRect[i] = false;
				rectPic[i] = Assets.foodArray[(int)(Math.random()*3)][(int)(Math.random()*4)];
			}

	}

	@Override
	public void tick() {
		movePosition();
		
		playerRect.x = x;
		playerRect.y = y;
		
		tempTimer++;
		
		if (tempTimer%60 == 0) {
			clockTimer--;
		} else if (tempTimer%20 == 0) {
			sendRect[60-clockTimer] = true;
		}
		
		if (clockTimer < 1 || score >= 900) {
			handler.getKeyManager().forceKeyChange(KeyEvent.VK_Z, false);
			handler.getGame().setRecentlyPlayed();
			handler.getGame().setGameState();

		}
	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(new Color(176, 224, 230));
		g.fillRect(0, 0, 600, 400);
		
		
		g.setFont(Assets.font16);
		
		if (score/(60 - (clockTimer-1)) > 900/60) {
			g.drawImage(Assets.happyFace, playerRect.x - 10, playerRect.y - 10, playerRect.width + 10, playerRect.height + 10, null);
		} else {
			g.drawImage(Assets.sadFace, playerRect.x - 10, playerRect.y - 10, playerRect.width + 10, playerRect.height + 10, null);
		}
		//g.fillRect(playerRect.x, playerRect.y, playerRect.width, playerRect.height);
		
		int tempY = 2 * tempTimer - 200;
		
		for (int i = 0; i < 200; i++) {
			if (sendRect[i] && !caughtRect[i]) {
				g.setColor(Color.red);
				
				g.drawImage(rectPic[i], tempRect[i].x, tempY + tempRect[i].y, tempRect[i].width, tempRect[i].height , null);
				
				//g.fillRect(tempRect[i].x, 2*tempTimer - 200 + tempRect[i].y, tempRect[i].width, tempRect[i].height);
			}
			if(new Rectangle(tempRect[i].x, tempY + tempRect[i].y, tempRect[i].width, tempRect[i].height).intersects(playerRect)) {
				caughtRect[i] = true;
				//g.drawImage(rectPic[i], tempRect[i].x, tempY + tempRect[i].y, tempRect[i].width, tempRect[i].height , null);
				score++;
			}
		}
		//g.setColor(new Color(144, 238, 144));
		g.setColor(Color.GREEN);
		if (score < 900) {
			g.fillRect(560, 85, 20, score/3);
		} else if (score >= 900) {
			g.fillRect(560, 85, 20, 900);
		}
		g.setColor(Color.BLACK);
		g.drawRect(560, 85, 20, 300);
		g.drawString("Time Left: " + clockTimer, 40, 375);
		g.drawString("Score: " + score, 340, 375);
		g.drawImage(Assets.foodBanner, 0, 0, null);
	}
	
	public void movePosition() {
		if (handler.getKeyManager().left) {
			if (x > 20) {
				x -= 10;
			}
		} else if (handler.getKeyManager().right) {
			if (x < 580) {
				x += 10;
			}
		}
	}

}
