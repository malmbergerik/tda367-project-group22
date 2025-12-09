package td_game.view.panel;

import java.awt.*;
import javax.swing.*;

public class WindowFrame extends JFrame {

    private final CardLayout cardLayout;
    private final JPanel mainPanel;

    public WindowFrame(int width, int height) {
        super("Dungeon Defence");

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.add(mainPanel);

    }

    public void addView(JPanel viewPanel, String viewName) {
        mainPanel.add(viewPanel, viewName);
        this.pack();
        this.setLocationRelativeTo(null);

    }

    public void showView(String view) {
        cardLayout.show(mainPanel, view);
    }

    public void makeVisible() {
        this.setVisible(true);
    }

    public void showOverlay(JPanel overlayPanel) {
        this.setGlassPane(overlayPanel);
        overlayPanel.setVisible(true);
        overlayPanel.revalidate();
        overlayPanel.repaint();
    }

    public void hideOverlay() {
        this.getGlassPane().setVisible(false);
    }

}
