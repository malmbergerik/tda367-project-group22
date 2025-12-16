package td_game.model.player;

/**
 * Represents the monetary system for the player, tracking current funds
 * and handling transactions.
 */
public class PlayerMoney implements IMoney {

    private int money;

    public PlayerMoney(int startingMoney) {
        this.money = startingMoney;
    }

    /**
     * Adds a specified amount to the player's total money.
     *
     * @param value The amount of money to add.
     */
    @Override
    public void addMoney(int value) {
        this.money += value;
    }

    /**
     * Deducts a specified amount from the player's total money.
     *
     * @param value The amount of money to spend.
     */
    @Override
    public void spendMoney(int value) {
        this.money -= value;
    }

    @Override
    public int getMoney() {
        return this.money;
    }
}