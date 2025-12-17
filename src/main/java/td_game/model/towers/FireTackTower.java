package td_game.model.towers;

import td_game.model.enemy.ABaseEnemy;
import td_game.model.map.Tile;
import td_game.model.projectile.ProjectileManager;
import td_game.model.projectile.damageTypeStrategy.BasicDamageTypeStrategy;
import td_game.model.projectile.factory.ProjectileFactory;
import td_game.model.projectile.lifeTimeStrategy.BasicLifeTimeStrategy;
import td_game.model.projectile.movementStrategy.BasicMovementStrategy;
import td_game.model.projectile.pierceStrategy.BasicPierceStrategy;
import td_game.model.projectile.sizeStrategy.BasicRoundSizeStrategy;
import td_game.model.towers.attackStrategies.AllAroundAttackStrategy;
import td_game.model.towers.attackStrategies.DoubleAttackStrategy;
import td_game.model.towers.cooldownStrategies.BasicCooldownStrategy;
import td_game.model.towers.placementRules.GrassOnlyPlacementRule;
import td_game.model.towers.rangeStrategies.NormalCircularRangeStrategy;
import td_game.model.towers.targetStrategy.TargetingFirst;

import java.util.Collection;
import java.util.List;

/**
 * Represents a Fire Tack Tower entity.
 * This tower specializes in area-of-effect or multi-directional attacks using the AllAroundAttackStrategy.
 * It fires short-lived "Fireball" projectiles with high damage potential relative to its fire rate.
 */
public class FireTackTower extends ATower{

    /**
     * Constructs a new FireTackTower with predefined strategies.
     * Configured with a higher cost (450), fast cooldown (20), and specific fireball projectiles.
     * The projectiles have a short lifetime (35 ticks) and slow movement speed (0.15).
     *
     * @param x                 The x-coordinate in pixels.
     * @param y                 The y-coordinate in pixels.
     * @param projectileManager The manager responsible for handling projectile entities.
     */
    public FireTackTower(int x, int y, ProjectileManager projectileManager)
    {
        super(x,y,450,
                new AllAroundAttackStrategy(
                        new ProjectileFactory(new BasicMovementStrategy(0.15),new BasicPierceStrategy(1),new BasicDamageTypeStrategy(5),new BasicLifeTimeStrategy(35),new BasicRoundSizeStrategy(10,10), "Fireball"),

                        projectileManager
                ),
                new NormalCircularRangeStrategy(80),
                new BasicCooldownStrategy(20),
                new TargetingFirst(),
                new GrassOnlyPlacementRule());
    }

    /**
     * Checks if the tower can be placed on the specified tile.
     * Enforces the GrassOnly placement rule.
     *
     * @param tile The tile to check.
     * @return true if the tile allows placement, false otherwise.
     */
    @Override
    public boolean canBePlaced(Tile tile) {
        return placementRule.canBePlaced(tile);
    }

    /**
     * Updates the tower logic for the current game tick.
     * Manages target acquisition and triggers the all-around attack strategy if the cooldown permits.
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


    /**
     * Retrieves the list of enemies currently within the tower's range.
     *
     * @return A list of ABaseEnemy objects inside the detection radius.
     */
    public List<ABaseEnemy> getEnemiesInRage() {
        return enemiesInRange;
    }
}