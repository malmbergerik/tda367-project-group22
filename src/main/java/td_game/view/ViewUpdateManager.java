package td_game.view;

import td_game.view.render.RenderingContext;

public class ViewUpdateManager {

    private final ViewUpdateRegistry registry;
    private final RenderingContext renderingContext;

    public ViewUpdateManager(RenderingContext renderingContext) {
        this.renderingContext = renderingContext;
        this.registry = new ViewUpdateRegistry();
        registerDefaultStrategies();
    }

    public void updateView(String strategyType, Object data) {
        IViewUpdateStrategy strategy = registry.getStrategy(strategyType);
        if (strategy == null) {
            throw new IllegalArgumentException("No strategy registered for type: " + strategyType);
        }
        strategy.updateView(renderingContext, data);

        try {
            strategy.updateView(renderingContext, data);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update view using strategy: " + strategyType, e);
        }
    }

    public void updateTowers(Object towerData) {
        updateView(TowerUpdateStrategy.STRATEGY_TYPE, towerData);
    }

    public void updateEnemies(Object enemyData) {
        updateView(EnemyUpdateStrategy.STRATEGY_TYPE, enemyData);
    }

    public void updateMap(Object mapData) {
        updateView(MapUpdateStrategy.STRATEGY_TYPE, mapData);
    }

    public void updateSelectedTower(int row, int col, boolean placeable, String towerType) {
        var selectedData = new SelectedTowerUpdateStrategy.SelectedTowerViewData(row, col, placeable, towerType);
        updateView(SelectedTowerUpdateStrategy.STRATEGY_TYPE, selectedData);
    }

    public void clearSelectedTower() {
        renderingContext.clearSelectedTower();
    }

    public void registerDefaultStrategies() {
        registry.registerStrategy(new TowerUpdateStrategy());
        registry.registerStrategy(new EnemyUpdateStrategy());
        registry.registerStrategy(new MapUpdateStrategy());
        registry.registerStrategy(new SelectedTowerUpdateStrategy());
    }

}
