package td_game.model.map.tileSpecfication;

import td_game.model.map.Tile;

public class PathSpecification implements ITileSpecification {

    @Override
    public boolean isSatisfiedBy(Tile tile) {
        return "Path".equals(tile.getType());
    }
}
