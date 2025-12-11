package td_game.model.projectile.factory;

import td_game.model.projectile.Projectile;

public interface IProjectileFactory {
    Projectile create(double angle, double x, double y);
}
