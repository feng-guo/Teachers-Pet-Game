package entities.statics;

import entities.Entity;
import game.Handler;

import java.awt.image.BufferedImage;

/* Class to make objects that will be placed in the map
 * inherited by specific objects
 */
public abstract class StaticEntity extends Entity{

	// The constructor for each object with its bounds and the creator
	public StaticEntity(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
	}

}
