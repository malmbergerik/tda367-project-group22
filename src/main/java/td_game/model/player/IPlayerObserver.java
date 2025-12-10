package td_game.model.player;

public interface IPlayerObserver {
    void onHealthChanged(int health);
    void onPlayerDeath();
    void onMoneyChanged();
}
