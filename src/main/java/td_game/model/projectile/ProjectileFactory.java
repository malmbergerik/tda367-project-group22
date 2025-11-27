package td_game.model.projectile;

public class ProjectileFactory implements IProjectileFactory{
    private final int pixelsPerMs;
    private final int width;
    private final int height;
    private final int damage;
    private final int pierce;
    private final int timeAliveMs;

    public ProjectileFactory(
            int pixelsPerMs,
            int width,
            int height,
            int damage,
            int pierce,
            int timeAliveMs
    ) {
        this.pixelsPerMs = pixelsPerMs;
        this.width = width;
        this.height = height;
        this.damage = damage;
        this.pierce = pierce;
        this.timeAliveMs = timeAliveMs;
    }

    @Override
    public Projectile create(double angle, int x, int y) {
        return new Projectile(
                angle,
                pixelsPerMs,
                width,
                height,
                damage,
                pierce,
                timeAliveMs,
                x,
                y
        );
    }
}
