package td_game.view;

public interface IGameMouseListener {
    void onMouseMoved(int posX, int posY);
    void onMouseClicked(int posX, int posY);
    void onMouseExit();
}
