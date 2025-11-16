package td_game;

import td_game.model.map.*;
import td_game.model.modelnit.GameModel;
import td_game.view.WindowFrame;

public class Main {

    public static void main(String[] args) {
        GameModel gameModel = new GameModel(320,320,16);
        WindowFrame frame = new WindowFrame(gameModel);




    }
}

