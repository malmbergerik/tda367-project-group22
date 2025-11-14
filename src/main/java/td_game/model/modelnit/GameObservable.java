package td_game.model.modelnit;

public interface GameObservable {
    void registerObserver(GameObserver observer);
    void unregisterObserver(GameObserver observer);
    void notifyObserver();
}
