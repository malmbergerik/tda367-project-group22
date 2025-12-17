package td_game.model.towers.attackStrategies;

import td_game.model.enemy.ABaseEnemy;
import td_game.model.projectile.factory.IProjectileFactory;
import td_game.model.projectile.Projectile;
import td_game.model.projectile.ProjectileManager;
import td_game.model.towers.ATower;
import td_game.model.towers.MathHelper;

/**
 * An attack strategy that fires a single projectile directly at the target.
 * This is the standard behavior for most basic towers (e.g., Canon, Sniper).
 */
public class SingleAttackStrategy implements IAttackStrategy{

    private final IProjectileFactory projectileFactory;
    private final ProjectileManager projectileManager;

    /**
     * Constructs a new SingleAttackStrategy.
     *
     * @param projectileFactory The factory used to create the specific projectiles for this attack.
     * @param projectileManager The manager that handles the lifecycle of the spawned projectiles.
     */
    public SingleAttackStrategy(IProjectileFactory projectileFactory, ProjectileManager projectileManager) {
        this.projectileFactory = projectileFactory;
        this.projectileManager = projectileManager;
    }


    /**
     * Executes the single-shot attack.
     * Calculates the precise angle to the target enemy and spawns one projectile in that direction.
     *
     * @param tower   The tower performing the attack.
     * @param targets The primary target (first element) to aim at.
     */
    public void attack(ATower tower, ABaseEnemy... targets) {
        if(targets == null || targets.length ==0) return;

        ABaseEnemy enemy = targets[0];
        for (int i = 0; i < getProjectileAmount(tower); i++) {
            Projectile projectile = projectileFactory.create(
                    MathHelper.getAngleToTarget(tower, enemy),
                    tower.getX()-8,
                    tower.getY()-8
            );
            projectileManager.addProjectile(projectile);
        }

    }

    /**
     * Retrieves the number of projectiles to fire per attack.
     *
     * @param tower The tower instance (unused in this fixed implementation).
     * @return The fixed amount of projectiles (1).
     */
    @Override
    public int getProjectileAmount(ATower tower) {
        return 1;
    }
}