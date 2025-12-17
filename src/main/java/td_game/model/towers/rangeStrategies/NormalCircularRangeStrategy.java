package td_game.model.towers.rangeStrategies;

import td_game.model.towers.ATower;

/**
 * A range strategy that defines a standard circular detection area.
 * This is the most common form of range check in tower defense games.
 */
public class NormalCircularRangeStrategy implements IRangeStrategy {
    private int radius;

    /**
     * Constructs a new circular range strategy.
     *
     * @param radius The radius of the detection circle in pixels.
     */
    public NormalCircularRangeStrategy(int radius) {
        this.radius = radius;
    }

    /**
     * Checks if a target coordinate is within the circular range of the tower.
     * Uses squared distance calculation to avoid the performance cost of Math.sqrt().
     *
     * @param tower   The tower performing the check (center of the circle).
     * @param targetX The x-coordinate of the target.
     * @param targetY The y-coordinate of the target.
     * @return true if the distance to the target is less than or equal to the radius.
     */
    @Override
    public boolean isInRange(ATower tower, double targetX, double targetY) {
        double dx = tower.getX() - targetX;
        double dy = tower.getY() - targetY;
        double distanceSquared = dx * dx + dy * dy;
        return distanceSquared <= radius * radius;
    }

    /**
     * Retrieves the radius of the detection circle.
     *
     * @return The range radius in pixels.
     */
    @Override
    public int getRange() {
        return radius;
    }
}