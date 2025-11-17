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

public class GameViewPanel extends JPanel implements GameObserver{
    private GameModel gameModel;
    private final TileViewManager tileViewManager;
    private final int SCALE = 3;

    private final Queue<Consumer<Graphics>> drawNextQueue = new LinkedList<>();


    public GameViewPanel(GameModel gameModel) {
        this.gameModel = gameModel;
        gameModel.registerObserver(this);

        tileViewManager = new TileViewManager();

        int width = gameModel.getX();
        int height = gameModel.getY();


        this.setSize(width * SCALE, height * SCALE);
        this.setDoubleBuffered(true);

        drawNextQueue.add(this::drawTiles);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        while(!drawNextQueue.isEmpty()){
            Consumer<Graphics> task = drawNextQueue.poll();
            task.accept(g);
        }


        //g.dispose();
    }

    @Override
    public void update(GameEventType eventType) {
        switch (eventType){


            case MOVING_OBJECTS_UPDATE -> {
                //Redraw projectiles and enemies
            }
            case TOWER_UPDATE -> {
                //Redraw towers
            }
            case TILES_UPDATE -> {
                drawNextQueue.add(this::drawTiles);
                repaint();
            }

        }
    }

    public void update(){
        drawNextQueue.add(this::drawTiles);
        repaint();
    }

    private void drawTiles(Graphics g){
        GridMap gridMap = gameModel.getGridMap();

        for (int row = 0; row < gridMap.getRow(); ++row) {
            for (int col = 0; col < gridMap.getCol(); ++col) {
                TileBase currentTile = gridMap.getTile(row,col);
                BufferedImage image = tileViewManager.getTileImage(currentTile);

                int tilesize = gridMap.getTileSize();

                int screen_x = col*tilesize;
                int screen_y = row*tilesize;

                g.drawImage(image, screen_x*SCALE, screen_y*SCALE, tilesize*SCALE, tilesize*SCALE, null);

            }
        }
    }
}
