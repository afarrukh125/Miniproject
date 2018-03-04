package me.afarrukh.miniproject.entities.stillentities;

import java.awt.Graphics;

import me.afarrukh.miniproject.Manager;
import me.afarrukh.miniproject.gfx.Visuals;
import me.afarrukh.miniproject.items.Item;
import me.afarrukh.miniproject.tiles.Tile;

public class Tree extends StillEntity {

	public Tree(Manager manager, float x, float y) {
		super(manager, x, y, Tile.TILEWIDTH*2, Tile.TILEHEIGHT*2);
		
		hitbox.x = hitbox.width /2 - 20;
		hitbox.y = hitbox.height/2 + 15;
		hitbox.width = 40;
		hitbox.height = (int) (height - height / 1.2f);
	}

	@Override
	public void tick() {
		
	}
	
	@Override
	public void die() {
		manager.getMap().getItemManager().addItem(Item.woodItem.createCopy((int) x, (int) y));
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Visuals.tree, (int) (x - manager.getGameCamera().getxOffSet()), (int) (y - manager.getGameCamera().getyOffSet()), width, height, null);
		
//		g.fillRect((int) (x + hitbox.x - handler.getGameCamera().getxOffSet()),
//				   (int) (y + hitbox.y - handler.getGameCamera().getyOffSet()), 
//						   hitbox.width, hitbox.height);
	
	}
	
	

}
