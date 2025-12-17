package td_game.model.player;
/**
 * Defines the contract for money management components.
 */
public interface IMoney {
    void addMoney(int value);
    void spendMoney(int value);
    int getMoney();
}
