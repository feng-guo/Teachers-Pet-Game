package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import game.Handler;
import graphics.Assets;

public class MathContestState extends State {
	
	private int additionQuestion = (int) Math.random()*2;
	
	private int rand1 = (int) (Math.random()*10);
	private int rand2 = (int) (Math.random()*10);
	private int answer;
	private JTextArea input;
	private int timesInitialized = 0;
	private JFrame frame;
	private int score = 0;
	
	int timer = 0;
	int clockTimer = 60;

	public MathContestState(Handler handler) {
		super(handler);
		

	}

	@Override
	public void tick() {
//		handler.getGame().getDisplay().getFrame().setFocusable(false);
//		handler.getGame().getDisplay().getCanvas().setFocusable(false);


		
		timer++;
		if(timer%60 == 0) {
			clockTimer--;
		}
		
			if(additionQuestion == 1) {
				answer = rand1 + rand2;
			} else {
				answer = rand1 * rand2;
			}
		
		try {
			System.out.println(input.getText());
			if(input.getText().equals("" + answer)) {
				input.setText("");
				score++;
				rand1 = (int) (Math.random()*10);
				rand2 = (int) (Math.random()*10);
				additionQuestion = (int) Math.ceil(Math.random()*2);
			}
		} catch (NullPointerException | NumberFormatException e) {
			
		}
		
		
		
//		if (Integer.parseInt((input.getText()) == answer) {
//			
//		}
		

		
	}

	@Override
	public void render(Graphics g) {

		
		g.setColor(new Color(176, 224, 230));
		g.fillRect(0, 0, 600, 400);
		g.setColor(Color.black);
		
		g.setFont(Assets.font12); 
		if(additionQuestion == 1) {
			g.drawString("what is " + rand1 + " + " + rand2 + "?", 10, 50);
		} else {
			g.drawString("what is " + rand1 + " x " + rand2 + "?", 10, 50);		
			
		}
		g.drawString("" + answer, 10, 70);
		g.drawString("" + score, 10, 90);
		
		if (timesInitialized < 1) {
			g.setFont(Assets.font12); 
			frame = new JFrame();
			frame.setUndecorated(true);
			frame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/4, Toolkit.getDefaultToolkit().getScreenSize().height/4);
			frame.setSize(30, 30);
			input = new JTextArea(5, 5);
			input.setBackground(new Color(176, 224, 230)); 
			input.setBounds(10, 10, 200, 20);
			input.setFont(Assets.font12);
			input.setBorder(null);
			input.setFocusable(true);
			frame.add(input);
			
			frame.setVisible(true);
			timesInitialized++;
		}
	}

}
