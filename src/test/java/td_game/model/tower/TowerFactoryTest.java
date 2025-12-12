package td_game.model.tower;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import td_game.model.modelnit.GameModel;
import td_game.model.projectile.ProjectileManager;
import td_game.model.towers.ATower;
import td_game.model.towers.CanonTower;
import td_game.model.towers.SniperTower;
import td_game.model.towers.factory.TowerFactory;

import static org.junit.jupiter.api.Assertions.*;

class TowerFactoryTest {

    private TowerFactory factory;

    @BeforeEach
    void setUp() {
        // We need a GameModel to create a ProjectileManager
        GameModel model = new GameModel(16);
        ProjectileManager projectileManager = new ProjectileManager(model);
        factory = new TowerFactory(projectileManager);
    }

    @Test
    void testCreateCanonTower() {
        ATower tower = factory.createTower("CanonTower", 100, 200);

        assertNotNull(tower, "Factory should return a tower instance");
        assertTrue(tower instanceof CanonTower, "Should be instance of CanonTower");
        assertEquals(100, tower.getX(), "X coordinate should match");
        assertEquals(200, tower.getY(), "Y coordinate should match");
    }

    @Test
    void testCreateSniperTower() {
        ATower tower = factory.createTower("SniperTower", 50, 50);

        assertNotNull(tower);
        assertInstanceOf(SniperTower.class, tower, "Should be instance of SniperTower");
    }

    @Test
    void testCreateUnknownTower() {
        // Your implementation returns null and prints an error for unknown types
        ATower tower = factory.createTower("UnknownTower", 0, 0);
        assertNull(tower, "Factory should return null for unknown tower types");
    }

    @Test
    void testIsValidTower() {
        assertTrue(factory.isValidTower("CanonTower"));
        assertTrue(factory.isValidTower("SniperTower"));
        assertFalse(factory.isValidTower("MegaTower"));
    }
}