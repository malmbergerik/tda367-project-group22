package td_game.view.render;

import td_game.view.helper.*;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RenderingContext {

    private final List<IRenderer> renderers;

    private MapViewData mapViewData;
    private TowerViewData towerViewData;
    private List<EnemyViewData> enemyViewData;
    private List<ProjectileViewData> projectileViewData;

    // UI state
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

    public void addRenderer(IRenderer renderer) {
        renderers.add(renderer);
        renderers.sort(Comparator.comparingInt(IRenderer::getRenderPriority));
    }

    public void renderAll(Graphics2D g2) {
        for (IRenderer renderer : renderers) {
            renderer.render(g2);
        }
    }

    public synchronized void updateMapViewData(MapViewData mapViewData) {
        this.mapViewData = mapViewData;
    }

    public synchronized void updateTowerViewData(TowerViewData towers) {
        this.towerViewData = towers;
    }

    public synchronized void updateEnemyViewData(List<EnemyViewData> enemyViewDataList) {
        this.enemyViewData = new ArrayList<>(enemyViewDataList);
    }

    public synchronized void updateProjectileViewData(List<ProjectileViewData> projectileViewDataList) {
        this.projectileViewData = new ArrayList<>(projectileViewDataList);
    }

    public synchronized void updateSelectedTower(int row, int col, Boolean placeable, String towerType) {
        this.hoverRow = row;
        this.hoverCol = col;
        this.placeable = placeable;
        this.selectedTower = towerType;
    }

    public synchronized void clearSelectedTower() {
        this.hoverRow = -1;
        this.hoverCol = -1;
        this.placeable = null;
        this.selectedTower = null;
    }

    public synchronized MapViewData getMapViewData() {
        return mapViewData;
    }

    public synchronized TowerViewData getTowerViewData() {
        return towerViewData;
    }

    public synchronized List<EnemyViewData> getEnemyViewData() {
        return enemyViewData;
    }

    public synchronized List<ProjectileViewData> getProjectileViewData() {
        return projectileViewData;
    }

    public synchronized int getHoverRow() { return hoverRow; }
    public synchronized int getHoverCol() { return hoverCol; }
    public synchronized String getSelectedTower(){ return selectedTower; }
    public synchronized Boolean getPlaceable(){ return placeable; }

    public int getScale(){ return SCALE; }
}