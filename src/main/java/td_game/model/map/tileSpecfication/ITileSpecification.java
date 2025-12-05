package td_game.model.map.tileSpecfication;

import td_game.model.map.Tile;

public interface ITileSpecification {
    boolean isSatisfiedBy(Tile tile);
}
