package td_game.controller;

import td_game.model.events.*;
import td_game.model.modelnit.GameModel;
import td_game.view.panel.GameViewPanel;
import td_game.view.listener.IGameMouseListener;
import td_game.view.panel.SideBarPanel;

import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameController implements IGameObserver {
    private final GameModel model;
    private final GameViewPanel view;
    private final SideBarPanel sideBar;
    private PlacementController placementController;
    private IGameUpdateController gameUpdateController;
    private InputController inputController;
    public GameController(GameModel model, GameViewPanel view, SideBarPanel sideBar) {
        this.model = model;
        this.view = view;
        this.sideBar = sideBar;
        this.model.registerObserver(this);

        this.placementController = new PlacementController(model, view);
        this.gameUpdateController = new GameUpdateController(model, view, sideBar);
        this.inputController = new InputController(model);

        initGameMouseListener();
        initUIListeners();

        gameUpdateController.handleTileUpdate();
        gameUpdateController.handleWaveUpdate();
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

    private void initUIListeners() {
        // Connect the button in the view to the model method
        sideBar.getGameSpeedPanel().addStartWaveListener(e -> model.startNextWave());
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

    @Override
    public void onPlayerHealthUpdate(PlayerHealthUpdateEvent event) {
        gameUpdateController.handleHealthUpdate();
    }

    @Override
    public void onPlayerMoneyUpdate(PlayerMoneyUpdateEvent event) {
        gameUpdateController.handleMoneyUpdate();
    }

    @Override
    public void onWaveUpdate(WaveUpdateEvent event) {
        gameUpdateController.handleWaveUpdate();
    }

    public PlacementController getPlacementController(){
        return placementController;
    }

    public IGameUpdateController getGameUpdateController(){
        return gameUpdateController;
    }

    public InputController getInputController(){ return inputController;}







}
