package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.Handler;
import graphics.Assets;

public class MiniGameState extends State {

	
	private int clockTimer = 60;
	private int tempTimer = 0;
	
	private int x = 300;
	private int y = 300;
	
	private Rectangle playerRect;
	private Rectangle[] tempRect;
	private boolean[] sendRect;
	
	private int score = 0;
	
	public MiniGameState(Handler handler) {
		super(handler);
		
			playerRect = new Rectangle(x, y, 20, 20);
			tempRect = new Rectangle[200];
			sendRect = new boolean[200];
			for (int i = 0; i < 200; i++) {
				tempRect[i] = new Rectangle((int) (Math.random()*600), - 200 * i, 10, 10);
				sendRect[i] = false;
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
	}

	@Override
	public void render(Graphics g) {
		
		
		g.fillRect(playerRect.x, playerRect.y, playerRect.width, playerRect.height);
		
		for (int i = 0; i < 200; i++) {
			if (sendRect[i]) {
				g.setColor(Color.red);
				
				g.drawImage(Assets.feng_down[1], tempRect[i].x, 2*tempTimer - 200 + tempRect[i].y, tempRect[i].width, tempRect[i].height, null);
				
				//g.fillRect(tempRect[i].x, 2*tempTimer - 200 + tempRect[i].y, tempRect[i].width, tempRect[i].height);
			}
			if(new Rectangle(tempRect[i].x, 2*tempTimer - 200 + tempRect[i].y, tempRect[i].width, tempRect[i].height).intersects(playerRect)) {
				g.setColor(Color.green);
				g.fillRect(tempRect[i].x, 2*tempTimer - 200 + tempRect[i].y, tempRect[i].width, tempRect[i].height);
				score++;
			}
		}
		g.setColor(Color.BLACK);
		g.drawString("" + clockTimer, 20, 20);
		g.drawString("" + score, 100, 20);
	}
	
	public void movePosition() {
		if (handler.getKeyManager().left) {
			x -= 10;
		} else if (handler.getKeyManager().right) {
			x += 10;
		}
	}

}
