package td_game.model;

public interface IGameObserver {
    void onGameEvent(GameEventType eventType);
}
