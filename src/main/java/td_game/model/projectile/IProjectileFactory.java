package td_game.model.projectile;

public interface IProjectileFactory {
    Projectile create(double angle, double x, double y);
}
