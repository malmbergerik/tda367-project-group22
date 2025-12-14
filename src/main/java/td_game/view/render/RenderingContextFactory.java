package td_game.view.render;

import td_game.view.helper.*;

/**
 * A factory class for creating RenderingContext instances with appropriate renderers.
 */
public class RenderingContextFactory {

    public static RenderingContext createRenderingContext(
            TileViewManager tileViewManager,
            TowerViewManager towerViewManager,
            EnemyViewManager enemyViewManager,
            ProjectileViewManager projectileViewManager
    ) {

        RenderingContext context = new RenderingContext();

        context.addRenderer(new TileRenderer(tileViewManager, context));
        context.addRenderer(new TowerRenderer(towerViewManager, context));
        context.addRenderer(new SelectedTowerRenderer(towerViewManager, context));
        context.addRenderer(new EnemyRenderer(enemyViewManager, context));
        context.addRenderer(new ProjectileRenderer(projectileViewManager, context));

        return context;
    }
}
