package td_game.model.enemy;

public interface IDamageable {
    void takeDamage(int amount);
    boolean isAlive();
}