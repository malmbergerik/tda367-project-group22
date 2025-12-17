package td_game.model.player;

import td_game.model.enemy.ABaseEnemy;

/**
 * Handles game logic related to currency events, such as earning money from enemies
 * or spending money on towers.
 */
public class MoneySystem {

    private final Player player;

    public MoneySystem(Player player) {
        this.player = player;
    }

    /**
     * Awards money to the player when an enemy is killed.
     *
     * @param enemy The monetary value of the killed enemy.
     */
    public void handleEnemyKilled(int enemy) {
        player.addMoney(enemy);
    }

    /**
     * Checks if the player has enough money to afford a specific cost.
     *
     * @param cost The cost to check against the player's current funds.
     * @return true if the player has enough money, false otherwise.
     */
    public boolean canAfford(int cost) {
        return player.getMoney() >= cost;
    }

    /**
     * Deducts money from the player when a tower is purchased.
     *
     * @param cost The cost of the tower being purchased.
     */
    public void handleTowerPurchase(int cost) {
        player.spendMoney(cost);
    }

    /**
     * Refunds money to the player when a tower is sold.
     *
     * @param value The sell value of the tower.
     */
    public void handleTowerSell(int value) {
        player.addMoney(value);
    }
}