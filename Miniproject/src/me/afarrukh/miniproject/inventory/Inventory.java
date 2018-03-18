package me.afarrukh.miniproject.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import me.afarrukh.miniproject.Manager;
import me.afarrukh.miniproject.gfx.Text;
import me.afarrukh.miniproject.gfx.Visuals;
import me.afarrukh.miniproject.items.Item;

public class Inventory {
	
	private Manager manager;
	private boolean active = false;
	
	private int invWidth = 512, invHeight = 384, invX = 64, invY = 48;
	
	private int invListCenterX = invX + 171,
			invListCenterY = invY + invHeight / 2 + 5,
			invListSpacing = 30;
	
	private int invImageX = 452, invImageY = 82,
			invImageWidth = 64, invImageHeight = 64;
	
	private int invCountX = 484, invCountY = 172;
	
	private int selectedItem = 0;
	
	
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
		
		if(manager.getKeyManager().keyJustPressed(KeyEvent.VK_UP))
			selectedItem --;
		if(manager.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN))
			selectedItem ++;
		
		if(selectedItem < 0)
			selectedItem = inventoryItems.size() - 1;
		else if(selectedItem >= inventoryItems.size())
			selectedItem = 0;
		
		
	}
	
	public void render(Graphics g) {
		if(!active)
			return;
		
		g.drawImage(Visuals.inventoryScreen, invX, invY, invWidth, invHeight, null);
		
		int length = inventoryItems.size();
		if(length == 0)
			return;
		
		for(int i = -5; i < 6; i++) {
			if(selectedItem + i < 0 || selectedItem +i >= length) //Checking bounds
				continue;
			if(i == 0) {
				Text.drawString(g, ">> " +inventoryItems.get(selectedItem + i).getName() + " <<",
					invListCenterX, invListCenterY + i * invListSpacing, true, Color.YELLOW, Visuals.font28);
			} else {
				Text.drawString(g, inventoryItems.get(selectedItem + i).getName(),
						invListCenterX, invListCenterY + i * invListSpacing, true, Color.WHITE, Visuals.font28);
			}
		}
		
		Item item = inventoryItems.get(selectedItem);
		g.drawImage(item.getTexture(), invImageX, invImageY, invImageWidth,
				invImageHeight, null);
		Text.drawString(g, Integer.toString(item.getInstances()), 
				invCountX, invCountY, true, Color.WHITE, Visuals.font28);
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

	public boolean isActive() {
		return active;
	}
	
	
	
	
}
