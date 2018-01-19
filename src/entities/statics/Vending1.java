package entities.statics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import game.Handler;
import graphics.Assets;
import tiles.Tile;

public class Vending1 extends StaticEntity{

    public Vending1(Handler handler, float x, float y) {
        super(handler, x, y, (int) (Tile.TILE_WIDTH * 1.25), (int) (Tile.TILE_HEIGHT * 2.25));

        // SPECIFIC TO VENDING MACHINE 1
        bounds.x = 0;
        bounds.y = 10;
        bounds.width = 35;
        bounds.height = 40;
    }

    @Override
    public void tick() {
    		Rectangle vendingRect = new Rectangle((int) x, (int) y, (int) width, (int) height-30);
		Rectangle playerRect = new Rectangle((int) handler.getWorld().getEntityManager().getPlayer().getX(),
					(int) handler.getWorld().getEntityManager().getPlayer().getY(),
					(int) handler.getWorld().getEntityManager().getPlayer().getWidth(),
					(int) handler.getWorld().getEntityManager().getPlayer().getHeight());
		
		if(playerRect.intersects(vendingRect)) {
			//System.out.println(true);
			handler.getKeyManager().forceKeyChange(KeyEvent.VK_Z, true);
;
		}
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.vending1, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        //g.setColor(Color.RED);
        //g.fillRect((int)x, (int)y, width, height);
//        //g.fillRect( (int) handler.getWorld().getEntityManager().getPlayer().getX(),
//					(int) handler.getWorld().getEntityManager().getPlayer().getY(),
//					(int) handler.getWorld().getEntityManager().getPlayer().getWidth(),
//					(int) handler.getWorld().getEntityManager().getPlayer().getHeight());
        //g.fillRect((int) (x - handler.getGameCamera().getxOffset() + bounds.x), (int) (y - handler.getGameCamera().getyOffset() + bounds.y), bounds.width, bounds.height);
    }

}
