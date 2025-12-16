package td_game.view.render;

import td_game.view.helper.MapViewData;
import td_game.view.helper.TowerViewData;
import td_game.view.helper.TowerViewManager;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A renderer for displaying the selected tower at the hover position.
 */
public class SelectedTowerRenderer extends ABaseRenderer {

    private final TowerViewManager towerViewManager;

    public SelectedTowerRenderer(TowerViewManager towerViewManager, RenderingContext context) {
        super(context);
        this.towerViewManager = towerViewManager;
    }

    /**
     * Renders the selected tower at the hover position with transparency.
     *
     * @param g2 The Graphics2D object to draw on.
     */
    @Override
    public void render(Graphics2D g2) {
        String selectedTower = context.getSelectedTower();
        int hoverRow = context.getHoverRow();
        int hoverCol = context.getHoverCol();
        Boolean placeable = context.getPlaceable();
        MapViewData mapViewData = context.getMapViewData();
        TowerViewData towerViewData = context.getTowerViewData();

        if (selectedTower == null || hoverRow < 0 || hoverCol < 0 || mapViewData == null) return;

        BufferedImage towerImage = towerViewManager.getTowerImage(selectedTower);
        if (towerImage == null) return;

        Composite originalComposite = g2.getComposite();

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));

        drawAtTile(g2, towerImage, hoverRow, hoverCol, mapViewData.getTileSize());

        if (placeable != null){
            Color indicatorColor = placeable ?
                    new Color(0, 255, 0, 128) :  // Green for not placeable
                    new Color(255, 0, 0, 128); // Red for placeable

            Color rangeColor = new Color(18, 18, 18,  128);

            fillTile(g2, indicatorColor, hoverRow, hoverCol, mapViewData.getTileSize());
            fillCircle(g2, rangeColor, hoverRow, hoverCol, mapViewData.getTileSize(), context.getSelectedTowerRange() * 2);

        }

        g2.setComposite(originalComposite);
    }

    /**
     * Gets the render priority for the selected tower.
     *
     * @return The render priority.
     */
    @Override
    public int getRenderPriority() {
        return 20;
    }
}
