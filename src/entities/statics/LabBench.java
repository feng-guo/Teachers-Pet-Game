package entities.statics;

import java.awt.Color;
import java.awt.Graphics;

import game.Handler;
import graphics.Assets;
import tiles.Tile;

public class LabBench extends StaticEntity{

    public LabBench(Handler handler, float x, float y) {
        super(handler, x, y, (int) (Tile.TILE_WIDTH * 2), (int) (Tile.TILE_HEIGHT * 2));

        // SPECIFIC TO LAB BENCH
        bounds.x = 0;
        bounds.y = 10;
        bounds.width = 65;
        bounds.height = 55;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.labBench, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        //g.setColor(Color.RED);
        //g.fillRect((int) (x - handler.getGameCamera().getxOffset() + bounds.x), (int) (y - handler.getGameCamera().getyOffset() + bounds.y), bounds.width, bounds.height);
    }

}
