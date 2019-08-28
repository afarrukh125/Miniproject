package me.afarrukh.miniproject.ui;

import me.afarrukh.miniproject.Manager;
import me.afarrukh.miniproject.gfx.Text;
import me.afarrukh.miniproject.gfx.Visuals;

import java.awt.*;

/**
 * Offers a way to get the time of the game
 */

public class GameTimer {

    private int gameTime;
    private final Manager manager;

    public GameTimer(Manager manager) {
        gameTime = 0;
        this.manager = manager;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", gameTime / 60, gameTime % 60);
    }

    public void tick() {
        gameTime ++;
    }

    public void render(Graphics g) {
        Text.drawString(g, this.toString(), manager.getWidth() - 50, 20, true, Color.WHITE, new Visuals().font28);
    }


    public int getGameTime() {
        return gameTime;
    }
}
