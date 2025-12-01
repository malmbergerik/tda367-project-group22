package td_game.view;

import java.util.HashMap;
import java.util.Set;
import java.util.Map;

public class ViewUpdateRegistry {

    private final Map<String, IViewUpdateStrategy> strategies = new HashMap<>();

    public void registerStrategy(IViewUpdateStrategy strategy) {
        if (strategy == null || strategy.getStrategyType() == null) {
            throw new IllegalArgumentException("Invalid strategy");
        }
        strategies.put(strategy.getStrategyType(), strategy);
    }

    public IViewUpdateStrategy getStrategy(String strategyType) {
        return strategies.get(strategyType);
    }

    public boolean hasStrategy(String strategyType) {
        return strategies.containsKey(strategyType);
    }

    public Set<String> getRegisteredStrategyTypes() {
        return Set.copyOf(strategies.keySet());
    }

}
