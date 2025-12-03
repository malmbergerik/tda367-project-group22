package td_game.model.map;

public class WaterTile extends TileBase{

    public WaterTile(){
        super("Water");
    }

    @Override
    public boolean isPathTile() {
        return false;
    }
}