package td_game.model.modelnit;

import td_game.model.events.TileUpdateEvent;

public class PlayingState implements IGameState {
    private final GameModel gameModel;

    public PlayingState(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void enterState() {
        gameModel.notifyObserver(new TileUpdateEvent());

        // Code to initialize the playing state
    }

    @Override
    public void exitState() {
        // Code to clean up the playing state
    }

    @Override
    public void update() {
        gameModel.updateEnemies();
        gameModel.updateProjectile();
        gameModel.updateTower();

        /*
        if (gameModel.getLives() <= 0) {
            gameModel.changeState(new GameOverState(gameModel));
        }

        if (gameModel.isWaveComplete()) {
            gameModel.startNextWave();
        }
        */

    }


}
