package td_game.model.player;

public interface IMoney {
    void addMoney(int value);
    void spendMoney(int value);
    int getMoney();
}
