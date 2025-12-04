package td_game.model.towers.placementRules;

import td_game.model.map.Tile;

public interface IPlacementRule {
    boolean canBePlaced(Tile tile);
}
