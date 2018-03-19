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

	//*******************************************************************
	//Very specific coordinates
	private int invWidth = 528, invHeight = 424, invX = 275, invY = 140;
	
	private int invListCenterX = invX + 220,
			invListCenterY = invY + invHeight / 2,
			invListSpacing = 85;

	private int invImgCenterX = invX + 20,
			invImgCenterY = invListCenterY - 32;

	private int invImageX = 452, invImageY = 82,
			invImageWidth = 64, invImageHeight = 64;
	
	private int invCountX = 495, invCountY = 417;

	//Drawing on the character and stats
	private int charImgX = invX + invWidth - 165,
				charImgY = invY + 15,
				charImgWidth = 128,
				charImgHeight = 128;

	private int charLabelX = charImgX + 70,
				charLabelY = charImgY + 168;

	//*******************************************************************

	//Other variables
	private int selectedItem = 0;
	
	
	private ArrayList<Item> inventoryItems;

	public Inventory(Manager manager) {
		this.manager = manager;
		inventoryItems = new ArrayList<Item>();
		
	}
	
	public void tick() {
		if(manager.getKeyManager().keyJustPressed(KeyEvent.VK_I))
			active = !active; //switches the status of the active status of the inventory

		if(manager.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE) && active)
			active = false;

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
		
		g.drawImage(Visuals.inventoryUI, invX, invY, invWidth, invHeight, null);
		
		int length = inventoryItems.size();
		Item item = inventoryItems.get(selectedItem);
		if(length == 0)
			return;
		
		for(int i = -2; i < 3; i++) {
			if(selectedItem + i < 0 || selectedItem +i >= length) //Checking bounds
				continue;
			if(i == 0) {
				//Drawing both text and image for selected image
				Text.drawString(g, ">> " +inventoryItems.get(selectedItem + i).getName() + " <<",
					invListCenterX, invListCenterY + i * invListSpacing, true, Color.GREEN, Visuals.font28);

				g.drawImage(item.getTexture(), invImgCenterX, invImgCenterY + i * invListSpacing, invImageWidth,
						invImageHeight, null);

			} else {
				g.drawImage(inventoryItems.get(selectedItem + i).getTexture(),
						invImgCenterX, invImgCenterY + i *invListSpacing, invImageWidth, invImageHeight, null);
			}
		}
		//Draws the number of instances of selected item
		Text.drawString(g, Integer.toString(item.getInstances()), 
				invCountX, invCountY, true, Color.WHITE, Visuals.font28);

		//Drawing character attributes and image onto the inventory menu
		g.drawImage(manager.getMap().getEntityManager().getPlayer().getCurrentAnimationFrame(),
				charImgX, charImgY, charImgWidth, charImgHeight, null);

		Text.drawString(g, manager.getMap().getEntityManager().getPlayer().getType(),
						charLabelX, charLabelY, true, Color.WHITE, Visuals.font28);
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
