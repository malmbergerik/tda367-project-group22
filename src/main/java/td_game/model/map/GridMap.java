package td_game.model.map;

/**
 * Represents a 2D rectangular grid-based map of {@link Tile} objects.
 */
public class GridMap implements IMap{
    private int row;
    private int col;
    private Tile[][] tiles;
    private int tileSize;

    public GridMap(int row, int col, int tileSize){
        this.row = row;
        this.col = col;
        this.tileSize = tileSize;
        this.tiles = new Tile[row][col];
    }

    public int getRow() {return row;}
    public int getCol() {return col;}

    @Override
    public int getHeight() {return row*tileSize;}

    @Override
    public int getWidth() {return col*tileSize;}

    @Override
    public int getTileSize() {return tileSize;}

    public Tile[][] getMap() {return tiles;}

    /**
     * @param x row index
     * @param y col index
     * @return the tile at the given coordinates
     */

    @Override
    public Tile getTile(int x, int y) {
        if(x<=row && x>=0 && y<=col && y>=0){
            return tiles[x][y];
        }
        else {
            throw new IndexOutOfBoundsException("The index is out of bounce");
        }
    }

    /**
     * @param x row index
     * @param y col index
     * @param tile tile that should be placed
     * @throws IndexOutOfBoundsException if the coordinates are outside of map bounds
     */
    @Override
    public void setTile(int x, int y, Tile tile) {
        if(x<=row && x>=0 && y<=col && y>=0) {
            tiles[x][y] = tile;
        }
        else {
            throw new IndexOutOfBoundsException("The index is out of bounds");
        }
    }
}
