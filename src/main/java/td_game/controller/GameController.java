package td_game.controller;

import td_game.model.events.*;
import td_game.model.modelnit.GameModel;
import td_game.view.panel.GameViewPanel;
import td_game.view.listener.IGameMouseListener;

public class GameController implements IGameObserver {
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
    public void onTileUpdate(TileUpdateEvent event) {
        gameUpdateController.handleTileUpdate();
    }

    @Override
    public void onTowersUpdate(TowersUpdateEvent event) {
        gameUpdateController.handleTowersUpdate();
    }

    @Override
    public void onMovingObjectsUpdate(MovingObjectUpdateEvent event) {
        gameUpdateController.handleMovingObjectsUpdate();
    }

    @Override
    public void onProjectileUpdate(ProjectileUpdateEvent event) {
        gameUpdateController.handleProjectilesUpdate();
    }


    public PlacementController getPlacementController(){
        return placementController;
    }

    public IGameUpdateController getGameUpdateController(){
        return gameUpdateController;
    }








}
