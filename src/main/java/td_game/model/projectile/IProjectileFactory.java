package td_game.model.projectile;

public interface IProjectileFactory {
    Projectile create(double angle, int x, int y);
}
