package me.afarrukh.miniproject.inventory;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import me.afarrukh.miniproject.Manager;
import me.afarrukh.miniproject.items.Item;

public class Inventory {
	
	private Manager manager;
	private boolean active = false;
	
	private ArrayList<Item> inventoryItems;

	public Inventory(Manager manager) {
		this.manager = manager;
		inventoryItems = new ArrayList<Item>();
	}
	
	public void tick() {
		if(manager.getKeyManager().keyJustPressed(KeyEvent.VK_I))
			active = !active; //switches the status of the active status of the inventory
		if(!active)
			return;
		
		//Code to tick the inventory here
		System.out.println("Inventory contents: ");
		for(Item i: inventoryItems) {
			System.out.println(i.getName() + ": " + i.getInstances());
		}
	}
	
	public void render(Graphics g) {
		if(!active)
			return;
	}
	
	//Inventory methods here
	
	public void addItem(Item item) {
		for(Item i: inventoryItems) {
			//This increments the item count in the players inventory if they already have it
			if(i.getId() == item.getId()) {
				i.setInstances(i.getInstances() + item.getInstances());
				return;
			}
		}
		//If they already have it, add a new item
		inventoryItems.add(item);
	}

	//Getters and setters
	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
	
}
