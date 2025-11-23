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
        switch (eventType) {
            case TILES_UPDATE -> handleTileUpdate();
            case MOVING_OBJECTS_UPDATE -> handleMovingObjectsUpdate();
            case TOWER_UPDATE -> handleTowerUpdate();
        }
    }

    private void handleTileUpdate(){
        view.updateTiles(model.getGridMap());
    }

    private void handleMovingObjectsUpdate(){
        //TODO link to view with gets for projectiles and enemies from model
        /*
        Should link to a method like updateTiles, see in view
         */
    }

    private void handleTowerUpdate(){
        //TODO link to view with gets for towers from model
        /*
        Should link to a method like updateTiles, see in view
         */
    }
}
