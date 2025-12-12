package td_game.model.player;

public interface IHealth {
    int getHealth();
    void takeDamage(int amount);
    boolean isDead();
}
