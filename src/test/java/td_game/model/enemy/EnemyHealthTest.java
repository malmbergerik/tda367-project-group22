package td_game.model.enemy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import td_game.model.modelnit.GameModel;
import td_game.model.path.Path;

import static org.junit.jupiter.api.Assertions.*;

class EnemyHealthTest {

    private ABaseEnemy enemy;

    @BeforeEach
    void setUp() {
        GameModel model = new GameModel(16);
        Path path = model.getCurrentPath();

        // Constructor: Skeleton(int health, double speed, Path path, int damageAmount)
        // Using 100 HP
        enemy = new Skeleton(100, 1.0, path, 10);
    }

    @Test
    void testTakeDamage() {
        enemy.takeDamage(10);
        assertEquals(90, enemy.getHealth(), "Health should decrease by damage amount");
        assertTrue(enemy.isAlive(), "Enemy should still be alive with positive health");
    }

    @Test
    void testDeathStatus() {
        assertTrue(enemy.isAlive(), "Enemy should be alive initially");

        // Deal exact lethal damage
        enemy.takeDamage(100);

        assertEquals(0, enemy.getHealth(), "Health should be 0");
        assertFalse(enemy.isAlive(), "Enemy should NOT be alive when health is 0");
    }

    @Test
    void testOverkill() {
        // Deal more damage than HP
        enemy.takeDamage(200);

        assertTrue(enemy.getHealth() <= 0, "Health should be 0 or less");
        assertFalse(enemy.isAlive(), "Enemy should not be alive after overkill damage");
    }
}