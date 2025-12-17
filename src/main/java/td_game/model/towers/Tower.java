package td_game.model.towers;
import td_game.model.enemy.ABaseEnemy;
import td_game.model.projectile.factory.ProjectileFactory;
import td_game.model.towers.placementRules.IPlacementRule;

import java.util.ArrayList;

/**
 * The Tower class represents a generic tower entity in the game.
 * It manages its own state regarding cooldowns, positioning, and attack range.
 * Holds references to the necessary factories and rules to spawn projectiles and validate placement.
 */
public class Tower{
    private int cooldownTicks; //Ticks
    private int x;
    private int y;
    private int attackRange;
    private int projectileAmount;
    private int attackCooldownCounter;
    private ArrayList<ABaseEnemy> enemyInRange = new ArrayList<>();
    private ProjectileFactory projectileFactory;
    private IPlacementRule placementRule;

    /**
     * Constructs a new Tower with the specified attributes.
     *
     * @param cooldownTicks    The number of ticks required between attacks.
     * @param x                The x-coordinate in pixels.
     * @param y                The y-coordinate in pixels.
     * @param attackRange      The radius of the tower's attack range.
     * @param projectileAmount The number of projectiles fired per attack.
     * @param projectileFactory The factory used to create projectiles for this tower.
     * @param placementRule    The rule determining valid placement locations.
     */
    public Tower(int cooldownTicks, int x, int y, int attackRange, int projectileAmount, ProjectileFactory  projectileFactory, IPlacementRule placementRule){
        this.cooldownTicks = cooldownTicks;
        this.x = x;
        this.y = y;
        this.attackRange = attackRange;
        this.projectileAmount = projectileAmount;
        this.attackCooldownCounter = 0;
        this.projectileFactory = projectileFactory;
        this.placementRule = placementRule;
    }

    /**
     * Updates the position of the tower.
     *
     * @param positionY The new y-coordinate.
     * @param positionX The new x-coordinate.
     */
    public void setPos(int positionY, int positionX)
    {
        this.x = positionX;
        this.y = positionY;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public int getAttackRange()
    {
        return this.attackRange;
    }

    public int getProjectileAmount()
    {
        return this.projectileAmount;
    }

    /**
     * Increments the attack cooldown counter.
     * Should be called once per game tick to progress the reload cycle.
     */
    public void attackCooldownCounterTick()
    {
        this.attackCooldownCounter++;
    }

    public int getAttackCooldownCounter(){
        return this.attackCooldownCounter;
    }

    /**
     * Resets the attack cooldown counter to zero.
     * Typically called immediately after the tower fires.
     */
    public void resetCooldown()
    {
        this.attackCooldownCounter = 0;
    }

    /**
     * Checks if the tower is ready to shoot.
     *
     * @return true if the cooldown counter has reached the required threshold, false otherwise.
     */
    public boolean checkIfCanShoot(){
        return this.attackCooldownCounter >= this.cooldownTicks;
    }

    /**
     * Retrieves the list of enemies currently within the tower's range.
     *
     * @return The list of enemies in range.
     */
    public ArrayList<ABaseEnemy> getEnemiesInRange() {
        return enemyInRange;
    }

    public ProjectileFactory getProjectileFactory() { return projectileFactory; }

    public IPlacementRule getPlacementRule() {return placementRule;}
}