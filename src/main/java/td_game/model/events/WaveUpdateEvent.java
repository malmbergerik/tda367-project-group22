package td_game.model.events;

/**
 * Event indicating that the current wave information has been updated.
 * Sent to observers so they can update the view or UI accordingly.
 */
public class WaveUpdateEvent implements IGameEvent{
    public void dispatch(IGameObserver observer){
        observer.onWaveUpdate(this);
    }
}
