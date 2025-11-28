package td_game.model.towers;
import td_game.model.enemy.ABaseEnemy;
import td_game.model.modelnit.GameModel;
import td_game.model.projectile.ProjectileFactory;

import java.util.ArrayList;
import java.util.List;

public class Tower{
    private int attackCooldown; //Millisekunder
    private int x;
    private int y;
    private int attackRange;
    private long lastTimeSinceShoot = 0;
    private ArrayList<ABaseEnemy> enemyInRange;
    private int projectileAmount;
    private ProjectileFactory projectileFactory;
    private GameModel gameModel;

    public Tower( int attackCooldown, int x, int y, int attackRange, int projectileAmount, ProjectileFactory projectileFactory, GameModel gameModel ){ //kommer också ta en class Projectile så att man dynamiskt kan ändra projectile
        this.attackCooldown = attackCooldown;
        this.x = x;
        this.y = y;
        this.attackRange = attackRange;
        this.enemyInRange = new ArrayList<ABaseEnemy>();
        this.projectileAmount = projectileAmount;
        this.projectileFactory = projectileFactory;
        this.gameModel = gameModel;
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
    public double lenTooEnemy(ABaseEnemy enemy)
    {
        return  Math.sqrt(Math.pow(enemy.getX() - (getX()),2) + Math.pow(enemy.getY()- (getY() ),2));
    }
    public double getAngleToEnemy(ABaseEnemy enemy)
    {
        return  Math.atan2(enemy.getY()- (getY()), enemy.getX() - (getX())) * (180/Math.PI);
    }

    public ArrayList<ABaseEnemy> getEnemyInRangeInOrder(List<ABaseEnemy> enemies)
    {
        if (enemies.isEmpty()) {return enemyInRange;}

        for (ABaseEnemy e: enemies)
        {
            if ((lenTooEnemy(e) <= this.attackRange) && !enemyInRange.contains(e))
            {
                enemyInRange.add(e);
            }
            if ((lenTooEnemy(e) > this.attackRange) && enemyInRange.contains(e))
            {
                enemyInRange.remove(e);
            }
        }
        return enemyInRange;
    }

    public void shoot()
    {
        List<ABaseEnemy> enemies = gameModel.getActiveEnemies();

        if (enemies == null){return;}
        if (getEnemyInRangeInOrder(enemies).isEmpty()) {return;}
        ABaseEnemy firstEnemyInRange = getEnemyInRangeInOrder(enemies).get(0);
        for (ABaseEnemy e: enemies)
        {
            if ((lenTooEnemy(e) < this.attackRange) && ((System.currentTimeMillis() - lastTimeSinceShoot) > this.attackCooldown))
            {
                for (int i=0; i<= this.projectileAmount; i++) {
                    projectileFactory.create((getAngleToEnemy( (firstEnemyInRange )) - 15*Math.round(0.5*projectileAmount) + 15*i),this.x,this.y);
                }
                lastTimeSinceShoot = System.currentTimeMillis();
                return;
            }
        }
    }
}
