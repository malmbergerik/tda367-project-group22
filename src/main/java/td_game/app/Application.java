package td_game.app;

import td_game.controller.MenuController;
import td_game.model.modelnit.GameModel;
import td_game.view.WindowFrame;

import javax.swing.*;

public class Application {

    public static void main(String [] args){
        SwingUtilities.invokeLater(Application::startGame);
    }

    private static void startGame() {
        GameModel gameModel = new GameModel(576, 640,16);
        WindowFrame view = new WindowFrame(gameModel);
        MenuController gameController = new MenuController(view);

    }
}
