package td_game.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import td_game.model.enemy.EnemyFactory;
import td_game.model.enemy.Skeleton;
import td_game.model.modelnit.GameModel;
import td_game.view.helper.EnemyViewManager;
import td_game.view.helper.ProjectileViewManager;
import td_game.view.helper.TileViewManager;
import td_game.view.helper.TowerViewManager;
import td_game.view.panel.GamePanel;
import td_game.view.render.RenderingContext;
import td_game.view.render.RenderingContextFactory;

import static org.junit.jupiter.api.Assertions.*;

class PlayerControllerTest {
    private PlayerController playerController;
    private PlacementController placementController;
    private SelectionController selectionController;
    private GameModel gameModel;

    @BeforeEach
    void setUp() {
        gameModel = new GameModel(32);

        GameModel model = new GameModel(32);

        TileViewManager tileManager = new TileViewManager();
        TowerViewManager towerManager = new TowerViewManager(model.getTowerPrices());
        EnemyViewManager enemyManager = new EnemyViewManager();
        ProjectileViewManager projectileManager = new ProjectileViewManager();

        RenderingContext renderingContext = RenderingContextFactory.createRenderingContext(
                tileManager,
                towerManager,
                enemyManager,
                projectileManager
        );

        GamePanel gameView = new GamePanel(10, 10, renderingContext, towerManager);
        selectionController = new SelectionController(model, gameView.getBottomBar(),gameView.getGameViewPanel().getSCALE());
        placementController = new PlacementController(model,gameView.getGameViewPanel());
        playerController = new PlayerController(placementController, selectionController);

    }

    @Test
    void testSetPlacementController(){
        playerController.setMouseController(placementController);
        assertEquals(placementController, playerController.getCurrentMouseController());
    }

    @Test
    void testSetSelectionController(){
        playerController.setMouseController(selectionController);
        assertEquals(selectionController, playerController.getCurrentMouseController());
    }


}