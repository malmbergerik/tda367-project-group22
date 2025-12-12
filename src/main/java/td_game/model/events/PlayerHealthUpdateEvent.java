package td_game.model.events;

public class PlayerHealthUpdateEvent implements IGameEvent {
    public void dispatch(IGameObserver observer){
        observer.onPlayerHealthUpdate(this);
    }
}