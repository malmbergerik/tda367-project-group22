package td_game.controller;

import td_game.model.modelnit.GameModel;
import td_game.model.modelnit.IGameStateObserver;
import td_game.view.constants.ViewTypes;
import td_game.view.panel.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Observes the game state and updates the UI accordingly.
 * Displays overlays for pause, game won, and gameover-states.
 * Implements {@link IGameStateObserver}.
 */

public class StateController implements IGameStateObserver {
    private GameModel model;
    private WindowFrame window;


    public StateController(GameModel model, WindowFrame window){
        this.model = model;
        this.window = window;
    }

    private final ActionListener onMenuAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            window.hideOverlay();
            window.showView(ViewTypes.MENU_VIEW);
            model.resetGame();
        }
    };
    private final ActionListener onRestartAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            window.hideOverlay();
            window.showView(ViewTypes.MENU_VIEW);
            model.resetGame();
        }
    };
    private final ActionListener onContinueAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            window.hideOverlay();
            model.togglePause();

        }
    };

    /** Call for view to display game won overlay. */
    @Override
    public void onGameWon() {
        window.showOverlay(new GameWonPanel(onRestartAction, onMenuAction));
    }

    /** Call for view to display game over overlay. */
    @Override
    public void onGameLost() {
        window.showOverlay(new GameOverPanel(onRestartAction, onMenuAction));
    }

    /** Call for view to display game pause overlay. */
    @Override
    public void onGamePause() {
        window.showOverlay(new PausePanel(onContinueAction, onMenuAction));
    }

    /**Hides overlay when game is unpaused. */
    @Override
    public void onGameUnPause() {
        window.hideOverlay();
    }

}
