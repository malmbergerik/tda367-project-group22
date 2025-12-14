package td_game.view.render;

import td_game.view.helper.ProjectileViewData;
import td_game.view.helper.ProjectileViewManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * A renderer for projectiles in the game.
 */
public class ProjectileRenderer extends ABaseRenderer {

    private final ProjectileViewManager projectileViewManager;

    public ProjectileRenderer(ProjectileViewManager projectileViewManager, RenderingContext context) {
        super(context);
        this.projectileViewManager = projectileViewManager;
    }

    /**
     * Renders the projectiles on the game view.
     *
     * @param g2 The Graphics2D object to draw on.
     */
    @Override
    public void render(Graphics2D g2) {
        List<ProjectileViewData> projectileViewData = context.getProjectileViewData();
        if (projectileViewData == null) return;

        for (ProjectileViewData data : projectileViewData) {
            BufferedImage projectileImage = projectileViewManager.getProjectileImage(data.getName());

            if (projectileImage != null) {
                drawCenteredImage(g2, projectileImage, data.getX(), data.getY(), data.getWidth(), data.getHeight());
            }
        }

    }

    /**
     * Gets the render priority for projectiles.
     *
     * @return The render priority.
     */
    @Override
    public int getRenderPriority() {
        return 40;
    }
}
