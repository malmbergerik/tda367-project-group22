package td_game.model.map;

public class GridMap implements IMap{
    private int row;
    private int col;
    private TileBase[][] tiles;
    private int tileSize;

    public GridMap(int row, int col, int tileSize){
        this.row = row;
        this.col = col;
        this.tileSize = tileSize;
        this.tiles = new TileBase[row][col];
    }

    public int getRow() {return row;}
    public int getCol() {return col;}

    @Override
    public int getWidth() {
        return col*tileSize;
    }

    @Override
    public int getHeight() {
        return row*tileSize;
    }

    @Override
    public int getTileSize() {return tileSize;}

    public TileBase[][] getMap() {return tiles;}

    @Override
    public TileBase getTile(int x, int y) {
        if (x >= 0 && x < row && y >= 0 && y < col) {
            return tiles[x][y];
        }
        else {
            throw new IndexOutOfBoundsException("The index is out of bounce");
        }
    }

    @Override
    public void setTile(int x, int y, TileBase tile) {
        if (x >= 0 && x < row && y >= 0 && y < col) {
            tiles[x][y] = tile;
        } else {
            throw new IndexOutOfBoundsException("The index is out of bounds");
        }
    }
}
