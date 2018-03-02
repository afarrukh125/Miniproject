package me.afarrukh.miniproject.tiles;

import me.afarrukh.miniproject.gfx.Assets;

public class LavaTile extends Tile {
	
	public LavaTile(int id) {
		super(Assets.lava, id);
	}
	
	@Override
	public boolean isSolid() { 
		return true;
	}

}
