package td_game.model.tower;

//import static org.junit.jupiter.api.Assertions.*;


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
    public void testShoot()
    {
        List<Waypoint> pathList = new ArrayList<Waypoint>();
        ABaseEnemy enemy = new Skeleton(1,1,new Path(pathList));
        ProjectileFactory p = new ProjectileFactory(2,10,10,5,3,50);
        Tower tower = new Tower(100,5,5,10,3, p);
        tower.addEnemyInTestList(enemy);
        tower.shoot();
    }
    @org.junit.jupiter.api.Test
    public void testAngleTooEnemy()
    {
        List<Waypoint> pathList = new ArrayList<Waypoint>();
        ABaseEnemy enemy = new Skeleton(1,1,new Path(pathList));
        ProjectileFactory p = new ProjectileFactory(2,10,10,5,3,50);
        Tower tower = new Tower(100,5,5,10,3, p);
        tower.addEnemyInTestList(enemy);
        System.out.println(tower.getAngleToEnemy(enemy));
        tower.getAngleToEnemy(enemy);
    }

    @org.junit.jupiter.api.Test
    public void testFirstTooAngleTooEnemy()
    {
        List<Waypoint> pathList = new ArrayList<Waypoint>();
        ABaseEnemy enemy = new Skeleton(1,1,new Path(pathList));
        ABaseEnemy enemy2 = new Skeleton(1,1,new Path(pathList));
        ProjectileFactory p = new ProjectileFactory(2,10,10,5,3,50);
        Tower tower = new Tower(100,5,5,10,3, p);
        tower.addEnemyInTestList(enemy);
        tower.addEnemyInTestList(enemy2);

        ArrayList<ABaseEnemy> enemiesInRange = tower.getEnemyInRangeInOrder(tower.getEnemyInTestList());

        tower.getAngleToEnemy(enemiesInRange.get(0));
    }


}
