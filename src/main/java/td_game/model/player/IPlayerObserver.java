package td_game.model.player;

public interface IPlayerObserver {
    void onHealthChanged(int newHealth);
    void onPlayerDeath();
    void onMoneyChanged();
}
