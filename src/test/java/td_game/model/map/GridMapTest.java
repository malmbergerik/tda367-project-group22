package td_game.model.map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridMapTest {

    @Test
    void testGridDimensions(){
        GridMap gridMap = new GridMap(10,15,10);
        assertEquals(10, gridMap.getRow());
        assertEquals(15, gridMap.getCol());

    }

    @Test
    void testGridPixelDimensions(){
        GridMap gridMap = new GridMap(10,15,16);
        assertEquals(10*16, gridMap.getHeight());
        assertEquals(15*16, gridMap.getWidth());

    }

    @Test
    void getTileSize() {
        GridMap gridMap = new GridMap(1,1,16);
        assertEquals(16, gridMap.getTileSize());
    }

    @Test
    void getMap() {
        GridMap gridMap = new GridMap(10,12,16);
        TileBase[][] map = gridMap.getMap();
        assertNotNull(map);
        assertEquals(10, map.length);
        assertEquals(12, map[0].length);
    }

    @Test
    void setAndGetTile() {
        GridMap gridMap = new GridMap(10,12,16);
        TileBase tileBase = new GrassTile();
        gridMap.setTile(9,9, tileBase);
        assertEquals(tileBase, gridMap.getTile(9,9));
    }
    @Test
    void setTileOutOfBounce() {
        GridMap gridMap = new GridMap(10,12,16);
        TileBase tileBase = new GrassTile();
        assertThrows(IndexOutOfBoundsException.class,() ->
                { gridMap.setTile(13,13, tileBase);
                });
        assertThrows(IndexOutOfBoundsException.class,() ->
        { gridMap.getTile(13,13);
        });
    }


}