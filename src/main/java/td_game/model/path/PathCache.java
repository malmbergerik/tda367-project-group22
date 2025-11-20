package td_game.model.path;

import td_game.model.map.IMap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Optional;

/**
 * Simple cache that stores a Path per IMap instance.
 * We key by the map instance (object reference) because GridMap doesn't
 * currently expose a stable string id.
 *
 * This is thread-safe for concurrent reads/writes.
 */
public class PathCache {
    private final Map<IMap, Path> cache = new ConcurrentHashMap<>();

    public Optional<Path> get(IMap map) {
        return Optional.ofNullable(cache.get(map));
    }

    public void put(IMap map, Path path) {
        cache.put(map, path);
    }

    public boolean contains(IMap map) {
        return cache.containsKey(map);
    }

    public void clear() {
        cache.clear();
    }
}
