package td_game.model.enemy;

import td_game.model.path.Path;
import td_game.model.path.Waypoint;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class EnemyMovementTest {

    private Path createTestPath() {
        List<Waypoint> waypoints = Arrays.asList(
                new Waypoint(16.0, 16.0, 0, 0),
                new Waypoint(48.0, 16.0, 0, 1),
                new Waypoint(48.0, 48.0, 1, 1)
        );
        return new Path(waypoints);
    }

    private EnemyFactory getEnemyFactory(double speed) {
        EnemyFactory enemyFactory = new EnemyFactory();
        enemyFactory.registerFactory("Skeleton", path -> new Skeleton(2, speed, path, 2));
        return enemyFactory;
    }

    private static final double DELTA = 0.0001;

    @Test
    void testEnemyInitialization() {
        Path path = createTestPath();


        EnemyFactory enemyFactory = getEnemyFactory(1.0);
        ABaseEnemy enemy = enemyFactory.createEnemy("Skeleton", path);

        assertNotNull(enemy, "Factory should create an enemy.");
        assertEquals(16.0, enemy.getX(), DELTA);
        assertEquals(16.0, enemy.getY(), DELTA);

        PathFollowingEnemy pathEnemy = (PathFollowingEnemy) enemy;
        assertEquals(1, pathEnemy.currentWaypointIndex);
    }

    @Test
    void TestMovementTowardsWaypoint() {
        Path path = createTestPath();

        EnemyFactory enemyFactory = getEnemyFactory(4.0);
        ABaseEnemy enemy = enemyFactory.createEnemy("Skeleton", path);

        // Move 1 tick (4 pixels)
        enemy.move();

        assertEquals(20.0, enemy.getX(), DELTA);
        assertEquals(16.0, enemy.getY(), DELTA);

        PathFollowingEnemy pathEnemy = (PathFollowingEnemy) enemy;
        assertEquals(1, pathEnemy.currentWaypointIndex);

        // Move 7 more times (Total 8 moves * 4 speed = 32 pixels)
        for (int i = 0; i < 7; i++) {
            enemy.move();
        }

        // 16.0 + 32.0 = 48.0
        assertEquals(48.0, enemy.getX(), DELTA);
        assertEquals(2, pathEnemy.currentWaypointIndex);
    }

    @Test
    void TestReachingEndOfPath() {
        Path path = createTestPath();

        EnemyFactory enemyFactory = getEnemyFactory(8.0);
        ABaseEnemy enemy = enemyFactory.createEnemy("Skeleton", path);

        PathFollowingEnemy pathEnemy = (PathFollowingEnemy) enemy;

        // Move until end
        while (!pathEnemy.hasReachedEnd()) {
            pathEnemy.move();
        }

        assertTrue(pathEnemy.hasReachedEnd());
        assertEquals(48.0, enemy.getX(), DELTA);
        assertEquals(48.0, enemy.getY(), DELTA);
        assertEquals(3, pathEnemy.currentWaypointIndex);
    }
}