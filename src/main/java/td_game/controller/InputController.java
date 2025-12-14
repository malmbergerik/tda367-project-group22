package td_game.controller;

import td_game.model.modelnit.GameModel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputController implements KeyListener {
    private GameModel model;

    public InputController(GameModel model){
        this.model = model;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
            model.togglePause();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
