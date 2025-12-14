package td_game.view.render;

import td_game.view.helper.*;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The RenderingContext class manages the rendering state and data for the game view.
 * Works as a View Model in the MVVM architecture.
 * It holds references to various view data objects and provides methods to update and retrieve them.
 *
 * DISCLAIMER: Synchronization is used to ensure thread safety when updating and accessing view data.
 * Avoids accidentaly trying to draw while the data is being updated from another thread.
 * AI helped with this reasoning.
 */
public class RenderingContext {

    private final List<IRenderer> renderers;

    private MapViewData mapViewData;
    private TowerViewData towerViewData;
    private List<EnemyViewData> enemyViewData;
    private List<ProjectileViewData> projectileViewData;


    private int hoverRow = -1;
    private int hoverCol = -1;
    private String selectedTower;
    private Boolean placeable;
    private final int SCALE = 3;

    public RenderingContext() {
        this.renderers = new ArrayList<>();
        this.enemyViewData = new ArrayList<>();
        this.projectileViewData = new ArrayList<>();
    }

    /**
     * Adds a renderer to the rendering context.
     *
     * @param renderer The renderer to add.
     */
    public void addRenderer(IRenderer renderer) {
        renderers.add(renderer);
        renderers.sort(Comparator.comparingInt(IRenderer::getRenderPriority));
    }

    /**
     * Renders all registered renderers.
     *
     * @param g2 The Graphics2D object to draw on.
     */
    public void renderAll(Graphics2D g2) {
        for (IRenderer renderer : renderers) {
            renderer.render(g2);
        }
    }

    /**
     * Updates the map view data.
     *
     * @param mapViewData The new map view data.
     */
    public synchronized void updateMapViewData(MapViewData mapViewData) {
        this.mapViewData = mapViewData;
    }

    /**
     * Updates the tower view data.
     *
     * @param towers The new tower view data.
     */
    public synchronized void updateTowerViewData(TowerViewData towers) {
        this.towerViewData = towers;
    }

    /**
     * Updates the enemy view data.
     *
     * @param enemyViewDataList The new list of enemy view data.
     */
    public synchronized void updateEnemyViewData(List<EnemyViewData> enemyViewDataList) {
        this.enemyViewData = new ArrayList<>(enemyViewDataList);
    }

    /**
     * Updates the projectile view data.
     *
     * @param projectileViewDataList The new list of projectile view data.
     */
    public synchronized void updateProjectileViewData(List<ProjectileViewData> projectileViewDataList) {
        this.projectileViewData = new ArrayList<>(projectileViewDataList);
    }

    /**
     * Updates the selected tower information.
     *
     * @param row        The row of the selected tower.
     * @param col        The column of the selected tower.
     * @param placeable  Whether the tower can be placed at the specified location.
     * @param towerType  The type of the selected tower.
     */
    public synchronized void updateSelectedTower(int row, int col, Boolean placeable, String towerType) {
        this.hoverRow = row;
        this.hoverCol = col;
        this.placeable = placeable;
        this.selectedTower = towerType;
    }

    /**
     * Clears the selected tower information.
     */
    public synchronized void clearSelectedTower() {
        this.hoverRow = -1;
        this.hoverCol = -1;
        this.placeable = null;
        this.selectedTower = null;
    }

    /**
     * Retrieves the current map view data.
     *
     * @return The current map view data.
     */
    public synchronized MapViewData getMapViewData() {
        return mapViewData;
    }

    /**
     * Retrieves the current tower view data.
     *
     * @return The current tower view data.
     */
    public synchronized TowerViewData getTowerViewData() {
        return towerViewData;
    }

    /**
     * Retrieves the current list of enemy view data.
     *
     * @return The current list of enemy view data.
     */
    public synchronized List<EnemyViewData> getEnemyViewData() {
        return enemyViewData;
    }

    /**
     * Retrieves the current list of projectile view data.
     *
     * @return The current list of projectile view data.
     */
    public synchronized List<ProjectileViewData> getProjectileViewData() {
        return projectileViewData;
    }

    public synchronized int getHoverRow() { return hoverRow; }
    public synchronized int getHoverCol() { return hoverCol; }
    public synchronized String getSelectedTower(){ return selectedTower; }
    public synchronized Boolean getPlaceable(){ return placeable; }

    public int getScale(){ return SCALE; }
}