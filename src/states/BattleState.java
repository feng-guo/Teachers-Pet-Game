package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import battle.BattleRunner;
import battle.BattleRunner;
import game.Handler;
import graphics.Assets;
import battle.Character;

public class BattleState extends State{


	private int count = 0;
	private int timer = 0;

	private String battleText;
	private BattleRunner battleTest;
	private int answer = -1;
	boolean textLoading = false;

	public BattleState(Handler handler, Graphics g) {
		super(handler);
		//handler.setWorld(null);
		battleTest = new BattleRunner();


	}

	@Override
	public void tick() {
		if (battleTest.isBattleEnd()) {
			State.setState(handler.getGame().getGameState());
			return;
		}
		answer = -1;
		if (handler.getKeyManager().first) {
			answer = 1;
			handler.getKeyManager().first = false;
		}
		if (handler.getKeyManager().second) {
			answer = 2;
			handler.getKeyManager().second = false;
		}
		if (handler.getKeyManager().third) {
			answer = 3;
			handler.getKeyManager().third = false;
		}
		if (handler.getKeyManager().fourth) {
			answer = 4;
			handler.getKeyManager().fourth = false;
		}
		if (handler.getKeyManager().backspace) {
			answer = 10;
			handler.getKeyManager().backspace = false;
		}
		if (handler.getKeyManager().enter) {
			answer = 20;
		}

		if (handler.getKeyManager().enter && textLoading) {
			if (count < battleTest.getTextArrayList().get(0).length()*4) {
				count = battleTest.getTextArrayList().get(0).length() * 4 + 200;
			} else {
				count = 100000;
			}
			handler.getKeyManager().enter = false;
		} else if (answer != -1) {
			handler.getKeyManager().enter = false;
			battleTest.runPhase(answer);
			answer = -1;
		}
	}

	@Override
	public void render(Graphics g) {
		count++;
		//g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.drawImage(Assets.battleBackground, 0, 0, null);
		if (battleTest.getPlayer().getName().length() > 15) {
			g.setFont(Assets.font10);
		} else if (battleTest.getPlayer().getName().length() > 10) {
			g.setFont(Assets.font12);
		} else {
			g.setFont(Assets.font16);
		}
		g.drawString(battleTest.getPlayer().getName(), 350, 210);
		if (battleTest.getOpponent().getName().length() > 15) {
			g.setFont(Assets.font10);
		} else if (battleTest.getOpponent().getName().length() > 10) {
			g.setFont(Assets.font12);
		} else {
			g.setFont(Assets.font16);
		}
		g.drawString(battleTest.getOpponent().getName(), 50, 70);
		//g.setFont(new Font("Arial", Font.PLAIN, 17));
		g.setFont(Assets.font12);
		g.drawString(Integer.toString(battleTest.getPlayer().getCurrentHealth()), 475, 255);
		g.drawString(Integer.toString(battleTest.getPlayer().getInitialHealth()), 530, 255);
		g.drawString(Integer.toString(battleTest.getOpponent().getCurrentHealth()), 50, 90);


		if (battleTest.getTextArrayList().size() > 0) {
			textLoading = true;
			//g.setFont(new Font("Arial", Font.PLAIN, 20));
			g.setFont(Assets.font16);
			if (count/4 < battleTest.getTextArrayList().get(0).length()) {
				if (battleTest.getTextArrayList().get(0).length() > 25) {
					g.setFont(Assets.font12);
				}
				g.drawString(battleTest.getTextArrayList().get(0).substring(0, count/4), 25, 320);
			} else {
				if (battleTest.getTextArrayList().get(0).length() > 25) {
					g.setFont(Assets.font12);
				}
				g.drawString(battleTest.getTextArrayList().get(0), 25, 320);
			}
		}
		if (battleTest.getTextArrayList().size() > 0) {
			if (count/4 > battleTest.getTextArrayList().get(0).length() + 100 ) {
				battleTest.getTextArrayList().remove(0);
				count = 0;
			}
		}
		if (battleTest.getTextArrayList().size() == 0) {
			textLoading = false;
			count = 0;
			if (!battleTest.getSelectionStrings(0).equals("null")) {
				g.setFont(Assets.font12);
				g.drawString(battleTest.getSelectionStrings(0), 30, 320);
				g.drawString(battleTest.getSelectionStrings(1), 300, 320);
				g.drawString(battleTest.getSelectionStrings(2), 30, 370);
				g.drawString(battleTest.getSelectionStrings(3), 300, 370);
			}
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
