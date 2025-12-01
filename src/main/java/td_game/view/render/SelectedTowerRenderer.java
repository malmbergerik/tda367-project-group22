package td_game.view.render;

import td_game.view.helper.MapViewData;
import td_game.view.helper.TowerViewManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SelectedTowerRenderer implements IRenderer {

    private final TowerViewManager towerViewManager;
    private final RenderingContext context;

    public SelectedTowerRenderer(TowerViewManager towerViewManager, RenderingContext context) {
        this.towerViewManager = towerViewManager;
        this.context = context;
    }

    @Override
    public void render(Graphics2D g2) {
        String selectedTower = context.getSelectedTower();
        int hoverRow = context.getHoverRow();
        int hoverCol = context.getHoverCol();
        Boolean placeable = context.getPlaceable();

        if (selectedTower == null || hoverRow < 0 || hoverCol < 0) return;

        BufferedImage towerIamge = towerViewManager.getTowerImage(selectedTower);
        if (towerIamge == null) return;

        MapViewData mapViewData = context.getMapViewData();
        if (mapViewData == null) return;

        int tileSize = mapViewData.getTileSize();
        int scale = context.getScale();
        int screen_x = hoverCol * tileSize * scale;
        int screen_y = hoverRow * tileSize * scale;

        Composite originalComposite = g2.getComposite();

        AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
        g2.setComposite(alpha);
        g2.drawImage(towerIamge, screen_x, screen_y, tileSize * scale, tileSize * scale, null);

        if (placeable != null){
            Color indicatorColor = placeable ?
                    new Color(0, 255, 0, 128) :  // Green for not placeable
                    new Color(255, 0, 0, 128); // Red for placeable
            g2.setColor(indicatorColor);
            g2.fillRect(screen_x, screen_y, tileSize * scale, tileSize * scale);
        }

        g2.setComposite(originalComposite);
    }

    @Override
    public int getRenderPriority() {
        return 20;
    }
}
