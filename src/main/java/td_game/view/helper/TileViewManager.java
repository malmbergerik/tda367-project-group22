
package td_game.view.helper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class TileViewManager {

    private final Map<String, BufferedImage> tileImages;


    public TileViewManager(){
        tileImages = new HashMap<>();
        loadTileImages();
    }

    public void loadTileImages() {

        tileImages.put("Grass", loadImage("assets/tileset/underground/grass.png"));
        tileImages.put("Path", loadImage("assets/tileset/underground/path.png"));
        tileImages.put("Water", loadImage("assets/tileset/underground/water.png"));

    }

    private BufferedImage loadImage(String path) {
        try {
            InputStream stream = getClass().getClassLoader().getResourceAsStream(path);
            if (stream == null) {
                throw new IOException("Could not find resource: " + path);
            }
            return ImageIO.read(stream);
        } catch (IOException e) {
            System.err.println("Failed to load image: " + path);
            return null;
        }
    }

    public BufferedImage getTileImage(String key) {
        return tileImages.get(key);
    }
}

