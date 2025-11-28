package td_game.model.modelnit;

import td_game.model.GameEventType;
import td_game.model.IGameObserver;

public interface GameObservable {
    void registerObserver(IGameObserver observer);
    void unregisterObserver(IGameObserver observer);
    void notifyObserver(GameEventType eventType);
}
