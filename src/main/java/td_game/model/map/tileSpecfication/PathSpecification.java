package td_game.model.map.tileSpecfication;

import td_game.model.map.Tile;

/**
 * Specification that checks whether a {@link Tile} is a path tile.
 */

public class PathSpecification implements ITileSpecification {

    @Override
    public boolean isSatisfiedBy(Tile tile) {
        return "Path".equals(tile.getType());
    }
}
