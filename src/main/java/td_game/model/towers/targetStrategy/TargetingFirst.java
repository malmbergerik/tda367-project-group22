package td_game.model.towers.targetStrategy;

import td_game.model.enemy.ABaseEnemy;
import td_game.model.towers.ATower;

import java.util.List;

public class TargetingFirst implements ITargetStrategy {


    @Override
    public ABaseEnemy selectTarget(ATower tower, List<ABaseEnemy> allEnemiesInRange) {

        // No speciall logic, take first enemy in range.
        if (allEnemiesInRange.isEmpty()) {
            return null;
        }

        return allEnemiesInRange.get(0);
    }
}
