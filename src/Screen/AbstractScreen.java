package Screen;

import com.badlogic.gdx.Screen;
import Run.FinalGame;
public abstract class AbstractScreen implements Screen{

	private FinalGame app;
	
	public AbstractScreen(FinalGame app) {
		this.app = app;
	}
	
	@Override
	public abstract void dispose();

	@Override
	public abstract void hide();

	@Override
	public abstract void pause();

	@Override
	public abstract void render(float delta);

	@Override
	public abstract void resize(int width, int height);

	@Override
	public abstract void resume();

	@Override
	public abstract void show();

}
