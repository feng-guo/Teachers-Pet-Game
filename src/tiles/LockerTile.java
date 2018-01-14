package tiles;

import java.awt.image.BufferedImage;

import graphics.Assets;

public class LockerTile extends Tile{

	public LockerTile(int id) {
		super(Assets.rock, id);
	}

	@Override
	public boolean isSolid() {
		return true;
	}

}
