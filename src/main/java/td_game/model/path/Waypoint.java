package td_game.model.path;

/**
 * Immutable value object representing a waypoint.
 * Implemented as a Java record for brevity and correctness,
 * with compatibility accessors matching the previous class.
 */
public record Waypoint(double x, double y, int tileRow, int tileCol) {

    // Backwards-compatible accessor names, would need to recompile whole project otherwise
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public int getTileRow() {
        return tileRow;
    }
    public int getTileCol() {
        return tileCol;
    }

    @Override
    public String toString() {
        return String.format("Waypoint(tile=%d,%d -> world=%.1f,%.1f)", tileRow, tileCol, x, y);
    }
}