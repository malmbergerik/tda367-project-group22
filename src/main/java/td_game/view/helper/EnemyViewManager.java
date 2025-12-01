package td_game.view.helper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class EnemyViewManager {

    private final Map<String, BufferedImage> enemyImages;


    public EnemyViewManager(){
            enemyImages = new HashMap<>();
            loadTileImages();
        }

        public void loadTileImages() {
            enemyImages.put("Slime", loadImage("assets/enemies/slimeR.png"));
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

        public BufferedImage getEnemyImage(String key) {
            return enemyImages.get(key);
        }
    }


