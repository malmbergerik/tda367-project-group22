package td_game.model.map;

public class WaterTile extends TileBase{

    public WaterTile(){
        super("Water");
    }

    @Override
    public boolean isTraversable() {
        return false;
    }

    public PathType getPathRole() {
        return PathType.NONE;
    }
}