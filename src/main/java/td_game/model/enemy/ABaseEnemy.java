package td_game.model.enemy;

import td_game.model.towers.IPositionable;
import java.lang.Math;

/**
 * Abstract base class representing an enemy entity in the game.
 * Defines the core attributes and abstract behaviors for movement, health, and damage.
 */
public abstract class ABaseEnemy implements IMoveable, IDamageable, IDamageDealer {
    protected int health;
    protected double speed;
    protected double posX;
    protected double posY;
    protected int width;
    protected int height;
    protected int damageAmount;

    /**
     * Constructs a new enemy with the specified attributes.
     *
     * @param health       The initial health points of the enemy.
     * @param speed        The movement speed (pixels per update/tick).
     * @param width        The width of the enemy hitbox.
     * @param height       The height of the enemy hitbox.
     * @param damageAmount The amount of damage this enemy deals to the player upon reaching the end.
     */
    public ABaseEnemy(int health, double speed, int width, int height, int damageAmount) {
        this.health = health;
        this.speed = speed;
        this.width = width;
        this.height = height;
        this.damageAmount = damageAmount;
    }

    /**
     * Checks if the enemy has reached the end of its path or destination.
     *
     * @return true if the enemy has reached the destination, false otherwise.
     */
    public abstract boolean hasReachedEnd();

    /**
     * Reduces the enemy's health by the specified amount.
     *
     * @param amount The amount of damage to inflict.
     */
    @Override
    public abstract void takeDamage(int amount);

    /**
     * Checks if the enemy is currently alive.
     *
     * @return true if health is greater than 0, false otherwise.
     */
    @Override
    public boolean isAlive() {
        return health > 0;
    }

    public double getX() {
        return posX;
    }

    public double getY() {
        return posY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getHealth() {
        return health;
    }

    /**
     * Updates the enemy's position based on its speed and movement logic.
     * This method is intended to be called once per game tick.
     */
    @Override
    public abstract void move();

    public abstract String getName();

    public int getDamageAmount() {
        return damageAmount;
    }
}