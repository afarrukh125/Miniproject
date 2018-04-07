package me.afarrukh.miniproject.items;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import me.afarrukh.miniproject.Manager;

/**
 * 
 * @author Abdullah
 * Stores any items lying on the ground. This does not manage the inventory of the player.
 * 
 */
public class ItemManager {

	private Manager manager;
	private ArrayList<Item> items;
	
	public ItemManager(Manager manager) {
		this.manager = manager;
		items = new ArrayList<>();
		
	}
	
	public void tick() {
		Iterator<Item> iter = items.iterator(); //An iterator for the arraylist of items
		while(iter.hasNext()) {
			Item i = iter.next();
			i.tick();
			if(i.isPickedUp())
				iter.remove();
		}
	}
	
	public void render(Graphics g) {
		for(Item i : items) {
			i.render(g);
		}
	}
	
	public void addItem(Item i) {
		//We have to set the manager here so the manager does not come as null. 
		//We need to do this because we cannot guarantee they will be added from the items class itself since it may be
		//contained in the inventory
		i.setManager(manager);
		items.add(i);
	}

	//Getters/Setters
	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
}
