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

/**
 * Represents a Canon Tower entity.
 * This tower type is configured with high-pierce projectiles (pierce 5) and a specific set of strategies.
 * It uses a factory to spawn "Bullet" projectiles and targets the first enemy along the path.
 */
public class CanonTower extends ATower{

    /**
     * Constructs a new CanonTower with predefined strategies.
     * Sets up the projectile factory, range (80), cooldown (50), and grass-only placement rules.
     *
     * @param x                 The x-coordinate in pixels.
     * @param y                 The y-coordinate in pixels.
     * @param projectileManager The manager responsible for handling projectile entities.
     */
    public CanonTower(int x, int y,ProjectileManager projectileManager)
    {
        super(x,y,25,
                new SingleAttackStrategy(
                        new ProjectileFactory(new BasicMovementStrategy(1),new BasicPierceStrategy(5),new BasicDamageTypeStrategy(7),new BasicLifeTimeStrategy(120),new BasicRoundSizeStrategy(50,50), "Bullet"),
                        projectileManager
                ),
                new NormalCircularRangeStrategy(60),
                new BasicCooldownStrategy(50),
                new TargetingFirst(),
                new GrassOnlyPlacementRule());
    }

    /**
     * Checks if the tower can be placed on the specified tile.
     * Enforces the specific placement rule (GrassOnly) for this tower type.
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
     * Identifies enemies in range, processes cooldowns, and executes the attack strategy if ready.
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