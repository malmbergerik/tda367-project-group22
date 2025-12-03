package td_game.view.render;

import td_game.view.helper.TowerViewData;
import td_game.view.helper.TowerViewManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TowerRenderer implements IRenderer {

    private final TowerViewManager towerViewManager;
    private final RenderingContext context;

    public TowerRenderer(TowerViewManager towerViewManager, RenderingContext context) {
        this.towerViewManager = towerViewManager;
        this.context = context;
    }

    @Override
    public void render(Graphics2D g2) {
        TowerViewData towerViewData = context.getTowerViewData();
        if (towerViewData == null) return;

        String[][] towerKeys = towerViewData.getTowerKeys();
        int tileSize = context.getMapViewData().getTileSize();
        int scale = context.getScale();
        int rows = towerKeys.length;
        int cols = towerKeys[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                String towerKey = towerKeys[row][col];
                if (towerKey != null) {
                    BufferedImage towerImage = towerViewManager.getTowerImage(towerKey);
                    if (towerImage != null) {
                        int screen_x = col * tileSize * scale;
                        int screen_y = row * tileSize * scale;
                        g2.drawImage(towerImage, screen_x, screen_y, tileSize * scale, tileSize * scale, null);
                    }
                }
            }
        }
    }

    @Override
    public int getRenderPriority() {
        return 10;
    }
}
