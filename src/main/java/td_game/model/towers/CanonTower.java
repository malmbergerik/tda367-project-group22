package td_game.model.towers;

import td_game.model.enemy.ABaseEnemy;
import td_game.model.map.Tile;
import td_game.model.projectile.damageTypeStrategy.BasicDamageTypeStrategy;
import td_game.model.projectile.factory.ProjectileFactory;
import td_game.model.projectile.ProjectileManager;
import td_game.model.projectile.lifeTimeStrategy.BasicLifeTimeStrategy;
import td_game.model.projectile.movementStrategy.BasicMovementStrategy;
import td_game.model.projectile.movementStrategy.IMovementStrategy;
import td_game.model.projectile.pierceStrategy.BasicPierceStrategy;
import td_game.model.projectile.sizeStrategy.BasicRoundSizeStrategy;
import td_game.model.towers.attackStrategies.SingleAttackStrategy;
import td_game.model.towers.cooldownStrategies.BasicCooldownStrategy;
import td_game.model.towers.placementRules.GrassOnlyPlacementRule;
import td_game.model.towers.rangeStrategies.NormalCircularRangeStrategy;
import td_game.model.towers.targetStrategy.TargetingFirst;

import java.util.Collection;
import java.util.List;

public class CanonTower extends ATower{

    public CanonTower(int x, int y,ProjectileManager projectileManager)
    {
        super(x,y,
                new SingleAttackStrategy(
                    new ProjectileFactory(new BasicMovementStrategy(1),new BasicPierceStrategy(3),new BasicDamageTypeStrategy(1),new BasicLifeTimeStrategy(120),new BasicRoundSizeStrategy(8,8)),
                    projectileManager
                ),
                new NormalCircularRangeStrategy(80),
                new BasicCooldownStrategy(50),
                new TargetingFirst(),
                new GrassOnlyPlacementRule());
    }

    @Override
    public boolean canBePlaced(Tile tile) {
        return placementRule.canBePlaced(tile);
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


    public List<ABaseEnemy> getEnemiesInRage() {
        return enemiesInRange;
    }
}
