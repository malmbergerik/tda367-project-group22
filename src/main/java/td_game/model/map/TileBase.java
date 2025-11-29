package td_game.model.map;

public abstract class TileBase {

    private final String type;

    public TileBase(String type){
        this.type = type;
    }

    //Getter for a tile
    public String getType(){return type;}


}