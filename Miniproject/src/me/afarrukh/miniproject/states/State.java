package me.afarrukh.miniproject.states;

import java.awt.Graphics;

//This class defines everything each state of the game should have,
//whether it be the main game, the settings menu or the launcher

public abstract class State {
	
	private static State currentState = null;
	
	//The top section of this class is just getters/setters for the current state of the game
	
	public static void setState(State state) {
		currentState = state;
	}
	
	public static State getState() {
		return currentState;
	}
	
	//Class methods required of subclasses
	public abstract void tick();
	
	public abstract void render(Graphics g);
}
