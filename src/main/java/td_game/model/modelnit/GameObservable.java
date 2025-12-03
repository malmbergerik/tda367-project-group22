package td_game.model.modelnit;

import td_game.model.events.IGameEvent;
import td_game.model.events.IGameObserver;

public interface GameObservable {
    void registerObserver(IGameObserver observer);
    void unregisterObserver(IGameObserver observer);
    void notifyObserver(IGameEvent event);
}
