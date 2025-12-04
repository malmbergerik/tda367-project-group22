package td_game.model.path;

import td_game.model.map.IMap;
import td_game.model.map.PathTile;
import td_game.model.map.PathType;
import td_game.model.map.TileBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Scans the map to discover a linear ordered list of path tile coordinates
 * from START -> ... -> END.
 *
 * Assumptions:
 * - Path tiles implement isPathTile() == true
 * - Exactly one START and one END exist
 * - Path is a single connected non-branching sequence (4-neighbour connectivity)
 */
public class WaypointExtractor {

    /**
     * Returns an ordered sequence of (tileRow,tileCol) from START to END inclusive.
     *
     * @throws IllegalArgumentException if no path or malformed path found
     */
    public List<int[]> extractTilePath(IMap map) {
        int rows = map instanceof td_game.model.map.GridMap ? ((td_game.model.map.GridMap) map).getRow() : map.getHeight() / map.getTileSize();
        int cols = map instanceof td_game.model.map.GridMap ? ((td_game.model.map.GridMap) map).getCol() : map.getWidth() / map.getTileSize();

        // find start
        int startR = -1, startC = -1;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                TileBase tile = map.getTile(r, c);

                // Use isPathTile() for the check
                if (tile.isTraversable()) {
                    // Safe cast is now possible if needed, but we check PathType directly
                    PathTile pt = (PathTile) tile;
                    if (pt.getPathType() == PathType.START) {
                        startR = r;
                        startC = c;
                        break;
                    }
                }
            }
            if (startR != -1) break;
        }


        if (startR == -1) {
            throw new IllegalArgumentException("No START tile found on the map");
        }

        // Walk the path
        boolean[][] visited = new boolean[rows][cols];
        List<int[]> ordered = new ArrayList<>();
        int curR = startR;
        int curC = startC;
        ordered.add(new int[]{curR, curC});
        visited[curR][curC] = true;

        boolean reachedEnd = false;
        while (!reachedEnd) {
            // check if current is END
            TileBase curTile = map.getTile(curR, curC);

            // Check if current tile is END (requires checking isPathTile() first)
            if (curTile.isTraversable() && ((PathTile) curTile).getPathType() == PathType.END) {
                reachedEnd = true;
                break;
            }

            // check up/down/left/right for the next unvisited path tile
            int[][] neighbours = {
                    {curR - 1, curC}, // up
                    {curR + 1, curC}, // down
                    {curR, curC - 1}, // left
                    {curR, curC + 1}  // right
            };

            boolean foundNext = false;
            for (int[] n : neighbours) {
                int nr = n[0], nc = n[1];
                if (nr < 0 || nr >= rows || nc < 0 || nc >= cols) continue;
                if (visited[nr][nc]) continue;

                TileBase t = map.getTile(nr, nc);

                // Polymorphic check: Does the tile claim to be a path?
                if (t.isTraversable()) {
                    // append and move
                    visited[nr][nc] = true;
                    ordered.add(new int[]{nr, nc});
                    curR = nr;
                    curC = nc;
                    foundNext = true;
                    break;
                }
            }

            if (!foundNext) {
                // malformed/branched path or dead end
                throw new IllegalArgumentException("Path terminated unexpectedly (no next path tile). Map may be malformed or branching paths are unsupported.");
            }
        }

        return ordered;
    }
}