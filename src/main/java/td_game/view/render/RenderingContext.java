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
    private int hoverRow = -1;
    private int hoverCol = -1;
    private String selectedTower;
    private Boolean placable;
    private final int SCALE = 3;

    public RenderingContext() {
        this.renderers = new ArrayList<>();
        this.enemyViewData = new ArrayList<>();
    }

    public void addRenderer(IRenderer renderer) {
        renderers.add(renderer);
        renderers.sort(Comparator.comparingInt(IRenderer::getRenderPriority));
    }

    public void remnoveRenderer(IRenderer renderer) {
        renderers.remove(renderer);
    }

    public void renderAll(Graphics2D g2) {
        for (IRenderer renderer : renderers) {
            renderer.render(g2);
        }
    }

    public void updateMapViewData(MapViewData mapViewData) {
        this.mapViewData = mapViewData;
    }

    public void updateEnemyViewData(List<EnemyViewData> enemyViewDataList) {
        this.enemyViewData = new ArrayList<>(enemyViewDataList);
    }

    public void updateSelectTower(int row, int col, Boolean placable, String towerType) {
        this.hoverRow = row;
        this.hoverCol = col;
        this.placable = placable;
        this.selectedTower = towerType;
    }

    public void clearSelectedTower() {
        this.hoverRow = -1;
        this.hoverCol = -1;
        this.placable = null;
        this.selectedTower = null;
    }

    public MapViewData getMapViewData() {
        return mapViewData;
    }

    public TowerViewData getTowerViewData() {
        return towerViewData;
    }

    public List<EnemyViewData> getEnemyViewData() {
        return enemyViewData;
    }

    public int getHoverRow() {
        return hoverRow;
    }

    public int getHoverCol() {
        return hoverCol;
    }

    public String getSelectedTower(){
        return selectedTower;
    }

    public int getScale(){
        return SCALE;
    }
}
