package td_game.view;

import td_game.model.map.GrassTile;
import td_game.model.map.GridMap;
import td_game.model.map.TileBase;

import javax.swing.*;

public class WindowFrame extends JFrame {
    private final int width = 1024;
    private final int height = 768;
    private GamePanel gamePanel;
    private GridMap grid;

    public WindowFrame() {
        grid = new GridMap(12 ,16,16);
        TileBase GrassTile = new GrassTile(0, 0);
        grid.setTile(1, 1, GrassTile);
        gamePanel =new GamePanel(grid);
        JFrame frame = new JFrame();
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(gamePanel);


    }

}
