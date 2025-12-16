package td_game.model.enemy;

import td_game.model.path.Path;
import td_game.model.path.Waypoint;

/**
 * An abstract implementation of ABaseEnemy that follows a pre-defined {@link Path}.
 * Handles the logic for moving from one waypoint to the next.
 */
public abstract class PathFollowingEnemy extends ABaseEnemy {
    protected Path path;
    protected int currentWaypointIndex = 0;

    public PathFollowingEnemy(int health, double speed, Path path, int width, int height, int damageAmount) {
        super(health, speed, width, height, damageAmount);
        this.path = path;
        initializePosition();
    }

    /**
     * Sets the enemy's initial position to the coordinates of the first waypoint in the path.
     */
    private void initializePosition() {
        if (path != null && path.length() > 0) {
            Waypoint startingWaypoint = path.get(0);
            this.posX = startingWaypoint.getX();
            this.posY = startingWaypoint.getY();
            this.currentWaypointIndex = 1;
        }
    }

    /**
     * Checks if the enemy has traversed the entire path or if the path is invalid.
     *
     * @return true if the current waypoint index is beyond the path length or path is null.
     */
    @Override
    public boolean hasReachedEnd() {
        if (path == null || path.length() == 0) {
            return true;
        }

        return currentWaypointIndex >= path.length();
    }

    /**
     * Moves the enemy along the path towards the next waypoint.
     * Handles direction calculation and waypoint transitions if the speed allows covering the distance.
     * AI was used to help write this method.
     */
    @Override
    public void move() {
        if (hasReachedEnd()) {
            return;
        }

        double speedForTick = speed; // The total distance the enemy can move this tick.

        // Loop as long as the enemy has movement speed left for this tick and has not reached the end.
        while (speedForTick > 0 && !hasReachedEnd()) {
            Waypoint targetWaypoint = path.get(currentWaypointIndex);
            double targetX = targetWaypoint.getX();
            double targetY = targetWaypoint.getY();

            double dx = targetX - posX;
            double dy = targetY - posY;

            double distanceToWaypoint = Math.sqrt(dx * dx + dy * dy);

            if (distanceToWaypoint <= speedForTick) {
                // The enemy can reach the waypoint in this step.
                posX = targetX;
                posY = targetY;
                currentWaypointIndex++;
                speedForTick -= distanceToWaypoint; // Subtract the distance traveled from the speed budget.
            } else {
                // The enemy cannot reach the waypoint in this step, so move towards it.
                double moveX = (dx / distanceToWaypoint) * speedForTick;
                double moveY = (dy / distanceToWaypoint) * speedForTick;
                posX += moveX;
                posY += moveY;
                speedForTick = 0; // All speed for this tick has been used.
            }
        }
    }

    public int getCurrentWaypointIndex() {
        return currentWaypointIndex;
    }
}