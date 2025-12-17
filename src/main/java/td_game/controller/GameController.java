package td_game.controller;

import td_game.model.events.*;
import td_game.model.modelnit.GameModel;
import td_game.view.panel.GameViewPanel;
import td_game.view.panel.SideBarPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Controls the game by connecting the model, view, and player input.
 * Implements {@link IGameObserver} to update the view on model changes.
 */

public class GameController implements IGameObserver {
    private final GameModel model;
    private final GameViewPanel view;
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
        initUIListeners(sideBar);

        gameUpdateController.handleTileUpdate();
        gameUpdateController.handleWaveUpdate();
    }

    /** Initializes mouse listeners for the game view **/
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

    /** Initializes UI listeners for the game view **/
    private void initUIListeners(SideBarPanel sideBar) {
        // Connect the button in the view to the model method
        sideBar.getGameSpeedPanel().addStartWaveListener(e -> model.startNextWave());
        sideBar.getGameSpeedPanel().addPaueButtonListener(e -> model.togglePause());
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
