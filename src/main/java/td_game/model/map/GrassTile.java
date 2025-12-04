package td_game.model.map;

public class GrassTile extends TileBase{

    public GrassTile(){
        super("Grass");
    }

    @Override
    public boolean isTraversable() {
        return false; // Not part of the path route
    }
    @Override
    public PathType getPathRole() {
        return PathType.NONE;
    }
}
