package td_game.model.enemy;

import td_game.model.path.Path;
import java.util.HashMap;
import java.util.Map;
import td_game.model.enemy.IEnemyFactory;

/**
 * Factory class for creating enemy instances based on a string identifier.
 * Uses a registration map to support dynamic addition of new enemy types.
 * AI was used to help implement this class
 */
public class EnemyFactory {
    private static final Map<String, IEnemyFactory> enemyFactories = new HashMap<>();

    /**
     * Registers a new enemy type with a corresponding creation function.
     *
     * @param name    The unique string identifier for the enemy (e.g., "Slime").
     * @param factory The functional interface implementation that creates the enemy.
     */
    public void registerFactory(String name, IEnemyFactory factory) {
        enemyFactories.put(name, factory);
    }

    /**
     * Creates a specific enemy instance associated with the given name.
     *
     * @param name The name of the enemy type to create.
     * @param path The path the new enemy should follow.
     * @return A new instance of the requested enemy.
     * @throws IllegalArgumentException If the enemy name has not been registered.
     */
    public ABaseEnemy createEnemy(String name, Path path) {
        IEnemyFactory factory = enemyFactories.get(name);
        if (factory == null) {
            throw new IllegalArgumentException("No factory registered for enemy type: " + name);
        }
        return factory.createEnemy(path);
    }
}