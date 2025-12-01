package td_game.view.factory;

import td_game.view.IView;

/**
 * Simple ViewFactory using Strategy Pattern.
 * Clean implementation without unnecessary complexity.
 */
public class ViewFactory {

    private final ViewBuilderRegistry registry = new ViewBuilderRegistry();

    public ViewFactory() {
        // Register default builders
        registry.registerBuilder(new MenuViewBuilder());
        registry.registerBuilder(new GameViewBuilder());
    }

    /**
     * Creates a view of the specified type.
     */
    public IView createView(String viewType, int width, int height) {
        IViewBuilder builder = registry.getBuilder(viewType);
        if (builder == null) {
            throw new IllegalArgumentException("Unknown view type: " + viewType);
        }
        return builder.createView(width, height);
    }

    /**
     * Registers a new view builder (for extension).
     */
    public void registerBuilder(IViewBuilder builder) {
        registry.registerBuilder(builder);
    }
}
