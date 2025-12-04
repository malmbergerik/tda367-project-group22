package td_game.model.towers.factory;

import td_game.model.towers.ATower;

public interface ITowerFactory {
    ATower createTower(int x, int y);
}
