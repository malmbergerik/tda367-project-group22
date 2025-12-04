package td_game.view.render;

import td_game.view.helper.MapViewData;
import td_game.view.helper.TileViewManager;

import java.awt.*;
import java.awt.image.BufferedImage;

// Extend BaseRenderer!
public class TileRenderer extends ABaseRenderer {

    private final TileViewManager tileViewManager;
    private BufferedImage tileLayer;

    public TileRenderer(TileViewManager tileViewManager, RenderingContext context) {
        super(context);
        this.tileViewManager = tileViewManager;
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

    private void buildTileLayer(MapViewData mapData) {
        String[][] tileKeys = mapData.getTileKeys();
        int tileSize = mapData.getTileSize();
        int rows = tileKeys.length;
        int cols = tileKeys[0].length;

        int width = scale(cols * tileSize);
        int height = scale(rows * tileSize);

        tileLayer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = tileLayer.createGraphics();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                String tileKey = tileKeys[row][col];
                BufferedImage tileImage = tileViewManager.getTileImage(tileKey);

                if (tileImage != null) {
                    drawAtTile(g2, tileImage, row, col, tileSize);
                }
            }
        }

        g2.dispose();
    }

    @Override
    public int getRenderPriority() {
        return 0; //
    }
}