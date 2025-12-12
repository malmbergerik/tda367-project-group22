package td_game.model.events;

public class PlayerMoneyUpdateEvent implements IGameEvent {
    public void dispatch(IGameObserver observer){
        observer.onPlayerMoneyUpdate(this);
    }
}