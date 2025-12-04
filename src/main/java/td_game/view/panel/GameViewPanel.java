package td_game.view.panel;

import td_game.view.listener.IGameMouseListener;
import td_game.view.helper.*;
import td_game.view.render.RenderingContext;
import td_game.view.render.RenderingContextFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class GameViewPanel extends JPanel {
   private final RenderingContext renderingContext;



    public GameViewPanel(int width, int height, RenderingContext renderingContext) {
        this.renderingContext = renderingContext;

        setPreferredSize(new Dimension(width, height));
        setDoubleBuffered(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderingContext.renderAll((Graphics2D) g);
    }

    public void addGameMouseListener(MouseListener mouseListener) {
        this.addMouseListener(mouseListener);
    }

    public void addGameMouseMotionListener(MouseMotionListener mouseListener) {
        this.addMouseMotionListener(mouseListener);
    }

    public int getSCALE() {
        return renderingContext.getScale();
    }

    public RenderingContext getRenderingContext() {
        return renderingContext;
    }
}
