package td_game.view;

import td_game.view.render.RenderingContext;

public interface IViewUpdateStrategy {

    void updateView(RenderingContext renderingContext, Object data);

    String getStrategyType();

    default  boolean isDataValid(Object data) {
        return data != null;
    }
}
