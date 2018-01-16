package tiles;

import graphics.Assets;

public class NullTile extends Tile{

	public NullTile(int id) {
		super(Assets.blackBlock, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
}
