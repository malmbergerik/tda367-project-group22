package td_game.view;

import td_game.view.helper.MapViewData;
import td_game.view.helper.TileViewManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;


public class GameViewPanel extends JPanel {
    private IGameMouseListener mouseListener;
    private final TileViewManager tileViewManager;
    private MapViewData currentMapViewData;
    private final int SCALE = 3;
    private BufferedImage tileLayer;

    public GameViewPanel(int width, int height) {
        tileViewManager = new TileViewManager();
        setPreferredSize(new Dimension(width, height));
        this.setDoubleBuffered(true);

        initMouseHandler();

    }

    private void initMouseHandler(){
        MouseAdapter mouseHandler = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if(mouseListener != null) mouseListener.onMouseMoved(e.getX(),e.getY());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(mouseListener != null) mouseListener.onMouseClicked(e.getX(),e.getY());
            }
        };

        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    public int getSCALE(){return SCALE;}

    public void updateTiles(MapViewData mapViewData){
        this.currentMapViewData = mapViewData;
        drawTiles();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (tileLayer != null)
            g.drawImage(tileLayer, 0, 0, null);

        //TODO add enemy/projectile/towers drawing using g2 for smoothness

    }

    private void drawTiles() {
        if (currentMapViewData == null) return;

        String[][] tileKeys = currentMapViewData.getTileKeys();
        int tileSize = currentMapViewData.getTileSize();
        int rows = tileKeys.length;
        int cols = tileKeys[0].length;

        int width = cols * tileSize * SCALE;
        int height = rows * tileSize * SCALE;

        tileLayer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = tileLayer.createGraphics();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                String tileKey = tileKeys[row][col];
                // TileViewManager använder nu en String-nyckel
                BufferedImage tileImage = tileViewManager.getTileImage(tileKey);
                int screen_x = col * tileSize;
                int screen_y = row * tileSize;
                g2.drawImage(tileImage, screen_x * SCALE, screen_y * SCALE, tileSize * SCALE, tileSize * SCALE, null);
            }
        }

        g2.dispose();
    }

    public void setGameMouseListener(IGameMouseListener listener){
        this.mouseListener = listener;
    }

}
