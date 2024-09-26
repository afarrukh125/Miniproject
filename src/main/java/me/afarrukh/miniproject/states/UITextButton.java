package me.afarrukh.miniproject.states;

import me.afarrukh.miniproject.gfx.Visuals;
import me.afarrukh.miniproject.ui.ClickListener;
import me.afarrukh.miniproject.ui.UIObject;

import java.awt.Color;
import java.awt.Graphics;

class UITextButton extends UIObject {
    private final ClickListener clickListener;
    private final String text;

    public UITextButton(String text, float x, int y, int width, int height, ClickListener clickListener) {
        super(x, y, width, height);
        this.text = text;
        this.clickListener = clickListener;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        drawBackgroundRectangle(g);
        drawText(g);
    }

    private void drawBackgroundRectangle(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect((int) x, (int) y, width, height);
    }

    private void drawText(Graphics g) {
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
