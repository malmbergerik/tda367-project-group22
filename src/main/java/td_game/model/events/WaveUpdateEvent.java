package td_game.model.events;

public class WaveUpdateEvent implements IGameEvent{
    public void dispatch(IGameObserver observer){
        observer.onWaveUpdate(this);
    }
}
