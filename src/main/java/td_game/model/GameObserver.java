package td_game.model;

public interface GameObserver {
    void update(GameEventType eventType);
    //void update();
}
