package td_game.controller;

import td_game.model.enemy.ABaseEnemy;
import td_game.model.map.GridMap;
import td_game.model.modelnit.GameModel;
import td_game.view.GameViewPanel;
import td_game.view.helper.EnemyViewData;
import td_game.view.helper.MapViewData;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class GameUpdateController implements IGameUpdateController{
    private final GameModel model;
    private final GameViewPanel view;
    private List<EnemyViewData> enemyList;
    public GameUpdateController(GameModel model, GameViewPanel view){
        this.model = model;
        this.view = view;
        this.enemyList =  new ArrayList<>();
    }

    @Override
    public void handleTileUpdate() {
        updateMapInView();
    }

    public void handleMovingObjectsUpdate(){
        updateMovingObjects();
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

    private void updateMovingObjects(){
        List<ABaseEnemy> currentEnemies = model.getActiveEnemies();
        enemyList.clear();
        for(ABaseEnemy enemy : currentEnemies){
            Double enemyX = enemy.getX();
            Double enemyY = enemy.getY();
            String name = new String(String.valueOf(enemy));
            EnemyViewData enemyViewData = new EnemyViewData(name, enemyX, enemyY);
            enemyList.add(enemyViewData);

        }
        view.updateMovingObjects(enemyList);
    }
}
