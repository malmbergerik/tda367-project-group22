package td_game.model.towers;
import td_game.model.enemy.EnemyType;
import java.awt.*;
public class Tower{
    private int attackCooldown; //Millisekunder
    private double x;
    private double y;
    private int attackRange;
    private long lastTimeSinceShoot = 0;

    public Tower( int attackCooldown, double x, double y, int attackRange){
        this.attackCooldown = attackCooldown;
        this.x = x;
        this.y = y;
        this.attackRange = attackRange;
    }
    public  void setPos(double positionY, double positionX)
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
    public double lenTooEnemy(EnemyType enemy)
    {
        return  Math.sqrt(Math.pow(enemy.getX() - (getTowerX() + 8),2) + Math.pow(enemy.getY()- (getY() + 8),2));
    }
    public double getAngleToEnemy(EnemyType enemy)
    {
        return  Math.atan2(enemy.getY()- (getTowerY() + 8), enemy.getX() - (getX() + 8)) * (180/Math.PI);
    }
   /*
    public EnemyType getEnemyClosest(List enemies)
    {
        for (EnemyType e: enemies)
        {

            if (lenTooEnemy(e) < this.attackRange)
            {
                EnemyType currentEnemyInList = e;
                if
            }
            return null;
        }
    }
    */

    public void shoot()
    {
        List<EnemyType> enemies = Game.enemies; //Listan med enemies som finns, det skapas en kopia för att listan inte ska kunna ändras medan den itereras i.

        if (enemies == null){return;}

        for (EnemyType e: enemies)
        {
            if ((lenTooEnemy(e) < this.attackRange) && ((System.currentTimeMillis() - lastTimeSinceShoot) > this.attackCooldown ))
            {
               // new Projectile(getAngleToEnemy(e));
                lastTimeSinceShoot = System.currentTimeMillis();
                return;
            }
            else {return;}
        }
    }
}
