package td_game.controller;

import td_game.model.modelnit.GameModel;
import td_game.model.towers.ATower;
import td_game.view.panel.BottomBarPanel;

/**
 * Handles selecting towers on the game grid and send tower information
 * to the view.
 * Implements {@link IMouseController}.
 */
public class SelectionController implements IMouseController {

    private GameModel model;
    private BottomBarPanel bottomBar;
    private int gameViewScale;
    private ATower selectedTower;
    private int selectedRow=-1;
    private int selectedCol=-1;

    public SelectionController(GameModel model, BottomBarPanel bottomBar, int gameViewScale){
        this.model = model;
        this.bottomBar = bottomBar;
        this.gameViewScale = gameViewScale;

    }

    /** Not used for selection. */
    @Override
    public void handleMouseMoved(int x, int y) {

    }

    /**
     * Selects a tower when the user clicks on an occupied tile.
     * @param x the x-coordinate of the mouse click
     * @param y the y-coordinate of the mouse click
     */
    @Override
    public void handleMouseClicked(int x, int y) {
        int tileSize = model.getGridMap().getTileSize() * gameViewScale;

        if (tileSize == 0) {
            return; // Avoid division by zero
        }

        int row = y / tileSize;
        int col = x / tileSize;

        boolean occupied = model.gridOccupied(row,col);
        if(occupied){
            selectedCol = col;
            selectedRow = row;
            selectedTower =  model.getTower(row,col);
            int sellprice = selectedTower.getValue() /2;
            bottomBar.showTowerInformation(selectedTower.getName(),sellprice);
        }
        else {
            reset();
        }
    }

    /** Not used for selection. */
    @Override
    public void handleMouseExit() {
    }
    /** Sells the currently selected tower. */
    public void onTowerSell(){
        model.sellTower(selectedRow,selectedCol);
    }

    /** Resets the selection and clears tower information from the UI. */
    public void reset(){
        selectedCol = -1;
        selectedRow = -1;
        selectedTower= null;
        bottomBar.hideTowerInformation();
    }
}
