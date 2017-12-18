package Screen;

import com.badlogic.gdx.Gdx;  
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import Controller.PlayerController;

import Model.Actor;
import Model.Camera;
import Model.TERRAIN;
import Model.TileMap;

import Run.FinalGame;
import Run.Settings;
import Utility.AnimationSet;

public class GameScreen extends AbstractScreen{

	private PlayerController controller;
	
	private Camera camera;
	private Actor player;
	private TileMap map;
	
	private SpriteBatch batch;
	//private Texture fengDown1;
	private Texture grass1;
	private Texture grass2;
	
	public GameScreen(FinalGame app) {
		super(app);
		//fengDown1 = new Texture("TeachersPet/images/sprites/unpacked/feng_down_1.png");
		grass1 = new Texture("TeachersPet/images/sprites/grass_1.png");
		grass2 = new Texture("TeachersPet/images/sprites/grass_2.png");
		batch = new SpriteBatch();
		
		TextureAtlas atlas = app.getAssetManager().get("TeachersPet/images/sprites/packed/feng_textures.atlas", TextureAtlas.class);
		
		AnimationSet animations = new AnimationSet(
				new Animation(0.3f/2f, atlas.findRegions("feng_up"), PlayMode.LOOP_PINGPONG),
				new Animation(0.3f/2f, atlas.findRegions("feng_down"), PlayMode.LOOP_PINGPONG),
				new Animation(0.3f/2f, atlas.findRegions("feng_right"), PlayMode.LOOP_PINGPONG),
				new Animation(0.3f/2f, atlas.findRegions("feng_left"), PlayMode.LOOP_PINGPONG),
				atlas.findRegion("feng_up"),
				atlas.findRegion("feng_down"),
				atlas.findRegion("feng_right"),
				atlas.findRegion("feng_left")
		);
			
		
		map = new TileMap(100, 100);
		player = new Actor(map, 0, 0, animations);
		camera = new Camera();
		
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
		
		controller.update(delta);
		
		player.update(delta);
		camera.update(player.getWorldX() + 0.5f, player.getWorldY() + 0.5f);

		
		batch.begin();
		
		float worldStartX = Gdx.graphics.getWidth()/2 - camera.getCameraX()*Settings.SCALED_TILE_SIZE;
		float worldStartY = Gdx.graphics.getHeight()/2 - camera.getCameraY()*Settings.SCALED_TILE_SIZE;
		
		
		for (int i = 0; i < map.getWidth(); i++) {
			for (int j = 0; j < map.getHeight(); j++) {
				
				Texture render;
				
				if (map.getTile(i, j).getTerrain() == TERRAIN.GRASS_1) {
					render = grass1;
				} else {
					render = grass2;
				}
		
				//batch.draw(render, player.getX()+i*Settings.SCALED_TILE_SIZE, player.getY()+j*Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE);
				batch.draw(render, worldStartX+i*Settings.SCALED_TILE_SIZE, worldStartY+j*Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE);
			}
			
		}
		//batch.draw(player.getSprite(), player.getWorldX()*Settings.SCALED_TILE_SIZE, player.getWorldY()*Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE*1.3f);

		batch.draw(player.getSprite(), worldStartX+player.getWorldX()*Settings.SCALED_TILE_SIZE, worldStartY+player.getWorldY()*Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE*1.3f);
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
