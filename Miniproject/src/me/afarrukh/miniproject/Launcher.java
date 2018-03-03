package me.afarrukh.miniproject;;

public class Launcher {
	
	public static void main(String[] args) {
		
		Game game = new Game("GameWindow", 1024, 580);
		game.start(); //This will actually start running the game.
	}
}
