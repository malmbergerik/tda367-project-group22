package td_game.view;

import td_game.model.map.GridMap;
import td_game.model.modelnit.GameModel;

import javax.swing.*;
import java.awt.*;

public class WindowFrame extends JFrame {
    private final int width = 1024;
    private final int height = 768;
    private GamePanel gamePanel;
    private MenuPanel menuPanel;
    private CardLayout cl;


    public WindowFrame(GameModel model) {

        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cl = new CardLayout();
        setLayout(cl);

        gamePanel = new GamePanel(model);
        menuPanel = new MenuPanel();

        add(gamePanel, "Game");
        add(menuPanel, "Menu");

        setVisible(true);
        pack();
        showPanel("Menu");

    }

    public MenuPanel getMenuPanel() {
        return menuPanel;
    }

    protected GamePanel getGamePanel(){
        return gamePanel;
    }

    public void showPanel(String panelName) {
        cl.show(getContentPane(), panelName);

    }



}
