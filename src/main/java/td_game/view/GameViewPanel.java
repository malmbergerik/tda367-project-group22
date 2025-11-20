package td_game.view;
import td_game.model.GameEventType;
import td_game.model.GameObserver;
import td_game.model.map.GridMap;
import td_game.model.map.TileBase;
import td_game.model.modelnit.GameModel;
import td_game.view.helper.TileViewManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class GameViewPanel extends JPanel {
    private final TileViewManager tileViewManager;
    private GridMap currentGridMap;
    private final int SCALE = 3;

    public GameViewPanel(int width, int height) {
        tileViewManager = new TileViewManager();
        setPreferredSize(new Dimension(width, height));
        this.setDoubleBuffered(true);
    }

    public void updateTiles(GridMap gridMap) {
        this.currentGridMap = gridMap;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (currentGridMap != null) {
            drawTiles(g);
        }
    }

    private void drawTiles(Graphics g){
        for (int row = 0; row < currentGridMap.getRow(); row++) {
            for (int col = 0; col < currentGridMap.getCol(); col++) {
                TileBase currentTile = currentGridMap.getTile(row, col);
                BufferedImage tileImage = tileViewManager.getTileImage(currentTile);
                int tilesize = currentGridMap.getTileSize();
                int screen_x = col * tilesize;
                int screen_y = row * tilesize;
                g.drawImage(tileImage, screen_x * SCALE, screen_y * SCALE, tilesize * SCALE, tilesize * SCALE, null);
            }
        }
    }
}
