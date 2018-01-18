package entities.statics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.Handler;
import graphics.Assets;
import tiles.Tile;

public class HallwayDoorOpen extends StaticEntity{
	
	private Rectangle tempDoorRect;
	private Rectangle tempPlayerRect;

    public HallwayDoorOpen(Handler handler, float x, float y) {
        super(handler, x, y, (int) (Tile.TILE_WIDTH * 1.25), (int) (Tile.TILE_HEIGHT * 1.75));

        // SPECIFIC TO DOOR
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = 38;
        bounds.height = 50;
    }

    @Override
    public void tick() {
    		
//    		 tempDoorRect = new Rectangle((int) (x - handler.getGameCamera().getxOffset() - 1), 
//    					(int) (y - handler.getGameCamera().getyOffset() - 1), 
//    					bounds.width + 2, 
//    					bounds.height + 2);
    		
    		tempDoorRect = new Rectangle((int) x, (int) y, width, height);
    		
    		tempPlayerRect = new Rectangle((int) handler.getWorld().getEntityManager().getPlayer().getX(),
					(int) handler.getWorld().getEntityManager().getPlayer().getY(),
					(int) handler.getWorld().getEntityManager().getPlayer().getWidth(),
					(int) handler.getWorld().getEntityManager().getPlayer().getHeight());
    		
    		if (tempDoorRect.intersects(tempPlayerRect)) {
    			System.out.println("truuuuue");
    			handler.getGame().getGameState().setCurrentWorld("res/worlds/world2.txt");
    		}
    }

    @Override
    public void render(Graphics g) {
    		
    		
        g.drawImage(Assets.openDoor, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
       
        //g.setColor(Color.RED);
        //g.fillRect((int) (x - handler.getGameCamera().getxOffset() + bounds.x), (int) (y - handler.getGameCamera().getyOffset() + bounds.y), bounds.width, bounds.height);
        //g.fillRect(tempPlayerRect.x - 1,tempPlayerRect.y - 1,tempPlayerRect.width + 2,tempPlayerRect.height + 2);
        //g.fillRect(tempDoorRect.x - 1,tempDoorRect.y - 1,tempDoorRect.width + 2,tempDoorRect.height + 2);
    }

}
