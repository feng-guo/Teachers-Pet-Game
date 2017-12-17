package Model;

public class Tile {

	private Actor actor;
	private TERRAIN terrain;
	
	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public Tile(TERRAIN terrain) {
		this.terrain = terrain;
	}
	
	public TERRAIN getTerrain() {
		return terrain;
	}	
	
}
