package td_game.controller;

import td_game.model.modelnit.GameModel;
import td_game.view.*;

public class AppController {
    public AppController(GameModel model, int width, int height) {

        ViewFactory viewFactory = new ViewFactory();
        WindowFrame windowFrame = new WindowFrame(width, height);

        IView menuView = viewFactory.createView("MENU_VIEW", width, height);
        IView gameView = viewFactory.createView("GAME_VIEW", model.getX(), model.getY());

        windowFrame.addView(menuView.getViewPanel(), "MENU_VIEW");
        windowFrame.addView(gameView.getViewPanel(), "GAME_VIEW");

        if (menuView instanceof MenuPanel menu) {
            menu.addPlayListener(e -> windowFrame.showView("GAME_VIEW"));
            menu.addExitListener(e -> System.exit(0));
        }

        if (gameView instanceof GamePanel game) {
            new GameController(model, game.getGameViewPanel());
        }

        windowFrame.showView("MENU_VIEW");
        windowFrame.makeVisible();

    }
}
