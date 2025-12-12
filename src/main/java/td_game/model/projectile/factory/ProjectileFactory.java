package td_game.model.projectile.factory;

import td_game.model.projectile.Projectile;
import td_game.model.projectile.damageTypeStrategy.IDamagetypeStrategy;
import td_game.model.projectile.lifeTimeStrategy.ILifeTimeStrategy;
import td_game.model.projectile.movementStrategy.IMovementStrategy;
import td_game.model.projectile.pierceStrategy.IPierceStrategy;
import td_game.model.projectile.sizeStrategy.ISizeStrategy;

public class ProjectileFactory implements IProjectileFactory {

    private final IMovementStrategy movementStrategy;
    private final IPierceStrategy pierceStrategy;
    private final IDamagetypeStrategy damagetypeStrategy;
    private final ILifeTimeStrategy lifeTimeStrategy;
    private final ISizeStrategy sizeStrategy;
    private final String name;

    public ProjectileFactory(
            IMovementStrategy movementStrategy,
            IPierceStrategy pierceStrategy,
            IDamagetypeStrategy damagetypeStrategy,
            ILifeTimeStrategy lifeTimeStrategy,
            ISizeStrategy sizeStrategy,
            String name
    ) {
        this.movementStrategy = movementStrategy;
        this.pierceStrategy = pierceStrategy;
        this.damagetypeStrategy = damagetypeStrategy;
        this.lifeTimeStrategy = lifeTimeStrategy;
        this.sizeStrategy = sizeStrategy;
        this.name = name;
    }

    @Override
    public Projectile create(double angle, double x, double y) {
        return new Projectile(
                angle,
                x,
                y,
                movementStrategy.copy(),
                this.pierceStrategy.copy(),
                this.damagetypeStrategy,
                this.lifeTimeStrategy.copy(),
                this.sizeStrategy,
                this.name



        );
    }
}