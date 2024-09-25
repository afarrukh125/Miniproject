package me.afarrukh.miniproject.entities.stillentities;

import java.awt.Graphics;
import java.util.Random;

import me.afarrukh.miniproject.Manager;
import me.afarrukh.miniproject.constants.Constants;
import me.afarrukh.miniproject.gfx.Visuals;
import me.afarrukh.miniproject.items.Item;
import me.afarrukh.miniproject.tiles.Tile;

public class RockPile extends StillEntity {

	public RockPile(Manager manager, float x, float y) {
		super(manager, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		health = Constants.ROCK_HEALTH;
		
		hitbox.x = hitbox.width /2 - 20;
		hitbox.y = hitbox.height/2 + 15;
		hitbox.width = 40;
		hitbox.height = (int) (height - height / 1.2f);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Visuals.rockPile, (int) (x - manager.getGameCamera().getxOffSet()), (int) (y - manager.getGameCamera().getyOffSet()), width, height, null);
		
	}

	@Override
	public void die() {
		Random random = new Random();
		int rng = random.nextInt(100);
		int gemChance = Constants.GEM_CHANCE + manager.getMap().getEntityManager().getPlayer().getCharLuck();
		if(rng < gemChance)
			manager.getMap().getItemManager().addItem(Item.gemItem.createCopy((int) x, (int) y));
		else
			manager.getMap().getItemManager().addItem(Item.rockItem.createCopy((int) x, (int) y));
		System.out.println("RNG Roll for gem: " +rng+ ". Where less than " +gemChance+ " will give a gem.");
	}
	

}
