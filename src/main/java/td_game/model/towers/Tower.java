package td_game.model.towers;
import td_game.model.enemy.ABaseEnemy;
import td_game.model.projectile.ProjectileFactory;
import td_game.model.towers.placementRules.IPlacementRule;

import java.util.ArrayList;

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
    public void attackCooldownCounterTick()
    {
        this.attackCooldownCounter++;
    }
    public int getAttackCooldownCounter(){
        return this.attackCooldownCounter;
    }
    public void resetCooldown()
    {
        this.attackCooldownCounter = 0;
    }
    public boolean checkIfCanShoot(){
        return this.attackCooldownCounter >= this.cooldownTicks;
    }

    public ArrayList<ABaseEnemy> getEnemiesInRange() {
        return enemyInRange;
    }

    public ProjectileFactory getProjectileFactory() { return projectileFactory; }

    public IPlacementRule getPlacementRule() {return placementRule;}
}
