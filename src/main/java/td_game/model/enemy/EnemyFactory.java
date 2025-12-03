package td_game.model.enemy;

import td_game.model.path.Path;
import java.util.HashMap;
import java.util.Map;
import td_game.model.enemy.IEnemyFactory;

public class EnemyFactory {
    private static final Map<String, IEnemyFactory> enemyFactories = new HashMap<>();

    public void registerFactory(String name, IEnemyFactory factory) {
        enemyFactories.put(name, factory);
    }

    public ABaseEnemy createEnemy(String name, int health, double speed, Path path) {
        IEnemyFactory factory = enemyFactories.get(name);
        if (factory == null) {
            throw new IllegalArgumentException("No factory registered for enemy type: " + name);
        }
        return factory.createEnemy(health, speed, path);
    }
}