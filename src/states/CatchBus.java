package states;

import game.Handler;
import graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CatchBus extends State{
    private int clockTimer = 60;
    private int tempTimer = 0;

    private int x = 230;
    private int y = 370;
    private Rectangle playerRect;
    private Rectangle[] tempRect;
    private boolean[] sendRect;

    public CatchBus(Handler handler) {
        super(handler);

        playerRect = new Rectangle(x, y, 30, 30);
        tempRect = new Rectangle[200];
        sendRect = new boolean[200];

        for (int i = 0; i < 200; i++) {
            int determiner = (int)(Math.random() * 6);
            int X;
            if(determiner == 0){
                X = 30;
            }else if (determiner == 1){
                X = 130;
            }else if (determiner == 2){
                X = 230;
            }else if (determiner == 3){
                X = 330;
            }else if (determiner == 4){
                X = 430;
            }else{
                X = 530;
            }
            tempRect[i] = new Rectangle(X, -200 * i, 30, 30);
            sendRect[i] = false;
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
        }else if(tempTimer % 20 == 0){
            sendRect[60 - clockTimer] = true;
        }
        if (clockTimer < 1) {
            handler.getGame().setGameState();
        }
    }

    @Override
    public void render(Graphics g) {
        //creates background
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, 600, 400);

        //create player
        g.setColor(Color.BLACK);
        g.fillRect(playerRect.x, playerRect.y, playerRect.width, playerRect.height);

        int tempY = tempTimer - 200;
        for(int i = 0; i < 200; i++){
            if(sendRect[i]){
                g.setColor(Color.WHITE);
                g.fillRect(tempRect[i].x, tempY + tempRect[i].y, tempRect[i].width, tempRect[i].height);
            }
            if(new Rectangle(tempRect[i].x, tempY + tempRect[i].y, tempRect[i].width, tempRect[i].height).intersects(playerRect)){
                playerRect.x = 262;
                playerRect.y = 400;
            }
        }

        g.setColor(Color.BLACK);
        g.drawString("Time Left: " + clockTimer, 40, 375);
    }

    public void movePosition() {
        if (handler.getKeyManager().left) {
            if (tempTimer % 2 == 0) {
                if(x == 530) {
                    x = 430;
                }else if (x == 430) {
                    x = 330;
                }else if(x == 330){
                    x = 230;
                }else if(x == 230){
                    x = 130;
                }else if(x == 130){
                    x = 30;
                }
            }
        } else if (handler.getKeyManager().right) {
            if (tempTimer % 2 == 0) {
                if(x == 30) {
                    x = 130;
                }else if (x == 130) {
                    x = 230;
                }else if(x == 230){
                    x = 330;
                }else if(x == 330){
                    x = 430;
                }else if(x == 430){
                    x = 530;
                }
            }
        }
    }
}
