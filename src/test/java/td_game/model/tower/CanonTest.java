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
import td_game.model.towers.ATower;
import td_game.model.towers.CanonTower;
import td_game.model.towers.placementRules.GrassOnlyPlacementRule;
import td_game.model.towers.Tower;
import td_game.model.towers.TowerManager;

import java.util.ArrayList;
import java.util.List;

public class CanonTest {

    private GameModel gameModel;
    private TowerManager manager;
    private ProjectileManager projectileManager;
    @BeforeEach
    public void setup() {
        gameModel = new GameModel(32);
        projectileManager = new ProjectileManager(gameModel);
        manager = new TowerManager( gameModel);
    }


    @org.junit.jupiter.api.Test
    public void testEnemyInRange()
    {
        List<Waypoint> pathList = new ArrayList<>();
        ArrayList<ABaseEnemy> enemyList = new ArrayList<>();

        ABaseEnemy enemy1 = new Skeleton(1,1,new Path(pathList));
        ABaseEnemy enemy2 = new Skeleton(1,1,new Path(pathList));

        CanonTower tower = new CanonTower(1,1,projectileManager);

        enemyList.add(enemy1);
        tower.update(enemyList);
        Assertions.assertEquals(1, tower.getEnemiesInRage().size(), "Tower should have one enemy in range");

        enemyList.add(enemy2);
        tower.update(enemyList);
        Assertions.assertEquals(2, tower.getEnemiesInRage().size(), "Tower should have two enemies in range");
    }

    @org.junit.jupiter.api.Test
    public void testLenToEnemyX()
    {
        List<Waypoint> pathList = new ArrayList<>();
        ABaseEnemy enemy = new Skeleton(1,1,new Path(pathList));

        ATower tower = new CanonTower(5,0,projectileManager);

        Assertions.assertEquals(5, tower.getX()-enemy.getX());
    }

    @org.junit.jupiter.api.Test
    public void testLenToEnemyY()
    {
        List<Waypoint> pathList = new ArrayList<>();
        ABaseEnemy enemy = new Skeleton(1,1,new Path(pathList));

        ATower tower = new CanonTower(0,5,projectileManager);

        Assertions.assertEquals(5, tower.getY() - enemy.getY());
    }
}