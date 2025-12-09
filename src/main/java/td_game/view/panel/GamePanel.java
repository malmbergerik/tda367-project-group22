package td_game.view.panel;

import td_game.view.helper.TowerViewManager;
import td_game.view.listener.ITowerPlacementListener;
import td_game.view.IView;
import td_game.view.render.RenderingContext;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements IView {
    private final GameViewPanel gameView;
    private final SideBarPanel sideBar;

    public GamePanel(int width, int height, RenderingContext renderingContext, TowerViewManager towerManager) {
        setLayout(new BorderLayout());
        gameView = new GameViewPanel(width, height, renderingContext);

        JPanel bottomBar = new JPanel(); // Replace with actual bottom bar implementation
        bottomBar.setPreferredSize(new Dimension(720, 192));
        bottomBar.setBackground(Color.decode("#222222"));

        // Left container for game view and bottom bar
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(gameView, BorderLayout.CENTER);
        leftPanel.add(bottomBar, BorderLayout.SOUTH);

        sideBar = new SideBarPanel(304, 768, towerManager);

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
        //SideBarPanel sideBar = getSideBar();

        sideBar.getTowerPanel().setListener(listener);
    }
}
