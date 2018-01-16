package entities.statics;

import java.awt.Color;
import java.awt.Graphics;

import game.Handler;
import graphics.Assets;
import tiles.Tile;

public class Bench extends StaticEntity{

    public Bench(Handler handler, float x, float y) {
        super(handler, x, y, (int) (Tile.TILE_WIDTH * 0.5), (int) (Tile.TILE_HEIGHT * 0.5));

        // SPECIFIC TO BENCH
        bounds.x = 10;
        bounds.y = 20;
        bounds.width = 40;
        bounds.height = 10;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.bench, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        //g.setColor(Color.RED);
        //g.fillRect((int) (x - handler.getGameCamera().getxOffset() + bounds.x), (int) (y - handler.getGameCamera().getyOffset() + bounds.y), bounds.width, bounds.height);
    }

}
