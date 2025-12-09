package td_game.controller;

import td_game.model.modelnit.*;

import td_game.view.constants.ViewTypes;
import td_game.view.helper.*;
import td_game.view.panel.GamePanel;
import td_game.view.panel.MenuPanel;
import td_game.view.panel.WindowFrame;
import td_game.view.render.RenderingContext;
import td_game.view.render.RenderingContextFactory;

public class AppController {

    public AppController(GameModel model, IGameLoop gameLoop,  int windowWidth, int windowHeight, int gameWidth, int gameHeight) {

        TileViewManager tileManager = new TileViewManager();
        TowerViewManager towerManager = new TowerViewManager();
        EnemyViewManager enemyManager = new EnemyViewManager();
        ProjectileViewManager projectileManager = new ProjectileViewManager();

        RenderingContext renderingContext = RenderingContextFactory.createRenderingContext(
                tileManager,
                towerManager,
                enemyManager,
                projectileManager
        );

        WindowFrame windowFrame = new WindowFrame(windowWidth, windowHeight);
        MenuPanel menuView = new MenuPanel(windowWidth, windowHeight);
        GamePanel gameView = new GamePanel(gameWidth, gameHeight, renderingContext, towerManager);


        windowFrame.addView(menuView, ViewTypes.MENU_VIEW);
        windowFrame.addView(gameView, ViewTypes.GAME_VIEW);

        menuView.addPlayListener(e -> {
            windowFrame.showView(ViewTypes.GAME_VIEW);
            model.setGameState(new PlayingState(model));
            gameView.setFocusable(true);
            gameView.requestFocusInWindow();
            gameLoop.start();
        });

        menuView.addExitListener(e -> System.exit(0));

        GameController gameController = new GameController(model, gameView.getGameViewPanel(), gameView.getSideBar());
        gameView.addSideBarListener(gameController.getPlacementController());
        gameView.addKeyListener(gameController.getInputController());

        windowFrame.showView(ViewTypes.MENU_VIEW);
        windowFrame.makeVisible();


    }


}
