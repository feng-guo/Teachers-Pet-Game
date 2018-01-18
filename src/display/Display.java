package display;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Display {

	private JFrame frame;
	private Canvas canvas;
	
	private String title;
	private int width, height;
	
	public Display(String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;
		
		createDisplay();
	}
	
	void createDisplay(){
		frame = new JFrame(title);
		frame.setUndecorated(true);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		//frame.setBackground(new Color(0, 0, 0, 0));
		frame.setVisible(true);
		frame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/4, Toolkit.getDefaultToolkit().getScreenSize().height/4);

		
		//frame.setFocusable(true);
		//frame.requestFocusInWindow();
		
		canvas = new Canvas();
		canvas.setBackground(new Color(0, 0, 0, 0));
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		
		frame.add(canvas);
		frame.pack();
	}

	public Canvas getCanvas(){
		return canvas;
	}
	
	public JFrame getFrame(){
		return frame;
	}
	
}