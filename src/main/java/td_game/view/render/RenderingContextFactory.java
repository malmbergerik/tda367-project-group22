package td_game.view.render;

import td_game.view.helper.*;

public class RenderingContextFactory {

    public static RenderingContext createRenderingContext() {

        RenderingContext context = new RenderingContext();

        TileViewManager tileViewManager = new TileViewManager();
        TowerViewManager towerViewManager = new TowerViewManager();
        EnemyViewManager enemyViewManager = new EnemyViewManager();
        ProjectileViewManager projectileViewManager = new ProjectileViewManager();



        context.addRenderer(new TileRenderer(tileViewManager, context));
        context.addRenderer(new TowerRenderer(towerViewManager, context));
        context.addRenderer(new SelectedTowerRenderer(towerViewManager, context));
        context.addRenderer(new EnemyRenderer(enemyViewManager, context));
        context.addRenderer(new ProjectileRenderer(projectileViewManager, context));

        return context;
    }
}
