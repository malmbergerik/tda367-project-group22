package td_game.view.render;

import td_game.view.helper.MapViewData;
import td_game.view.helper.TileViewManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TileRenderer implements IRenderer{

    private final TileViewManager tileViewManager;
    private final RenderingContext context;
    private BufferedImage tileLayer;

    public TileRenderer(TileViewManager tileViewManager, RenderingContext context) {
        this.tileViewManager = tileViewManager;
        this.context = context;
    }

    @Override
    public void render(Graphics2D g2) {
        MapViewData mapViewData = context.getMapViewData();
        if (mapViewData == null) return;

        if (tileLayer == null) {
            buildTileLayer(mapViewData);
        }
        if (tileLayer != null) {
            g2.drawImage(tileLayer, 0, 0, null);
        }
    }

    @Override
    public int getRenderPriority() {
        return 0;
    }

    private void buildTileLayer(MapViewData mapData) {
        String[][] tileKeys = mapData.getTileKeys();
        int tileSize = mapData.getTileSize();
        int rows = tileKeys.length;
        int cols = tileKeys[0].length;
        int scale = context.getScale();

        int width = cols * tileSize * scale;
        int height = rows * tileSize * scale;

        tileLayer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = tileLayer.createGraphics();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                String tileKey = tileKeys[row][col];
                BufferedImage tileImage = tileViewManager.getTileImage(tileKey);
                if (tileImage != null) {
                    int screen_x = col * tileSize * scale;
                    int screen_y = row * tileSize * scale;
                    g2.drawImage(tileImage, screen_x, screen_y, tileSize * scale, tileSize * scale, null);
                }
            }
        }

        g2.dispose();
    }

    public void invalidCache() {
        this.tileLayer = null;
    }

}
