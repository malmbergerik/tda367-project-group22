package td_game.view;

import java.awt.*;
import javax.swing.*;

public class WindowFrame extends JFrame {

    private MenuPanel menuView;
    //private LevelSelectPanel levelSelectView;
    private GamePanel gameView;
    //private PausePanel pauseView;
    //private GameOverPanel gameOverView;

    private static final int TILEWIDHT = 16;
    private static final int TILEHEIGHT = 16;
    private CardLayout cl;


    public WindowFrame(int width, int height) {
        super();
        Dimension size = new Dimension(width, height);
        this.setTitle("Dungeon Defence");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setSize(size);
        this.setVisible(true);

        this.menuView = new MenuPanel(width, height);
        //this.levelSelectView = new LevelSelectPanel(this);
        //this.gameView = new GamePanel(width, height, TILEWIDHT, TILEHEIGHT);
        //this.pauseView = new PausePanel(this);
        //this.gameOverView = new GameOverPanel(this);

    }

    private void addViewToFrame(JPanel view) {
        this.add(view);
        this.validate();
    }

    private void removeViewFromFrame(JPanel view) {
        this.remove(view);
        this.repaint();
    }

    public void displayMenuView() {
        //this.removeViewFromFrame(this.levelSelectView);
        //this.removeViewFromFrame(this.gameView);
        //this.removeViewFromFrame(this.pauseView);
        //this.removeViewFromFrame(this.gameOverView);
        this.addViewToFrame(this.menuView);
    }

    public void displayGameView() {
        this.removeViewFromFrame(this.menuView);
        //this.removeViewFromFrame(this.levelSelectView);
        //this.removeViewFromFrame(this.pauseView);
        //this.removeViewFromFrame(this.gameOverView);
        this.addViewToFrame(this.gameView);
    }

    public void displayPauseView() {
        this.removeViewFromFrame(this.menuView);
        //this.removeViewFromFrame(this.levelSelectView);
        this.removeViewFromFrame(this.gameView);
        //this.removeViewFromFrame(this.gameOverView);
        //this.addViewToFrame(this.pauseView);
    }

    public void displayGameOverView() {
        this.removeViewFromFrame(this.menuView);
        //this.removeViewFromFrame(this.levelSelectView);
        this.removeViewFromFrame(this.gameView);
        //this.removeViewFromFrame(this.pauseView);
        //this.addViewToFrame(this.gameOverView);
    }

    public void displayLevelSelectView() {
        this.removeViewFromFrame(this.menuView);
        this.removeViewFromFrame(this.gameView);
        //this.removeViewFromFrame(this.pauseView);
        //this.removeViewFromFrame(this.gameOverView);
        //this.addViewToFrame(this.levelSelectView);
    }

    public boolean getStartButtonPressed() {
        boolean pressed = this.menuView.getPlayButtonPressed();
        this.menuView.resetPlayButtonPressed();
        return pressed;
    }
}
