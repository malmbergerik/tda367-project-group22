package td_game.view;
import td_game.model.map.GridMap;
import td_game.model.map.TileBase;
import td_game.view.helper.TileViewManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {
    private GridMap gridMap;
    private TileViewManager tileViewManager;
    private int SCALE = 3;

    public GamePanel(GridMap gridMap) {
        this.gridMap = gridMap;
        tileViewManager = new TileViewManager();

        int width = gridMap.getWidth();
        int height = gridMap.getHeight();

        this.setSize(width * SCALE, height * SCALE);
        this.setDoubleBuffered(true);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int row = 0; row < gridMap.getRow(); ++row) {
            for (int col = 0; col < gridMap.getCol(); ++col) {
                TileBase currentTile = gridMap.getTile(row,col);
                BufferedImage image = tileViewManager.getTileImage(currentTile);

                int screen_x = col*16;
                int screen_y = row*16;

                g.drawImage(image, screen_x*SCALE, screen_y*SCALE, 16*SCALE, 16*SCALE, null);




            }
        }

        g.dispose();
    }



}
