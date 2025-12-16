package td_game.view.panel;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The game won panel that displays when the player wins the game.
 */

public class GameWonPanel extends ABaseOverlayPanel{
    public GameWonPanel(ActionListener onRestart, ActionListener onMenu) {
        addTitle("VICTORY!", Color.GREEN);
        addButton("Play Again", onRestart);
        addButton("Exit to Menu", onMenu);
    }
}
