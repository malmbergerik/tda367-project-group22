package td_game.view;

import td_game.model.map.GrassTile;
import td_game.model.map.GridMap;
import td_game.model.map.TileBase;
import td_game.model.modelnit.GameModel;

import javax.swing.*;

public class WindowFrame extends JFrame {
    private final int width = 1024;
    private final int height = 768;
    private GamePanel gamePanel;
    private GridMap model;

    public WindowFrame(GameModel model) {

        gamePanel =new GamePanel(model);
        JFrame frame = new JFrame();
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(gamePanel);


    }

}
