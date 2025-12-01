package td_game.view;

import td_game.model.modelnit.GameModel;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements IView{
    private final GameViewPanel gameView;
    private final JPanel bottomBar; //This needs to be its own class later
    private final SideBarPanel sideBar;
    public GamePanel(int width, int height) {

        setLayout(new BorderLayout());

        gameView = new GameViewPanel(width, height);

        bottomBar = new JPanel(); // Replace with actual bottom bar implementation
        bottomBar.setPreferredSize(new Dimension(720, 192));
        bottomBar.setBackground(Color.BLUE);

        // Left container for game view and bottom bar
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(gameView, BorderLayout.CENTER);
        leftPanel.add(bottomBar, BorderLayout.SOUTH);

        sideBar = new SideBarPanel(304,768);

        add(leftPanel, BorderLayout.CENTER);
        add(sideBar, BorderLayout.EAST);
    }

    public GameViewPanel getGameViewPanel() {
        return gameView;
    }

    @Override
    public JPanel getViewPanel() {
        return this;
    }

    public SideBarPanel getSideBar(){
        return sideBar;
    }

    public void addSideBarListener(ITowerPlacementListener listener){
        sideBar.setListener(listener);
    }
}
