package td_game.view;

import td_game.view.render.RenderingContext;

public class SelectedTowerUpdateStrategy implements IViewUpdateStrategy{

    public static final String STRATEGY_TYPE = "SELECTED_TOWER_UPDATE";


    public static class SelectedTowerViewData {
        public final int row;
        public final int col;
        public final boolean placeable;
        public final String towerType;

        public SelectedTowerViewData(int row, int col, boolean placeable, String towerType) {
            this.row = row;
            this.col = col;
            this.placeable = placeable;
            this.towerType = towerType;
        }
    }

    @Override
    public void updateView(RenderingContext renderingContext, Object data) {
        if (!isDataValid(data)) {
            throw new IllegalArgumentException("Invalid selected tower data provided");
        }

        SelectedTowerViewData selectedTower = (SelectedTowerViewData) data;
        renderingContext.updateSelectedTower(
                selectedTower.row,
                selectedTower.col,
                selectedTower.placeable,
                selectedTower.towerType
        );
    }

    @Override
    public boolean isDataValid(Object data) {
        return data instanceof SelectedTowerViewData;
    }

    @Override
    public String getStrategyType() {
        return STRATEGY_TYPE;
    }

}
