package td_game.model.player;
/**
 * Defines the contract for health management components.
 */
public interface IHealth {
    int getHealth();
    void takeDamage(int amount);
    boolean isDead();
}
