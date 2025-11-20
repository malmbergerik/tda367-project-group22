
package td_game.view.helper;
import td_game.model.map.TileBase;
import td_game.model.map.TileType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class TileViewManager {

    private final Map<TileType, BufferedImage> tileImages = new HashMap<>();

    public TileViewManager(){
        loadTileImages();
    }

    public void loadTileImages(){

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        try {
            InputStream grassStream = classLoader.getResourceAsStream("assets/tileset/overworld/grass.png");
            InputStream pathStream = classLoader.getResourceAsStream("assets/tileset/overworld/path.png");
            InputStream waterStream = classLoader.getResourceAsStream("assets/tileset/overworld/water.png");

            if (grassStream == null || pathStream == null || waterStream == null) {
                throw new IOException("Could not find path to file");
            }

            tileImages.put(TileType.GRASS, ImageIO.read(grassStream));
            tileImages.put(TileType.PATH, ImageIO.read(pathStream));
            tileImages.put(TileType.WATER, ImageIO.read(waterStream));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedImage getTileImage(TileBase tile) {
        if(tile == null){
            return createPurpleTile();
        }
        else if(tile.getType() == null){
            return createPurpleTile();
        }

        return tileImages.getOrDefault(tile.getType(),createPurpleTile());



    }
    //Current for nonexisting tiles
    private BufferedImage createPurpleTile() {
        BufferedImage img = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = img.createGraphics();
        g2.setColor(new Color(128, 0, 128));
        g2.fillRect(0, 0, 16, 16);
        g2.dispose();
        return img;
    }
}

