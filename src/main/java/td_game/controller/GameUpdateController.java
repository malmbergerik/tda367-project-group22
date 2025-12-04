package td_game.controller;

import td_game.model.enemy.ABaseEnemy;
import td_game.model.map.GridMap;
import td_game.model.modelnit.GameModel;
import td_game.model.projectile.Projectile;
import td_game.model.towers.ATower;
import td_game.model.towers.Tower;
import td_game.view.helper.ProjectileViewData;
import td_game.view.panel.GameViewPanel;
import td_game.view.strategy.ViewUpdateManager;
import td_game.view.helper.EnemyViewData;
import td_game.view.helper.MapViewData;
import td_game.view.helper.TowerViewData;

import java.util.ArrayList;
import java.util.List;


public class GameUpdateController implements IGameUpdateController {

    private final GameModel model;
    private final GameViewPanel view;
    private final ViewUpdateManager updateManager;
    private final List<EnemyViewData> enemyList;
    private final List<ProjectileViewData> projectileList;

    public GameUpdateController(GameModel model, GameViewPanel view) {
        this.model = model;
        this.view = view;
        this.updateManager = new ViewUpdateManager(view.getRenderingContext());
        this.enemyList = new ArrayList<>();
        this.projectileList = new ArrayList<>();
    }

    @Override
    public void handleTileUpdate() {
        updateMapInView();
    }

    public void handleMovingObjectsUpdate() {
        updateMovingObjects();
    }

    public void handleProjectilesUpdate() {
        updateProjectiles();
    }

    public void handleTowersUpdate() {
        ATower[][] activeTowers = model.getPlacedTowerGrid();

        int rows = activeTowers.length;
        int cols = activeTowers[0].length;
        String[][] towerKeys = new String[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (activeTowers[row][col] != null) {
                    // Change to tower name/type
                    towerKeys[row][col] = "Tower 1";
                }
            }
        }

        TowerViewData towerViewData = new TowerViewData(towerKeys);

        updateManager.updateTowers(towerViewData);
        view.repaint();
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

        updateManager.updateMap(mapViewData);
        view.repaint();
    }

    private void updateMovingObjects() {
        List<ABaseEnemy> currentEnemies = model.getActiveEnemies();
        enemyList.clear();

        for (ABaseEnemy enemy : currentEnemies) {
            double enemyX = enemy.getX();
            double enemyY = enemy.getY();
            String name = enemy.getName();
            EnemyViewData enemyViewData = new EnemyViewData(name, enemyX, enemyY);
            enemyList.add(enemyViewData);
        }

        updateManager.updateEnemies(enemyList);
        view.repaint();
    }

    private void updateProjectiles() {
        List <Projectile> currentProjectiles = model.getActiveProjectiles();
        projectileList.clear();

        for (Projectile projectile : currentProjectiles) {
            double projectileX = projectile.getX();
            double projectileY = projectile.getY();
            String name = projectile.getName();

            ProjectileViewData projectileViewData = new ProjectileViewData(name, projectileX, projectileY);
            projectileList.add(projectileViewData);
        }

        updateManager.updateProjectiles(projectileList);
        view.repaint();
    }

}
