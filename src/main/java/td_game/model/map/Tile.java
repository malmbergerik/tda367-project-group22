package td_game.model.map;

/**
 * Represents a single tile with a specific type.
 */
public class Tile {

    private final String type;

    public Tile(String type){
        this.type = type;
    }

    /** @return the tile type */
    public String getType(){return type;}


}