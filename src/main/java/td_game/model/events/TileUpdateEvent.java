package td_game.model.events;

public class TileUpdateEvent implements IGameEvent {
    public void dispatch(IGameObserver observer){
        observer.onTileUpdate(this);
    }
}
