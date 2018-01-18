package states;

import java.awt.Graphics;

import entities.creatures.Player;
import entities.statics.Tree;
import game.Game;
import game.Handler;
import graphics.Assets;
import tiles.Tile;
import worlds.World;

public class GameState extends State{

	private World world, world1, world2, cafeteria, currentWorld;
		
	public GameState(Handler handler) {
		super(handler);


		world1 = new World(handler, "res/worlds/world1.txt");
		world2 = new World(handler, "res/worlds/world2.txt");
		cafeteria = new World(handler, "res/worlds/cafeteria.txt");
		
		world = world1;
		
		handler.setWorld(world);
				
	}
	
	
	@Override
	public void tick() {
		world.tick();
	}

	@Override
	public void render(Graphics g) {
		// Stuff to render
		world.render(g);		
		
	}
	
	public void setCurrentWorld(String path) {
		if (path.equals("res/worlds/world1.txt")) {
			world = world1;
		} else if (path.equals("res/worlds/world2.txt")) {
			world = world2;
		} else if (path.equals("res/worlds/cafeteria.txt")) {
			world = cafeteria;
		}
		handler.setWorld(world);
	}
 	
	

}
