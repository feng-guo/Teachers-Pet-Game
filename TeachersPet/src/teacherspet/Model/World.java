package Model;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.GridPoint2;

public class World {
	
	private TileMap map;
	private List<Actor> actors;
	private List<WorldObject> objects;
	
	public World (int width, int height) {
		this.map = new TileMap (width, height);
		actors = new ArrayList<Actor>();
		objects = new ArrayList<WorldObject>();
	}
	
	public void addActor(Actor a) {
		map.getTile(a.getX(), a.getY()).setActor(a);
		actors.add(a);
	}
	
	public void addObject (WorldObject o) {
		for (GridPoint2 p : o.getTiles()) {
			map.getTile(o.getX() + p.x, o.getY() + p.y).setObject(o);
		}
		objects.add(o);
	}
	
	public void update(float delta) {
		for (Actor a : actors) {
			a.update(delta);
		}
		for (WorldObject o : objects) {
			o.update(delta);
		}
	}
	
	public TileMap getMap() {
		return map;
	}

}
