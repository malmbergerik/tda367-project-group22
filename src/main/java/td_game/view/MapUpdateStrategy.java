package td_game.view;

import td_game.view.helper.MapViewData;
import td_game.view.render.RenderingContext;

public class MapUpdateStrategy implements IViewUpdateStrategy {

    public static final String STRATEGY_TYPE = "MAP_UPDATE";

    @Override
    public void updateView(RenderingContext renderingContext, Object data) {
        if (!isDataValid(data)) {
            throw new IllegalArgumentException("Invalid map data provided");
        }

        MapViewData mapData = (MapViewData) data;
        renderingContext.updateMapViewData(mapData);
    }

    @Override
    public String getStrategyType() {
        return STRATEGY_TYPE;
    }

    @Override
    public boolean isDataValid(Object data) {
        return data instanceof MapViewData;
    }
}
