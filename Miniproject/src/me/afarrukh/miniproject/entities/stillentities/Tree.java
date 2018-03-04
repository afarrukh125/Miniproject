package me.afarrukh.miniproject.entities.stillentities;

import java.awt.Graphics;

import me.afarrukh.miniproject.Handler;
import me.afarrukh.miniproject.gfx.Assets;
import me.afarrukh.miniproject.tiles.Tile;

public class Tree extends StillEntity {

	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH*2, Tile.TILEHEIGHT*2);
		
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
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.tree, (int) (x - handler.getGameCamera().getxOffSet()), (int) (y - handler.getGameCamera().getyOffSet()), width, height, null);
		
//		g.fillRect((int) (x + hitbox.x - handler.getGameCamera().getxOffSet()),
//				   (int) (y + hitbox.y - handler.getGameCamera().getyOffSet()), 
//						   hitbox.width, hitbox.height);
	
	}
	
	

}
