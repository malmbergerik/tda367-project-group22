package td_game.model.map;

public abstract class TileBase {

    // Look into the need for x and y coordinates

    private final int x;
    private final int y;
    private final TileType type;

    public TileBase(int x, int y, TileType type){
        this.x = x;
        this.y = y;
        this.type = type;
    }

    //Getters for a tile
    public int getX(){return x;}
    public int getY(){return y;}
    public TileType getType(){return type;}


}
