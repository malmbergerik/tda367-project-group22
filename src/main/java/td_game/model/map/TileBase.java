package td_game.model.map;

public abstract class TileBase {

    private final TileType type;

    public TileBase(TileType type){
        this.type = type;
    }

    //Getter for a tile
    public TileType getType(){return type;}


}
