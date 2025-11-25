package td_game.view.helper;

public class MapViewData {
    private final String[][] tileKeys;
    private final int tileSize;

    public MapViewData(String[][] tileKeys, int tileSize) {
        this.tileKeys = tileKeys;
        this.tileSize = tileSize;
    }

    public String[][] getTileKeys() {
        return tileKeys;
    }

    public int getTileSize() {
        return tileSize;
    }
}
