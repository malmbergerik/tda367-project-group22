package td_game.model.map;

/**
 * Represents a 2D map composed of {@link Tile}.
 */

public interface IMap {
    int getWidth();
    int getHeight();
    Tile getTile(int x, int y);
    void setTile(int x, int y, Tile tile);
    int getTileSize();
}
