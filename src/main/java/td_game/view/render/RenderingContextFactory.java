package td_game.view.render;

import td_game.view.helper.EnemyViewManager;
import td_game.view.helper.TileViewManager;
import td_game.view.helper.TowerViewManager;

public class RenderingContextFactory {

    public static RenderingContext createRenderingContext() {

        RenderingContext context = new RenderingContext();

        TileViewManager tileViewManager = new TileViewManager();
        TowerViewManager towerViewManager = new TowerViewManager();
        EnemyViewManager enemyViewManager = new EnemyViewManager();


        context.addRenderer(new TileRenderer(tileViewManager, context));
        context.addRenderer(new TowerRenderer(towerViewManager, context));
        context.addRenderer(new SelectedTowerRenderer(towerViewManager, context));
        context.addRenderer(new EnemyRenderer(enemyViewManager, context));

        return context;
    }
}
