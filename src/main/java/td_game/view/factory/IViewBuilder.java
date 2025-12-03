package td_game.view.factory;

import td_game.view.IView;

/**
 * Simple strategy interface for creating views.
 */
public interface IViewBuilder {

    /**
     * Creates a view.
     */
    IView createView(int width, int height);

    /**
     * Gets the view type identifier.
     */
    String getViewType();
}
