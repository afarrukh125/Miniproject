package me.afarrukh.miniproject.tiles;

import me.afarrukh.miniproject.constants.Constants;
import me.afarrukh.miniproject.gfx.Animation;
import me.afarrukh.miniproject.gfx.Visuals;

public class LavaTile extends Tile {
	
	public LavaTile(int id) {
		super(new Visuals().lava, id);
		this.animation = new Animation(750, new Visuals().lava);
	}
	
	@Override
	public boolean isSolid() { 
		return true;
	}

}
