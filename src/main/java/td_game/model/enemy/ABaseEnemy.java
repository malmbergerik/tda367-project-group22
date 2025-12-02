package td_game.model.enemy;

import td_game.model.path.Path;
import td_game.model.path.Waypoint;
import java.lang.Math;

public abstract class ABaseEnemy implements ITargetable, IMoveable, IDamageable {
    protected int health;
    protected double speed;
    protected double posX;
    protected double posY;
    protected int width;
    protected int height;

    protected Path path;
    protected int currentWaypointIndex = 0;

    public ABaseEnemy (int health, double speed, Path path, int width, int height) {
        this.health = health;
        this.speed = speed;
        this.width = width;
        this.height = height;
        this.path = path;

        if (path != null && path.length() > 0) {
            Waypoint firstWaypoint = path.get(0); // Get the first waypoint (starting position)
            this.posX = firstWaypoint.getX();
            this.posY = firstWaypoint.getY();

            this.currentWaypointIndex = 1; // Set the next waypoint index
        }
        else {
            this.posX = 0;
            this.posY = 0;
        }
    }

    public abstract void takeDamage(int amount);

    public boolean isAlive() {
        return health > 0;
    }


    public boolean hasReachedEnd() {
        //if the enemy has no path, consider it has reached the end
        if (path == null || path.length() == 0) {
            return true;
        }

        return currentWaypointIndex >= path.length();
    }

    @Override
    public double getX() {
        return posX;
    }

    @Override
    public double getY() {
        return posY;
    }

    @Override
    public int getWidth() {return width;}

    @Override
    public int getHeight() {return height;}

    public int getHealth() {return  health;}

    @Override
    public  void move() {
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
    };
}