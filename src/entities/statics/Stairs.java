package entities.statics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.Handler;
import graphics.Assets;
import tiles.Tile;

public class Stairs extends StaticEntity{
	
	private Rectangle tempStairRect, tempPlayerRect;
	String worldPath;
	int setX, setY;

    public Stairs(Handler handler, float x, float y, String worldPath, int setX, int setY) {
        super(handler, x, y, (int) (Tile.TILE_WIDTH * 3), (int) (Tile.TILE_HEIGHT * 2));

        // SPECIFIC TO STAIRS
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = 0;
        bounds.height = 0;
        
        this.worldPath = worldPath;
        this.setX = setX;
        this.setY = setY;
        
    }

    @Override
    public void tick() {
    	
    	tempStairRect = new Rectangle((int) x, (int) y, width, height);
		
	tempPlayerRect = new Rectangle((int) handler.getWorld().getEntityManager().getPlayer().getX(),
				(int) handler.getWorld().getEntityManager().getPlayer().getY(),
				(int) handler.getWorld().getEntityManager().getPlayer().getWidth(),
				(int) handler.getWorld().getEntityManager().getPlayer().getHeight());
		
		if (tempStairRect.intersects(tempPlayerRect)) {
			
			handler.getGame().getGameState().setCurrentWorld(worldPath);
			handler.getWorld().getEntityManager().getPlayer().setX(setX);
			handler.getWorld().getEntityManager().getPlayer().setY(setY);

		}

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.stairs, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        //g.setColor(Color.RED);
        //g.fillRect((int) (x - handler.getGameCamera().getxOffset() + bounds.x), (int) (y - handler.getGameCamera().getyOffset() + bounds.y), bounds.width, bounds.height);
    }

}
