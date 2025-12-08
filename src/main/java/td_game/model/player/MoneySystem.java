package td_game.model.player;

import td_game.model.enemy.ABaseEnemy;

public class MoneySystem {

    private final Player player;

    public MoneySystem(Player player) {
        this.player = player;
    }

    public void handleEnemyKilled(int enemy) {
        player.addMoney(enemy);
    }

    public boolean canAfford(int cost) {
        return player.getMoney() >= cost;
    }

    public void handleTowerPurchase(int cost) {
        player.spendMoney(cost);
    }

    public void handleTowerSell(int value) {
        player.addMoney(value);
    }
}
