package td_game.controller;

/**
 * Interface for handling mouse events in the game.
 */
public interface IMouseController {
    void handleMouseMoved(int x, int y);
    void handleMouseClicked(int x, int y);
    void handleMouseExit();
}
