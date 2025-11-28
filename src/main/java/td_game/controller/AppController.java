package td_game.controller;

import td_game.model.modelnit.GameModel;
import td_game.model.modelnit.IGameLoop;

import td_game.model.modelnit.PlayingState;
import td_game.view.*;

public class AppController {
    public AppController(GameModel model, IGameLoop gameLoop,  int windowWidth, int windowHeight, int gameWidth, int gameHeight) {

        ViewFactory viewFactory = new ViewFactory();
        WindowFrame windowFrame = new WindowFrame(windowWidth, windowHeight);

        IView menuView = viewFactory.createView("MENU_VIEW", windowWidth, windowHeight);
        IView gameView = viewFactory.createView("GAME_VIEW", gameWidth, gameHeight);

        windowFrame.addView(menuView.getViewPanel(), "MENU_VIEW");
        windowFrame.addView(gameView.getViewPanel(), "GAME_VIEW");

        if (menuView instanceof MenuPanel menu) {
            menu.addPlayListener(e -> {
                windowFrame.showView("GAME_VIEW");
                model.setGameState(new PlayingState(model));
                gameLoop.start();
            });
            menu.addExitListener(e -> System.exit(0));
        }

        if (gameView instanceof GamePanel game) {
            new GameController(model, game.getGameViewPanel());
        }

        windowFrame.showView("MENU_VIEW");
        windowFrame.makeVisible();

    }


}
