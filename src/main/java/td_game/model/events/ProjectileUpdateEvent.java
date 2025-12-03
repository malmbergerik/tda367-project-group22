package td_game.model.events;

public class ProjectileUpdateEvent implements IGameEvent {
    public void dispatch(IGameObserver observer){
        observer.onProjectileUpdate(this);
    }
}
