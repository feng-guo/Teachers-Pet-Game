package entities.statics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import game.Handler;
import graphics.Assets;
import tiles.Tile;

public class Blackboard extends StaticEntity{

	private Rectangle boardRect, tempPlayerRect;
	
    public Blackboard(Handler handler, float x, float y) {
        super(handler, x, y, (int) (Tile.TILE_WIDTH * 1.75), (int) (Tile.TILE_HEIGHT * 1));

        // SPECIFIC TO BLACKBOARD
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = 60;
        bounds.height = 20;
    }

    @Override
    public void tick() {
    		boardRect = new Rectangle((int) x, (int) y, width, height);
		
    		tempPlayerRect = new Rectangle((int) handler.getWorld().getEntityManager().getPlayer().getX(),
    				(int) handler.getWorld().getEntityManager().getPlayer().getY(),
    				(int) handler.getWorld().getEntityManager().getPlayer().getWidth(),
    				(int) handler.getWorld().getEntityManager().getPlayer().getHeight());
    		
    		if (boardRect.intersects(tempPlayerRect)) {
        		handler.getKeyManager().forceKeyChange(KeyEvent.VK_V, true);
    		}
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.blackBoard, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        //g.setColor(Color.RED);
        //g.fillRect((int) (x - handler.getGameCamera().getxOffset() + bounds.x), (int) (y - handler.getGameCamera().getyOffset() + bounds.y), bounds.width, bounds.height);
    }

}
