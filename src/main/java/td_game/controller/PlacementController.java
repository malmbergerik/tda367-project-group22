package td_game.controller;

import td_game.model.map.GridMap;
import td_game.model.modelnit.GameModel;
import td_game.view.panel.GameViewPanel;
import td_game.view.render.RenderingContext;


public class PlacementController implements IMouseController {
    private final GameViewPanel view;
    private final GameModel model;
    private final RenderingContext renderingContext;

    private int selectedRow=-1;
    private int selectedCol=-1;
    private String selectedTower;

    public PlacementController(GameModel model, GameViewPanel view){
        this.model = model;
        this.view = view;
        this.renderingContext = view.getRenderingContext();
    }

    @Override
    public void handleMouseMoved(int posX, int posY) {
        int tileSize = model.getGridMap().getTileSize() * view.getSCALE();

        if (tileSize == 0) {
            return; // Avoid division by zero
        }

        int row = posY / tileSize;
        int col = posX / tileSize;

        if(selectedTower != null){
            boolean occupied = model.gridOccupied(row,col);
            boolean placable = model.canBePlaced(row,col,selectedTower);
            boolean canPlace = !occupied && placable;

            renderingContext.updateSelectedTower(row, col, canPlace, selectedTower);
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

            renderingContext.clearSelectedTower();
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
        view.repaint();


    }

    public void handleMouseExit(){
        selectedCol =-1;
        selectedRow =-1;
        renderingContext.clearSelectedTower();
        view.repaint();
    }

    public void onTowerSelection(String name) {
        if (selectedTower != null && selectedTower.equals(name)) {
            selectedTower = null;
            renderingContext.clearSelectedTower();
        } else {
            selectedTower = name;
        }
        view.repaint();
    }

    public String getSelectedTower(){
        return selectedTower;
    }

    public void reset(){
        selectedTower = null;
        handleMouseExit();
    }

}
