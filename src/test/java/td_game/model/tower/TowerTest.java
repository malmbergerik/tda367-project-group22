package td_game.model.tower;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import td_game.model.enemy.ABaseEnemy;
import td_game.model.enemy.Skeleton;
import td_game.model.modelnit.GameModel;
import td_game.model.path.Path;
import td_game.model.path.Waypoint;
import td_game.model.projectile.ProjectileFactory;
import td_game.model.projectile.ProjectileManager;
import td_game.model.towers.GrassOnlyPlacementRule;
import td_game.model.towers.Tower;
import td_game.model.towers.TowerManager;

import java.util.ArrayList;
import java.util.List;

public class TowerTest {

    private GameModel gameModel;
    private TowerManager manager;

    @BeforeEach
    public void setup() {
        gameModel = new GameModel(32);
        manager = new TowerManager( gameModel);
    }

    @org.junit.jupiter.api.Test
    public void testAngleAtEnemy()
    {
        List<Waypoint> pathList = new ArrayList<>();
        ABaseEnemy enemy = new Skeleton(1,1,new Path(pathList));

        Tower tower = new Tower(100,5,0,10,3,new ProjectileFactory(2,10,10,5,3,50, true),new GrassOnlyPlacementRule());

        double angle = manager.getAngleToEnemy(tower, enemy);

        Assertions.assertEquals(180, angle);
    }

    @org.junit.jupiter.api.Test
    public void testEnemyInRange()
    {
        List<Waypoint> pathList = new ArrayList<>();
        ArrayList<ABaseEnemy> enemyList = new ArrayList<>();

        ABaseEnemy enemy1 = new Skeleton(1,1,new Path(pathList));
        ABaseEnemy enemy2 = new Skeleton(1,1,new Path(pathList));

        Tower tower = new Tower(100,5,5,10,3,new ProjectileFactory(2,10,10,5,3,50, true), new GrassOnlyPlacementRule());

        enemyList.add(enemy1);

        Assertions.assertEquals(1, manager.getEnemyInRangeInOrder(tower, enemyList).size());

        enemyList.add(enemy2);

        Assertions.assertEquals(2, manager.getEnemyInRangeInOrder(tower, enemyList).size());
    }

    @org.junit.jupiter.api.Test
    public void testLenToEnemyX()
    {
        List<Waypoint> pathList = new ArrayList<>();
        ABaseEnemy enemy = new Skeleton(1,1,new Path(pathList));

        Tower tower = new Tower(100,5,0,10,3,new ProjectileFactory(2,10,10,5,3,50, true), new GrassOnlyPlacementRule());

        Assertions.assertEquals(5, manager.lenTooEnemy(tower, enemy));
    }

    @org.junit.jupiter.api.Test
    public void testLenToEnemyY()
    {
        List<Waypoint> pathList = new ArrayList<>();
        ABaseEnemy enemy = new Skeleton(1,1,new Path(pathList));

        Tower tower = new Tower(100,0,5,10,3,new ProjectileFactory(2,10,10,5,3,50, true), new GrassOnlyPlacementRule());

        Assertions.assertEquals(5, manager.lenTooEnemy(tower, enemy));
    }
}