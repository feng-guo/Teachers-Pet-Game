package states;

import game.Handler;
import graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CatchBusState extends State{
    private int clockTimer = 60;
    private int tempTimer = 0;

    private int x = 267;
    private int y = 300;
    private Rectangle playerRect;
    private Rectangle[] tempRect;
    private boolean[] sendRect;
    private int score = 400;

    public CatchBusState(Handler handler) {
        super(handler);

        playerRect = new Rectangle(x, y, 67, 100);
        tempRect = new Rectangle[200];
        sendRect = new boolean[200];

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
        if (clockTimer < 1 || score == 0) {
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

        int tempY;
        for(int i = 0; i < 200; i++){
            tempY = 7 * tempTimer - 800;
            if(sendRect[i]){
                g.setColor(Color.WHITE);
                g.fillRect(tempRect[i].x, tempY + tempRect[i].y, tempRect[i].width, tempRect[i].height);
            }
            if(new Rectangle(tempRect[i].x, tempY + tempRect[i].y, tempRect[i].width, tempRect[i].height).intersects(playerRect)){
                score /= 2;
            }
        }
        g.setColor(Color.GREEN);
        if (score > 0) {
            g.fillRect(560, 85, 20, score/3);
        } else if (score <= 0) {
            g.fillRect(560, 85, 20, 900);
        }
        g.setColor(Color.BLACK);
        g.drawRect(560, 85, 20, 300);
        g.drawString("Time Left: " + clockTimer, 40, 375);
        g.drawString("Score: " + score, 340, 375);
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
