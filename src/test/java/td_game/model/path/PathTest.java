package td_game.model.path;

import org.junit.jupiter.api.Test;
import td_game.model.map.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PathTest {

    // Constant for floating point precision checks
    private static final double DELTA = 1e-6;
    private static final int TILE_SIZE = 32;

    /**
     * Helper: create a GridMap pre-filled with non-path TileBase instances
     * (GrassTile). This requires GrassTile to implement isTraversable() and getPathRole().
     */
    private GridMap makeEmptyMap(int rows, int cols) {
        GridMap map = new GridMap(rows, cols, TILE_SIZE);
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // Skapar GrassTile som har isTraversable() == false och getPathRole() == NONE
                map.setTile(r, c, new GrassTile());
            }
        }
        return map;
    }

    // --- WaypointExtractor Tests (Core Path Logic) ---

    @Test
    public void testExtractSimpleLinearPath() {
        // Path: (0,0) START -> (0,4) END
        GridMap map = makeEmptyMap(1, 5);
        map.setTile(0, 0, new PathTile(PathType.START));
        map.setTile(0, 1, new PathTile(PathType.NORMAL));
        map.setTile(0, 2, new PathTile(PathType.NORMAL));
        map.setTile(0, 3, new PathTile(PathType.NORMAL));
        map.setTile(0, 4, new PathTile(PathType.END));

        WaypointExtractor extractor = new WaypointExtractor();
        List<int[]> tilePath = extractor.extractTilePath(map);

        assertEquals(5, tilePath.size(), "Expected five tiles in the linear path (S, N, N, N, E)");
        assertArrayEquals(new int[]{0, 0}, tilePath.get(0), "Start must be at (0, 0)");
        assertArrayEquals(new int[]{0, 4}, tilePath.get(4), "End must be at (0, 4)");
    }

    @Test
    public void testExtractPathWithTurn() {
        // Path: (1,1) START -> (1,2) -> (2,2) END
        GridMap map = makeEmptyMap(3, 3);
        map.setTile(1, 1, new PathTile(PathType.START));
        map.setTile(1, 2, new PathTile(PathType.NORMAL));
        map.setTile(2, 2, new PathTile(PathType.END));

        WaypointExtractor extractor = new WaypointExtractor();
        List<int[]> tilePath = extractor.extractTilePath(map);

        assertEquals(3, tilePath.size(), "Expected three tiles in the path with one turn");
        assertArrayEquals(new int[]{1, 1}, tilePath.get(0));
        assertArrayEquals(new int[]{1, 2}, tilePath.get(1));
        assertArrayEquals(new int[]{2, 2}, tilePath.get(2));
    }

    @Test
    public void testExtractorThrowsIfNoStart() {
        GridMap map = makeEmptyMap(1, 2);
        map.setTile(0, 0, new PathTile(PathType.NORMAL));
        map.setTile(0, 1, new PathTile(PathType.END));

        WaypointExtractor extractor = new WaypointExtractor();
        assertThrows(IllegalArgumentException.class, () -> extractor.extractTilePath(map),
                "Should throw if no START tile is found.");
    }

    @Test
    public void testExtractorThrowsOnDeadEnd() {
        // Path: (0,0) START -> (0,1) NORMAL. (1,1) is END but unreachable.
        GridMap map = makeEmptyMap(2, 2);
        map.setTile(0, 0, new PathTile(PathType.START));
        map.setTile(0, 1, new PathTile(PathType.NORMAL)); // Dead end
        map.setTile(1, 1, new PathTile(PathType.END)); // END is present but unreachable

        WaypointExtractor extractor = new WaypointExtractor();
        assertThrows(IllegalArgumentException.class, () -> extractor.extractTilePath(map),
                "Should throw if the path terminates unexpectedly (dead end).");
    }

    // --- PathFactory and PathUtils Tests ---

    @Test
    public void testPathFactoryCreatesWaypointsWithCorrectWorldCoordinates() {
        // Path: S(0,0) -> N(0,1) -> E(1,1) (3 tiles)
        GridMap map = makeEmptyMap(2, 2);
        map.setTile(0, 0, new PathTile(PathType.START));
        map.setTile(0, 1, new PathTile(PathType.NORMAL));
        map.setTile(1, 1, new PathTile(PathType.END));

        PathFactory factory = new PathFactory();
        Path path = factory.buildPathForMap(map);

        // Waypoint at (1,1), which is the third point (index 2)
        Waypoint w2 = path.get(2);

        // World X for col=1: 1 * 32 + 32/2.0 = 48.0
        // World Y for row=1: 1 * 32 + 32/2.0 = 48.0
        assertEquals(48.0, w2.getX(), DELTA, "World X coordinate for tile (1,1) is incorrect.");
        assertEquals(48.0, w2.getY(), DELTA, "World Y coordinate for tile (1,1) is incorrect.");
        assertEquals(1, w2.getTileRow(), "Tile row is incorrect.");
        assertEquals(1, w2.getTileCol(), "Tile column is incorrect.");
    }

    // --- PathManager Tests (Caching Logic) ---

    @Test
    public void testPathManagerCachesPathInstance() {
        GridMap map = makeEmptyMap(1, 3);
        map.setTile(0, 0, new PathTile(PathType.START));
        map.setTile(0, 1, new PathTile(PathType.NORMAL));
        map.setTile(0, 2, new PathTile(PathType.END));

        PathManager manager = new PathManager();

        // 1. Compute and cache
        Path p1 = manager.computeAndCacheForMap(map);
        assertNotNull(p1);

        // 2. Get the path again (should hit the cache)
        Path p2 = manager.getPathForMap(map);

        // 3. Verify that the two references point to the same object instance
        assertSame(p1, p2, "PathManager must return the same cached Path instance.");

        // 4. Verify clearCache works
        manager.clearCache();

        Path p3 = manager.getPathForMap(map);
        // p3 should be a newly computed path, so it should not be the same instance as p1/p2
        assertNotSame(p1, p3, "Path should be recomputed and be a new instance after cache clear.");
    }
}