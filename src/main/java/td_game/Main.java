package td_game;

import td_game.controller.ViewController;
import td_game.model.map.*;
import td_game.model.modelnit.GameModel;
import td_game.view.WindowFrame;

import javax.swing.text.View;

public class Main {

    public static void main(String[] args) {
        GameModel gameModel = new GameModel(576,640,16);
        WindowFrame frame = new WindowFrame(1024, 768);
        frame.displayMenuView();
        ViewController gameController = new ViewController(frame);
    }
}

