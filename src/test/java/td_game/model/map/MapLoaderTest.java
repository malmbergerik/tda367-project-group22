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

    @Test
    void loadMapStartEnd() {
        GridMap gridMap = MapLoader.loadMap("levels/lvl1.txt",16);
        PathTile tile1 = (PathTile) gridMap.getTile(7,0);
        PathTile pathStart = new PathTile(PathType.START);
        assertEquals(pathStart.getPathType(), tile1.getPathType());

        PathTile tile2 = (PathTile) gridMap.getTile(2,14);
        PathTile pathEnd = new PathTile(PathType.END);
        assertEquals(pathEnd.getPathType(), tile2.getPathType());

    }
}