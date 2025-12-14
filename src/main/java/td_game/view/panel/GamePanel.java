package td_game.view.panel;

import td_game.view.helper.TowerViewManager;
import td_game.view.listener.ITowerActionListener;
import td_game.view.listener.ITowerPlacementListener;
import td_game.view.IView;
import td_game.view.render.RenderingContext;

import javax.swing.*;
import java.awt.*;

/**
 * The main game panel that contains the game view, side bar, and bottom bar.
 */

public class GamePanel extends JPanel implements IView {
    private final GameViewPanel gameView;
    private final SideBarPanel sideBar;
    private final BottomBarPanel bottomBar;

    public GamePanel(int width, int height, RenderingContext renderingContext, TowerViewManager towerManager) {
        setLayout(new BorderLayout());
        gameView = new GameViewPanel(width, height, renderingContext);

        bottomBar = new BottomBarPanel(720,192, towerManager);
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

    public BottomBarPanel getBottomBar(){
        return bottomBar;
    }

    public void addSideBarListener(ITowerPlacementListener listener){
        sideBar.getTowerPanel().setListener(listener);
    }

    public void addBottomBarListener(ITowerActionListener listener){
        bottomBar.setListener(listener);
    }
}
