package td_game.view;

import td_game.model.towers.Tower;
import td_game.view.helper.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;



public class GameViewPanel extends JPanel {
    private IGameMouseListener mouseListener;
    private final TileViewManager tileViewManager;
    private final TowerViewManager towerViewManager;
    private final EnemyViewManager enemyViewManager;
    private MapViewData currentMapViewData;
    private final int SCALE = 3;
    private BufferedImage tileLayer;
    private TowerViewData activeTowers;
    private List<EnemyViewData> activeEnemies;
    private int hoverRow = -1;
    private int hoverCol = -1;
    private String selectedTower;
    private Boolean placable;

    public GameViewPanel(int width, int height) {
        tileViewManager = new TileViewManager();
        towerViewManager = new TowerViewManager();
        enemyViewManager = new EnemyViewManager();
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

            @Override
            public void mouseExited(MouseEvent e) {
                if(mouseListener != null) mouseListener.onMouseExit();
            }
        };

        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    public int getSCALE(){return SCALE;}

    public void updateTowers(TowerViewData towers){
        this.activeTowers = towers;
        repaint();
    }

    public void updateSelectedTowers(int row, int col, Boolean bool,String selectedTower){
        this.hoverRow = row;
        this.hoverCol = col;
        this.selectedTower = selectedTower;
        this.placable = bool;
        repaint();
    }

    public void updateMovingObjects(List<EnemyViewData> enemies){
        this.activeEnemies = enemies;
        repaint();
    }
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

        drawTowers(g2);
        drawSelectedTower(g2);
        drawEnemies(g2);
        //TODO add enemy/projectile/towers drawing using g2 for smoothness
    }

    private void drawTowers(Graphics2D g2){
        if(activeTowers == null) return;
        String[][] towerKeys = activeTowers.getTowerKeys();
        int rows = towerKeys.length;
        int cols = towerKeys[0].length;

        int tileSize = currentMapViewData.getTileSize();

        int width = cols * tileSize * SCALE;
        int height = rows * tileSize * SCALE;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                String towerKey = towerKeys[row][col];
                if (towerKey == null) continue; // skip empty cells
                BufferedImage towerImage = towerViewManager.getTowerImage(towerKey);
                int screen_x = col * tileSize;
                int screen_y = row * tileSize;
                g2.drawImage(towerImage, screen_x * SCALE, screen_y * SCALE, tileSize * SCALE, tileSize * SCALE, null);
            }
        }
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
                // TileViewManager uses string key
                BufferedImage tileImage = tileViewManager.getTileImage(tileKey);
                int screen_x = col * tileSize;
                int screen_y = row * tileSize;
                g2.drawImage(tileImage, screen_x * SCALE, screen_y * SCALE, tileSize * SCALE, tileSize * SCALE, null);
            }
        }

        g2.dispose();
    }

    private void drawSelectedTower(Graphics2D g2) {
        if (selectedTower != null && hoverRow >= 0 && hoverCol >= 0) {
            BufferedImage towerImage = towerViewManager.getTowerImage(selectedTower);
            if(towerImage != null) {
                int tileSize = currentMapViewData.getTileSize();
                int screen_x = hoverCol * tileSize * SCALE;
                int screen_y = hoverRow * tileSize * SCALE;

                AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
                g2.setComposite(ac);
                g2.drawImage(towerImage,screen_x,screen_y,tileSize*SCALE,tileSize*SCALE,null);

                if(placable){
                    g2.setColor(new Color(255,0,0,128));
                    g2.fillRect(screen_x,screen_y,tileSize*SCALE,tileSize*SCALE);
                }

                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));

            }
        }
    }

    private void drawEnemies(Graphics2D g2) {
        if (activeEnemies == null) return;

        for (EnemyViewData enemy: activeEnemies) {
            String name = enemy.getEnemyName();
            double x = enemy.getEnemyX() * SCALE;
            double y = enemy.getEnemyY() * SCALE;

            BufferedImage image = enemyViewManager.getEnemyImage("Slime");

            g2.drawImage(image,(int) x  - 24 ,(int) y - 24 , 48, 48, null);

        }
    }

    public void setGameMouseListener(IGameMouseListener listener){
        this.mouseListener = listener;
    }

}
