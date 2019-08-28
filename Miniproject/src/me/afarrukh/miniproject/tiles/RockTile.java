package me.afarrukh.miniproject.tiles;

import me.afarrukh.miniproject.gfx.Visuals;

public class RockTile extends Tile {

	public RockTile(int id) {
		super(new Visuals().rock, id);
	}
	
	@Override
	public boolean isSolid() { //A rock is not traversable
		return true;
	}
}
