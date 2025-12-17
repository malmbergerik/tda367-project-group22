package td_game.model.towers;

import td_game.model.enemy.ABaseEnemy;
import td_game.model.map.Tile;
import td_game.model.towers.attackStrategies.IAttackStrategy;
import td_game.model.towers.cooldownStrategies.ICooldownStrategy;
import td_game.model.towers.placementRules.IPlacementRule;
import td_game.model.towers.rangeStrategies.IRangeStrategy;
import td_game.model.towers.targetStrategy.ITargetStrategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The abstract base class for all towers in the game.
 * Uses the Strategy Pattern to compose tower behavior dynamically.
 * Instead of subclassing for every variation, towers are defined by their
 * specific strategies for attacking, targeting, cooling down, and placement.
 */
public abstract class ATower implements IPositionable, IPlacementRule, IValue {

    private int x;
    private int y;
    private int value;
    protected List<ABaseEnemy> enemiesInRange = new ArrayList<>();
    protected IAttackStrategy attackStrategy;
    protected IRangeStrategy rangeStrategy;
    protected ICooldownStrategy cooldownStrategy;
    protected ITargetStrategy targetStrategy;
    protected IPlacementRule placementRule;

    /**
     * Constructs a new Tower with the specified strategies and initial position.
     *
     * @param x                The x-coordinate (top-left) in pixels.
     * @param y                The y-coordinate (top-left) in pixels.
     * @param value            The monetary value (cost) of the tower.
     * @param attackStrategy   Defines how the tower attacks (e.g., spawn projectile, instant hit).
     * @param rangeStrategy    Defines the tower's range logic.
     * @param cooldownStrategy Defines the rate of fire.
     * @param targetStrategy   Defines how the tower selects a target from enemies in range.
     * @param placementRule    Defines where the tower is allowed to be placed.
     */
    public ATower(
            int x, int y, int value,
            IAttackStrategy attackStrategy,
            IRangeStrategy rangeStrategy,
            ICooldownStrategy cooldownStrategy,
            ITargetStrategy targetStrategy,
            IPlacementRule placementRule)

    {
        this.value = value;
        // Offset position to center the logic anchor point relative to the tile
        this.x = x+8;
        this.y = y+8;
        this.attackStrategy = attackStrategy;
        this.rangeStrategy = rangeStrategy;
        this.cooldownStrategy = cooldownStrategy;
        this.targetStrategy = targetStrategy;
        this.placementRule = placementRule;
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public void setPos(int positionY, int positionX) {
        this.x = positionX;
        this.y = positionY;
    }

    /**
     * Checks if the tower can be placed on the specified tile.
     * Delegates the check to the assigned PlacementRule.
     *
     * @param tile The tile to check against.
     * @return true if placement is valid, false otherwise.
     */
    @Override
    public boolean canBePlaced(Tile tile) {
        return placementRule.canBePlaced(tile);
    }

    /**
     * Updates the tower logic for the current game tick.
     * 1. Identifies enemies within range.
     * 2. Ticks the cooldown timer.
     * 3. If ready to shoot, selects a target and executes the attack strategy.
     *
     * @param activeEnemies A collection of all currently active enemies in the game.
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
     * Retrieves the monetary value of the tower.
     *
     * @return The value/cost.
     */
    @Override
    public int getValue() {
        return value;
    }

    /**
     * Retrieves the maximum attack range of the tower.
     *
     * @return The range in pixels.
     */
    public int getRange() {
        return rangeStrategy.getRange();
    }
}