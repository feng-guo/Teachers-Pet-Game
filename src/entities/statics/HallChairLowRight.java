package entities.statics;

import java.awt.Color;
import java.awt.Graphics;

import game.Handler;
import graphics.Assets;
import tiles.Tile;

public class HallChairLowRight extends StaticEntity{

    public HallChairLowRight(Handler handler, float x, float y) {
        super(handler, x, y, (int) (Tile.TILE_WIDTH * 0.75), (int) (Tile.TILE_HEIGHT * 0.75));

        // SPECIFIC TO HALL CHAIR LOW RIGHT
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = 15;
        bounds.height = 10;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

        g.drawImage(Assets.hallChairLowRight, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        //g.setColor(Color.RED);
        //g.fillRect((int) (x - handler.getGameCamera().getxOffset() + bounds.x), (int) (y - handler.getGameCamera().getyOffset() + bounds.y), bounds.width, bounds.height);
    }

}
