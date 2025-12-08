package td_game.model.towers.targetStrategy;

import td_game.model.enemy.ABaseEnemy;
import td_game.model.towers.ATower;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ITargetStrategy {

    Optional<ABaseEnemy> selectTarget(ATower tower, Collection<ABaseEnemy> allEnemiesInRange);

}
