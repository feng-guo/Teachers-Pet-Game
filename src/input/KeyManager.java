package input;

import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{

	private boolean[] keys;
	
	public boolean up, down, left, right;
	public boolean battle, first, second, third, fourth;
	
	public KeyManager() {
		keys = new boolean[256];
	}
	
	public void tick() {
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		
		battle = keys[KeyEvent.VK_X];
		first = keys[KeyEvent.VK_1];
		second = keys[KeyEvent.VK_1];
		third = keys[KeyEvent.VK_1];
		fourth = keys[KeyEvent.VK_1];

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
