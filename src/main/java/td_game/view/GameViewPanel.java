package td_game.view;

import td_game.view.helper.*;
import td_game.view.render.RenderingContext;
import td_game.view.render.RenderingContextFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class GameViewPanel extends JPanel {
   private IGameMouseListener mouseListener;
   private final RenderingContext renderingContext;


    public GameViewPanel(int width, int height, RenderingContext renderingContext) {
        this.renderingContext = renderingContext; // FIX: Use injected context

        setPreferredSize(new Dimension(width, height));
        this.setDoubleBuffered(true);

        initMouseHandler();

    }

    // Contrucor for backwards compatibility
    public GameViewPanel(int width, int height) {
        this(width, height, RenderingContextFactory.createRenderingContext());
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

    @Override
    protected void paintComponent(Graphics g) {
        renderingContext.renderAll((Graphics2D) g);
    }

    public void updateTowers(TowerViewData towers) {
        renderingContext.updateTowerViewData(towers);
        repaint();
    }

    public void updateSelectedTowers(int row, int col, Boolean placeable, String selectedTower) {
        renderingContext.updateSelectedTower(row, col, placeable, selectedTower);
        repaint();
    }

    public void updateMovingObjects(List<EnemyViewData> enemies) {
        renderingContext.updateEnemyViewData(enemies);
        repaint();
    }

    public void updateTiles(MapViewData mapViewData) {
        renderingContext.updateMapViewData(mapViewData);
        // Invalidate tile cache if TileRenderer supports it
        repaint();
    }

    public int getSCALE() {
        return renderingContext.getScale();
    }

    public RenderingContext getRenderingContext() {
        return renderingContext;
    }

    public void setGameMouseListener(IGameMouseListener listener) {
        this.mouseListener = listener;
    }

}
