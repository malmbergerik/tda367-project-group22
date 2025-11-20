package td_game.model.path;

import td_game.model.map.IMap;

/**
 * Orchestrator: compute+cache paths and provide them to callers.
 * Intended to be created and owned by GameModel (or a central initializer).
 */
public class PathManager implements IPathProvider {
    private final PathFactory factory;
    private final PathCache cache;

    public PathManager() {
        this.factory = new PathFactory();
        this.cache = new PathCache();
    }

    /**
     * Compute and cache the path for this map. Safe to call multiple times.
     */
    public synchronized Path computeAndCacheForMap(IMap map) {
        return cache.get(map).orElseGet(() -> {
            Path p = factory.buildPathForMap(map);
            cache.put(map, p);
            return p;
        });
    }

    @Override
    public Path getPathForMap(IMap map) {
        // lazy compute if missing
        return cache.get(map).orElseGet(() -> computeAndCacheForMap(map));
    }

    public void clearCache() {
        cache.clear();
    }
}
