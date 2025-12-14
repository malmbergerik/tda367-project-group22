package td_game.model.events;


/**
 * Represents a game event that can be dispatched to an observer.
 */
public interface IGameEvent {
    /**
     * Dispatches this event to the given observer.
     * @param observer the game observer to notify
     */
    void dispatch(IGameObserver observer);
}
