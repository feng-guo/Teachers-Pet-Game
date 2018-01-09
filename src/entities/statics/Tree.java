package entities.statics;

import java.awt.Graphics;

import game.Handler;
import graphics.Assets;
import tiles.Tile;

public class Tree extends StaticEntity{

	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT * 2);
		
		// SPECIFIC TO TREE
		bounds.x = 10;
		bounds.y = 20;
		bounds.width = 16;
		bounds.height = 22;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.rock, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}

}
