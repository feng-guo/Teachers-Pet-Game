package states;

import game.Handler;
import graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CatchBusState extends State{

        private int clockTimer = 60;
        private int tempTimer = 0;

        private int x = 300;
        private int y = 300;

        private Rectangle playerRect;
        private Rectangle[] tempRect;
        private boolean[] sendRect;
        private BufferedImage[] rectPic;

        private int score = 1000;

        public CatchBusState(Handler handler) {
            super(handler);

            playerRect = new Rectangle(x, y, 20, 20);
            tempRect = new Rectangle[200];
            sendRect = new boolean[200];
            rectPic = new BufferedImage[200];

            for (int i = 0; i < 200; i++) {
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

            if (clockTimer < 1 || score > 900) {
                handler.getGame().setGameState();
            }
        }

        @Override
        public void render(Graphics g) {
            int numHit = 0;
            g.setColor(new Color(176, 224, 230));
            g.fillRect(0, 0, 600, 400);


            g.setFont(Assets.font16);
            g.setColor(Color.red);

            if (score/(60 - (clockTimer-1)) > 900/60) {
                g.fillRect(playerRect.x - 10, playerRect.y - 10, playerRect.width + 10, playerRect.height + 10);
            } else {
                g.fillRect(playerRect.x - 10, playerRect.y - 10, playerRect.width + 10, playerRect.height + 10);
            }
            //g.fillRect(playerRect.x, playerRect.y, playerRect.width, playerRect.height);

            int tempY = 2 * tempTimer - 200;

            for (int i = 0; i < 200; i++) {
                if (sendRect[i]) {
                    g.setColor(Color.red);

                    g.drawImage(rectPic[i], tempRect[i].x, tempY + tempRect[i].y, tempRect[i].width, tempRect[i].height , null);

                    //g.fillRect(tempRect[i].x, 2*tempTimer - 200 + tempRect[i].y, tempRect[i].width, tempRect[i].height);
                }
                if(new Rectangle(tempRect[i].x, tempY + tempRect[i].y, tempRect[i].width, tempRect[i].height).intersects(playerRect)) {

                    g.drawImage(rectPic[i], tempRect[i].x, tempY + tempRect[i].y, tempRect[i].width, tempRect[i].height , null);
                    numHit = 1;
                }
                if(numHit == 1){
                    score /= 2;
                    numHit = 0;
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
