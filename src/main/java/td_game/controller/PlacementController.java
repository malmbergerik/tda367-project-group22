package td_game.controller;

import td_game.model.map.GridMap;
import td_game.model.modelnit.GameModel;
import td_game.view.panel.GameViewPanel;
import td_game.view.listener.ITowerPlacementListener;
import td_game.view.strategy.ViewUpdateManager;

public class PlacementController implements IPlacementController, ITowerPlacementListener {
    private final GameViewPanel view;
    private final GameModel model;
    private final ViewUpdateManager updateManager;
    private int selectedRow=-1;
    private int selectedCol=-1;
    private String selectedTower;

    public PlacementController(GameModel model, GameViewPanel view){
        this.model = model;
        this.view = view;
        this.updateManager = new ViewUpdateManager(view.getRenderingContext());
    }

    @Override
    public void handleMouseMoved(int posX, int posY) {
        int tileSize = model.getGridMap().getTileSize() * view.getSCALE();
        int row = posY / tileSize;
        int col = posX / tileSize;

        if(selectedTower != null){
            boolean occupied = model.gridOccupied(row,col);
            boolean placable = model.canBePlaced(row,col,selectedTower);
            boolean canPlace = !occupied && placable;
            updateManager.updateSelectedTower(row, col, canPlace, selectedTower);
            view.repaint();
        }

    }

    @Override
    public void handleMouseClicked(int posX, int posY) {
        GridMap currentGridMap = model.getGridMap();
        int tileSize = currentGridMap.getTileSize() * view.getSCALE();
        int row = posY / tileSize;
        int col = posX / tileSize;

        if (selectedTower != null) {
            model.placeTower(row, col, selectedTower);
            selectedTower = null;
            updateManager.clearSelectedTower();
            System.out.println("Placed tower at x:" + col + " and y:" + row + " - " + selectedTower);
        }

        if(row<=currentGridMap.getRow() && row>=0 && col>=0 &&col<=currentGridMap.getCol()){
            selectedRow = row;
            selectedCol = col;
            System.out.println("Selected tile is " + selectedCol + " " + selectedRow + selectedTower);
        }
        else{
            selectedRow = -1;
            selectedCol = -1;
            System.out.println("Out of bounce");

        }

    }

    public void handleMouseExit(){
        selectedCol =-1;
        selectedRow =-1;
        updateManager.clearSelectedTower();
        view.repaint();
    }

    @Override
    public void onTowerSelection(String name) {
        if (selectedTower != null && selectedTower.equals(name)) {
            selectedTower = null;
            updateManager.clearSelectedTower();
        } else {
            selectedTower = name;
        }
        view.repaint();
    }
}
