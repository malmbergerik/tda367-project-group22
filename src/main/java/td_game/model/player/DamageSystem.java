package td_game.model.player;

import td_game.model.enemy.ABaseEnemy;

public class DamageSystem {

    private final Player player;

    public DamageSystem(Player player){
        this.player = player;
    }
    public void handleEnemyReachedEnd(int damageAmount){
        player.takeDamage(damageAmount);
    }

}
