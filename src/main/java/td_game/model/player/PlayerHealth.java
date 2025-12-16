package td_game.model.player;

/**
 * Manages the health state of the player, including taking damage and tracking
 * whether the player is alive or dead.
 */
public class PlayerHealth implements IHealth {
    private int health;
    private boolean isDead;

    /**
     * Constructs a new PlayerHealth instance with a specified maximum health.
     *
     * @param health The initial health points for the player.
     */
    public PlayerHealth(int health) {
        this.health = health;
        this.isDead = false;
    }

    @Override
    public int getHealth() {
        return health;
    }

    /**
     * Reduces the player's health by the specified amount.
     * If health drops to zero or below, the player is marked as dead.
     *
     * @param amount The amount of damage to inflict on the player.
     */
    @Override
    public void takeDamage(int amount) {
        health = health - amount;
        if (health <= 0) {
            isDead = true;
        }
    }

    public boolean isDead() {
        return isDead;
    }
}