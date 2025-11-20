package td_game.view;

import td_game.model.modelnit.GameModel;

import java.awt.*;
import javax.swing.*;

public class WindowFrame extends JFrame {

    private final CardLayout cardLayout;
    private final JPanel mainPanel;

    private static final String MENU_VIEW = "MenuView";
    private static final String GAME_VIEW = "GameView";

    private static final int TILEWIDHT = 16;
    private static final int TILEHEIGHT = 16;

    public WindowFrame(int width, int height, GameModel model) {
        super("Dungeon Defence");

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        IMenuView menuView = new MenuPanel(width, height);
        IView gameView = new GamePanel(model);

        mainPanel.add(menuView.getViewPanel(), MENU_VIEW);
        mainPanel.add(gameView.getViewPanel(), GAME_VIEW);

        menuView.addPlayListener(e -> showView(GAME_VIEW));
        menuView.addExitListener(e -> System.exit(0));


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(mainPanel);
        this.pack(); // Sets size based on subcomponents
        this.setLocationRelativeTo(null);

        this.setVisible(true);
    }

    public void showView(String view) {
        cardLayout.show(mainPanel, view);
    }
}
