package td_game.model.towers.cooldownStrategies;

import td_game.model.towers.ATower;

public class BasicCooldownStrategy implements ICooldownStrategy {
    private final int cooldDownTicks;
    private int counter;

    public BasicCooldownStrategy(int cooldDownTicks){
        this.cooldDownTicks = cooldDownTicks;
        this.counter = cooldDownTicks;
    }

    @Override
    public boolean canShoot(int currentCounter, ATower tower) {
        return counter >= cooldDownTicks;
    }

    @Override
    public void tick() {
        counter ++;
    }

    @Override
    public void reset() {
        counter = 0;
    }

    @Override
    public int getCoolDownTicks() {
        return cooldDownTicks;
    }
}
