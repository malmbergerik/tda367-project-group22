package td_game.view;

import td_game.model.modelnit.GameModel;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements IView{
    private final GameViewPanel gameView;
    private final JPanel sideBar; //This needs to be its own class later
    private final JPanel bottomBar; //This needs to be its own class later

    public GamePanel(GameModel model) {

        setLayout(new BorderLayout());

        gameView = new GameViewPanel(model);
        gameView.setPreferredSize(new Dimension(720, 576));

        bottomBar = new JPanel(); // Replace with actual bottom bar implementation
        bottomBar.setPreferredSize(new Dimension(720, 192));
        bottomBar.setBackground(Color.BLUE);

        // Left container for game view and bottom bar
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(gameView, BorderLayout.CENTER);
        leftPanel.add(bottomBar, BorderLayout.SOUTH);

        sideBar = new JPanel(); // Replace with actual side bar implementation
        sideBar.setPreferredSize(new Dimension(304, 768));
        sideBar.setBackground(Color.RED);

        add(leftPanel, BorderLayout.CENTER);
        add(sideBar, BorderLayout.EAST);
    }

    @Override
    public JPanel getViewPanel() {
        return this;
    }
}
