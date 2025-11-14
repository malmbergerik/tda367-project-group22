package td_game.model;

public interface GameObserver {
    void onGameEvent(GameEventType eventType);
}
