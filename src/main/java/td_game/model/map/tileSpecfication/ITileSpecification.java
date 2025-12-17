package td_game.model.map.tileSpecfication;

import td_game.model.map.Tile;

/**
 * Defines a specification to check if a {@link Tile} meets certain criteria.
 */

public interface ITileSpecification {
    boolean isSatisfiedBy(Tile tile);
}
