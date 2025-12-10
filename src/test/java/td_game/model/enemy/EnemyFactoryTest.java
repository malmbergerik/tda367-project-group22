package td_game.model.enemy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import td_game.model.modelnit.GameModel;
import td_game.model.path.Path;

import static org.junit.jupiter.api.Assertions.*;

class EnemyFactoryTest {

    private EnemyFactory factory;
    private Path dummyPath;

    @BeforeEach
    void setUp() {
        GameModel model = new GameModel(16);
        dummyPath = model.getCurrentPath();

        factory = new EnemyFactory();

        // Register test enemies manually
        factory.registerFactory("Skeleton", p -> new Skeleton(10, 1.0, p, 5));
    }

    @Test
    void testCreateKnownEnemy() {
        ABaseEnemy enemy = factory.createEnemy("Skeleton", dummyPath);

        assertNotNull(enemy, "Factory should return an enemy object");
        assertTrue(enemy instanceof Skeleton, "Enemy should be instance of Skeleton");
        assertEquals(10, enemy.getHealth(), "Skeleton should have configured health");
    }

    @Test
    void testCreateUnknownEnemy() {
        // Your code throws IllegalArgumentException for unknown types
        assertThrows(IllegalArgumentException.class, () -> {
            factory.createEnemy("NonExistentMonster", dummyPath);
        });
    }

    @Test
    void testFactoryCreatesUniqueInstances() {
        ABaseEnemy enemy1 = factory.createEnemy("Skeleton", dummyPath);
        ABaseEnemy enemy2 = factory.createEnemy("Skeleton", dummyPath);

        assertNotSame(enemy1, enemy2, "Factory should create distinct objects");
    }
}