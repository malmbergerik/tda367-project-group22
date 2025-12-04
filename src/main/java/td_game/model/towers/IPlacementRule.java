package td_game.model.towers;

import td_game.model.map.Tile;

public interface IPlacementRule {
    boolean canBePlaced(Tile tile);
}
