package me.afarrukh.miniproject;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import me.afarrukh.miniproject.display.Display;
import me.afarrukh.miniproject.gfx.Assets;
import me.afarrukh.miniproject.gfx.GameCamera;
import me.afarrukh.miniproject.input.KeyManager;
import me.afarrukh.miniproject.states.GameState;
import me.afarrukh.miniproject.states.MenuState;
import me.afarrukh.miniproject.states.SettingState;
import me.afarrukh.miniproject.states.State;

public class Game implements Runnable {
	//We implement runnable so we can run it off a thread
	
	private Display display;
	
	public String title;
	private int width, height;
	
	private boolean running = false; //This decides if the game is already running
	private Thread thread; //Each individual game is only a section of the larger program
	
	private BufferStrategy buffStrat; //A bufferstrategy governs how and what is drawn
	//A bufferstrategy prevents flickering since it preloads elements onto the display.
	private Graphics g; //A graphics object is a paintbrush style object
	
	private State gameState; //The state of the game in which characters interact (This should be the main one)
	private State menuState; //The main menu state
	private State settingState; //The state for the settings menu
	
	private KeyManager keyManager; //The keyboard input manager we have made
	
	private GameCamera gameCamera; //The camera for the game
	
	private Handler handler;
	
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		
	}
	
	private void init() {
		//This method aims to start up the graphics and prepare the game
		//We also want to begin everything so that the game loops over and over
		//By looping over and over we continually update the game elements
		
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		Assets.init();
		
		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0, 0);
		
		gameState = new GameState(handler);
		//We want to initialise the states we may switch to for easy access
		menuState = new MenuState(handler); 
		settingState = new SettingState(handler);
		
		State.setState(gameState); //This sets the state of the program to our game
		
	}
	
	private void tick() {
		//This method updates the game continually
		keyManager.tick(); //Updates our KeyManager object
		
		if(State.getState() != null) {
			State.getState().tick();
		}
	}
	
	private void render() {
		//This sets our buffer strategy to whatever the buffer strategy is of the canvas
		buffStrat = display.getCanvas().getBufferStrategy(); 
		if(buffStrat == null) {
			display.getCanvas().createBufferStrategy(3); //We will have 3 buffered screens for the game
			return;
		}
		
		g = buffStrat.getDrawGraphics();
		//Clear the screen
		g.clearRect(0, 0, width, height); //Clear for further rendering
		
		if(State.getState() !=null) {
			State.getState().render(g);
		}
		//Drawings to be done in this space
		
		
		
		buffStrat.show();
		g.dispose();
	}
	
	public void run() {
		
		init(); //Initialises the game
		
		int fps = 30;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) /timePerTick;
			timer += (now - lastTime);
			lastTime = now;
			
			if(delta >= 1) { //This checks if the game is running at 30 frames
				tick();
				render();
				ticks++;
				delta--;
				
			}
			
			if(timer >= 1000000000) {
				System.out.println("Ticks and frames " +ticks);
				ticks = 0;
				timer = 0;
			}
			
		}
		
		stop(); //If the game has not already been stopped
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
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
		if(running)
			return; //We do not want to start an instance of game when it is already running
		running = true;
		thread = new Thread(this); //We want to create a new thread that contains our game
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		running = false;
		
		try {
			thread.join();
		} catch (InterruptedException e) 
			{e.printStackTrace();
		}
		
	}
	
}
