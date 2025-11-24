package td_game.model.enemy;

import td_game.model.path.Path;

public class EnemyFactory {
    public static ABaseEnemy createEnemy(EnemyType enemy, double health, double speed, Path path) {
        switch (enemy) {
            case skeleton:
                return new Skeleton(health, speed, path);
            default:
                return null;
        }
    }
}