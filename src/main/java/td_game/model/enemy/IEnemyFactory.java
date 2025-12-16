package td_game.model.enemy;

import td_game.model.path.Path;

/**
 * Functional interface for creating instances of enemies.
 * Used by the {@link EnemyFactory} to register and create different enemy types dynamically.
 */
public interface IEnemyFactory {

    /**
     * Creates a new instance of a specific enemy type.
     *
     * @param path The path the created enemy will follow.
     * @return A new instance of an {@link ABaseEnemy} subclass.
     */
    ABaseEnemy createEnemy(Path path);
}