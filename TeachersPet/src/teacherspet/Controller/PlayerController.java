package Controller;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import Model.Actor;
import Model.DIRECTION;

public class PlayerController extends InputAdapter {
	
	private Actor player;
	
	private boolean up, down, left, right;
	
	public PlayerController (Actor p) {
		this.player = p;
	}
	
	
	@Override
	public boolean keyDown(int keycode) {
		
		if(keycode == Keys.W) {
			up = true;
		}
		
		if(keycode == Keys.S) {
			down = true;
		}
		
		if(keycode == Keys.A) {
			left = true;
		}
		
		if(keycode == Keys.D) {
			right = true;
		}
		
		return false;
	}
	
	@Override
	public boolean keyUp(int keycode) {
		
		if(keycode == Keys.W) {
			up = false;
		}
		
		if(keycode == Keys.S) {
			down = false;
		}
		
		if(keycode == Keys.A) {
			left = false;
		}
		
		if(keycode == Keys.D) {
			right = false;
		}
		
		return false;
	}
	
	public void update(float delta) {
		if (up) {
			player.move(DIRECTION.NORTH);
			return;
		}
		if (down) {
			player.move(DIRECTION.SOUTH);
			return;
		}
		if (left) {
			player.move(DIRECTION.WEST);
			return;
		}
		if (right) {
			player.move(DIRECTION.EAST);
			return;
		}
		
	}
}
