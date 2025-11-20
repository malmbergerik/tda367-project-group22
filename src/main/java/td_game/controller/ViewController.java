package td_game.controller;

import td_game.view.WindowFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController {

    private WindowFrame view;

    public MenuController(WindowFrame view) {
        this.view = view;
        initController();
    }

    private void initController() {
        view.getMenuPanel().addPlayButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.displayGameView();
            }
        });

    }
}

