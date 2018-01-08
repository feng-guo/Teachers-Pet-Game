package states;

import java.awt.Color;  
import java.awt.Font;
import java.awt.Graphics;

import battle.BattleTester;
import game.Handler;
//import battle.Battle;

public class BattleState extends State{
	
	
	private int count = 0;
	
	private String battleText;
	private BattleTester battleTest;

	public BattleState(Handler handler) {
		super(handler);
		battleTest = new BattleTester(handler);
	}

	@Override
	public void tick() {
		System.out.println("here");
			if(count < 1) {
				battleTest.simulateBattle();
				count++;	
			}
			//battleText = battleTest.getFinalText();
			//System.out.println(battleText);
	}

	@Override
	public void render(Graphics g) {
		//g.setFont(new Font("Arial", Font.PLAIN, 50));
		//g.drawString(battleText, 10, 60);
		//System.out.println(battleText);
			
		//System.out.println("done");
		
	}

}
