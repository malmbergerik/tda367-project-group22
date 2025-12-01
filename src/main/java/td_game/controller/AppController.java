package td_game.controller;

import td_game.model.modelnit.GameModel;
import td_game.model.modelnit.IGameLoop;

import td_game.model.modelnit.PlayingState;
import td_game.view.*;
import td_game.view.constants.ViewTypes;
import td_game.view.factory.ViewFactory;
import td_game.view.panel.GamePanel;
import td_game.view.panel.MenuPanel;
import td_game.view.panel.WindowFrame;

public class AppController {
    public AppController(GameModel model, IGameLoop gameLoop,  int windowWidth, int windowHeight, int gameWidth, int gameHeight) {

        ViewFactory viewFactory = new ViewFactory();
        WindowFrame windowFrame = new WindowFrame(windowWidth, windowHeight);

        IView menuView = viewFactory.createView(ViewTypes.MENU_VIEW, windowWidth, windowHeight);
        IView gameView = viewFactory.createView(ViewTypes.GAME_VIEW, gameWidth, gameHeight);

        windowFrame.addView(menuView.getViewPanel(), ViewTypes.MENU_VIEW);
        windowFrame.addView(gameView.getViewPanel(), ViewTypes.GAME_VIEW);

        if (menuView instanceof MenuPanel menu) {
            menu.addPlayListener(e -> {
                windowFrame.showView(ViewTypes.GAME_VIEW);
                model.setGameState(new PlayingState(model));
                gameLoop.start();
            });
            menu.addExitListener(e -> System.exit(0));
        }

        if (gameView instanceof GamePanel game) {
            GameController gameController  = new GameController(model, game.getGameViewPanel());
            ((GamePanel) gameView).addSideBarListener(gameController.getPlacementController());

        }

        windowFrame.showView(ViewTypes.MENU_VIEW);
        windowFrame.makeVisible();

    }


}
