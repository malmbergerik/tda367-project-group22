package td_game.model.projectile;

public class ProjectileFactory implements IProjectileFactory {
    private final int pixelsPerTick;
    private final int width;
    private final int height;
    private final int damage;
    private final int pierce;
    private final int timeAliveTicks;
    private final boolean hitBoxRound;

    public ProjectileFactory(
            int pixelsPerTick,
            int width,
            int height,
            int damage,
            int pierce,
            int timeAliveTicks,
            boolean hitBoxRound
    ) {
        this.pixelsPerTick = pixelsPerTick;
        this.width = width;
        this.height = height;
        this.damage = damage;
        this.pierce = pierce;
        this.timeAliveTicks = timeAliveTicks;
        this.hitBoxRound = hitBoxRound;
    }

    @Override
    public Projectile create(double angle, int x, int y) {
        return new Projectile(
                angle,
                this.pixelsPerTick,
                this.width,
                this.height,
                this.damage,
                this.pierce,
                this.timeAliveTicks,
                x,
                y,
                this.hitBoxRound
        );
    }
}