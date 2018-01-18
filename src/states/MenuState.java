package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.text.StyledEditorKit.FontSizeAction;

import game.Game;
import game.Handler;
import graphics.Assets;
import ui.ClickListener;
import ui.UIImageButton;
import ui.UIManager;

public class MenuState extends State{
	
	private UIManager uiManager;
	
	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		// Add the front logo button
		uiManager.addObject(new UIImageButton(250, 150, 100, 100, Assets.logo, new ClickListener(){

			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().gameState);
			}}));
	}

	@Override
	public void tick() {
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		
		//g.setColor(Color.black);
		//g.fillRect(0, 0, 600, 400);
		g.drawImage(Assets.loadingBackground, 0, 0, 600, 400, null);
		uiManager.render(g);

		// g.setFont(new Font("Courier New", 10, 10));
		//g.setFont(Font.BOLD);
		
		// THIS ADDS TIME FOR SOME REASON
		//g.drawString("RICHMOND HILL HIGH SIMULATOR", 190, 50);

	}

}
