package td_game.controller;

public interface IGameUpdateController {
    void handleTileUpdate();
    void handleMovingObjectsUpdate();
    void handleTowersUpdate();
    void handleProjectilesUpdate();

}
