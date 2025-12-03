package td_game.view.strategy;

import td_game.view.helper.ProjectileViewData;
import td_game.view.render.RenderingContext;

import java.util.List;

public class ProjectileUpdateStrategy implements IViewUpdateStrategy {

    public static final String STRATEGY_TYPE = "PROJECTILE_UPDATE";


    @Override
    public void updateView(RenderingContext renderingContext, Object data) {

        // Somethins is wront with this validation, needs to be fixed.
        //if (!isDataValid(data)) {
        //     throw new IllegalArgumentException("Invalid projectile data provided");
        //}

        List<ProjectileViewData> projectiles = (List<ProjectileViewData>) data;
        renderingContext.updateProjectileViewData(projectiles);
    }

    @Override
    public String getStrategyType() {
        return STRATEGY_TYPE;
    }

    @Override
    public boolean isDataValid(Object data) {
        return data instanceof ProjectileViewData;
    }
}
