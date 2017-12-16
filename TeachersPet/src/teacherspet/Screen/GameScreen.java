package Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Controller.PlayerController;
import Model.Player;
import Run.FinalGame;
import Run.Settings;

public class GameScreen extends AbstractScreen{

	private PlayerController controller;
	private Player player;
	private SpriteBatch batch;
	private Texture fengDown1;
	
	public GameScreen(FinalGame app) {
		super(app);
		fengDown1 = new Texture("TeachersPet/images/sprites/feng_down_1.png");
		batch = new SpriteBatch();
		
		player = new Player(0, 0);
		
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
		batch.draw(fengDown1, player.getX()*Settings.SCALED_TILE_SIZE, player.getY()*Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE);
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
