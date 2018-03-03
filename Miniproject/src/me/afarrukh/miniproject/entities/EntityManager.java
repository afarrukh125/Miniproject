package me.afarrukh.miniproject.entities;

import java.awt.Graphics;
import java.util.ArrayList;

import me.afarrukh.miniproject.Handler;
import me.afarrukh.miniproject.entities.actors.Player;

/**
 * @author Abdullah
 * @description An entity manager class gives us an easier, less tedious way of adding entities to our class
 */
public class EntityManager {

	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities; //By using ArrayLists, we can have entities loaded/removed and 
										//don't have to commit to a specific number of entities we require for our game
	
	public EntityManager(Handler handler, Player player) {
		this.handler = handler;
		this.player = player;
		
		entities = new ArrayList<Entity>();
		addEntity(player);
	}
	
	public void tick() {
		for(int i = 0; i <entities.size(); i++) { 
			/*We use a regular for loop here in the tick() method unlike our 
			 * render method because of the way collisions between entities works.*/
			Entity e = entities.get(i);
			e.tick();
		}
	}
	
	public void render(Graphics g) {
		for(Entity e: entities) {
			e.render(g);
		}
	}
	
	public void addEntity(Entity e) {
		entities.add(e);
	}

	//Getter/Setter methods
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
}
