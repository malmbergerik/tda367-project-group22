package td_game.controller;

public interface IPlacementController {
    void handleMouseMoved(int x, int y);
    void handleMouseClicked(int x, int y);
    void handleMouseDragged(int x, int y);
}
