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
import td_game.model.towers.attackStrategies.DoubleAttackStrategy;
import td_game.model.towers.attackStrategies.SingleAttackStrategy;
import td_game.model.towers.cooldownStrategies.BasicCooldownStrategy;
import td_game.model.towers.placementRules.GrassOnlyPlacementRule;
import td_game.model.towers.rangeStrategies.NormalCircularRangeStrategy;
import td_game.model.towers.targetStrategy.TargetingFirst;

import java.util.Collection;
import java.util.List;

/**
 * Represents a Flame Thrower Tower entity.
 * This tower is characterized by a very high rate of fire and short range.
 * It utilizes a DoubleAttackStrategy to unleash a rapid stream of projectiles.
 */
public class FlameThrowerTower extends ATower{

    /**
     * Constructs a new FlameThrowerTower with predefined strategies.
     * Configured with a cost of 250, a very short range (48), and an extremely fast cooldown (2).
     * Fires slow-moving "Fireball" projectiles using a DoubleAttackStrategy.
     *
     * @param x                 The x-coordinate in pixels.
     * @param y                 The y-coordinate in pixels.
     * @param projectileManager The manager responsible for handling projectile entities.
     */
    public FlameThrowerTower(int x, int y, ProjectileManager projectileManager)
    {
        super(x,y,250,
                new DoubleAttackStrategy(
                        new ProjectileFactory(new BasicMovementStrategy(0.07),new BasicPierceStrategy(1),new BasicDamageTypeStrategy(1),new BasicLifeTimeStrategy(50),new BasicRoundSizeStrategy(12,12), "Fireball"),

                        projectileManager
                ),
                new NormalCircularRangeStrategy(48),
                new BasicCooldownStrategy(2),
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
     * Manages the rapid firing cycle by checking cooldowns and available targets within the short range.
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