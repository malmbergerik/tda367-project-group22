package td_game.view.render;

import td_game.view.helper.EnemyViewData;
import td_game.view.helper.ProjectileViewData;
import td_game.view.helper.ProjectileViewManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class ProjectileRenderer implements IRenderer {

    private final ProjectileViewManager projectileViewManager;
    private final RenderingContext context;

    public ProjectileRenderer(ProjectileViewManager projectileViewManager, RenderingContext context) {
        this.projectileViewManager = projectileViewManager;
        this.context = context;
    }

    @Override
    public void render(Graphics2D g2) {
        List<ProjectileViewData> projectileViewData = context.getProjectileViewData();
        if (projectileViewData == null) return;

        int scale = context.getScale();


        for (ProjectileViewData projectileData : projectileViewData) {
            String name = projectileData.getName();
            double x = projectileData.getX();
            double y = projectileData.getY();

            System.out.println("Rendering projectile: " + name + " at (" + x + ", " + y + ")");

            BufferedImage enemyImage = projectileViewManager.getProjectileImage(name);



            if (enemyImage != null) {
                int scaledX = (int) (x * scale) - 24;
                int scaledY = (int) (y * scale) - 24;
                g2.drawImage(enemyImage, scaledX, scaledY, 48, 48, null);
            }
        }

    }

    @Override
    public int getRenderPriority() {
        return 40;
    }
}
