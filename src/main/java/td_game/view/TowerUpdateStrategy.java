package td_game.view;


import td_game.view.helper.TowerViewData;
import td_game.view.render.RenderingContext;

public class TowerUpdateStrategy implements IViewUpdateStrategy {

    public static final String STRATEGY_TYPE = "TOWER_UPDATE_STRATEGY";

    @Override
    public void updateView(RenderingContext renderingContext, Object data) {
        if (!isDataValid(data)) {
            throw new IllegalArgumentException("Invalid tower data provided");
        }

        TowerViewData towerData = (TowerViewData) data;
        renderingContext.updateTowerViewData(towerData);
    }

    @Override
    public String getStrategyType() {
        return STRATEGY_TYPE;
    }

    @Override
    public boolean isDataValid(Object data) {
        return data instanceof TowerViewData;
    }


}
