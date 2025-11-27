package td_game.model.projectile;

import org.junit.jupiter.api.Assertions;
import td_game.model.enemy.ABaseEnemy;
import td_game.model.enemy.Skeleton;
import td_game.model.path.Path;
import td_game.model.path.Waypoint;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.*;
import java.util.List;

public class ProjectileTest {

    @org.junit.jupiter.api.Test
    public void testMoveProjectile()
    {
        Projectile p = new Projectile(135,2,10,10,1,2,10000, 10 , 10);

        p.update();

        Assertions.assertEquals(9, p.getX());
        Assertions.assertEquals(11, p.getY());
    }

    @org.junit.jupiter.api.Test
    public void testHitEnemy()
    {
        List<Waypoint> pathList = new ArrayList<Waypoint>();
        ABaseEnemy enemy1 = new Skeleton(1,1,new Path(pathList));
        Projectile p = new Projectile(90,2,10,10,1,2,10000,(int) enemy1.getX() ,(int) enemy1.getY());
        p.addTestEnemyList(enemy1);

        p.update();

        Assertions.assertEquals(0, enemy1.getHealth() );

    }

    @org.junit.jupiter.api.Test
    public void testPierceEnemy()
    {
        List<Waypoint> pathList = new ArrayList<Waypoint>();
        ABaseEnemy enemy1 = new Skeleton(1,1,new Path(pathList));
        ABaseEnemy enemy2 = new Skeleton(2,1,new Path(pathList));
        Projectile p = new Projectile(90,2,10,10,1,2,10000,(int) enemy1.getX() ,(int) enemy1.getY());
        p.addTestEnemyList(enemy1);
        p.addTestEnemyList(enemy2);

        p.update();

        Assertions.assertEquals(0, enemy1.getHealth());
        Assertions.assertEquals(1, enemy2.getHealth());
    }

    @org.junit.jupiter.api.Test
    public void testMoveAndHit()
    {
        List<Waypoint> pathList = new ArrayList<Waypoint>();
        ABaseEnemy enemy1 = new Skeleton(1,1,new Path(pathList));
        Projectile p = new Projectile(180,10,5,5,1,2,10000,(int) enemy1.getX()+ 25 ,(int) enemy1.getY());
        p.addTestEnemyList(enemy1);
        p.collisionProjectileEnemy();

        Assertions.assertEquals(1, enemy1.getHealth());
        p.update();
        Assertions.assertEquals(1, enemy1.getHealth());
        p.update();
        Assertions.assertEquals(0, enemy1.getHealth());
    }

    @org.junit.jupiter.api.Test
    public void testDieAfterTime()
    {

        Projectile z = new Projectile(180,10,5,5,1,2,1,5 ,5);


        Assertions.assertEquals(true, z.getIsAlive());
        z.update();
        z.update();
        System.out.println(z.getIsAlive());

        Assertions.assertEquals(false, z.getIsAlive());
    }
}
