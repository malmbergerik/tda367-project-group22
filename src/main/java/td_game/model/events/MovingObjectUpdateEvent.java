package td_game.model.events;


/**
 * Event indicating that moving objects (e.g., enemies) in the game have been updated.
 * Sent to observers so they can update the view accordingly.
 */
public class MovingObjectUpdateEvent implements IGameEvent {
    public void dispatch(IGameObserver observer){
        observer.onMovingObjectsUpdate(this);
    }
}
