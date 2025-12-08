package td_game.view.panel;

import td_game.view.helper.TowerViewManager;

import javax.swing.*;
import java.awt.*;

public class SideBarPanel extends JPanel {

    private StatsPanel statsPanel;
    private TowerPanel towerPanel;
    private TowerViewManager towerViewManager;
    private GameSpeedPanel gameSpeedPanel;

    public SideBarPanel(int width, int height, TowerViewManager towerViewManager){
        setPreferredSize(new Dimension(width, height));
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));


        this.statsPanel = new StatsPanel(width,144, 100); // TODO: Fixed 100 value, fix later
        this.towerPanel = new TowerPanel(width,364,towerViewManager);
        this.gameSpeedPanel = new GameSpeedPanel(width, 192);

        add(statsPanel);
        add(towerPanel);
        add(gameSpeedPanel);
    }

    public StatsPanel getStatsPanel() {
        return statsPanel;
    }

    public TowerPanel getTowerPanel() {
        return towerPanel;
    }

    public GameSpeedPanel getGameSpeedPanel() {
        return gameSpeedPanel;
    }
}
