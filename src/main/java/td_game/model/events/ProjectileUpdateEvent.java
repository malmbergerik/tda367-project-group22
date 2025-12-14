package td_game.model.events;

/**
 * Event indicating that projectiles in the game have been updated.
 * Sent to observers so they can update the view or UI accordingly.
 */
public class ProjectileUpdateEvent implements IGameEvent {
    public void dispatch(IGameObserver observer){
        observer.onProjectileUpdate(this);
    }
}
