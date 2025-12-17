package td_game.model.player;

import td_game.model.enemy.ABaseEnemy;

/**
 * Handles logic related to the player taking damage, specifically when enemies
 * reach the end of the path.
 */
public class DamageSystem {

    private final Player player;

    public DamageSystem(Player player) {
        this.player = player;
    }

    /**
     * Inflicts damage on the player based on the damage amount of an enemy
     * that has reached the end of the map.
     *
     * @param damageAmount The amount of damage the enemy inflicts.
     */
    public void handleEnemyReachedEnd(int damageAmount) {
        player.takeDamage(damageAmount);
    }
}