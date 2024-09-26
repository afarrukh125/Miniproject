package me.afarrukh.miniproject.states;

import java.awt.Graphics;
import me.afarrukh.miniproject.Manager;
import me.afarrukh.miniproject.gfx.Visuals;
import me.afarrukh.miniproject.ui.UIManager;

/**
 * @author Abdullah
 * This state controls what happens when the user is in the main menu of the game
 */
public class MenuState extends State {

    private final UIManager uiManager;

    public MenuState(Manager manager) {
        super(manager);
        uiManager = new UIManager(manager);

        manager.getMouseManager().setUIManager(uiManager);

        uiManager.addObject(new UITextButton(
                "Start",
                (manager.getWidth() / 2f) - Visuals.getWidth(),
                44,
                128,
                64,
                () -> State.setState(manager.getGame().gameState)));

        uiManager.addObject(new UITextButton(
                "Exit", (manager.getWidth() / 2f) - Visuals.getWidth(), 144, 128, 64, () -> System.exit(0)));
    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }

}
