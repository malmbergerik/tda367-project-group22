package td_game.model.projectile;

import td_game.model.enemy.ABaseEnemy;
import td_game.model.enemy.EnemyManager;
import td_game.model.modelnit.GameModel;

import java.util.List;

public class ProjectileFactory implements IProjectileFactory{
    private final int pixelsPerTick;
    private final int width;
    private final int height;
    private final int damage;
    private final int pierce;
    private final int timeAliveTicks;
    private final GameModel gameModel;

    public ProjectileFactory(
            int pixelsPerTick,
            int width,
            int height,
            int damage,
            int pierce,
            int timeAliveTicks,
            GameModel gameModel
    ) {
        this.pixelsPerTick = pixelsPerTick;
        this.width = width;
        this.height = height;
        this.damage = damage;
        this.pierce = pierce;
        this.timeAliveTicks = timeAliveTicks;
        this.gameModel = gameModel;
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
                y,
                gameModel



        );
    }
}
