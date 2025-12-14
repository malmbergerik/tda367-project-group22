package td_game.model.events;

/**
 * Observer interface for listening to game model updates.
 * Implementations update the view or UI when the model changes.
 */
public interface IGameObserver {
    /**
     * Called when tiles are updated in the game.
     * @param event the tile update event
     */
    void onTileUpdate(TileUpdateEvent event);
    /**
     * Called when moving objects (e.g, enemies) are updated in the game.
     * @param event the moving object update event
     */
    void onMovingObjectsUpdate(MovingObjectUpdateEvent event);
    /**
     * Called when towers are updated in the game.
     * @param event the towers update event
     */
    void onTowersUpdate(TowersUpdateEvent event);
    /**
     * Called when projectiles are updated in the game.
     * @param event the projectile update event
     */
    void onProjectileUpdate(ProjectileUpdateEvent event);
    /**
     * Called when players health is updated.
     * @param event the player health update event
     */
    void onPlayerHealthUpdate(PlayerHealthUpdateEvent event);
    /**
     * Called when players money is updated.
     * @param event the player money update event
     */
    void onPlayerMoneyUpdate(PlayerMoneyUpdateEvent event);
    /**
     * Called when wave information is updated.
     * @param event the wave update event
     */
    void onWaveUpdate(WaveUpdateEvent event);
}
