package td_game.view.helper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * A class to manage tower images for the game view.
 * Used for passive View in MVC architecture.
 */
public class TowerViewManager {

    private final Map<String, BufferedImage> towerImages;


    public TowerViewManager(){
        towerImages = new HashMap<>();
        loadTileImages();
    }

    public void loadTileImages() {

        towerImages.put("CanonTower", loadImage("assets/towers/tower1.png"));
        towerImages.put("SniperTower", loadImage("assets/towers/tower1.png"));
        towerImages.put("FlameThrowerTower", loadImage("assets/towers/tower2.png"));
        towerImages.put("FireTackTower", loadImage("assets/towers/tower2.png"));
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

    public BufferedImage getTowerImage(String key) {
        return towerImages.get(key);
    }
}

