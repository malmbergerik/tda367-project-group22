package td_game.model.modelnit;

public interface IGameStateObserver {
    void onGameWon();
    void onGameLost();
    void onGamePause();
    void onGameUnPause();
}
