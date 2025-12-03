package td_game.model.map;

public class PathTile extends TileBase{

    private final PathType pathType;

    public PathTile(PathType pathType){
        super("Path");
        this.pathType = pathType;
    }

    public PathType getPathType() {
        return pathType;
    }

    @Override
    public boolean isPathTile() {
        return true;
    }
}
