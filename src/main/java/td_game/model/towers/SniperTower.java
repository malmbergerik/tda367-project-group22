package td_game.model.towers;

import td_game.model.enemy.ABaseEnemy;
import td_game.model.map.GrassTile;
import td_game.model.map.TileBase;
import td_game.model.projectile.ProjectileFactory;
import td_game.model.projectile.ProjectileManager;
import td_game.model.towers.attackStrategies.SingleAttackStrategy;
import td_game.model.towers.cooldownStrategies.BasicCooldownStrategy;
import td_game.model.towers.rangeStrategies.NormalCircularRangeStrategy;
import td_game.model.towers.targetStrategy.TargetingFirst;

import java.util.Collection;

public class SniperTower extends ATower{

    public SniperTower(int x, int y, ProjectileManager projectileManager)
    {
        super(x,y,
                new SingleAttackStrategy(
                        new ProjectileFactory(6,4,4,1,1,400,true),
                        projectileManager
                ),
                new NormalCircularRangeStrategy(200),
                new BasicCooldownStrategy(400),
                new TargetingFirst());
    }

    @Override
    public boolean canBePlaced(TileBase tile) {
        return (tile instanceof GrassTile);
    }

    public void update(Collection<ABaseEnemy> activeEnemies){
        enemiesInRange.clear();
        for(ABaseEnemy enemy : activeEnemies){
            if(rangeStrategy.isInRange(this, enemy.getX(), enemy.getY())){
                enemiesInRange.add(enemy);
            }
        }
        cooldownStrategy.tick();

        if (cooldownStrategy.canShoot(cooldownStrategy.getCoolDownTicks(), this)){
            targetStrategy.selectTarget(this, enemiesInRange)
                    .ifPresent(target -> attackStrategy.attack(this,target));
            cooldownStrategy.reset();
        }

    }
}
