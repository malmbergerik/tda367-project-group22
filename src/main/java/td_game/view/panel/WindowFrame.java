package td_game.view.panel;

import java.awt.*;
import javax.swing.*;


/**
 * The main window frame that contains different views and overlays.
 */
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

    /**
     * Adds a new view panel to the window frame.
     *
     * @param viewPanel The panel representing the view.
     * @param viewName  The name identifier for the view.
     */
    public void addView(JPanel viewPanel, String viewName) {
        mainPanel.add(viewPanel, viewName);
        this.pack();
        this.setLocationRelativeTo(null);

    }

    /**
     * Shows the specified view panel.
     *
     * @param view The name identifier of the view to show.
     */
    public void showView(String view) {
        cardLayout.show(mainPanel, view);
    }

    /**
     * Makes the window frame visible.
     */
    public void makeVisible() {
        this.setVisible(true);
    }

    /**
     * Displays an overlay panel on top of the current view.
     *
     * @param overlayPanel The panel to be displayed as an overlay.
     */
    public void showOverlay(JPanel overlayPanel) {
        this.setGlassPane(overlayPanel);
        overlayPanel.setVisible(true);
        overlayPanel.revalidate();
        overlayPanel.repaint();
    }

    /**
     * Hides the currently displayed overlay panel.
     */
    public void hideOverlay() {
        this.getGlassPane().setVisible(false);
    }

}
