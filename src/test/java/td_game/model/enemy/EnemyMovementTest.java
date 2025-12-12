package td_game.model.enemy;

import td_game.model.path.Path;
import td_game.model.path.Waypoint;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class EnemyMovementTest {

    private Path createTestPath() {
        // Path has 3 waypoints: Indices 0, 1, 2
        List<Waypoint> waypoints = Arrays.asList(
                new Waypoint(16.0, 16.0, 0, 0),
                new Waypoint(48.0, 16.0, 0, 1),
                new Waypoint(48.0, 48.0, 1, 1)
        );
        return new Path(waypoints);
    }

    private EnemyFactory getEnemyFactory() {
        EnemyFactory enemyFactory = new EnemyFactory();
        enemyFactory.registerFactory("Skeleton", path -> new Skeleton(2, 0.3, path,2));
        return enemyFactory;
    }

    private static final double DELTA = 0.0001;

    @Test
    void testEnemyInitialization() {
        Path path = createTestPath();
        EnemyFactory enemyFactory = getEnemyFactory();
        ABaseEnemy enemy = enemyFactory.createEnemy("Skeleton", path);

        assertNotNull(enemy, "Factory should create an enemy.");
        assertEquals(16.0, enemy.getX(), DELTA);
        assertEquals(16.0, enemy.getY(), DELTA);

        // CAST to access protected field
        PathFollowingEnemy pathEnemy = (PathFollowingEnemy) enemy;

        // Assert using pathEnemy
        assertEquals(1, pathEnemy.currentWaypointIndex, "Enemy should target the second waypoint (index 1) immediately.");
    }

    @Test
    void TestMovementTowardsWaypoint() {
        Path path = createTestPath();
        double speed = 4.0;
        EnemyFactory enemyFactory = getEnemyFactory();
        ABaseEnemy enemy = enemyFactory.createEnemy("Skeleton", path);

        // Move 1 tick (4 pixels)
        enemy.move();

        assertEquals(20.0, enemy.getX(), DELTA);
        assertEquals(16.0, enemy.getY(), DELTA);

        PathFollowingEnemy pathEnemy = (PathFollowingEnemy) enemy;

        // Assert using pathEnemy (NOT enemy)
        assertEquals(1, pathEnemy.currentWaypointIndex);

        // Move 7 more times (Total 8 moves * 4 speed = 32 pixels)
        for (int i = 0; i < 7; i++) {
            enemy.move();
        }

        // Reached Waypoint 1 (48, 16)
        assertEquals(48.0, enemy.getX(), DELTA);
        assertEquals(16.0, enemy.getY(), DELTA);

        // Assert using pathEnemy (NOT enemy)
        assertEquals(2, pathEnemy.currentWaypointIndex);
    }

    @Test
    void TestReachingEndOfPath() {
        Path path = createTestPath();
        double speed = 8.0;
        EnemyFactory enemyFactory = getEnemyFactory();
        ABaseEnemy enemy = enemyFactory.createEnemy("Skeleton", path);

        PathFollowingEnemy pathEnemy = (PathFollowingEnemy) enemy;

        // Move until end
        while (!pathEnemy.hasReachedEnd()) {
            pathEnemy.move();
        }

        assertTrue(pathEnemy.hasReachedEnd());
        assertEquals(48.0, enemy.getX(), DELTA);
        assertEquals(48.0, enemy.getY(), DELTA);


        assertEquals(3, pathEnemy.currentWaypointIndex, "Final index should match path length (3).");
    }
}