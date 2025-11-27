package td_game.controller;

import td_game.model.map.GridMap;
import td_game.model.modelnit.GameModel;
import td_game.view.GameViewPanel;

public class PlacementController implements IPlacementController {
    private final GameViewPanel view;
    private final GameModel model;
    private int selectedRow=-1;
    private int selectedCol=-1;
    public PlacementController(GameModel model, GameViewPanel view){
        this.model = model;
        this.view = view;
    }

    @Override
    public void handleMouseMoved(int posX, int posY) {
        int tileSize = model.getGridMap().getTileSize() * view.getSCALE();
        int row = posY / tileSize;
        int col = posX / tileSize;
    }

    @Override
    public void handleMouseClicked(int posX, int posY) {
        GridMap currentGridMap = model.getGridMap();
        int tileSize = currentGridMap.getTileSize() * view.getSCALE();
        int row = posY / tileSize;
        int col = posX / tileSize;

        //This exists right now for testing purposes
        //TODO replace with input to model
        System.out.println("Clicked at x:" + col + " and y:" + row);

        if(row<=currentGridMap.getRow() && row>=0 && col>=0 &&col<=currentGridMap.getCol()){
            selectedRow = row;
            selectedCol = col;
            System.out.println("Selected tile is " + selectedCol + " " + selectedRow);
        }
        else{
            selectedRow = -1;
            selectedCol = -1;
            System.out.println("Out of bounce");

        }

    }
}
