package td_game.view;

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
