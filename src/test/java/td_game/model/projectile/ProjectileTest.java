package td_game.model.projectile;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import td_game.model.enemy.ABaseEnemy;
import td_game.model.enemy.Skeleton;
import td_game.model.modelnit.GameModel;
import td_game.model.path.Path;
import td_game.model.path.Waypoint;
import td_game.model.projectile.damageTypeStrategy.BasicDamageTypeStrategy;
import td_game.model.projectile.factory.ProjectileFactory;
import td_game.model.projectile.lifeTimeStrategy.BasicLifeTimeStrategy;
import td_game.model.projectile.movementStrategy.BasicMovementStrategy;
import td_game.model.projectile.pierceStrategy.BasicPierceStrategy;
import td_game.model.projectile.sizeStrategy.BasicRoundSizeStrategy;
import td_game.model.towers.TowerManager;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.*;
import java.util.List;

public class ProjectileTest {
    private ProjectileManager pM;
    private GameModel gameModel;

    @BeforeEach
    public void setup() {
        gameModel = new GameModel(32);
        pM = new ProjectileManager(gameModel);
    }



    @org.junit.jupiter.api.Test
    public void testMoveProjectile()
    {
        Projectile p = new Projectile(0,0,0,new BasicMovementStrategy(1),new BasicPierceStrategy(1),new BasicDamageTypeStrategy(5),new BasicLifeTimeStrategy(35),new BasicRoundSizeStrategy(10,10), "Fireball");
        pM.addProjectile(p);
        pM.update();

        Assertions.assertEquals(9, p.getX());
        Assertions.assertEquals(8, p.getY());
    }

    @org.junit.jupiter.api.Test
    public void testHitEnemy()
    {
        List<Waypoint> pathList = new ArrayList<Waypoint>();
        ABaseEnemy enemy1 = new Skeleton(1,0,new Path(pathList),2);
        Projectile p = new Projectile(0,-9,-8,new BasicMovementStrategy(1),new BasicPierceStrategy(1),new BasicDamageTypeStrategy(1),new BasicLifeTimeStrategy(35),new BasicRoundSizeStrategy(10,10), "Fireball");

        gameModel.addEnemy(enemy1);
        pM.addProjectile(p);
        pM.update();

        Assertions.assertEquals(0, enemy1.getHealth());

    }

    @org.junit.jupiter.api.Test
    public void testPierceEnemy()
    {
        List<Waypoint> pathList = new ArrayList<Waypoint>();
        ABaseEnemy enemy1 = new Skeleton(1,1,new Path(pathList),2);
        ABaseEnemy enemy2 = new Skeleton(2,1,new Path(pathList),2);
        Projectile p = new Projectile(0,-8,-8,new BasicMovementStrategy(0.15),new BasicPierceStrategy(2),new BasicDamageTypeStrategy(1),new BasicLifeTimeStrategy(35),new BasicRoundSizeStrategy(10,10), "Fireball");

        pM.addProjectile(p);
        gameModel.addEnemy(enemy1);
        gameModel.addEnemy(enemy2);

        pM.update();

        Assertions.assertEquals(0, enemy1.getHealth());
        Assertions.assertEquals(1, enemy2.getHealth());
    }

    @org.junit.jupiter.api.Test
    public void testMoveAndHit()
    {
        List<Waypoint> pathList = new ArrayList<Waypoint>();
        ABaseEnemy enemy1 = new Skeleton(1,1,new Path(pathList),2);
        Projectile p = new Projectile(0,-19,-8,new BasicMovementStrategy(1),new BasicPierceStrategy(1),new BasicDamageTypeStrategy(1),new BasicLifeTimeStrategy(35),new BasicRoundSizeStrategy(10,10), "Fireball");

        gameModel.addEnemy(enemy1);
        pM.addProjectile(p);
        pM.hitEnemy(p);

        Assertions.assertEquals(1, enemy1.getHealth());
        pM.update();
        Assertions.assertEquals(1, enemy1.getHealth());
        pM.update();
        Assertions.assertEquals(0, enemy1.getHealth());
    }

    @org.junit.jupiter.api.Test
    public void testDieAfterTime()
    {
        Projectile p = new Projectile(0,-8,-8,new BasicMovementStrategy(1),new BasicPierceStrategy(1),new BasicDamageTypeStrategy(5),new BasicLifeTimeStrategy(1),new BasicRoundSizeStrategy(10,10), "Fireball");

        pM.addProjectile(p);

        Assertions.assertEquals(true, p.getIsAlive());
        pM.update();
        pM.update();
        System.out.println(p.getIsAlive());

        Assertions.assertEquals(false, p.getIsAlive());
    }
}
