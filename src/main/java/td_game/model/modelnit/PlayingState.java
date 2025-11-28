package td_game.model.modelnit;

public class PlayingState implements IGameState {
    private final GameModel gameModel;

    public PlayingState(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void enterState() {
        // Code to initialize the playing state
    }

    @Override
    public void exitState() {
        // Code to clean up the playing state
    }

    @Override
    public void update() {
        gameModel.updateEnemies();
        gameModel.updateProjectiles();
        gameModel.checkCollisions();

        if (gameModel.getLives() <= 0) {
            gameModel.changeState(new GameOverState(gameModel));
        }

        if (gameModel.isWaveComplete()) {
            gameModel.startNextWave();
        }
    }
}
