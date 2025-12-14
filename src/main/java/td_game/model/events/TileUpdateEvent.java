package td_game.model.events;

/**
 * Event indicating that the game tiles have been updated.
 * Sent to observers so they can update the view or UI accordingly.
 */
public class TileUpdateEvent implements IGameEvent {
    public void dispatch(IGameObserver observer){
        observer.onTileUpdate(this);
    }
}
