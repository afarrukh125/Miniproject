package me.afarrukh.miniproject.tiles;

import me.afarrukh.miniproject.gfx.Visuals;

public class LavaTile extends Tile {
	
	public LavaTile(int id) {
		super(Visuals.lava, id);
	}
	
	@Override
	public boolean isSolid() { 
		return true;
	}

}
