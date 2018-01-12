package states;

import java.awt.Color;  
import java.awt.Font;
import java.awt.Graphics;

import battle.BattleRunner;
import battle.BattleRunner;
import game.Handler;
//import battle.Battle;

public class BattleState extends State{


	private int count = 60;

	private String battleText;
	private BattleRunner battleTest;
	private int answer = -1;

	public BattleState(Handler handler, Graphics g) {
		super(handler);
		//handler.setWorld(null);
		battleTest = new BattleRunner(handler, g);


	}

	@Override
	public void tick() {
		count++;
		if (count > 5) {
			answer = -1;
			if (handler.getKeyManager().first) {
				answer = 1;
			}
			if (handler.getKeyManager().second) {
				answer = 2;
			}
			if (handler.getKeyManager().third) {
				answer = 3;
			}
			if (handler.getKeyManager().fourth) {
				answer = 4;
			}
			if (handler.getKeyManager().backspace) {
				answer = 10;
			}
			if (handler.getKeyManager().enter) {
				answer = 1;
			}
			if (answer != -1) {
				//Eventually, this should be moved outside of this if statement because it's graphics
				battleTest.runPhase(answer);
				answer = -1;
			}
			count = 0;
		}
	}

	@Override
	public void render(Graphics g) {
		if(answer < 0){
			return;
		}
//		if (answer == 1) {
//			System.out.println("detected");
//			System.out.println("What move would you like to use");
//		}
//		if (answer == 2) {
//			System.out.println("Killed");
//			System.out.println("What move would you like to use");
//		}
//		g.setColor(Color.black);
//		g.fillRect(0, 0, 200, 200);
//		if(count <= 1) {
//			battleTest.simulateBattle(g);
//		}

		//g.setFont(new Font("Arial", Font.PLAIN, 50));
		//g.drawString(battleText, 10, 60);
		//g.drawString("display number"+count, 10, 60);

		//System.out.println(battleText);

		//System.out.println("done");

	}


	public BattleRunner getBattleTest() {
		return battleTest;
	}
}
