package td_game.model.projectile.lifeTimeStrategy;

import td_game.model.projectile.Projectile;

public interface ILifeTimeStrategy {
    void updateLifetime();
    BasicLifeTimeStrategy copy();
    boolean isAlive(Projectile p);
}
