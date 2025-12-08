package td_game.model.map;

import org.junit.jupiter.api.Test;
import td_game.model.towers.Tower;

import static org.junit.jupiter.api.Assertions.*;

class MapLoaderTest {

    @Test
    void loadMap() {
        MapLoader mapLoader = new MapLoader(new TileFactory());
        GridMap gridMap = mapLoader.loadMap("levels/testMap.txt",16);
        Tile tile1 = gridMap.getTile(2,4);
        Tile waterTile = new Tile("Water");
        assertEquals(waterTile.getType(), tile1.getType());

        Tile tile2 = gridMap.getTile(7,6);
        Tile pathTile = new Tile("Path");
        assertEquals(pathTile.getType(), tile2.getType());

    }


}