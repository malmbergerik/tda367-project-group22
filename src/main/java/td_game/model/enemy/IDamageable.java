package td_game.model.enemy;

public interface IDamageable {
    void takeDamage(double amount);
    boolean isAlive();
}