package states;

import java.awt.Color; 
import java.awt.Font;
import java.awt.Graphics;

import game.Handler;
//import battle.Battle;

public class BattleState extends State{
	
	private String battleText;
	//private Battle battle;

	public BattleState(Handler handler) {
		super(handler);
		
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setFont(new Font("Arial", Font.PLAIN, 50));
		
		g.drawString("", 10, 60);
			
		System.out.println("done");
		
	}

}
