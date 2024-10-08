package me.afarrukh.miniproject.display;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Display {
    private JFrame frame;
    private Canvas canvas; // This is the main object on which we will draw our game images

    private final String title;
    private final int width;
    private final int height;

    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    private void createDisplay() {
        frame = new JFrame(title);

        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));

        canvas.setFocusable(
                false); // Allows application to only focus on JFrame in order to allow keyboard inputs to work

        frame.add(canvas);
        frame.pack();
        ImageIcon img = new ImageIcon("res/images/icon.png");
        frame.setIconImage(img.getImage());
    }

    public Canvas getCanvas() { // Standard getter method to return our canvas
        return canvas;
    }

    public JFrame getFrame() {
        return frame;
    }
}
