package td_game.model.towers.targetStrategy;

import td_game.model.enemy.ABaseEnemy;
import td_game.model.towers.ATower;

import java.util.List;

public interface ITargetStrategy {

    ABaseEnemy selectTarget(ATower tower, List<ABaseEnemy> allEnemiesInRange);

}
