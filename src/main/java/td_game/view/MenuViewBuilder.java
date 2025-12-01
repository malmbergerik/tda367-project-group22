package td_game.view;

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
