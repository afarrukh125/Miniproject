package me.afarrukh.miniproject;

import me.afarrukh.miniproject.mokapot.MokaConstants;

import java.io.IOException;
import java.net.InetAddress;
import java.security.KeyManagementException;
import java.security.KeyStoreException;

class Launcher {
	
	public static void main(String[] args) {

		Game game = null;
		try {
			game = new Game("Wanderer", 1024, 670);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
		game.start(); //This will actually start running the game.
	}
}
