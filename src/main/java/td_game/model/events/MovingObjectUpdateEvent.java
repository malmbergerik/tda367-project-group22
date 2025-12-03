package td_game.model.events;

public class MovingObjectUpdateEvent implements IGameEvent {
    public void dispatch(IGameObserver observer){
        observer.onMovingObjectsUpdate(this);
    }
}
