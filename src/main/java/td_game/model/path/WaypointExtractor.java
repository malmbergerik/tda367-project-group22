package td_game.model.path;

import td_game.model.map.IMap;
import td_game.model.map.Tile;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Scans the map to discover a linear ordered list of path tile coordinates
 * from START -> ... -> END.
 *
 * Assumptions:
 *  - Path tiles are instances of PathTile (TileType.PATH)
 *  - Exactly one START and one END exist
 *  - Path is a single connected non-branching sequence (4-neighbour connectivity)
 */
public class WaypointExtractor {

    /**
     * Returns an ordered sequence of (tileRow,tileCol) from START to END inclusive.
     *
     * @throws IllegalArgumentException if no path or malformed path found
     */

    private static final int[][] DIRS = {
            {-1, 0}, //up
            {1, 0},  //down
            {0, -1}, //left
            {0, 1}   //right
    };
    public List<int[]> extractTilePath(IMap map) {
        int rows = map instanceof td_game.model.map.GridMap ? ((td_game.model.map.GridMap) map).getRow() : map.getHeight() / map.getTileSize();
        int cols = map instanceof td_game.model.map.GridMap ? ((td_game.model.map.GridMap) map).getCol() : map.getWidth() / map.getTileSize();

        // Find endpoints
        List<int[]> endpoints = new ArrayList<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Tile tile = map.getTile(r, c);
                if (tile.getType() != "Path") continue;

                int deg = countPathNeightbours(map, r, c,rows,cols);
                if (deg == 1){
                    endpoints.add(new int[]{r,c});
                }
            }

        }

        if(endpoints.size() !=2){
            throw new IllegalArgumentException("Expected exactly 2 path endpoints");
        }

        //Sets start Tile preferrly from top left
        int[] startTile = endpoints.stream()
                .min(Comparator.comparingInt(t ->t[0] + t[1]))
                .get();
        int startR = startTile[0];
        int startC = startTile[1];
        int endR = endpoints.get(1)[0];
        int endC = endpoints.get(1)[1];
        // Walk the path
        boolean[][] visited = new boolean[rows][cols];
        List<int[]> ordered = new ArrayList<>();
        int curR = startR;
        int curC = startC;
        ordered.add(new int[]{curR, curC});
        visited[curR][curC] = true;

        while (true) {
            boolean foundNext = false;

            for(int[] d : DIRS) {
                int nr = curR +d[0];
                int nc = curC + d[1];

                if(nr<0 || nr >= rows || nc < 0 || nc >= cols) continue;
                if(visited[nr][nc]) continue;

                Tile neighbourTile = map.getTile(nr, nc);
                if(neighbourTile.getType() == "Path"){
                    visited[nr][nc] = true;
                    ordered.add(new int[]{nr,nc});
                    curR = nr;
                    curC = nc;
                    foundNext = true;
                    break;
                }

            }
            if(!foundNext)break;
        }
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Tile t = map.getTile(r, c);
                if ("Path".equals(t.getType()) && !visited[r][c]) {
                    throw new IllegalArgumentException(
                            "Path terminated unexpectedly: unreachable tile at (" + r + "," + c + ")"
                    );
                }
            }
        }

        return ordered;

    }

    private int countPathNeightbours(IMap map, int r, int c, int rows, int cols) {
        int count = 0;
        for(int[] d : DIRS){
            int nr = r + d[0];
            int nc = c + d[1];

            if(nr<0 || nr >= rows) continue;
            if(nc<0 || nc >= cols) continue;

            Tile t = map.getTile(nr,nc);
            if(t.getType() == "Path") {
                count++;
            }
        }
        return count;
    }
}
