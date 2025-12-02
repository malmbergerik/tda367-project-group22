package td_game.model.enemy;

import java.lang.Math;
import td_game.model.path.Path;
import td_game.model.path.Waypoint;

public class Skeleton extends ABaseEnemy{
    private static final int ENEMY_WIDTH = 8;
    private static final int ENEMY_HEIGHT = 8;

    public Skeleton(int health, double speed, Path path){
        super(health, speed, path, ENEMY_WIDTH, ENEMY_HEIGHT);
    }


    @Override
    public void takeDamage(int amount) {
        health -= amount;
    }


    @Override
    public void move() {
        if (hasReachedEnd()) {
            return; // Stop if the end of the path is reached.
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
            }
            else {
                // The enemy cannot reach the waypoint in this step, so move towards it.
                double moveX = (dx / distanceToWaypoint) * speedForTick;
                double moveY = (dy / distanceToWaypoint) * speedForTick;
                posX += moveX;
                posY += moveY;
                speedForTick = 0; // All speed for this tick has been used.
            }
        }
    }

}
