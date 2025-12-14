package td_game.model.events;

/**
 * Event indicating that the player's health has changed.
 * Sent to observers so they can update the view or UI accordingly.
 */
public class PlayerHealthUpdateEvent implements IGameEvent {
    public void dispatch(IGameObserver observer){
        observer.onPlayerHealthUpdate(this);
    }
}