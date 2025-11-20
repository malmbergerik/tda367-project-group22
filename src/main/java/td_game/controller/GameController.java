package td_game.controller;

import td_game.model.GameEventType;
import td_game.model.modelnit.GameModel;
import td_game.view.GameViewPanel;
import td_game.model.GameObserver;

public class GameController implements GameObserver {
    private final GameModel model;
    private final GameViewPanel view;

    public GameController(GameModel model, GameViewPanel view) {
        this.model = model;
        this.view = view;
        this.model.registerObserver(this);

        this.view.updateTiles(this.model.getGridMap());
    }

    @Override
    public void update(GameEventType eventType) {
        if (eventType == GameEventType.TILES_UPDATE) {
            // Handle tile updates, (push data from model to view)
            view.updateTiles(model.getGridMap());
        }
    }
}
