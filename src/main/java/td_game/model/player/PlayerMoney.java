package td_game.model.player;

public class PlayerMoney implements IMoney{

    private int money;
    public PlayerMoney(int startingMoney){
        this.money = startingMoney;
    }

    @Override
    public void addMoney(int value) {
        this.money += value;
    }

    @Override
    public void spendMoney(int value) {
        this.money -= value;
    }

    @Override
    public int getMoney() {
        return this.money;
    }

}
