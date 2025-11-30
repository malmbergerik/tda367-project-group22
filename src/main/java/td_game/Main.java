package td_game;

import td_game.model.map.*;
import td_game.model.modelnit.GameLoop;
import td_game.model.modelnit.GameModel;
import td_game.controller.AppController;
import td_game.model.modelnit.IGameLoop;

public class Main {

    private static final int TILE_SIZE = 16;
    private static final int GAME_WIDTH = 720;
    private static final int GAME_HEIGHT = 576;
    private static final int WINDOW_WIDTH = 1024;;
    private static final int WINDOW_HEIGHT = 768;


    public static void main(String[] args) {
        GameModel gameModel = new GameModel(TILE_SIZE);
        IGameLoop gameLoop = new GameLoop(gameModel);
        new AppController(gameModel,gameLoop, WINDOW_WIDTH, WINDOW_HEIGHT, GAME_WIDTH, GAME_HEIGHT);
        gameModel.update();



    }
}

