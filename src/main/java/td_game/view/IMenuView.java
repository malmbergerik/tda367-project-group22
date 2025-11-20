package td_game.view;
import javax.swing.JPanel;

public interface IMenuView {

    JPanel getViewPanel();
    void addPlayListener(java.awt.event.ActionListener listener);
    void addExitListener(java.awt.event.ActionListener listener);
}
