package td_game.model.events;


/**
 * Event indicating that the player's money has changed.
 * Sent to observers so they can update the view or UI accordingly.
 */
public class PlayerMoneyUpdateEvent implements IGameEvent {
    public void dispatch(IGameObserver observer){
        observer.onPlayerMoneyUpdate(this);
    }
}