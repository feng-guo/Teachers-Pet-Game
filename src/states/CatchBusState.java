package states;

import game.Handler;
import graphics.Assets;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class CatchBusState extends State{
    private int clockTimer = 60;
    private int tempTimer = 0;

    private int x = 267;
    private int y = 300;
    private Rectangle playerRect;
    private Rectangle[] tempRect;
    private boolean[] sendRect;
    private BufferedImage[] rectSprite;
    private int health = 400;

    public CatchBusState(Handler handler) {
        super(handler);

        playerRect = new Rectangle(x, y, 67, 100);
        tempRect = new Rectangle[200];
        sendRect = new boolean[200];
        rectSprite = new BufferedImage[200];

        for (int i = 0; i < 200; i++) {
            int determiner = (int)(Math.random() * 3);
            int X;
            if(determiner == 0){
                X = 67;
            }else if (determiner == 1){
                X = 267;
            }else{
                X = 467;
            }
            tempRect[i] = new Rectangle(X, -500 * i, 67, 100);
            sendRect[i] = false;
            double tempRand = Math.random();
            if (tempRand < 0.33) {
            		rectSprite[i] = Assets.cars[0];
            } else if (tempRand < 0.66) {
            		rectSprite[i] = Assets.cars[1];
            } else {
            		rectSprite[i] = Assets.cars[2];
            }
        }
    }

    @Override
    public void tick() {
        movePosition();

        playerRect.x = x;
        playerRect.y = y;

        tempTimer++;

        if (tempTimer % 60 == 0) {
            clockTimer--;
        }
        for(int i = 0; i < 200; i++) {
            if (tempTimer % 5 == 0) {
                sendRect[i] = true;
            }
        }
        if (clockTimer < 1 || health == 0) {
			handler.getKeyManager().forceKeyChange(KeyEvent.VK_B, false);
			handler.getGame().setRecentlyPlayed();
			handler.getGame().setGameState();
			handler.getGame().increaseScore(50);
		} else if (clockTimer < 1) {
			handler.getKeyManager().forceKeyChange(KeyEvent.VK_B, false);
			handler.getGame().setRecentlyPlayed();
			handler.getGame().setGameState();
		}
    }

    @Override
    public void render(Graphics g) {
    	g.setFont(Assets.font16);
        //creates background
        //g.setColor(Color.LIGHT_GRAY);
        g.drawImage(Assets.blackScreen, 0, 0, 600, 400, null);
        
        g.setColor(Color.LIGHT_GRAY);
        
        for (int i = 0; i < 5; i++) {
        		g.fillRect(195, 80 * i, 10, 40);
            g.fillRect(395, 80 * i, 10, 40);
        }
        

        //create player
        g.setColor(Color.BLACK);
        g.drawImage(Assets.player_up[0], playerRect.x, playerRect.y, playerRect.width, playerRect.height, null);

        int tempY;
        for(int i = 0; i < 200; i++){
            tempY = 7 * tempTimer - 800;
            if(sendRect[i]){
                g.setColor(Color.WHITE);
                g.drawImage(rectSprite[i], tempRect[i].x, tempY + tempRect[i].y, tempRect[i].width, tempRect[i].height, null);
            }
            if(new Rectangle(tempRect[i].x, tempY + tempRect[i].y, tempRect[i].width, tempRect[i].height).intersects(playerRect)){
                health /= 2;
            }
        }
        g.setColor(Color.GREEN);
        if (health > 0) {
            g.fillRect(560, 85, 20, health/3);
        } else if (health <= 0) {
            g.fillRect(560, 85, 20, 0);
        }
        g.setColor(Color.BLACK);
        g.drawRect(560, 85, 20, 300);
        g.setColor(Color.LIGHT_GRAY);
        
        g.drawString("Time: " + clockTimer, 40, 375);
        g.drawString("Health: " + health, 340, 375);
    }

    public void movePosition() {
        if (handler.getKeyManager().left) {
            if (tempTimer % 7 == 0) {
                if(x == 467) {
                    x = 267;
                }else if (x == 267) {
                    x = 67;
                }
            }
        } else if (handler.getKeyManager().right) {
            if (tempTimer % 7 == 0) {
                if(x == 67) {
                    x = 267;
                }else if (x == 267) {
                    x = 467;
                }
            }
        }
    }
}
