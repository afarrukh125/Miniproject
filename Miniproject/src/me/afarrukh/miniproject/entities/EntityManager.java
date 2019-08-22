package me.afarrukh.miniproject.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import me.afarrukh.miniproject.Manager;
import me.afarrukh.miniproject.entities.actors.Player;
import me.afarrukh.miniproject.mokapot.MokaConstants;

/**
 * @author Abdullah
 * @description An entity manager class gives us an easier, less tedious way of adding entities to our class
 */
public class EntityManager {

	private Manager manager;
	private final Player player;
	private final ArrayList<Entity> entities; //By using ArrayLists, we can have entities loaded/removed and
										//don't have to commit to a specific number of entities we require for our game
	private final Comparator<Entity> renderSorter = new Comparator<Entity>() { //Sorting entities so they are rendered such that the entities higher up render later than entities lower down

		@Override
		public int compare(Entity a, Entity b) {
			//Returns -1 if a should be rendered before be or +1 if vice versa
			if(a.getY() + a.getHeight() < b.getY() + b.getHeight()) //Add the heights so we are checking bottom areas of entities
				return - 1;
			return 1;
		}
		
	};
	
	public EntityManager(Manager manager, Player player) {
		this.manager = manager;
		this.player = player;
		
		entities = MokaConstants.getCommunicator().runRemotely(
				() -> new ArrayList<>(), MokaConstants.getRemoteAddress()
		);
		addEntity(player);
	}
	
	public void tick() {
		//Iterator used to prevent entities being removed during an important tick event
        //Also because removing an entity using the ArrayList method 'remove' can have unwanted consequences.
		Iterator<Entity> iter = entities.iterator();
		while(iter.hasNext()) {
				/*We use a regular for loop here in the tick() method unlike our 
				 * render method because of the way collisions between entities works.*/
				Entity e = iter.next();
				e.tick();
				if(!e.isActive())
					iter.remove(); //Removes the entity. This would be the same as e.remove for an individual entity removal
		}
		entities.sort(renderSorter);
	}
	
	public void render(Graphics g) {
		for(Entity e: entities) {
			e.render(g);
		}
		
		player.postRender(g);
	}
	
	public void addEntity(Entity e) {
		entities.add(e);
	}

	//Getter/Setter methods
	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Player getPlayer() {
		return player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}
}
