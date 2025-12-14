package td_game.controller;

import td_game.model.modelnit.IResetListener;
import td_game.view.listener.ITowerActionListener;
import td_game.view.listener.ITowerPlacementListener;

public class PlayerController implements ITowerPlacementListener, ITowerActionListener, IResetListener {
    private IMouseController mouseController;

    private PlacementController placementController;
    private SelectionController selectionController;

    public PlayerController(PlacementController placementController, SelectionController selectionController){
        this.placementController = placementController;
        this.selectionController = selectionController;
        mouseController = selectionController;
    }
    public void setMouseController(IMouseController controller){
        this.mouseController = controller;
    }

    public IMouseController getCurrentMouseController(){
        return mouseController;
    }

    public void handleMouseClicked(int x, int y){
        mouseController.handleMouseClicked(x,y);
        if (placementController.getSelectedTower() == null){
            setMouseController(selectionController);
        }

    }

    public void handleMouseMoved(int x, int y){
        mouseController.handleMouseMoved(x,y);
    }

    public void handleMouseExit(){
        mouseController.handleMouseExit();
    }

    @Override
    public void onTowerPlacementSelection(String name) {
        setMouseController(placementController);
        placementController.onTowerSelection(name);
    }

    @Override
    public void onTowerSell() {
        selectionController.onTowerSell();
    }

    public void onReset(){
        selectionController.reset();
        placementController.reset();
    }

}
