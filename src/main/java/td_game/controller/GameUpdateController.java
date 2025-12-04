package td_game.controller;

import td_game.model.enemy.ABaseEnemy;
import td_game.model.map.GridMap;
import td_game.model.modelnit.GameModel;
import td_game.model.projectile.Projectile;
import td_game.model.towers.Tower;
import td_game.view.helper.ProjectileViewData;
import td_game.view.panel.GameViewPanel;
import td_game.view.helper.EnemyViewData;
import td_game.view.helper.MapViewData;
import td_game.view.helper.TowerViewData;
import td_game.view.render.RenderingContext;

import java.util.ArrayList;
import java.util.List;

public class GameUpdateController implements IGameUpdateController {

    private final GameModel model;
    private final GameViewPanel view;
    private final RenderingContext renderingContext;

    private final List<EnemyViewData> enemyList;
    private final List<ProjectileViewData> projectileList;

    public GameUpdateController(GameModel model, GameViewPanel view) {
        this.model = model;
        this.view = view;
        this.renderingContext = view.getRenderingContext();

        this.enemyList = new ArrayList<>();
        this.projectileList = new ArrayList<>();
    }

    @Override
    public void handleTileUpdate() {
        updateMapInView();
    }

    @Override
    public void handleMovingObjectsUpdate() {
        updateMovingObjects();
    }

    @Override
    public void handleProjectilesUpdate() {
        updateProjectiles();
    }

    public void handleTowersUpdate() {
        Tower[][] activeTowers = model.getPlacedTowerGrid();
        int rows = activeTowers.length;
        int cols = activeTowers[0].length;
        String[][] towerKeys = new String[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (activeTowers[row][col] != null) {
                    towerKeys[row][col] = "Tower 1"; // change for activeTowers[row][col].getType() later
                }
            }
        }

        renderingContext.updateTowerViewData(new TowerViewData(towerKeys));
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

        renderingContext.updateMapViewData(new MapViewData(tileKeys, tileSize));
        view.repaint();
    }

    private void updateMovingObjects() {
        List<ABaseEnemy> currentEnemies = model.getActiveEnemies();
        enemyList.clear();

        for (ABaseEnemy enemy : currentEnemies) {
            enemyList.add(new EnemyViewData(enemy.getName(), enemy.getX(), enemy.getY()));
        }

        renderingContext.updateEnemyViewData(enemyList);
        view.repaint();
    }

    private void updateProjectiles() {
        List<Projectile> currentProjectiles = model.getActiveProjectiles();
        projectileList.clear();

        for (Projectile projectile : currentProjectiles) {
            projectileList.add(new ProjectileViewData(projectile.getName(), projectile.getX(), projectile.getY()));
        }

        renderingContext.updateProjectileViewData(projectileList);
        view.repaint();
    }
}