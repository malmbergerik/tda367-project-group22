package td_game.model.towers;

import td_game.model.enemy.ABaseEnemy;

/**
 * A utility class containing mathematical helper methods for tower mechanics.
 * primarily used for calculating distances and angles between game entities.
 */
public class MathHelper {

    /**
     * Calculates the Euclidean distance between a tower and a target enemy.
     *
     * @param source The tower acting as the origin point.
     * @param target The enemy acting as the destination point.
     * @return The distance between the source and the target in pixels.
     */
    public static double lenToTarget(ATower source, ABaseEnemy target) {
        double dx = target.getX() - source.getX();
        double dy = target.getY() - source.getY();
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    /**
     * Calculates the angle from a tower to a target enemy in degrees.
     * Adjusts the target coordinates to account for the enemy's dimensions.
     *
     * @param source The tower acting as the origin point.
     * @param target The enemy acting as the target point.
     * @return The angle to the target in degrees.
     */
    public static double getAngleToTarget(ATower source, ABaseEnemy target) {
        double dx = (target.getX() - target.getWidth() / 2.0) - source.getX();
        double dy = (target.getY() - target.getHeight() / 2.0) - source.getY();
        return Math.atan2(dy, dx)*(180/Math.PI);
    }
}