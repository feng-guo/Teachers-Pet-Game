package entities.statics;

import java.awt.Color;
import java.awt.Graphics;

import game.Handler;
import graphics.Assets;
import tiles.Tile;

public class Locker extends StaticEntity{

	public Locker(Handler handler, float x, float y) {
		super(handler, x, y, (int) (Tile.TILE_WIDTH * 1.5), Tile.TILE_HEIGHT * 2);

		// SPECIFIC TO LOCKER
		bounds.x = 10;
		bounds.y = 20;
		bounds.width = 20;
		bounds.height = 40;
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.locker, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		//g.setColor(Color.RED);
		//g.fillRect((int) (x - handler.getGameCamera().getxOffset() + bounds.x), (int) (y - handler.getGameCamera().getyOffset() + bounds.y), bounds.width, bounds.height);
	}

}