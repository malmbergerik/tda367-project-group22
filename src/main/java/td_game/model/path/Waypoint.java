package td_game.model.path;

/**
 * Immutable value object representing one waypoint in world coordinates
 * and the corresponding tile coordinates.
 */

public class Waypoint {
    private final double x; //world x (pixels)
    private final double y; //world y (pixels)
    private final int tileRow; // row index in grid (x in your GridMap)
    private final int tileCol; // row index in grid (x in your GridMap)

    public Waypoint(double x, double y, int tileRow, int tileCol) {
        this.x = x;
        this.y = y;
        this.tileRow = tileRow;
        this.tileCol = tileCol;
    }

    public double getX() {return x;}
    public double getY() {return y;}
    public int getTileRow() {return tileRow;}
    public int getTileCol() {return tileCol;}

    @Override
    public String toString() {
        return String.format("Waypoint(tile=%d,%d -> world=%.1f,%.1f)", tileRow, tileCol, x, y);
    }
}
