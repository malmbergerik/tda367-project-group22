package td_game.model.enemy;

import td_game.model.path.Path;
import td_game.model.path.Waypoint;

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
    public abstract void move();
}