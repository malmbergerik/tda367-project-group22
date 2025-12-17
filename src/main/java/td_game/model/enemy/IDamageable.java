package td_game.model.enemy;

/**
 * Interface representing an entity that can take damage.
 */

public interface IDamageable {
    void takeDamage(int amount);
    boolean isAlive();
}