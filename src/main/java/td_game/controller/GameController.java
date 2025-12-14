package td_game.controller;

import td_game.model.events.*;
import td_game.model.modelnit.GameModel;
import td_game.model.player.Player;
import td_game.view.panel.GameViewPanel;
import td_game.view.listener.IGameMouseListener;
import td_game.view.panel.SideBarPanel;

import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameController implements IGameObserver {
    private final GameModel model;
    private final GameViewPanel view;
    private PlacementController placementController;
    private IGameUpdateController gameUpdateController;
    private PlayerController playerController;
    private InputController inputController;
    public GameController(GameModel model, GameViewPanel view, PlayerController playerController, SideBarPanel sideBar) {
        this.model = model;
        this.view = view;
        this.model.registerObserver(this);
        this.playerController = playerController;
        this.inputController = new InputController(model);
        this.gameUpdateController = new GameUpdateController(model, view, sideBar);

        initGameMouseListener();

        gameUpdateController.handleTileUpdate();

    }

    private void initGameMouseListener() {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                playerController.handleMouseMoved(e.getX(),e.getY());
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                playerController.handleMouseClicked(e.getX(),e.getY());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                playerController.handleMouseExit();
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


    public IGameUpdateController getGameUpdateController(){
        return gameUpdateController;
    }

    public InputController getInputController(){ return inputController;}







}
