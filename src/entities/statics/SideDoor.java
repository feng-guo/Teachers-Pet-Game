package entities.statics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.Handler;
import graphics.Assets;
import tiles.Tile;

public class SideDoor extends StaticEntity{
	
	private Rectangle tempDoorRect;
	private Rectangle tempPlayerRect;
	private String worldPath;
	private int setX, setY;

    public SideDoor(Handler handler, float x, float y, String worldPath, int setX, int setY) {
        super(handler, x, y, (int) (Tile.TILE_WIDTH * 0.3), (int) (Tile.TILE_HEIGHT * 1.75));
        
        this.worldPath = worldPath;
        this.setX = setX;
        this.setY = setY;
        
        // SPECIFIC TO DOOR
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = 12;
        bounds.height = 56;
    }

    @Override
    public void tick() {
    		
//    		 tempDoorRect = new Rectangle((int) (x - handler.getGameCamera().getxOffset() - 1), 
//    					(int) (y - handler.getGameCamera().getyOffset() - 1), 
//    					bounds.width + 2, 
//    					bounds.height + 2);
    		
    		tempDoorRect = new Rectangle((int) x, (int) y, width, height-12);
    		
    		tempPlayerRect = new Rectangle((int) handler.getWorld().getEntityManager().getPlayer().getX(),
					(int) handler.getWorld().getEntityManager().getPlayer().getY(),
					(int) handler.getWorld().getEntityManager().getPlayer().getWidth(),
					(int) handler.getWorld().getEntityManager().getPlayer().getHeight());
    		
    		if (tempDoorRect.intersects(tempPlayerRect)) {
    			handler.getGame().getGameState().setCurrentWorld(worldPath);
    			handler.getWorld().getEntityManager().getPlayer().setX(setX);
    			handler.getWorld().getEntityManager().getPlayer().setY(setY);

    		}
    }

    @Override
    public void render(Graphics g) {
    		
    		
        g.drawImage(Assets.sideDoor, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
       
        //g.setColor(Color.RED);
        //g.fillRect((int) (x - handler.getGameCamera().getxOffset() + bounds.x), (int) (y - handler.getGameCamera().getyOffset() + bounds.y), bounds.width, bounds.height);
        //g.fillRect(tempPlayerRect.x - 1,tempPlayerRect.y - 1,tempPlayerRect.width + 2,tempPlayerRect.height + 2);
        //g.fillRect(tempDoorRect.x - 1,tempDoorRect.y - 1,tempDoorRect.width + 2,tempDoorRect.height + 2);
    }

}
