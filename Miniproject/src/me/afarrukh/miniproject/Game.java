package me.afarrukh.miniproject;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.net.InetAddress;
import java.security.KeyManagementException;
import java.security.KeyStoreException;

import me.afarrukh.miniproject.constants.Constants;
import me.afarrukh.miniproject.display.Display;
import me.afarrukh.miniproject.gfx.Visuals;
import me.afarrukh.miniproject.gfx.GameCamera;
import me.afarrukh.miniproject.input.KeyManager;
import me.afarrukh.miniproject.input.MouseManager;
import me.afarrukh.miniproject.states.GameState;
import me.afarrukh.miniproject.states.MenuState;
import me.afarrukh.miniproject.states.SettingState;
import me.afarrukh.miniproject.states.State;
import me.afarrukh.miniproject.ui.GameTimer;
import xyz.acygn.mokapot.CommunicationAddress;
import xyz.acygn.mokapot.DistributedCommunicator;

public class Game implements Runnable {
	//We implement runnable so we can run it off a thread
	
	private Display display;
	
	private final String title;
	private final int width;
	private final int height;
	
	private boolean running = false; //This decides if the game is already running
	private Thread thread; //Each individual game is only a section of the larger program

	public State gameState; //The state of the game in which characters interact (This should be the main one)

	private final KeyManager keyManager; //The keyboard input manager we have made
	private final MouseManager mouseManager; //This manages the mouse inputs
	
	private GameCamera gameCamera; //The camera for the game

	private GameTimer gameTimer;
	
	
	public Game(String title, int width, int height) throws IOException, KeyStoreException, KeyManagementException {
		this.width = width;
		this.height = height;
		this.title = title;

		DistributedCommunicator comm =
				new DistributedCommunicator("client.p12", "testpassword1".toCharArray());

		comm.startCommunication();

		CommunicationAddress remoteAddress = comm.lookupAddress(InetAddress.getLoopbackAddress(), 15238);

		keyManager = DistributedCommunicator.getCommunicator().runRemotely(
				() -> new KeyManager(), remoteAddress
		);

		mouseManager = new MouseManager();
		
	}
	
	private void init() {
		//This method aims to start up the graphics and prepare the game
		//We also want to begin everything so that the game loops over and over
		//By looping over and over we continually update the game elements
		
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		
		//Adding the mouseManager to the canvas reduces glitches
		display.getCanvas().addMouseListener(mouseManager); 
		display.getCanvas().addMouseMotionListener(mouseManager);
		
		
		Visuals.init();

		Manager.init(this); // Setting up our manager singleton

		gameCamera = new GameCamera(0, 0);
		gameTimer = new GameTimer();

		gameState = new GameState();
		//We want to initialise the states we may switch to for easy access
		//The main menu state
		State menuState = new MenuState();
		//The state for the settings menu
		State settingState = new SettingState();
		
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
		//A bufferstrategy governs how and what is drawn
		BufferStrategy buffStrat = display.getCanvas().getBufferStrategy();
		if(buffStrat == null) {
			display.getCanvas().createBufferStrategy(3); //We will have 3 buffered screens for the game
			return;
		}

		//A bufferstrategy prevents flickering since it preloads elements onto the display.
		//A graphics object is a paintbrush style object
		Graphics g = buffStrat.getDrawGraphics();
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
		
		int fps = Constants.FPS;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long currentTime;
		long prevTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		//By the time we get to our while loop the time will have changed
		//Since we are dealing with nanoseconds, this difference is pronounced more.
		
		while(running) {
			currentTime = System.nanoTime();
			delta += (currentTime - prevTime) /timePerTick;
			timer += (currentTime - prevTime);
			prevTime = currentTime;
			
			if(delta >= 1) { //This checks if the game is running at our frames
				tick();
				render();
				ticks++;
				delta--;
				
			}
			//If we have exceeded more than one nanosecond, then reset our ticks and
			//Operations within this if statement are things that occur once per second
			if(timer >= 1000000000) {
				//System.out.println("Frame rate " +ticks);
				gameTimer.tick(); //Only tick the timer once per second
				ticks = 0;
				timer = 0;
			}
			
		}
		
		stop(); //If the game has not already been stopped
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

	public GameTimer getGameTimer() { return gameTimer; }
	
	public synchronized void start() {
		if(running)
			return; //We do not want to start an instance of game when it is already running
		running = true;
		thread = new Thread(this); //We want to create a new thread that contains our game
		thread.start();
	}
	
	private synchronized void stop() {
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
