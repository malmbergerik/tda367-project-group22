package td_game.model.towers.attackStrategies;

import td_game.model.enemy.ABaseEnemy;
import td_game.model.projectile.IProjectileFactory;
import td_game.model.projectile.Projectile;
import td_game.model.projectile.ProjectileManager;
import td_game.model.towers.ATower;

import java.util.List;

public class SingleAttackStrategy implements IAttackStrategy{

    private final IProjectileFactory projectileFactory;
    private final ProjectileManager projectileManager;

    public SingleAttackStrategy(IProjectileFactory projectileFactory, ProjectileManager projectileManager) {
        this.projectileFactory = projectileFactory;
        this.projectileManager = projectileManager;
    }

    @Override
    public void attack(ATower tower, List<ABaseEnemy> targets) {
        if(targets.isEmpty()) return;

        ABaseEnemy enemy = targets.get(0);
        Projectile projectile = projectileFactory.create(
                Math.atan2(
                        enemy.getY() - tower.getY(),
                        enemy.getX() - tower.getX()
                ),
                tower.getX(),
                tower.getY()
        );

        projectileManager.addProjectile(projectile);
    }

    @Override
    public int getProjectileAmount(ATower tower) {
        return 1;
    }
}
