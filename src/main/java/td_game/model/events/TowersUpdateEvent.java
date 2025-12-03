package td_game.model.events;

public class TowersUpdateEvent implements IGameEvent {
    public void dispatch(IGameObserver observer){
        observer.onTowersUpdate(this);
    }
}
