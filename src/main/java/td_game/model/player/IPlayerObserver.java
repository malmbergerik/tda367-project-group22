package td_game.model.player;

/**
 * Interface for observers that need to respond to changes in the player's state.
 */
public interface IPlayerObserver {


    void onHealthChanged(int health);
    void onPlayerDeath();
    void onMoneyChanged();
}