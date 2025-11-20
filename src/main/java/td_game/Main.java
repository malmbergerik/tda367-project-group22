 package td_game;

import td_game.controller.MenuController;
import td_game.model.map.*;
import td_game.model.modelnit.GameModel;
import td_game.view.WindowFrame;

public class Main {

    public static void main(String[] args) {
        GameModel gameModel = new GameModel(576,640,16);
        WindowFrame frame = new WindowFrame(gameModel);
        MenuController gameController = new MenuController(frame);

    }
}

