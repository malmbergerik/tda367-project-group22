package td_game.model.events;


/**
 * Event indicating that the towers in the game have been updated.
 * Sent to observers so they can update the view or UI accordingly.
 */
public class TowersUpdateEvent implements IGameEvent {
    public void dispatch(IGameObserver observer){
        observer.onTowersUpdate(this);
    }
}
