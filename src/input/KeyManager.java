package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{

	private boolean[] keys;
	
	public boolean up, down, left, right;
	public boolean battle, first, second, third, fourth;
	public boolean enter, backspace;
	
	public KeyManager() {
		keys = new boolean[256];
	}
	
	public void tick() {
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		
		battle = keys[KeyEvent.VK_X];
		first = keys[KeyEvent.VK_C];
		second = keys[KeyEvent.VK_V];
		third = keys[KeyEvent.VK_B];
		fourth = keys[KeyEvent.VK_N];
		enter = keys[KeyEvent.VK_ENTER];
		backspace = keys[KeyEvent.VK_BACK_SPACE];
	}

	public void resetKeys() {
		up = false;
		down = false;
		left = false;
		right = false;

		battle = false;
		first = false;
		second = false;
		third = false;
		fourth = false;
		enter = false;
		backspace = false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {

	}

}
