package td_game.model.towers.rangeStrategies;

import td_game.model.towers.ATower;

public class NormalCircularRangeStrategy implements IRangeStrategy {
    private int radius;

    public NormalCircularRangeStrategy(int radius) {
        this.radius = radius;
    }

    @Override
    public boolean isInRange(ATower tower, double targetX, double targetY) {
        double dx = tower.getX() - targetX;
        double dy = tower.getY() - targetY;
        double distanceSquared = dx * dx + dy * dy;
        return distanceSquared <= radius * radius;
    }
}
