package td_game.model.projectile.factory;

import td_game.model.projectile.Projectile;
import td_game.model.projectile.damageTypeStrategy.IDamagetypeStrategy;
import td_game.model.projectile.lifeTimeStrategy.ILifeTimeStrategy;
import td_game.model.projectile.movementStrategy.IMovementStrategy;
import td_game.model.projectile.pierceStrategy.IPierceStrategy;
import td_game.model.projectile.sizeStrategy.ISizeStrategy;

/**
 * A concrete factory for creating specific types of Projectiles.
 * It holds a set of strategies acting as a "blueprint" or configuration.
 * When a new projectile is requested, it uses these stored strategies to instantiate it.
 */
public class ProjectileFactory implements IProjectileFactory {

    private final IMovementStrategy movementStrategy;
    private final IPierceStrategy pierceStrategy;
    private final IDamagetypeStrategy damagetypeStrategy;
    private final ILifeTimeStrategy lifeTimeStrategy;
    private final ISizeStrategy sizeStrategy;
    private final String name;

    /**
     * Constructs a new ProjectileFactory with a specific configuration.
     *
     * @param movementStrategy  The strategy defining how the projectile moves.
     * @param pierceStrategy    The strategy defining how many enemies it can hit.
     * @param damagetypeStrategy The strategy defining damage amount and type.
     * @param lifeTimeStrategy  The strategy defining how long the projectile exists.
     * @param sizeStrategy      The strategy defining the projectile's dimensions.
     * @param name              The name identifier for projectiles created by this factory.
     */
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

    /**
     * Creates a new Projectile instance based on the factory's blueprint.
     *
     * DISCLAIMER: Uses the .copy() method on stateful strategies (Movement, Pierce, Lifetime)
     * to ensure that each projectile has its own independent state counters.
     * Stateless strategies (Damage, Size) are passed by reference.
     *
     * @param angle The angle at which the projectile is fired.
     * @param x     The starting x-coordinate.
     * @param y     The starting y-coordinate.
     * @return A new, independent Projectile instance.
     */
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