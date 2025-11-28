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

    @BeforeEach
    public void setup() {
        gameModel = new GameModel(0, 0, 32);
    }

    @org.junit.jupiter.api.Test
    public void testMoveProjectile()
    {
        Projectile p = new Projectile(135,2,10,10,1,2,10000, 10 , 10, gameModel, true);

        p.update();

        Assertions.assertEquals(9, p.getX());
        Assertions.assertEquals(11, p.getY());
    }

    @org.junit.jupiter.api.Test
    public void testHitEnemyEllipse()
    {
        List<Waypoint> pathList = new ArrayList<Waypoint>();
        ABaseEnemy enemy1 = new Skeleton(1,1,new Path(pathList));
        Projectile p = new Projectile(90,2,10,10,1,2,10000,(int) enemy1.getX() ,(int) enemy1.getY(), gameModel, true);
        gameModel.addEnemy(enemy1);
        p.update();

        Assertions.assertEquals(0, enemy1.getHealth() );
    }
    @org.junit.jupiter.api.Test
    public void testHitEnemyRectangle()
    {
        List<Waypoint> pathList = new ArrayList<Waypoint>();
        ABaseEnemy enemy1 = new Skeleton(1,1,new Path(pathList));
        Projectile p = new Projectile(90,2,10,10,1,2,10000,(int) enemy1.getX() ,(int) enemy1.getY(), gameModel, false);
        gameModel.addEnemy(enemy1);
        p.update();

        Assertions.assertEquals(0, enemy1.getHealth() );
    }

    @org.junit.jupiter.api.Test
    public void testDestroyedWhen0Pierce()
    {
        List<Waypoint> pathList = new ArrayList<Waypoint>();
        ABaseEnemy enemy1 = new Skeleton(1,1,new Path(pathList));
        ABaseEnemy enemy2 = new Skeleton(1,1,new Path(pathList));
        Projectile p = new Projectile(90,2,10,10,1,1,10000,(int) enemy1.getX() ,(int) enemy1.getY(), gameModel, true);

        gameModel.addEnemy(enemy1);
        gameModel.addEnemy(enemy2);
        p.update();

        Assertions.assertEquals(0, enemy1.getHealth());
        Assertions.assertEquals(1, enemy2.getHealth());
    }

    @org.junit.jupiter.api.Test
    public void testHitBoxLocationUpdates()
    {
        List<Waypoint> pathList = new ArrayList<Waypoint>();
        ABaseEnemy enemy1 = new Skeleton(1,1,new Path(pathList));
        Projectile p = new Projectile(180,10,5,5,1,2,10000,(int) enemy1.getX()+ 25 ,(int) enemy1.getY(),gameModel, true );

        gameModel.addEnemy(enemy1);
        Assertions.assertEquals(1, enemy1.getHealth());
        p.update();
        Assertions.assertEquals(1, enemy1.getHealth());
        p.update();
        Assertions.assertEquals(0, enemy1.getHealth());
    }

    @org.junit.jupiter.api.Test
    public void testDieAfterTime()
    {

        Projectile z = new Projectile(180,10,5,5,1,2,1,5 ,5, gameModel, true);


        Assertions.assertEquals(true, z.getIsAlive());
        z.update();
        z.update();

        Assertions.assertEquals(false, z.getIsAlive());
    }

    @org.junit.jupiter.api.Test
    public void testNotHitSameEnemySameFrame()
    {
        List<Waypoint> pathList = new ArrayList<Waypoint>();
        Projectile p = new Projectile(180,0,50,50,1,20,100,5 ,5, gameModel, true);
        ABaseEnemy enemy1 = new Skeleton(5,1,new Path(pathList));

        gameModel.addEnemy(enemy1);

        Assertions.assertEquals(5, enemy1.getHealth());
        p.update();
        Assertions.assertEquals(4, enemy1.getHealth());
        p.update();
        Assertions.assertEquals(3, enemy1.getHealth());
    }
    @org.junit.jupiter.api.Test
    public void testCanHitSeveralEnemiesSameTime()
    {
        List<Waypoint> pathList = new ArrayList<Waypoint>();
        Projectile p = new Projectile(180,0,50,50,1,20,100,5 ,5, gameModel, true);
        ABaseEnemy enemy1 = new Skeleton(5,1,new Path(pathList));
        ABaseEnemy enemy2 = new Skeleton(5,1,new Path(pathList));

        gameModel.addEnemy(enemy2);
        gameModel.addEnemy(enemy1);

        Assertions.assertEquals(5, enemy1.getHealth());
        Assertions.assertEquals(5, enemy2.getHealth());
        p.update();
        Assertions.assertEquals(4, enemy2.getHealth());
        Assertions.assertEquals(4, enemy1.getHealth());
        p.update();
        Assertions.assertEquals(3, enemy1.getHealth());
        Assertions.assertEquals(3, enemy2.getHealth());
    }
}
