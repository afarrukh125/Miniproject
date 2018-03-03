package me.afarrukh.miniproject.gfx;

import me.afarrukh.miniproject.Handler;
import me.afarrukh.miniproject.entities.Entity;
import me.afarrukh.miniproject.tiles.Tile;

public class GameCamera {

	private Handler handler;
	private float xOffSet, yOffSet;
	
	public GameCamera(Handler handler, float xOffSet, float yOffSet) {
		this.handler = handler;
		this.xOffSet = xOffSet;
		this.yOffSet = yOffSet;
	}
	
	public void checkForBlank() { //Checks if we have reached any areas where null tiles/boundaries have been reached
		if(xOffSet < 0) {
			xOffSet = 0;
		} else if(xOffSet > handler.getMap().getWidth() * Tile.TILEWIDTH - handler.getWidth()) {
			//This calculation takes into account the frame's width (handler.getWidth()) so we do not scroll as we reach boundaries
			xOffSet = handler.getMap().getWidth() * Tile.TILEWIDTH - handler.getWidth();
		}
		if(yOffSet < 0) {
			yOffSet = 0;
		} else if(yOffSet > handler.getMap().getHeight() * Tile.TILEHEIGHT - handler.getHeight()) {
			yOffSet = handler.getMap().getHeight() * Tile.TILEHEIGHT - handler.getHeight();
		}
	}
	
	public void centerOnEntity(Entity entity) {
		/*We divide by 2 the first time to centre the camera, and the second entity dimensions
		 also being divided by 2 is to account for the entity movement being done from the top left of the entity.*/
		xOffSet = entity.getX() - handler.getWidth() / 2 + entity.getWidth() / 2;
		yOffSet = entity.getY() - handler.getHeight() / 2 + entity.getHeight() / 2;
		checkForBlank();
	}
	
	public void move(float xAmount, float yAmount) {
		xOffSet += xAmount;
		yOffSet += yAmount;
		checkForBlank();
	}

	public float getxOffSet() {
		return xOffSet;
	}

	public void setxOffSet(float xOffSet) {
		this.xOffSet = xOffSet;
	}

	public float getyOffSet() {
		return yOffSet;
	}

	public void setyOffSet(float yOffSet) {
		this.yOffSet = yOffSet;
	}
	
	
}
