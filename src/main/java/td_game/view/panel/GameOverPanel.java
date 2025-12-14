package td_game.view.panel;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The game over panel that displays when the player loses the game.
 */

public class GameOverPanel extends ABaseOverlayPanel{
    public GameOverPanel(ActionListener onRestart, ActionListener onMenu) {
        addTitle("Game Over", Color.RED);
        addButton("Restart", onRestart);
        addButton("Exit To Menu", onMenu);
    }
}
