package td_game.model.map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapLoaderTest {

    @Test
    void loadMap() {
        GridMap gridMap = MapLoader.loadMap("levels/testMap.txt",16);
        TileBase tile1 = gridMap.getTile(2,4);
        WaterTile waterTile = new WaterTile();
        assertEquals(waterTile.getType(), tile1.getType());

        TileBase tile2 = gridMap.getTile(7,6);
        PathTile pathTile = new PathTile(PathType.START);
        assertEquals(pathTile.getType(), tile2.getType());

    }
}