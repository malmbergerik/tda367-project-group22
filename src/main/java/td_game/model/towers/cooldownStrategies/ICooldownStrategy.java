package td_game.model.towers.cooldownStrategies;

import td_game.model.towers.ATower;

public interface ICooldownStrategy {

    boolean canShoot(int currentCounter, ATower tower);

    void tick();

    void reset();

    int getCoolDownTicks();

}