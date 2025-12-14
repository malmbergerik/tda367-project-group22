package td_game.view.render;

import td_game.view.helper.MapViewData;
import td_game.view.helper.TowerViewData;
import td_game.view.helper.TowerViewManager;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A renderer for towers in the game.
 */
public class TowerRenderer extends ABaseRenderer {

    private final TowerViewManager towerViewManager;

    public TowerRenderer(TowerViewManager towerViewManager, RenderingContext context) {
        super(context);
        this.towerViewManager = towerViewManager;
    }

    /**
     * Renders the towers on the game view.
     *
     * @param g2 The Graphics2D object to draw on.
     */
    @Override
    public void render(Graphics2D g2) {
        TowerViewData towerViewData = context.getTowerViewData();
        MapViewData mapData = context.getMapViewData();

        if (towerViewData == null || mapData == null) return;

        String[][] towerKeys = towerViewData.getTowerKeys();
        int tileSize = mapData.getTileSize();
        int rows = towerKeys.length;
        int cols = towerKeys[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                String towerKey = towerKeys[row][col];

                if (towerKey != null) {
                    BufferedImage image = towerViewManager.getTowerImage(towerKey);
                    if (image != null) {
                        drawAtTile(g2, image, row, col, tileSize);
                    }
                }
            }
        }
    }

    /**
     * Gets the render priority for towers.
     *
     * @return The render priority.
     */
    @Override
    public int getRenderPriority() {
        return 10;
    }
}
