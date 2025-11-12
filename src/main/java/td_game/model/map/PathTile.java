package td_game.model.map;

import java.nio.file.Path;

public class PathTile extends TileBase{

    private final PathType pathType;

    public PathTile(int x, int y, PathType pathType){
        super(x,y,TileType.PATH);
        this.pathType = pathType;
    }

    public PathType getPathType() {
        return pathType;
    }
}
