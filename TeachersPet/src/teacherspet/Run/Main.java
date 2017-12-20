package Run;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication; 
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {

	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "Richmond Hill High Simulator";
		config.height = 1920;
		config.width = 1080;
		config.vSyncEnabled = true;

		new LwjglApplication(new FinalGame(), config);
	}

}
