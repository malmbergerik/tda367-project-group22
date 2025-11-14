package td_game.model.modelnit;

import td_game.model.GameObserver;

public interface GameObservable {
    void registerObserver(GameObserver observer);
    void unregisterObserver(GameObserver observer);
    void notifyObserver();
}
