package td_game.model.path;

import org.junit.jupiter.api.Test;
import td_game.model.map.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PathTest {

    private static final int TILE_SIZE = 32;

    /**
     * Helper: create a GridMap pre-filled with non-path TileBase instances
     * so extractor won't see nulls.
     */
    private GridMap makeEmptyMap(int rows, int cols) {
        GridMap map = new GridMap(rows, cols, TILE_SIZE);
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // anonymous TileBase implementing a non-path tile (GRASS)
                map.setTile(r, c, new GrassTile() {});
            }
        }
        return map;
    }

    @Test
    public void testExtractSimpleLinearPath() {
        // 1 row, 5 columns -- linear path left -> right
        GridMap map = makeEmptyMap(1, 5);

        // place START at (0,0), NORMAL at (0,1..3) and END at (0,4)
        map.setTile(0, 0, new PathTile(PathType.START));
        map.setTile(0, 1, new PathTile(PathType.NORMAL));
        map.setTile(0, 2, new PathTile(PathType.NORMAL));
        map.setTile(0, 3, new PathTile(PathType.NORMAL));
        map.setTile(0, 4, new PathTile(PathType.END));

        WaypointExtractor extractor = new WaypointExtractor();
        List<int[]> tilePath = extractor.extractTilePath(map);

        assertNotNull(tilePath);
        assertEquals(5, tilePath.size(), "Expected five tiles in the linear path");

        // verify first and last coordinates (row, col)
        assertArrayEquals(new int[]{0, 0}, tilePath.get(0));
        assertArrayEquals(new int[]{0, 4}, tilePath.get(4));
    }

    @Test
    public void testPathFactoryCreatesWaypointsWithCorrectWorldCoordinates() {
        GridMap map = makeEmptyMap(2, 3);

        // Path: (0,0) START -> (0,1) NORMAL -> (1,1) END
        map.setTile(0, 0, new PathTile(PathType.START));
        map.setTile(0, 1, new PathTile(PathType.NORMAL));
        map.setTile(1, 1, new PathTile(PathType.END));

        PathFactory factory = new PathFactory();
        Path path = factory.buildPathForMap(map);

        assertNotNull(path);
        assertEquals(3, path.length());

        // Check world coordinates match formula:
        // worldX = tileCol * tileSize + tileSize/2.0
        // worldY = tileRow * tileSize + tileSize/2.0
        Waypoint w0 = path.get(0);
        assertEquals(0 * TILE_SIZE + TILE_SIZE / 2.0, w0.getX(), 1e-6);
        assertEquals(0 * TILE_SIZE + TILE_SIZE / 2.0, w0.getY(), 1e-6);

        Waypoint w2 = path.get(2); // at tile (1,1)
        assertEquals(1 * TILE_SIZE + TILE_SIZE / 2.0, w2.getX(), 1e-6);
        assertEquals(1 * TILE_SIZE + TILE_SIZE / 2.0, w2.getY(), 1e-6);
    }

    @Test
    public void testExtractorThrowsIfNoStart() {
        GridMap map = makeEmptyMap(2, 2);

        // place NORMAL and END but no START
        map.setTile(0, 0, new PathTile(PathType.NORMAL));
        map.setTile(0, 1, new PathTile(PathType.END));

        WaypointExtractor extractor = new WaypointExtractor();
        assertThrows(IllegalArgumentException.class, () -> extractor.extractTilePath(map));
    }

    @Test
    public void testPathManagerCachesPathInstance() {
        GridMap map = makeEmptyMap(1, 3);
        map.setTile(0, 0, new PathTile(PathType.START));
        map.setTile(0, 1, new PathTile(PathType.NORMAL));
        map.setTile(0, 2, new PathTile(PathType.END));

        PathManager manager = new PathManager();

        Path p1 = manager.computeAndCacheForMap(map);
        assertNotNull(p1);

        Path p2 = manager.getPathForMap(map);
        assertSame(p1, p2, "PathManager should return the same cached Path instance on subsequent calls");
    }
}
