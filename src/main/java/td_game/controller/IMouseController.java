package td_game.controller;

public interface IMouseController {
    void handleMouseMoved(int x, int y);
    void handleMouseClicked(int x, int y);
    void handleMouseExit();

}
