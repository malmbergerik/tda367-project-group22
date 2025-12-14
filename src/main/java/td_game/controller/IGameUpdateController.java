package td_game.controller;
/**
 * Interface for controllers that update the game view and UI
 * based on changes in the game model.
 */

public interface IGameUpdateController {
    void handleTileUpdate();
    void handleMovingObjectsUpdate();
    void handleTowersUpdate();
    void handleProjectilesUpdate();
    void handleHealthUpdate();
    void handleMoneyUpdate();
    void handleWaveUpdate();

}
