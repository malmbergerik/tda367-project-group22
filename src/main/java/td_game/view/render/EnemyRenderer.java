package td_game.view.render;

import td_game.view.helper.EnemyViewData;
import td_game.view.helper.EnemyViewManager;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.util.List;

public class EnemyRenderer implements IRenderer {

    private final EnemyViewManager enemyViewManager;
    private final RenderingContext context;

    public EnemyRenderer(EnemyViewManager enemyViewManager, RenderingContext context) {
        this.enemyViewManager = enemyViewManager;
        this.context = context;
    }

    @Override
    public void render(Graphics2D g2) {
        List<EnemyViewData> enemyViewData = context.getEnemyViewData();
        if (enemyViewData == null) return;

        int scale = context.getScale();

        for (EnemyViewData enemyData : enemyViewData) {
            String name = enemyData.getEnemyName();
            double x = enemyData.getEnemyX();
            double y = enemyData.getEnemyY();

            BufferedImage enemyImage = enemyViewManager.getEnemyImage(name);
            System.out.println("Rendering enemy: " + name + " at (" + x + ", " + y + ")");

            if (enemyImage != null) {
                int scaledX = (int) (x * scale) - 24;
                int scaledY = (int) (y * scale) - 24;
                g2.drawImage(enemyImage, scaledX, scaledY, 48, 48, null);
            }
        }

    }

    @Override
    public int getRenderPriority() {
        return 30;
    }
}
