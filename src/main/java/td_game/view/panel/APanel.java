package td_game.view.panel;
import javax.swing.JPanel;
import java.awt.Dimension;

public class APanel extends JPanel {

    protected APanel(int width, int height) {
        super();
        Dimension size = new Dimension(width, height);
        this.setPreferredSize(size);
    }

}
