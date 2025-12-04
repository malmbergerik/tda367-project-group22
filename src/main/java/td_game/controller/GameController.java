package td_game.controller;

import td_game.model.events.*;
import td_game.model.modelnit.GameModel;
import td_game.view.panel.GameViewPanel;
import td_game.view.listener.IGameMouseListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                placementController.handleMouseMoved(e.getX(),e.getY());
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                placementController.handleMouseClicked(e.getX(),e.getY());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                placementController.handleMouseExit();
            }
        };

        view.addGameMouseListener(mouseAdapter);
        view.addGameMouseMotionListener(mouseAdapter);

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
