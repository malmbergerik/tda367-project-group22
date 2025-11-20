package td_game.model.towers;
import td_game.model.enemy.ABaseEnemy;
import java.util.ArrayList;

public class Tower{
    private int attackCooldown; //Millisekunder
    private double x;
    private double y;
    private int attackRange;
    private long lastTimeSinceShoot = 0;
    private ArrayList<ABaseEnemy> enemyInRange;
    private ArrayList<ABaseEnemy> testEnemyList; //Test lista för att kunna testa koden

    public Tower( int attackCooldown, double x, double y, int attackRange ){ //kommer också ta en class Projectile så att man dynamiskt kan ändra projectile
        this.attackCooldown = attackCooldown;
        this.x = x;
        this.y = y;
        this.attackRange = attackRange;
        this.testEnemyList = new ArrayList<ABaseEnemy>();
        this.enemyInRange = new ArrayList<ABaseEnemy>();
    }
    public void addEnemyInTestList(ABaseEnemy enemy) //Test lista för att kunna testa koden
    {
        testEnemyList.add(enemy);
    }
    public ArrayList<ABaseEnemy> getEnemyInTestList() //Test lista för att kunna testa koden
    {
        return testEnemyList;
    }
    public void setPos(double positionY, double positionX)
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
        return  Math.atan2(enemy.getY()- (getY() + 8), enemy.getX() - (getX() + 8)) * (180/Math.PI);
    }

    public ArrayList<ABaseEnemy> getEnemyInRangeInOrder(ArrayList<ABaseEnemy> enemies)
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
        ArrayList<ABaseEnemy> enemies = testEnemyList; //Listan med enemies som finns, det skapas en kopia för att listan inte ska kunna ändras medan den itereras i.

        if (enemies == null){return;}
        if (getEnemyInRangeInOrder(enemies).isEmpty()) {return;}
        ABaseEnemy firstEnemyInRange = getEnemyInRangeInOrder(enemies).get(0);
        for (ABaseEnemy e: enemies)
        {
            if ((lenTooEnemy(e) < this.attackRange) && ((System.currentTimeMillis() - lastTimeSinceShoot) > this.attackCooldown))
            {
                //new Projectile(getAngleToEnemy(firstEnemyInRange));
                lastTimeSinceShoot = System.currentTimeMillis();
                return;
            }
        }
    }
}
