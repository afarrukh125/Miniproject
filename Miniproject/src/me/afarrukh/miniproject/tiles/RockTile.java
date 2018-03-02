package me.afarrukh.miniproject.tiles;

import me.afarrukh.miniproject.gfx.Assets;

public class RockTile extends Tile {

	public RockTile(int id) {
		super(Assets.rock, id);
	}
	
	@Override
	public boolean isSolid() { //A rock is not traversable
		return true;
	}
}
