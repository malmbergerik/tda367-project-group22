package td_game.model.projectile;

public class ProjectileFactory implements IProjectileFactory{
    private final int pixelsPerTick;
    private final int width;
    private final int height;
    private final int damage;
    private final int pierce;
    private final int timeAliveTicks;

    public ProjectileFactory(
            int pixelsPerTick,
            int width,
            int height,
            int damage,
            int pierce,
            int timeAliveTicks
    ) {
        this.pixelsPerTick = pixelsPerTick;
        this.width = width;
        this.height = height;
        this.damage = damage;
        this.pierce = pierce;
        this.timeAliveTicks = timeAliveTicks;
    }

    @Override
    public Projectile create(double angle, int x, int y) {
        return new Projectile(
                angle,
                pixelsPerTick,
                width,
                height,
                damage,
                pierce,
                timeAliveTicks,
                x,
                y
        );
    }
}
