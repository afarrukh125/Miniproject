package me.afarrukh.miniproject.gfx;

import me.afarrukh.miniproject.Game;
import me.afarrukh.miniproject.entities.Entity;

public class GameCamera {

	private Game game;
	private float xOffSet, yOffSet;
	
	public GameCamera(Game game, float xOffSet, float yOffSet) {
		this.game = game;
		this.xOffSet = xOffSet;
		this.yOffSet = yOffSet;
	}
	
	public void centerOnEntity(Entity entity) {
		/*We divide by 2 the first time to centre the camera, and the second entity dimensions
		 * also being divided by 2 is to account for the entity movement being done from the top left of the entity.*/
		xOffSet = entity.getX() - game.getWidth() / 2 + entity.getWidth() / 2;
		yOffSet = entity.getY() - game.getHeight() / 2 + entity.getHeight() /2;
	}
	
	public void move(float xAmount, float yAmount) {
		xOffSet += xAmount;
		yOffSet += yAmount;
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
