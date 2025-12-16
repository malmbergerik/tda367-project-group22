package td_game.model.towers.attackStrategies;

import td_game.model.enemy.ABaseEnemy;
import td_game.model.projectile.Projectile;
import td_game.model.projectile.ProjectileManager;
import td_game.model.projectile.factory.IProjectileFactory;
import td_game.model.towers.ATower;
import td_game.model.towers.MathHelper;

public class AllAroundAttackStrategy implements IAttackStrategy{

    private final IProjectileFactory projectileFactory;
    private final ProjectileManager projectileManager;

    public AllAroundAttackStrategy(IProjectileFactory projectileFactory, ProjectileManager projectileManager) {
        this.projectileFactory = projectileFactory;
        this.projectileManager = projectileManager;
    }


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

    @Override
    public int getProjectileAmount(ATower tower) {
        return 20;
    }
}
