package me.afarrukh.miniproject;

import me.afarrukh.miniproject.exceptions.ManagerAlreadyExistsException;
import me.afarrukh.miniproject.exceptions.ManagerNotInstantiatedException;
import me.afarrukh.miniproject.gfx.GameCamera;
import me.afarrukh.miniproject.gfx.Visuals;
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
 */
public class Manager {

	private static Manager instance;
	private static Game game;
	private Map map;
	private Visuals visuals;
	
	public Manager(Game game) {
		this.game = game;
		this.visuals = new Visuals();
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

	public Visuals getVisuals() {
		return visuals;
	}

	public void setVisuals(Visuals visuals) {
		this.visuals = visuals;
	}
}
