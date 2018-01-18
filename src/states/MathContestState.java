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

		if(clockTimer < 1 || score == 20) {
			handler.getGame().setGameState();
			frame.setVisible(false);
		}

		
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

		
		g.setColor(new Color(245, 245, 220));
		g.fillRect(0, 0, 600, 400);
		
		
		g.drawImage(Assets.blackBoard, 30, 40, 540, 320, null);
		g.setColor(Color.WHITE);
		g.setFont(Assets.font24); 
		if(additionQuestion == 1) {
			g.drawString("" + rand1 + " + " + rand2 + " equals ", 100, 150);
		} else {
			g.drawString("" + rand1 + " x " + rand2 + " equals ", 100, 150);		
			
		}
		
//		try {
//			//g.drawString(input.getText(), 260, 150);
//		} catch (NullPointerException e) {
//			
//		}
		g.drawString("Time Left: " + clockTimer, 100, 250);
		g.drawString("Your Score: " + score, 100, 300);
		
		if (timesInitialized < 1) {
			g.setFont(Assets.font24); 
			frame = new JFrame();
			frame.setUndecorated(true);
			frame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/4 + 410, Toolkit.getDefaultToolkit().getScreenSize().height/4 + 132);
			frame.setSize(120, 30);
			input = new JTextArea(5, 5);
			input.setBackground(Color.BLACK);
			//input.setOpaque(false);
			input.setForeground(Color.WHITE);
			input.setBounds(10, 30, 200, 20);
			input.setFont(Assets.font24);
			input.setBorder(null);
			input.setFocusable(true);
			frame.add(input);
			
			frame.setVisible(true);
			timesInitialized++;
		}
		
		g.setColor(Color.GREEN);
		g.fillRect(50, 50, score*25, 20);
	}

}
