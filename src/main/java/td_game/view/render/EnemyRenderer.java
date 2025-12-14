package td_game.view.render;

import td_game.view.helper.EnemyViewData;
import td_game.view.helper.EnemyViewManager;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.util.List;

/**
 * A renderer for enemies in the game.
 */
public class EnemyRenderer extends ABaseRenderer {

    private final EnemyViewManager enemyViewManager;

    public EnemyRenderer(EnemyViewManager enemyViewManager, RenderingContext context) {
        super(context);
        this.enemyViewManager = enemyViewManager;
    }

    /**
     * Renders the enemies on the game view.
     *
     * @param g2 The Graphics2D object to draw on.
     */
    @Override
    public void render(Graphics2D g2) {
        List<EnemyViewData> enemyViewData = context.getEnemyViewData();
        if (enemyViewData == null) return;


        for (EnemyViewData data : enemyViewData) {
            BufferedImage enemyImage = enemyViewManager.getEnemyImage(data.getEnemyName());

            if (enemyImage != null) {
                drawCenteredImage(g2, enemyImage, data.getEnemyX(), data.getEnemyY(), 16, 16);
            }
        }

    }

    /**
     * Gets the render priority for enemies.
     *
     * @return The render priority.
     */
    @Override
    public int getRenderPriority() {
        return 30;
    }
}
