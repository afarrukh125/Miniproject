package me.afarrukh.miniproject.states;

import java.awt.Color;
import java.awt.Graphics;
import me.afarrukh.miniproject.Manager;
import me.afarrukh.miniproject.gfx.Visuals;
import me.afarrukh.miniproject.ui.ClickListener;
import me.afarrukh.miniproject.ui.UIManager;
import me.afarrukh.miniproject.ui.UIObject;

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

    private static class UITextButton extends UIObject {
        private final ClickListener clickListener;
        private final String text;

        public UITextButton(String text, float x, int y, int width, int height, ClickListener clickListener) {
            super(x, y, width, height);
            this.text = text;
            this.clickListener = clickListener;
        }

        @Override
        public void tick() {}

        @Override
        public void render(Graphics g) {
            g.setColor(Color.GRAY);
            g.fillRect((int) x, (int) y, width, height);
            if (isHovering()) {
                g.setColor(Color.CYAN);
            } else {
                g.setColor(Color.BLACK);
            }
            g.setFont(Visuals.font28);
            g.drawString(text, (int) x + (width / 7), (int) y + (height / 2));
        }

        @Override
        protected void onClick() {
            clickListener.onClick();
        }
    }
}
