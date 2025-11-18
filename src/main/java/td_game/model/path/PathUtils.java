package td_game.model.path;

import td_game.model.map.IMap;

/**
 * Small helpers for converting tile coordinates to world coordinates etc.
 *
 * Note: your GridMap uses:
 *   tileRow = row index (0 .. row-1)
 *   tileCol = col index (0 .. col-1)
 *
 * World X = tileCol * tileSize + tileSize/2
 * World Y = tileRow * tileSize + tileSize/2
 */

public final class PathUtils {
    private PathUtils() {}

    public static double tileCenterWorldX(IMap map, int tileRow, int tileCol) {
        return tileCol * map.getTileSize() + map.getTileSize() / 2;
    }

    public static double tileCenterWorldY(IMap map, int tileRow, int tileCol) {
        return tileRow * map.getTileSize() + map.getTileSize() / 2.0;
    }
    /**
     * Convert tile indices to a waypoint world coordinate.
     */
    public static Waypoint tileToWaypoint(IMap map, int tileRow, int tileCol) {
        double wx = tileCenterWorldX(map, tileRow, tileCol);
        double wy = tileCenterWorldY(map, tileRow, tileCol);
        return new Waypoint(wx, wy, tileRow, tileCol);
    }
}
