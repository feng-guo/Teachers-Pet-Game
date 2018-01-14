 package game;

import java.awt.Color;     
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import display.Display;
import graphics.Assets;
import graphics.GameCamera;
import graphics.ImageLoader;
import graphics.SpriteSheet;
import input.KeyManager;
import input.MouseManager;
import states.BattleState;
import states.GameState;
import states.MenuState;
import states.SettingsState;
import states.State;

public class Game implements Runnable{

	private int width, height;
	private String title;
	private Display display;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	// States
	public State gameState;
	public State menuState;
	public State settingsState;
	public State battleState;
	
	// Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	// Camera
	private GameCamera gameCamera;
		
	// Handler
	private Handler handler;
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}
	
	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();
		
		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0, 0);
		
		settingsState = new SettingsState(handler);
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		battleState = new BattleState(handler, g);

		State.setState(menuState);

	}
	
	
	private void tick() {
		
		keyManager.tick();
		
		// CHANGE TO BATTLE STATE
		if(handler.getKeyManager().battle && !(State.getState() instanceof BattleState)) {
			State.setState(battleState);
			if (!((BattleState) battleState).getBattleTest().isBattleStart()) {
				startBattle();
			}
		}
		if(State.getState() != null) {
			State.getState().tick();
		}
	}

	public void startBattle() {
		((BattleState) battleState).getBattleTest().initializeBattleAssets();
		((BattleState) battleState).getBattleTest().startRandomBattle();
		((BattleState) battleState).getBattleTest().runPhase(-2);
	}

	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		
		
		
		// clear
		g.clearRect(0, 0, width, height);
		
		//beginning of drawing

		if(State.getState() != null) {
			State.getState().render(g);
		}
		
		//end of drawing
		
		
		bs.show();
		g.dispose();
		
	}
	
	public void run() {
		
		init();
		
		int fps = 60;
		double timePerTick = 1_000_000_000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += (now - lastTime);
			lastTime = now;
			
			if(delta >= 1) {
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if (timer >= 1_000_000_000) {
				//System.out.println(ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	public GameCamera getGameCamera() {
		return gameCamera;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	public synchronized void start() {
		if(running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		
		// this will call the run method
		thread.start();
	}
	
	public synchronized void stop() {
		
		if(!running) {
			return;
		}
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public State getGameState() {
		return gameState;
	}
}
