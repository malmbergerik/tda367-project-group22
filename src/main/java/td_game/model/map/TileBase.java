package td_game.model.map;

public abstract class TileBase {

    private final String type;

    public TileBase(String type){
        this.type = type;
    }

    //Getter for a tile
    public String getType(){return type;}

    /**
     * Determines if this tile is intended to be part of the path route.
     * Replaces the need for "instanceof PathTile".
     */
    public abstract boolean isTraversable();

}