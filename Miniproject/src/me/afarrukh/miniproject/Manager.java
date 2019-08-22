package me.afarrukh.miniproject;

import me.afarrukh.miniproject.exceptions.ManagerAlreadyExistsException;
import me.afarrukh.miniproject.exceptions.ManagerNotInstantiatedException;
import me.afarrukh.miniproject.gfx.GameCamera;
import me.afarrukh.miniproject.input.KeyManager;
import me.afarrukh.miniproject.input.MouseManager;
import me.afarrukh.miniproject.maps.Map;

/**
 * 
 * @author Abdullah
 * @description A manager class is required in order to have one location from which the game and the current world can be loaded from
 * It is basically a way to simplify our access to the game.
 * A manager also assists things like collision detection and items.
 *
 * Update: 22/08/2019 - After learning a little more about singleton design patterns I have decided to implement
 * the singleton design pattern as it is far more suitable for its purposes than passing it around.
 *
 */
public class Manager {

	private static Manager instance;
	private static Game game;
	private Map map;
	
	private Manager() {
	}

	public static void init(Game game1) {
		if(instance != null)
			throw new ManagerAlreadyExistsException();
		instance = new Manager();
		game = game1;
	}

	public static Manager getInstance() {
		if(game == null)
			throw new ManagerNotInstantiatedException();
		return instance;
	}
	
	public int getWidth() {
		return game.getWidth();
	}
	
	public int getHeight() {
		return game.getHeight();
	}
	
	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}
	
	public MouseManager getMouseManager() { 
		return game.getMouseManager();
	}
	
	public GameCamera getGameCamera() {
		return game.getGameCamera();
	}
	
	//Getter/Setter methods
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}
}
