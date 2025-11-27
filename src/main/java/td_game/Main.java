package td_game;

import td_game.model.map.*;
import td_game.model.modelnit.GameModel;
import td_game.controller.AppController;

public class Main {

    private static final int TILE_SIZE = 16;
    private static final int GAME_WIDTH = 720;
    private static final int GAME_HEIGHT = 576;
    private static final int WINDOW_WIDTH = 1024;;
    private static final int WINDOW_HEIGHT = 768;


    public static void main(String[] args) {
        GameModel gameModel = new GameModel(GAME_WIDTH, GAME_HEIGHT, TILE_SIZE);
        new AppController(gameModel, WINDOW_WIDTH, WINDOW_HEIGHT);
    }
}

