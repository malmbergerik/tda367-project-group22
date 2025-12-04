package td_game.model.map;

public interface IMap {
    int getWidth();
    int getHeight();
    TileBase getTile(int x, int y);
    void setTile(int x, int y, TileBase tile);
    int getTileSize();

    /**
     * Additions for cleaner pathfinding
     * Making WaypointExtractor completely independent of knowing
     * the concrete GridMap implementation
     */

    int getRow(); // returns the number of tile rows in the grid
    int getCol(); // returns the number of tile columns in the grid
}
