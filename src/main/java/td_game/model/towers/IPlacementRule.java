package td_game.model.towers;

import td_game.model.map.TileBase;

public interface IPlacementRule {
    boolean canBePlaced(TileBase tile);
}
