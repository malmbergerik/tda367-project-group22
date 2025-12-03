package td_game.model.towers.attackStrategies;

import td_game.model.enemy.ABaseEnemy;
import td_game.model.towers.ATower;

import java.util.List;

public interface IAttackStrategy {
    void attack(ATower tower, List<ABaseEnemy> targets);
    void attack(ATower tower, ABaseEnemy target);
    int getProjectileAmount(ATower tower);

}
