package td_game.view.listener;

/**
 * An interface for listening to mouse events in the game view.
 */
public interface IGameMouseListener {
    void onMouseMoved(int posX, int posY);
    void onMouseClicked(int posX, int posY);
    void onMouseExit();
}
