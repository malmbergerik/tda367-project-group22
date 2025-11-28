package td_game.controller;

import td_game.model.GameEventType;
import td_game.model.modelnit.GameModel;
import td_game.view.GameViewPanel;
import td_game.model.IGameObserver;
import td_game.view.IGameMouseListener;

public class GameController implements IGameObserver {
    private final GameModel model;
    private final GameViewPanel view;
    private IPlacementController placementController;
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
            public void onMouseDragged(int posX, int posY) {placementController.handleMouseDragged(posX,posY);}
        });
    }


    @Override
    public void onGameEvent(GameEventType eventType) {
        switch (eventType) {
            case TILES_UPDATE -> gameUpdateController.handleTileUpdate();
            case MOVING_OBJECTS_UPDATE -> gameUpdateController.handleMovingObjectsUpdate();
            case TOWER_UPDATE -> gameUpdateController.handleTowersUpdate();
        }
    }








}
