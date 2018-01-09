package states;

import java.awt.Color;  
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Canvas;

import battle.BattleTester;
import game.Handler;

import javax.swing.*;
//import battle.Battle;

public class BattleState extends State{
	
	
	private boolean ranBattle;
	
	private String battleText;
	private BattleTester battleTest;
	private Graphics g;
	private UpdateBattleText battleTextUpdate;

	public BattleState(Handler handler, String s){
		super (handler);
	}

	public BattleState(Handler handler) {
		super(handler);
		//handler.setWorld(null);
		ranBattle = false;
		this.battleTextUpdate = new UpdateBattleText(handler);
		battleTest = new BattleTester(handler, battleTextUpdate);

	}

	@Override
	public void tick() {

		System.out.println("penis");
		render(g);
	}

	@Override
	public void render(Graphics g) {
		/*try {
			if (g != null) {
				g.setColor(Color.black);
				g.fillRect(0, 0, 200, 200);
				c.repaint();
				frame.repaint();
				System.out.println("I should try to work here");
				if (count <= 1) {
					battleTest.simulateBattle(g);
				}

				//g.setFont(new Font("Arial", Font.PLAIN, 50));
				//g.drawString(battleText, 10, 60);
				//g.drawString("display number"+count, 10, 60);

				System.out.println(battleText);

				System.out.println("done");
			} else  {
				System.out.println("What happened here");
			}
		} catch (NullPointerException e) {
			System.out.println("What happened");
		} */
		if (g == null) {
			System.out.println("fuck u");
		}
		if (battleTextUpdate == null) {
			System.out.println("fucl");
		} else if (battleTextUpdate.getBattleText() == null) {
			System.out.println("fuck me");
		}

		//g.drawString(battleTextUpdate.getBattleText(), 200, 200);

		System.out.println("I should've printed");
		//battleTextUpdate.updateString(Double.toString(Math.random()));*/


		//g.fillRect(0, 0, 200, 200);
	}

	public void startBattle(Graphics g) {
		battleTest.simulateBattle(g);
	}



}
