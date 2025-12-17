package td_game.model.towers.attackStrategies;

import td_game.model.enemy.ABaseEnemy;
import td_game.model.projectile.Projectile;
import td_game.model.projectile.ProjectileManager;
import td_game.model.projectile.factory.IProjectileFactory;
import td_game.model.towers.ATower;
import td_game.model.towers.MathHelper;

/**
 * An attack strategy that fires two projectiles simultaneously.
 * The first projectile targets the enemy directly, while the second is fired with a slight angular offset.
 */
public class DoubleAttackStrategy implements IAttackStrategy{

    private final IProjectileFactory projectileFactory;
    private final ProjectileManager projectileManager;

    /**
     * Constructs a new DoubleAttackStrategy.
     *
     * @param projectileFactory The factory used to create the specific projectiles for this attack.
     * @param projectileManager The manager that handles the lifecycle of the spawned projectiles.
     */
    public DoubleAttackStrategy(IProjectileFactory projectileFactory, ProjectileManager projectileManager) {
        this.projectileFactory = projectileFactory;
        this.projectileManager = projectileManager;
    }


    /**
     * Executes the double attack.
     * Fires multiple projectiles based on the configured amount.
     * Each subsequent projectile in the burst is offset by -10 degrees relative to the target angle.
     *
     * @param tower   The tower performing the attack.
     * @param targets The primary target (and potentially others) for the attack.
     */
    public void attack(ATower tower, ABaseEnemy... targets) {
        if(targets == null || targets.length ==0) return;

        ABaseEnemy enemy = targets[0];
        for (int i = 0; i < getProjectileAmount(tower); i++) {
            Projectile projectile = projectileFactory.create(
                    (MathHelper.getAngleToTarget(tower, enemy) - 10*i),
                    tower.getX() - 8,
                    tower.getY() - 8
            );
            projectileManager.addProjectile(projectile);
        }

    }

    /**
     * Retrieves the number of projectiles to fire per attack.
     *
     * @param tower The tower instance (unused in this fixed implementation).
     * @return The fixed amount of projectiles (2).
     */
    @Override
    public int getProjectileAmount(ATower tower) {
        return 2;
    }
}