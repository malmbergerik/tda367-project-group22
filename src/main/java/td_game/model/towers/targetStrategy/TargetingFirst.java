package td_game.model.towers.targetStrategy;

import td_game.model.enemy.ABaseEnemy;
import td_game.model.enemy.PathFollowingEnemy;
import td_game.model.towers.ATower;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class TargetingFirst implements ITargetStrategy {


    @Override
    public Optional<ABaseEnemy> selectTarget(ATower tower, Collection<ABaseEnemy> allEnemiesInRange) {

        // No speciall logic, take first enemy in range
        return allEnemiesInRange.stream()
                .max(Comparator.comparingDouble(e -> {
                    if (e instanceof PathFollowingEnemy pe){
                        return pe.getCurrentWaypointIndex();
                    }
                    return 0;
                }));
    }
}
