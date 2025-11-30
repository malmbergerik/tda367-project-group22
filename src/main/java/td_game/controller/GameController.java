package td_game.controller;

import td_game.model.GameEventType;
import td_game.model.map.GridMap;
import td_game.model.map.TileBase;
import td_game.model.modelnit.GameModel;
import td_game.view.GamePanel;
import td_game.view.GameViewPanel;
import td_game.model.GameObserver;
import td_game.view.IGameMouseListener;
import td_game.view.ITowerPlacementListener;
import td_game.view.helper.MapViewData;

import java.awt.event.MouseEvent;

public class GameController implements GameObserver {
    private final GameModel model;
    private final GameViewPanel view;
    private PlacementController placementController;
    private IGameUpdateController gameUpdateController;

    public GameController(GameModel model, GameViewPanel view) {
        this.model = model;
        this.view = view;
        this.model.registerObserver(this);

        this.placementController = new PlacementController(model, view);
        this.gameUpdateController = new GameUpdateController(model, view);

        initGameMouseListener();

        gameUpdateController.handleTileUpdate();

    }

    private void initGameMouseListener() {
        view.setGameMouseListener(new IGameMouseListener() {
            @Override
            public void onMouseMoved(int posX, int posY) {
                placementController.handleMouseMoved(posX,posY);
            }

            @Override
            public void onMouseClicked(int posX, int posY) {
                placementController.handleMouseClicked(posX,posY);
            }

            @Override
            public void onMouseExit() {
                placementController.handleMouseExit();
            }
        });
    }


    @Override
    public void update(GameEventType eventType) {
        switch (eventType) {
            case TILES_UPDATE -> gameUpdateController.handleTileUpdate();
            case MOVING_OBJECTS_UPDATE -> gameUpdateController.handleMovingObjectsUpdate();
            case TOWER_UPDATE -> gameUpdateController.handleTowersUpdate();
        }
    }

    public PlacementController getPlacementController(){
        return placementController;
    }

    public IGameUpdateController getGameUpdateController(){
        return gameUpdateController;
    }








}
