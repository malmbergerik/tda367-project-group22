package td_game.model.path;
import td_game.model.map.IMap;

/**
 * Simple provider interface for getting a Path for a given map.
 * Consumers (EnemyFactory, enemies, controllers) should request a Path
 * from an implementation (eg PathManager).
 */

public interface IPathProvider {
    /**
     * Return the cached/singleton Path for the provided map instance.
     * Implementations may compute it lazily or require explicit precomputation.
     *
     * @param map the map to get the path for
     * @return Path for the map (non-null)
     * @throws IllegalArgumentException if no path can be found
     */
    Path getPathForMap(IMap map);
}
