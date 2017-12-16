package Screen;

import com.badlogic.gdx.Gdx; 
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Controller.PlayerController;
import Model.Actor;
import Model.TERRAIN;
import Model.TileMap;
import Run.FinalGame;
import Run.Settings;

public class GameScreen extends AbstractScreen{

	private PlayerController controller;
	private Actor player;
	private TileMap map;
	
	private SpriteBatch batch;
	private Texture fengDown1;
	private Texture grass1;
	private Texture grass2;
	
	public GameScreen(FinalGame app) {
		super(app);
		fengDown1 = new Texture("TeachersPet/images/sprites/feng_down_1.png");
		grass1 = new Texture("TeachersPet/images/sprites/grass_1.png");
		grass2 = new Texture("TeachersPet/images/sprites/grass_2.png");

		batch = new SpriteBatch();
		
		map = new TileMap(10, 10);
		player = new Actor(map, 0, 0);
		
		controller = new PlayerController(player);
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void render(float delta) {
		batch.begin();
		
		for (int i = 0; i < map.getWidth(); i++) {
			for (int j = 0; j < map.getHeight(); j++) {
				
				Texture render;
				
				if (map.getTile(i, j).getTerrain() == TERRAIN.GRASS_1) {
					render = grass1;
				} else {
					render = grass2;
				}
				
				batch.draw(render, i*Settings.SCALED_TILE_SIZE, j*Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE);
			}
			
		}
		
		
		
		batch.draw(fengDown1, player.getX()*Settings.SCALED_TILE_SIZE, player.getY()*Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE*1.5f);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(controller);
	}

}
