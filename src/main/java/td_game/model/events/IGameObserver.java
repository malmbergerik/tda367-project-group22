package td_game.model.events;

public interface IGameObserver {
    void onTileUpdate(TileUpdateEvent event);
    void onMovingObjectsUpdate(MovingObjectUpdateEvent event);
    void onTowersUpdate(TowersUpdateEvent event);
    void onProjectileUpdate(ProjectileUpdateEvent event);
    void onPlayerHealthUpdate(PlayerHealthUpdateEvent event);
    void onPlayerMoneyUpdate(PlayerMoneyUpdateEvent event);
    void onWaveUpdate(WaveUpdateEvent event);
}
