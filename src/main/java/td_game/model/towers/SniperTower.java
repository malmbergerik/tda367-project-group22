package td_game.model.towers;

import td_game.model.enemy.ABaseEnemy;
import td_game.model.map.Tile;
import td_game.model.projectile.damageTypeStrategy.BasicDamageTypeStrategy;
import td_game.model.projectile.factory.ProjectileFactory;
import td_game.model.projectile.ProjectileManager;
import td_game.model.projectile.lifeTimeStrategy.BasicLifeTimeStrategy;
import td_game.model.projectile.movementStrategy.BasicMovementStrategy;
import td_game.model.projectile.pierceStrategy.BasicPierceStrategy;
import td_game.model.projectile.sizeStrategy.BasicRoundSizeStrategy;
import td_game.model.towers.attackStrategies.SingleAttackStrategy;
import td_game.model.towers.cooldownStrategies.BasicCooldownStrategy;
import td_game.model.towers.placementRules.GrassOnlyPlacementRule;
import td_game.model.towers.rangeStrategies.NormalCircularRangeStrategy;
import td_game.model.towers.targetStrategy.TargetingFirst;

import java.util.Collection;

/**
 * Represents a Sniper Tower entity.
 * This tower specializes in long-range engagement and high-damage output.
 * It is configured with a very large detection radius and high pierce, making it effective against tough enemies,
 * though it suffers from a slow fire rate.
 */
public class SniperTower extends ATower{

    /**
     * Constructs a new SniperTower with predefined strategies.
     * Configured with a cost of 100, extreme range (200), and a slow cooldown (100).
     * Fires fast-moving, high-damage (50), high-pierce (10) projectiles.
     *
     * @param x                 The x-coordinate in pixels.
     * @param y                 The y-coordinate in pixels.
     * @param projectileManager The manager responsible for handling projectile entities.
     */
    public SniperTower(int x, int y, ProjectileManager projectileManager)
    {
        super(x,y,100,
                new SingleAttackStrategy(
                        new ProjectileFactory(new BasicMovementStrategy(4),new BasicPierceStrategy(10),new BasicDamageTypeStrategy(50),new BasicLifeTimeStrategy(400),new BasicRoundSizeStrategy(8,8), "Bullet"),
                        projectileManager
                ),
                new NormalCircularRangeStrategy(120),
                new BasicCooldownStrategy(100),
                new TargetingFirst(),
                new GrassOnlyPlacementRule());
    }


    /**
     * Checks if the tower can be placed on the specified tile.
     * Delegates to the GrassOnlyPlacementRule.
     *
     * @param tile The tile to check.
     * @return true if the tile is grass, false otherwise.
     */
    @Override
    public boolean canBePlaced(Tile tile) {
        return placementRule.canBePlaced(tile);
    }

    /**
     * Updates the tower logic for the current game tick.
     * Scans for enemies within the extended sniper range and fires if the long cooldown has reset.
     *
     * @param activeEnemies A collection of all currently active enemies.
     */
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