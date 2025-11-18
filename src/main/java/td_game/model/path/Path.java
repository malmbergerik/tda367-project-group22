package td_game.model.path;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Immutable ordered list of waypoints.
 */

public class Path {
    private final List<Waypoint> waypoints;

    public Path(List<Waypoint> waypoints) {
        Objects.requireNonNull(waypoints, "waypoints must not be null");
        this.waypoints = Collections.unmodifiableList(waypoints);
    }

    public List<Waypoint> getWaypoints() {
        return waypoints;
    }

    public int length() {
        return waypoints.size();
    }

    public Waypoint get(int index) {
        return waypoints.get(index);
    }

    public boolean isEmpty() {
        return waypoints.isEmpty();
    }

    @Override
    public String toString() {
        return "Path[" + waypoints + "]";
    }
}
