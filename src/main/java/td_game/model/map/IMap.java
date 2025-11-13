package td_game.model.map;

public interface IMap {
    int getWidth();
    int getHeight();
    TileBase getTile(int x, int y);
    void setTile(int x, int y, TileBase tile);
    int getTileSize();
}
