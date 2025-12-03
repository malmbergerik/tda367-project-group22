package td_game.view.factory;

import td_game.view.panel.GamePanel;
import td_game.view.IView;

/**
 * Simple strategy for building Game views.
 */
public class GameViewBuilder implements IViewBuilder {

    @Override
    public IView createView(int width, int height) {
        return new GamePanel(width, height);
    }

    @Override
    public String getViewType() {
        return "GAME_VIEW";
    }
}
