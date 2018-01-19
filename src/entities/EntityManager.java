package entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

import entities.creatures.Player;
import game.Handler;

public class EntityManager {

	// Variables
	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities;
	private Comparator<Entity> renderSorter = new Comparator<Entity>() {

		// Used to draw in order
		@Override
		public int compare(Entity a, Entity b) {
			if (a.getY() + a.getHeight() < b.getY() + b.getHeight()) {
				return -1;
			} else {
				return 1;
			}
		}
		
	};
	
	// Create the manager
	public EntityManager(Handler handler, Player player) {
		this.handler = handler;
		this.player = player;
		entities = new ArrayList<Entity>();
		addEntity(player);
		
	}
	
	// Makes the manager sort repeatedly
	public void tick() {
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
		}
		
		try {
			entities.sort(renderSorter);
		} catch (IllegalArgumentException e) {
			
		}
	}
	
	// Continue to render the entities to the screen
	public void render(Graphics g) {
		for (Entity e : entities) {
			e.render(g);
		}
	}
	
	// Allow new entities
	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	// Getters and setters

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
}
