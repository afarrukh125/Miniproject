package me.afarrukh.miniproject.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private final boolean[] keys;
    private final boolean[] justPressed;
    private final boolean[] cantPress;

    public boolean up, down, left, right; // Movement controls
    public boolean atkUp, atkDown, atkLeft, atkRight; // Attack controls

    public KeyManager() {
        keys = new boolean[256]; // Reasonable size for keys being pressed
        justPressed = new boolean[keys.length];
        cantPress = new boolean[keys.length];
    }

    public void tick() {

        for (int i = 0; i < keys.length; i++) {
            // This section of code here allows a menu to show up
            if (cantPress[i] && !keys[i]) {
                // If you cant press it but you havent pressed it then
                // you should be able to press it
                cantPress[i] = false;
            } else if (justPressed[i]) {
                cantPress[i] = true; // They cannot press the key until they release it
                justPressed[i] = false; // They have no longer just pressed it
            }
            if (!cantPress[i] && keys[i]) {
                // We have to make sure that we can press the key
                // And that the key has been pressed, to set the
                // justPressed value to true for that particular key
                justPressed[i] = true;
            }
        }

        up = keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];

        atkUp = keys[KeyEvent.VK_W];
        atkDown = keys[KeyEvent.VK_S];
        atkLeft = keys[KeyEvent.VK_A];
        atkRight = keys[KeyEvent.VK_D];

        isAttacking(); // We want to check if any of the attack keys are being pressed
    }

    public boolean keyJustPressed(int keyCode) {
        if (keyCode < 0 || keyCode >= keys.length) return false;
        return justPressed[keyCode];
    }

    /**
     *
     * @return whether or not any of the attack keys are being pressed for animation purposes
     */
    public boolean isAttacking() {
        if (this.atkUp || this.atkDown || this.atkRight || this.atkLeft) return true;

        return false;
    }

    /*
     * The keyboard input system revolves primarily around the keys being pressed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) return;
        keys[e.getKeyCode()] = true; // Keys being pressed is set to true at first when it is pressed
        // System.out.println("Pressed.");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) return;
        keys[e.getKeyCode()] =
                false; // When a key is released then it is no longer selected and so we set this to false
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
