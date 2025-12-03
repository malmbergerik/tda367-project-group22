package td_game.view.factory;

import java.util.*;

/**
 * Simple registry for ViewBuilder strategies.
 * Clean, minimal implementation without unnecessary complexity.
 */
public class ViewBuilderRegistry {

    private final Map<String, IViewBuilder> builders = new HashMap<>();

    /**
     * Registers a view builder.
     */
    public void registerBuilder(IViewBuilder builder) {
        if (builder == null || builder.getViewType() == null) {
            throw new IllegalArgumentException("Invalid builder");
        }

        builders.put(builder.getViewType(), builder);
    }

    /**
     * Gets a builder for the view type.
     */
    public IViewBuilder getBuilder(String viewType) {
        return builders.get(viewType);
    }

    /**
     * Checks if a view type is registered.
     */
    public boolean hasBuilder(String viewType) {
        return builders.containsKey(viewType);
    }

    /**
     * Gets all registered view types.
     */
    public Set<String> getRegisteredViewTypes() {
        return new HashSet<>(builders.keySet());
    }
}
