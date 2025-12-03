package td_game.view.factory;

import td_game.view.IView;
import td_game.view.panel.MenuPanel;

/**
 * Simple strategy for building Menu views.
 */
public class MenuViewBuilder implements IViewBuilder {

    @Override
    public IView createView(int width, int height) {
        return new MenuPanel(width, height);
    }

    @Override
    public String getViewType() {
        return "MENU_VIEW";
    }
}
