package td_game.model.towers.rangeStrategies;

import td_game.model.towers.ATower;

public interface IRangeStrategy {
    boolean isInRange(ATower tower, double targetX, double targetY);
}
