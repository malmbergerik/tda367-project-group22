package td_game.model.projectile;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import td_game.model.enemy.ABaseEnemy;
import td_game.model.enemy.Skeleton;
import td_game.model.modelnit.GameModel;
import td_game.model.path.Path;
import td_game.model.path.Waypoint;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.*;
import java.util.List;

public class ProjectileTest {
    private GameModel gameModel;
    private ProjectileManager pM;

    @BeforeEach
    public void setup() {
        gameModel = new GameModel(32);
        pM = new ProjectileManager(new ProjectileFactory(2,10,10,1,2,10000,true), gameModel);
    }


    @org.junit.jupiter.api.Test
    public void testMoveProjectile()
    {
        pM.createProjectile(135,1,10,10,1,2,10000, 10 , 10,  true);

        pM.update();

        Assertions.assertEquals(9, pM.getActiveProjectiles().get(0).getX());
        Assertions.assertEquals(11, pM.getActiveProjectiles().get(0).getY());
    }

    @org.junit.jupiter.api.Test
    public void testHitEnemyEllipse()
    {
        List<Waypoint> pathList = new ArrayList<Waypoint>();
        ABaseEnemy enemy1 = new Skeleton(1,1,new Path(pathList));
        pM.createProjectile(90,2,10,10,1,2,10000,(int) enemy1.getX() ,(int) enemy1.getY(),  true);
        gameModel.addEnemy(enemy1);
        pM.update();

        Assertions.assertEquals(0, enemy1.getHealth() );
    }
    /*
    @org.junit.jupiter.api.Test
    public void testHitEnemyRectangle()
    {
        List<Waypoint> pathList = new ArrayList<Waypoint>();
        ABaseEnemy enemy1 = new Skeleton(1,1,new Path(pathList));
        Projectile p = new Projectile(90,2,10,10,1,2,10000,(int) enemy1.getX() ,(int) enemy1.getY(), false);
        gameModel.addEnemy(enemy1);
        pM.update();

        Assertions.assertEquals(0, enemy1.getHealth() );
    }

    @org.junit.jupiter.api.Test
    public void testDestroyedWhen0Pierce()
    {
        List<Waypoint> pathList = new ArrayList<Waypoint>();
        ABaseEnemy enemy1 = new Skeleton(1,1,new Path(pathList));
        ABaseEnemy enemy2 = new Skeleton(1,1,new Path(pathList));
        Projectile p = new Projectile(90,2,10,10,1,1,10000,(int) enemy1.getX() ,(int) enemy1.getY(), true);

        gameModel.addEnemy(enemy1);
        gameModel.addEnemy(enemy2);
        pM.update();

        Assertions.assertEquals(0, enemy1.getHealth());
        Assertions.assertEquals(1, enemy2.getHealth());
    }

    @org.junit.jupiter.api.Test
    public void testHitBoxLocationUpdates()
    {
        List<Waypoint> pathList = new ArrayList<Waypoint>();
        ABaseEnemy enemy1 = new Skeleton(1,1,new Path(pathList));
        Projectile p = new Projectile(180,10,5,5,1,2,10000,(int) enemy1.getX()+ 25 ,(int) enemy1.getY(), true );

        gameModel.addEnemy(enemy1);
        Assertions.assertEquals(1, enemy1.getHealth());
        pM.update();
        Assertions.assertEquals(1, enemy1.getHealth());
        pM.update();
        Assertions.assertEquals(0, enemy1.getHealth());
    }

    @org.junit.jupiter.api.Test
    public void testDieAfterTime()
    {

        Projectile z = new Projectile(180,10,5,5,1,2,1,5 ,5, true);


        Assertions.assertEquals(true, z.getIsAlive());
        pM.update();
        pM.update();

        Assertions.assertEquals(false, z.getIsAlive());
    }

    @org.junit.jupiter.api.Test
    public void testNotHitSameEnemySameFrame()
    {
        List<Waypoint> pathList = new ArrayList<Waypoint>();
        Projectile p = new Projectile(180,0,50,50,1,20,100,5 ,5,  true);
        ABaseEnemy enemy1 = new Skeleton(5,1,new Path(pathList));

        gameModel.addEnemy(enemy1);

        Assertions.assertEquals(5, enemy1.getHealth());
        pM.update();
        Assertions.assertEquals(4, enemy1.getHealth());
        pM.update();
        Assertions.assertEquals(3, enemy1.getHealth());
    }
    @org.junit.jupiter.api.Test
    public void testCanHitSeveralEnemiesSameTime()
    {
        List<Waypoint> pathList = new ArrayList<Waypoint>();
        Projectile p = new Projectile(180,0,50,50,1,20,100,5 ,5,  true);
        ABaseEnemy enemy1 = new Skeleton(5,1,new Path(pathList));
        ABaseEnemy enemy2 = new Skeleton(5,1,new Path(pathList));

        gameModel.addEnemy(enemy2);
        gameModel.addEnemy(enemy1);

        Assertions.assertEquals(5, enemy1.getHealth());
        Assertions.assertEquals(5, enemy2.getHealth());
        pM.update();
        Assertions.assertEquals(4, enemy2.getHealth());
        Assertions.assertEquals(4, enemy1.getHealth());
        pM.update();
        Assertions.assertEquals(3, enemy1.getHealth());
        Assertions.assertEquals(3, enemy2.getHealth());
    }

     */
}
