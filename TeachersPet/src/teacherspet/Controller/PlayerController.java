package Controller;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import Model.Actor;

public class PlayerController extends InputAdapter {
	
	private Actor player;
	
	public PlayerController (Actor p) {
		this.player = p;
	}
	
	
	@Override
	public boolean keyDown(int keycode) {
		
		if(keycode == Keys.W) {
			player.move(0, 1);
		}
		
		if(keycode == Keys.S) {
			player.move(0, -1);
		}
		
		if(keycode == Keys.A) {
			player.move(-1, 0);
		}
		
		if(keycode == Keys.D) {
			player.move(1, 0);
		}
		
		return false;
	}

}
