package td_game.model.player;

import java.util.ArrayList;
import java.util.List;


public class Player{

    private IHealth health;
    private IMoney money;
    private final List<IPlayerObserver> observerList = new ArrayList<>();
    public Player(IHealth health, IMoney money){
        this.health = health;
        this.money = money;
    }

    public int getHealth() {
        return health.getHealth();
    }

    public void takeDamage(int amount){
        health.takeDamage(amount);
        notifyHealthChanged();

        if(health.isDead()){
            notifyPlayerDeath();
        }


    }

    public int getMoney(){
        return money.getMoney();
    }

    public void addMoney(int amount){
        money.addMoney(amount);
        notifyMoneyChanged();
    }

    public void spendMoney(int amount){
        money.spendMoney(amount);
        notifyMoneyChanged();
    }

    public void addObserver(IPlayerObserver playerObserver){
        observerList.add(playerObserver);
    }

    public void removeObserver(IPlayerObserver playerObserver){
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

    private void notifyMoneyChanged(){
        for (IPlayerObserver observer : observerList) {
            observer.onMoneyChanged();
        }
    }
}
