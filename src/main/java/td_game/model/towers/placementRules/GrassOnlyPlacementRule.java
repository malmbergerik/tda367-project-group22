package td_game.model.towers.placementRules;

import td_game.model.map.Tile;

public class GrassOnlyPlacementRule implements IPlacementRule {

    @Override
    public boolean canBePlaced(Tile tile) {
        return("Grass" == tile.getType());
    }
}
