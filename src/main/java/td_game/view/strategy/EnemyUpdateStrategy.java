package td_game.view.strategy;

import td_game.view.helper.EnemyViewData;
import td_game.view.render.RenderingContext;

import java.util.List;

public class EnemyUpdateStrategy implements IViewUpdateStrategy {

    public static final String STRATEGY_TYPE = "ENEMY_UPDATE";

    @Override
    public void updateView(RenderingContext renderingContext, Object data) {
        if (!isDataValid(data)) {
            throw new IllegalArgumentException("Invalid enemy data provided");
        }

        List<EnemyViewData> enemyData = (List<EnemyViewData>) data;
        renderingContext.updateEnemyViewData(enemyData);
    }

    @Override
    public String getStrategyType() {
        return STRATEGY_TYPE;
    }

    @Override
    public boolean isDataValid(Object data) {
        return data instanceof List<?> &&
                (((List<?>) data).isEmpty() ||
                 ((List<?>) data).get(0) instanceof EnemyViewData);
    }

}
