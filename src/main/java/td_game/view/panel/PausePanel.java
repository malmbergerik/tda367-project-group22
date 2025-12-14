package td_game.view.panel;

import java.awt.Color;
import java.awt.event.ActionListener;

/**
 * The pause panel that displays when the game is paused.
 */

public class PausePanel extends ABaseOverlayPanel {
    public PausePanel(ActionListener onContinue, ActionListener onMenu) {
        addTitle("PAUSED", Color.WHITE);
        addButton("Continue", onContinue);
        addButton("Exit to Menu", onMenu);
    }
}
