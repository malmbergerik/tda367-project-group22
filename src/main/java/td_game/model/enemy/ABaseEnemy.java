package td_game.model.enemy;

import td_game.model.path.Path;

public abstract class ABaseEnemy implements ITargetable, IMoveable, IDamageable {
    protected double health;
    protected double speed;
    protected double posX;
    protected double posY;

    protected Path path;
    protected int currentWaypointIndex = 0;

    public abstract void takeDamage(double amount);

    public boolean isAlive() {
        if (health <= 0) {
            return false;
        }

        else {
            return true;
        }
    }

    public boolean hasReachedEnd() {
        if (path == null || path.length() == 0) {
            return true;
        }

        return currentWaypointIndex >= path.length();
    }

    public double getX() {
        return posX;
    }

    public double getY() {
        return posY;
    }

    public abstract void move();
}