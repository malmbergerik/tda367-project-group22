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
    public int getHeight() {
        return col*tileSize;
    }

    @Override
    public int getWidth() {
        return row*tileSize;
    }

    @Override
    public int getTileSize() {return tileSize;}

    public TileBase[][] getMap() {return tiles;}

    @Override
    public TileBase getTile(int x, int y) {
        if(x<=col && x>=0 && y<=row && y>=0){
            return tiles[x][y];
        }
        throw new IndexOutOfBoundsException("The index is out of bounce");
    }

    @Override
    public void setTile(int x, int y, TileBase tile) {
        if(x<=row && x>=0 && y<=col && y>=0) {
            tiles[x][y] = tile;
        }
        throw new IndexOutOfBoundsException("The index is out of bounds");
    }
}
