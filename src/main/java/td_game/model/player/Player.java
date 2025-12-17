package td_game.model.player;

import java.util.ArrayList;
import java.util.List;

/**
 * The central Player class that aggregates health and money components.
 * It acts as the subject in the Observer pattern, notifying listeners of changes
 * to health, money, or the player's living state.
 */
public class Player {

    private IHealth health;
    private IMoney money;
    private final List<IPlayerObserver> observerList = new ArrayList<>();

    /**
     * Constructs a new Player with the specified health and money components.
     *
     * @param health The component managing player health.
     * @param money  The component managing player money.
     */
    public Player(IHealth health, IMoney money) {
        this.health = health;
        this.money = money;
    }

    public int getHealth() {
        return health.getHealth();
    }

    /**
     * Inflicts damage on the player, updates the health component, and notifies observers.
     * If the player dies, a death notification is sent.
     *
     * @param amount The amount of damage to take.
     */
    public void takeDamage(int amount) {
        health.takeDamage(amount);
        notifyHealthChanged();

        if (health.isDead()) {
            notifyPlayerDeath();
        }
    }

    public int getMoney() {
        return money.getMoney();
    }

    /**
     * Adds money to the player's balance and notifies observers of the change.
     *
     * @param amount The amount of money to add.
     */
    public void addMoney(int amount) {
        money.addMoney(amount);
        notifyMoneyChanged();
    }

    /**
     * Deducts money from the player's balance and notifies observers of the change.
     *
     * @param amount The amount of money to spend.
     */
    public void spendMoney(int amount) {
        money.spendMoney(amount);
        notifyMoneyChanged();
    }

    /**
     * Registers an observer to listen for player events (health change, money change, death).
     *
     * @param playerObserver The observer to add.
     */
    public void addObserver(IPlayerObserver playerObserver) {
        observerList.add(playerObserver);
    }

    /**
     * Unregisters an observer from the player.
     *
     * @param playerObserver The observer to remove.
     */
    public void removeObserver(IPlayerObserver playerObserver) {
        observerList.remove(playerObserver);
    }

    private void notifyHealthChanged() {
        for (IPlayerObserver observer : observerList) {
            observer.onHealthChanged(getHealth());
        }
    }

    private void notifyPlayerDeath() {
        for (IPlayerObserver observer : observerList) {
            observer.onPlayerDeath();
        }
    }

    private void notifyMoneyChanged() {
        for (IPlayerObserver observer : observerList) {
            observer.onMoneyChanged();
        }
    }
}