package td_game.model.towers.attackStrategies;

import td_game.model.enemy.ABaseEnemy;
import td_game.model.projectile.Projectile;
import td_game.model.projectile.ProjectileManager;
import td_game.model.projectile.factory.IProjectileFactory;
import td_game.model.towers.ATower;
import td_game.model.towers.MathHelper;

/**
 * An attack strategy that fires projectiles in a 360-degree radius around the tower.
 * Instead of targeting a specific enemy, this strategy releases a "nova" or burst of projectiles
 * evenly spaced in all directions.
 */
public class AllAroundAttackStrategy implements IAttackStrategy{

    private final IProjectileFactory projectileFactory;
    private final ProjectileManager projectileManager;

    /**
     * Constructs a new AllAroundAttackStrategy.
     *
     * @param projectileFactory The factory used to create the specific projectiles for this attack.
     * @param projectileManager The manager that handles the lifecycle of the spawned projectiles.
     */
    public AllAroundAttackStrategy(IProjectileFactory projectileFactory, ProjectileManager projectileManager) {
        this.projectileFactory = projectileFactory;
        this.projectileManager = projectileManager;
    }


    /**
     * Executes the radial attack.
     * Triggers when at least one target is present, but fires projectiles in a fixed circular pattern
     * regardless of the specific target's location.
     *
     * @param tower   The tower performing the attack.
     * @param targets The list of available targets (used only to validate that an enemy exists).
     */
    public void attack(ATower tower, ABaseEnemy... targets) {
        if(targets == null || targets.length ==0) return;

        ABaseEnemy enemy = targets[0];
        for (int i = 0; i < getProjectileAmount(tower); i++) {
            Projectile projectile = projectileFactory.create(
                    (i * 360 / getProjectileAmount(tower) ),
                    tower.getX()-8,
                    tower.getY()-8
            );
            projectileManager.addProjectile(projectile);
        }

    }

    /**
     * Retrieves the number of projectiles to fire in a single burst.
     *
     * @param tower The tower instance (unused in this fixed implementation).
     * @return The fixed amount of projectiles (20).
     */
    @Override
    public int getProjectileAmount(ATower tower) {
        return 20;
    }
}