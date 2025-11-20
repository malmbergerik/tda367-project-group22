package td_game.controller;

import td_game.view.WindowFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewController {

    private WindowFrame view;

    public ViewController(WindowFrame view) {
        this.view = view;
        initController();
    }

    private void initController() {
        if (view.getStartButtonPressed()) {
            view.displayGameView();
        }

    }
}

