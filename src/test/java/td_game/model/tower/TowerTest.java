package td_game.model.tower;

//import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Assertions;
import td_game.model.enemy.ABaseEnemy;
import td_game.model.enemy.Skeleton;
import td_game.model.path.Path;
import td_game.model.path.Waypoint;
import td_game.model.projectile.ProjectileFactory;
import td_game.model.towers.Tower;

import java.util.ArrayList;
import java.util.List;

public class TowerTest {



    @org.junit.jupiter.api.Test
    public void testAngleAtEnemy()
    {
        List<Waypoint> pathList = new ArrayList<Waypoint>();
        ABaseEnemy enemy = new Skeleton(1,1,new Path(pathList));
        ProjectileFactory p = new ProjectileFactory(2,10,10,5,3,50);
        Tower tower = new Tower(100,5,0,10,3, p);

        Assertions.assertEquals(180, tower.getAngleToEnemy(enemy));
    }
    @org.junit.jupiter.api.Test
    public void testEnemyInRange()
    {
        List<Waypoint> pathList = new ArrayList<Waypoint>();
        ArrayList<ABaseEnemy> enemyList = new ArrayList<ABaseEnemy>();
        ABaseEnemy enemy = new Skeleton(1,1,new Path(pathList));
        ABaseEnemy enemy2 = new Skeleton(1,1,new Path(pathList));
        ProjectileFactory p = new ProjectileFactory(2,10,10,5,3,50);
        Tower tower = new Tower(100,5,5,10,3, p);

        enemyList.add(enemy);

        Assertions.assertEquals(1, tower.getEnemyInRangeInOrder(enemyList).size());

        enemyList.add(enemy2);

        Assertions.assertEquals(2, tower.getEnemyInRangeInOrder(enemyList).size());
    }

    @org.junit.jupiter.api.Test
    public void testlenTooEnemyX()
    {
        List<Waypoint> pathList = new ArrayList<Waypoint>();
        ABaseEnemy enemy = new Skeleton(1,1,new Path(pathList));
        ProjectileFactory p = new ProjectileFactory(2,10,10,5,3,50);
        Tower tower = new Tower(100,5,0,10,3, p);

        Assertions.assertEquals(5, tower.lenTooEnemy(enemy));
    }
    @org.junit.jupiter.api.Test
    public void testlenTooEnemyY()
    {
        List<Waypoint> pathList = new ArrayList<Waypoint>();
        ABaseEnemy enemy = new Skeleton(1,1,new Path(pathList));
        ProjectileFactory p = new ProjectileFactory(2,10,10,5,3,50);
        Tower tower = new Tower(100,0,5,10,3, p);

        Assertions.assertEquals(5, tower.lenTooEnemy(enemy));
    }


}
