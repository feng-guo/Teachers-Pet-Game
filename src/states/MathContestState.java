package states;

import java.awt.Graphics;

import game.Handler;

public class MathContestState extends State {
	
	private int additionQuestion = (int) Math.random()*2;
	
	private int rand1 = (int) (Math.random()*10);
	private int rand2 = (int) (Math.random()*10);
	private int answer = 3;
	
	int timer = 0;
	int clockTimer = 60;

	public MathContestState(Handler handler) {
		super(handler);
	}

	@Override
	public void tick() {
		timer++;
		if(timer%60 == 0) {
			clockTimer--;
		}
		if(handler.getKeyManager().mathContest) {
			rand1 = (int) (Math.random()*10);
			rand2 = (int) (Math.random()*10);
			additionQuestion = (int) Math.random()*2;
			System.out.println("true");
		}
		

		
	}

	@Override
	public void render(Graphics g) {
		if(additionQuestion == 1) {
			g.drawString("what is " + rand1 + " + " + rand2 + "?", 10, 10);
		} else {
			g.drawString("what is " + rand1 + " x " + rand2 + "?", 10, 10);		}
	}

}
