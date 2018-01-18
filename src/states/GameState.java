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

	private World world;
		
	public GameState(Handler handler) {
		super(handler);

		//world = new World(handler, "res/worlds/world1.txt");
		//world = new World(handler, "res/worlds/world2.txt");
		//world = new World(handler, "res/worlds/cafeteria.txt");
		//world = new World(handler, "res/worlds/guidance.txt");

		//world = new World(handler, "res/worlds/compsci.txt");
		//world = new World(handler, "res/worlds/drama.txt");
		//world = new World(handler, "res/worlds/english.txt");
		//world = new World(handler, "res/worlds/gym.txt");
		//world = new World(handler, "res/worlds/math.txt");
		//world = new World(handler, "res/worlds/science.txt");

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

}
