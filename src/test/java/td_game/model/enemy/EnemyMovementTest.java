package td_game.model.enemy;

import td_game.model.path.Path;
import td_game.model.path.Waypoint;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyMovementTest {
    // Helper method to create a simple path for testing
    private Path createTestPath() {
        // Path consists of three waypoints (centers of 32x32 tiles):
        // 1. (16.0, 16.0) - Start (0,0)
        // 2. (48.0, 16.0) - to the right (0,1)
        // 3. (48.0, 48.0) - Corner (1,1)

        List<Waypoint> waypoints = Arrays.asList(
                new Waypoint(16.0, 16.0, 0, 0),
                new Waypoint(48.0, 16.0, 0, 1),
                new Waypoint(48.0, 48.0, 1, 1)
        );
        return new Path(waypoints);
    }

    private static final double DELTA = 0.0001;

    @Test
    void testEnemyInitialization (){
        Path path = createTestPath();
        int initialHealth = 100;
        double speed = 2.0;

        ABaseEnemy enemy = EnemyFactory.createEnemy(EnemyType.skeleton, initialHealth, speed, path);

        assertNotNull(enemy, "Factory should create an enemy.");
        assertEquals(16.0, enemy.getX(), DELTA, "Enemy should start at the first waypoint X.");
        assertEquals(16.0, enemy.getY(), DELTA, "Enemy should start at the first waypoint Y.");
        assertEquals(1, enemy.currentWaypointIndex, "Enemy should target the second waypoint (index 1).");
    }

    @Test
    void TestMovementTowardsWaypoint() {
        Path path = createTestPath();
        double speed = 4.0;
        ABaseEnemy enemy = EnemyFactory.createEnemy(EnemyType.skeleton, 100, speed, path); // Speed set to 4 pixels per tick

        assertNotNull(enemy, "Factory should create an enemy.");

        // Target: (48.0, 16.0). Distance: 32.0 units (48 - 16).
        // It should take 32 / 4 = 8 moves.

        // After 1 move
        enemy.move();

        // Since the target is straight right, only X changes by speed (4.0)
        assertEquals(20.0, enemy.getX(), DELTA);
        assertEquals(16.0, enemy.getY(), DELTA);
        assertEquals(1, enemy.currentWaypointIndex);

        // Move 7 more times to reach the waypoint
        for (int i = 0; i < 7; i++) {
            enemy.move();
        }

        assertEquals(48.0, enemy.getX(), DELTA, "X should be 48.0 after reaching W2.");
        assertEquals(16.0, enemy.getY(), DELTA, "Y should be 16.0 after reaching W2.");
        assertEquals(2, enemy.currentWaypointIndex, "Enemy should be targeting the third waypoint (index 2).");
    }

    @Test
    void TestReachingEndOfPath() {
        Path path = createTestPath();
        //increase speed to reach the end quickly
        double speed = 8.0;
        ABaseEnemy enemy = EnemyFactory.createEnemy(EnemyType.skeleton, 100, speed, path);

        assertNotNull(enemy, "Factory should create an enemy.");

        // Move until the enemy reaches the end
        while (!enemy.hasReachedEnd()) {
            enemy.move();
        }
        assertTrue(enemy.hasReachedEnd(), "Enemy should have reached the end of the path.");
        assertEquals(48.0, enemy.getX(), DELTA, "X should be 48.0 at the end.");
        assertEquals(48.0, enemy.getY(), DELTA, "Y should be 48.0 at the end.");
        assertEquals(3, enemy.currentWaypointIndex, "Index should be equal to path length (3).");
    }
}