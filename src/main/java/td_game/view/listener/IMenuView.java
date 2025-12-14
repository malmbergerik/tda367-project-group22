package td_game.view.listener;

/**
 * An interface for listening to menu view events.
 */
public interface IMenuView {

    void addPlayListener(java.awt.event.ActionListener listener);
    void addExitListener(java.awt.event.ActionListener listener);
}
