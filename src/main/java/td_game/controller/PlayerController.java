package td_game.controller;

import td_game.model.modelnit.IResetListener;
import td_game.view.listener.ITowerActionListener;
import td_game.view.listener.ITowerPlacementListener;

/**
 * Manages player interactions by delegating mouse input to the current
 * controller (placement or selection) and handling tower actions and reset events.
 * Implements {@link ITowerPlacementListener}, {@link ITowerActionListener}, and {@link IResetListener}.
 */
public class PlayerController implements ITowerPlacementListener, ITowerActionListener, IResetListener {
    private IMouseController mouseController;

    private PlacementController placementController;
    private SelectionController selectionController;

    /**
     * Creates a PlayerController with the given placement and selection controllers.
     *
     * @param placementController the controller for tower placement
     * @param selectionController the controller for tower selection
     */

    public PlayerController(PlacementController placementController, SelectionController selectionController){
        this.placementController = placementController;
        this.selectionController = selectionController;
        mouseController = selectionController;
    }

    /**
     * Sets the current mouse controller.
     * @param controller the mouse controller to use
     */
    public void setMouseController(IMouseController controller){
        this.mouseController = controller;
    }

    public IMouseController getCurrentMouseController(){
        return mouseController;
    }

    /**
     * Delegates a mouse click to the current controller.
     * @param x the x-coordinate of the mouse click
     * @param y the y-coordinate of the mouse click
     */
    public void handleMouseClicked(int x, int y){
        mouseController.handleMouseClicked(x,y);
        if (placementController.getSelectedTower() == null){
            setMouseController(selectionController);
        }

    }

    /**
     * Delegates mouse movement to the current controller.
     * @param x the x-coordinate of the mouse
     * @param y the y-coordinate of the mouse
     */
    public void handleMouseMoved(int x, int y){
        mouseController.handleMouseMoved(x,y);
    }

    /** Delegates mouse exit to the current controller. */
    public void handleMouseExit(){
        mouseController.handleMouseExit();
    }


    /**
     * Handles tower selection events from the UI.
     * @param name the name of the selected tower
     */
    @Override
    public void onTowerPlacementSelection(String name) {
        setMouseController(placementController);
        placementController.onTowerSelection(name);
    }

    /** Handles tower selling event from view */
    @Override
    public void onTowerSell() {
        selectionController.onTowerSell();
    }

    /** Resets both the selection and placement controllers. */
    @Override
    public void onReset(){
        selectionController.reset();
        placementController.reset();
    }

}
