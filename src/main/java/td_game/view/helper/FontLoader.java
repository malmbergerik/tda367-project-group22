package td_game.view.helper; // Eller där du vill ha den

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;

public class FontLoader {

    public static Font loadFont(String path, float size) {
        try {
            InputStream is = FontLoader.class.getResourceAsStream(path);

            if (is == null) {
                return new Font("Serif", Font.BOLD, (int) size);
            }

            Font customFont = Font.createFont(Font.TRUETYPE_FONT, is);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);

            return customFont.deriveFont(size);

        } catch (IOException | java.awt.FontFormatException e) {
            e.printStackTrace();
            return new Font("Serif", Font.BOLD, (int) size);
        }
    }
}