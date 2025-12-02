package td_game.model.enemy;
import td_game.model.path.Path;

public class SkeletonFactory implements IEnemyFactory {
    @Override
    public ABaseEnemy createEnemy(int health, double speed, Path path) {
        return new Skeleton(health, speed, path);
    }
}