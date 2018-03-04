package me.afarrukh.miniproject.states;

import java.awt.Graphics;

import me.afarrukh.miniproject.Manager;

//This class defines everything each state of the game should have,
//whether it be the main game, the settings menu or the launcher

public abstract class State {
	
	//The top section of this class is just getters/setters for the current state of the game
	//This could have easily been done in a separate class but as a matter of preference that has not been done.
	private static State currentState = null;
	
	public static void setState(State state) {
		currentState = state;
	}
	
	public static State getState() {
		return currentState;
	}
	
	//Class methods required of subclasses
	
	protected Manager manager;
	
	public State(Manager manager) {
		this.manager = manager;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
}
