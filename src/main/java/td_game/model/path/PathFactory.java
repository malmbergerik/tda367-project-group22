package td_game.model.path;

import td_game.model.map.IMap;
import td_game.model.map.tileSpecfication.PathSpecification;

import java.util.ArrayList;
import java.util.List;

/**
 * High-level builder that converts a map to an immutable Path.
 */
public class PathFactory {
    private final WaypointExtractor extractor;

    public PathFactory() {
        this.extractor = new WaypointExtractor();
    }

    public Path buildPathForMap(IMap map) {
        List<int[]> tileCoords = extractor.extractTilePath(map, new PathSpecification());
        List<Waypoint> waypoints = new ArrayList<>(tileCoords.size());
        for (int[] tc : tileCoords) {
            int row = tc[0];
            int col = tc[1];
            Waypoint wp = PathUtils.tileToWaypoint(map, row, col);
            waypoints.add(wp);
        }
        return new Path(waypoints);
    }
}
