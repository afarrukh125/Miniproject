package me.afarrukh.miniproject;

import me.afarrukh.miniproject.display.Display;

public class Launcher {
	
	public static void main(String[] args) {
		
		Game game = new Game("GameWindow", 1000, 768);
		game.start(); //This will actually start running the game.
	}
}
