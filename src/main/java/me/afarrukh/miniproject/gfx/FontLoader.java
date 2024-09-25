package me.afarrukh.miniproject.gfx;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

class FontLoader {

    public static Font loadFont(InputStream inputStream, int size) {

        try {
            return Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(Font.PLAIN, size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
