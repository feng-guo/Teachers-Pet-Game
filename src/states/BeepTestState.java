package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import game.Handler;
import graphics.Animation;
import graphics.Assets;

public class BeepTestState extends State {
	
	private int clicks = 0;
	private int tempTimer = 0;
	private int clockTimer = 30;
	private double clicksPerSecond;
	private int x = 20;
	private int velocity;
	private Animation animLeft, animRight;

	public BeepTestState(Handler handler) {
		super(handler);
		animLeft = new Animation(100, Assets.player_left);
		animRight = new Animation(100, Assets.player_right);
	}

	@Override
	public void tick() {
		
		animLeft.tick();
		animRight.tick();
		
		if(handler.getMouseManager().isSingleClick()) {
			clicks++;
			handler.getMouseManager().setSingleClick(false);
		}
		
		tempTimer++;
		
		if (tempTimer%60 == 0) {
			clockTimer--;
		}
		
		clicksPerSecond = (double) clicks/(30-clockTimer);
		if (clicksPerSecond < 20) {
			if (x > 500) {
				velocity = -1;
			} else if (x < 50) {
				velocity = 1;
			}
			x += clicksPerSecond * velocity;
		}
		// System.out.println(x);
		
		if (clockTimer < 1) {
			handler.getKeyManager().forceKeyChange(KeyEvent.VK_C, false);
			handler.getGame().setRecentlyPlayed();
			handler.getGame().setGameState();
		}

	}

	@Override
	public void render(Graphics g) {
		g.setFont(Assets.font16);
		g.setColor(new Color(176, 224, 230));
		g.fillRect(0, 0, 600, 400);
		
		if (clicksPerSecond > 5.7) {
			g.setColor(Color.GREEN);
			
		} else if (clicksPerSecond < 3.0) {
			g.setColor(Color.RED);
		} else {
			g.setColor(Color.YELLOW);
		}
		
		if (velocity > 0) {
			g.drawImage(animRight.getCurrentFrame(), x, 200, 40, 60, null);
		} else if (velocity < 0) {
			g.drawImage(animLeft.getCurrentFrame(), x, 200, 40, 60, null);
		}
		
//		if(clicksPerSecond < 6) {
//			g.fillRect(10, 80, (int) (clicksPerSecond * 50), 20);
//		} else {
//			g.fillRect(10, 80, 400, 20);
//		}
		
		g.fillRect(10, 80, (int) (clicksPerSecond * 50), 20);
		g.setColor(Color.BLACK);
		g.drawString("Stay in the green zone", 20, 30);
		g.drawString("by 30 seconds to win!", 20, 60);
		g.drawRect(10, 80, 570, 20);
		g.drawLine(300, 80, 300, 100);
		g.drawLine(150, 80, 150, 100);
		g.setFont(Assets.font24);
		g.drawString("" + clockTimer, 480, 50);
		
		
	}

}
