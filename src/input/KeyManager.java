package input;

import states.BattleState;
import states.State;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;
import java.util.TreeSet;

public class KeyManager implements KeyListener{

	private boolean[] keys;
	private Set<Integer> pressedKeys = new TreeSet<>();
	
	public boolean up, down, left, right;
	public boolean battle, stressEat, beepTest, mathContest, first, second, third, fourth;
	public boolean enter, backspace;
	
	public KeyManager() {
		keys = new boolean[256];
	}
	
	public void tick() {
		if (!(State.getState() instanceof BattleState)) {
			up = keys[KeyEvent.VK_W];
			down = keys[KeyEvent.VK_S];
			left = keys[KeyEvent.VK_A];
			right = keys[KeyEvent.VK_D];
			
			
			mathContest = keys[KeyEvent.VK_V];
			beepTest = keys[KeyEvent.VK_C];
			stressEat = keys[KeyEvent.VK_Z];
			battle = keys[KeyEvent.VK_X];
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		keys[e.getKeyCode()] = true;
		
		
		
		if (pressedKeys.contains(code)) {
			return;
		} else if (State.getState() instanceof BattleState){
			if (e.getKeyCode() == KeyEvent.VK_C || e.getKeyCode() == KeyEvent.VK_V|| e.getKeyCode() == KeyEvent.VK_B || e.getKeyCode() == KeyEvent.VK_N || e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_D) {
				up = keys[KeyEvent.VK_W];
				down = keys[KeyEvent.VK_S];
				left = keys[KeyEvent.VK_A];
				right = keys[KeyEvent.VK_D];
				first = keys[KeyEvent.VK_C];
				second = keys[KeyEvent.VK_V];
				third = keys[KeyEvent.VK_B];
				fourth = keys[KeyEvent.VK_N];
				enter = keys[KeyEvent.VK_ENTER];
				backspace = keys[KeyEvent.VK_BACK_SPACE];
			}
		}
		pressedKeys.add(code);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		pressedKeys.remove(e.getKeyCode());
	}
	
	@Override
	public void keyTyped(KeyEvent e) {

	}
	
	public void forceKeyChange(int keyCode, boolean b) {
		keys[keyCode] = b;
	}
	

}
