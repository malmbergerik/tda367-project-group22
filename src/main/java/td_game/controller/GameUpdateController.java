package td_game.controller;

import td_game.model.enemy.ABaseEnemy;
import td_game.model.map.GridMap;
import td_game.model.modelnit.GameModel;
import td_game.model.projectile.Projectile;
import td_game.model.towers.ATower;
import td_game.view.helper.ProjectileViewData;
import td_game.view.panel.GameViewPanel;
import td_game.view.helper.EnemyViewData;
import td_game.view.helper.MapViewData;
import td_game.view.helper.TowerViewData;
import td_game.view.panel.SideBarPanel;
import td_game.view.render.RenderingContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Updates the game view and sidebar based on changes in the model.
 * Implements {@link IGameUpdateController}.
 */

public class GameUpdateController implements IGameUpdateController {

    private final GameModel model;
    private final GameViewPanel view;
    private final SideBarPanel sideBar;
    private final RenderingContext renderingContext;

    private final List<EnemyViewData> enemyList;
    private final List<ProjectileViewData> projectileList;

    public GameUpdateController(GameModel model, GameViewPanel view, SideBarPanel sideBar) {
        this.model = model;
        this.view = view;
        this.sideBar = sideBar;
        this.renderingContext = view.getRenderingContext();

        this.enemyList = new ArrayList<>();
        this.projectileList = new ArrayList<>();

        updateHealth();
        updateMoney();
        updateWave();
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

    @Override
    public void handleHealthUpdate() {
        updateHealth();
    }

    public void handleMoneyUpdate(){
        updateMoney();
    }

    public void handleWaveUpdate() {
        updateWave();
    }

    /** Updates player health info in the sidebar. */
    private void updateHealth(){
        int currentHealth = model.getHealth();
        sideBar.getStatsPanel().updateHealth(currentHealth);
    }

    /** Updates player money info in the sidebar. */
    private void updateMoney(){
        int currentMoney = model.getMoney();
        sideBar.getStatsPanel().updateMoney(currentMoney);
    }

    /** Updates the wave info in the sidebar. */
    private void updateWave() {
        int currentWave = model.getCurrentWave();
        boolean isWaveActive = model.isWaveActive();

        sideBar.getGameSpeedPanel().updateWave(currentWave);
        sideBar.getGameSpeedPanel().setStartWaveButtonEnabled(!isWaveActive);
    }

    /** Updates the placed tower info in the view. */
    public void handleTowersUpdate() {
        ATower[][] activeTowers = model.getPlacedTowerGrid();
        int rows = activeTowers.length;
        int cols = activeTowers[0].length;
        String[][] towerKeys = new String[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (activeTowers[row][col] != null) {
                    towerKeys[row][col] = activeTowers[row][col].getName();
                }
            }
        }

        renderingContext.updateTowerViewData(new TowerViewData(towerKeys));
        view.repaint();
    }

    /** Updates the map/tiles in the view. */
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

    /** Updates the active enemies in the view. */
    private void updateMovingObjects() {
        List<ABaseEnemy> currentEnemies = model.getActiveEnemies();
        enemyList.clear();

        for (ABaseEnemy enemy : currentEnemies) {
            enemyList.add(new EnemyViewData(enemy.getName(), enemy.getX(), enemy.getY()));
        }

        renderingContext.updateEnemyViewData(enemyList);
        view.repaint();
    }

    /** Updates the active projectiles in the view. */
    private void updateProjectiles() {
        List<Projectile> currentProjectiles = model.getActiveProjectiles();
        projectileList.clear();

        for (Projectile projectile : currentProjectiles) {
            projectileList.add(new ProjectileViewData(projectile.getName(), projectile.getX(), projectile.getY(), projectile.getHeight(), projectile.getWidth()));
        }

        renderingContext.updateProjectileViewData(projectileList);
        view.repaint();
    }
}