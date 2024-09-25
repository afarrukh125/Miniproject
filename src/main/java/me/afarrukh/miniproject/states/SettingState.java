package me.afarrukh.miniproject.states;

import java.awt.Graphics;
import me.afarrukh.miniproject.Manager;

/**
 *
 * @author Abdullah
 * This state is the main state for the settings menu of the game.
 */
public class SettingState extends State {

    public SettingState(Manager manager) {
        super(manager);
    }

    @Override
    public void tick() {}

    @Override
    public void render(Graphics g) {}
}
