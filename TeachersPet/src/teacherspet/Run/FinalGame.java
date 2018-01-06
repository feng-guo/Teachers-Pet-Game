package Run;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import Screen.GameScreen;

public class FinalGame extends Game {

	private GameScreen screen;
	
	private AssetManager assetManager;
	
	@Override
	public void create() {
		
		assetManager = new AssetManager();
		assetManager.load("TeachersPet/images/sprites/characters/Feng/packed/feng_textures.atlas", TextureAtlas.class);
		assetManager.finishLoading();
		
		screen = new GameScreen(this);
		
		this.setScreen(screen);
	}

	@Override
	public void render() {
		
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		super.render();
	}
	
	public AssetManager getAssetManager() {
		return assetManager;
	}
	
}
