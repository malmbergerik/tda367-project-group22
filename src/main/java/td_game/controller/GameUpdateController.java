package td_game.controller;

import td_game.model.map.GridMap;
import td_game.model.modelnit.GameModel;
import td_game.view.GameViewPanel;
import td_game.view.helper.MapViewData;

public class GameUpdateController implements IGameUpdateController{
    private final GameModel model;
    private final GameViewPanel view;
    public GameUpdateController(GameModel model, GameViewPanel view){
        this.model = model;
        this.view = view;
    }

    @Override
    public void handleTileUpdate() {
        updateMapInView();
    }

    public void handleMovingObjectsUpdate(){
        //TODO link to view with gets for projectiles and enemies from model
        /*
        Should link to a method like updateTiles, see in view
         */
    }

    public void handleTowersUpdate(){
        //TODO link to view with gets for towers from model
        /*
        Should link to a method like updateTiles, see in view
         */
    }

    private void updateMapInView() {
        GridMap currentMap = model.getGridMap();
        int rows = currentMap.getRow();
        int cols = currentMap.getCol();
        int tileSize = currentMap.getTileSize();
        String[][] tileKeys = new String[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                tileKeys[row][col] = currentMap.getTile(row, col).getType();
            }
        }

        MapViewData mapViewData = new MapViewData(tileKeys, tileSize);
        view.updateTiles(mapViewData);
    }
}
