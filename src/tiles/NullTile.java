package tiles;

import graphics.Assets;

public class NullTile extends Tile{

	public NullTile(int id) {
		super(Assets.rock, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
}
