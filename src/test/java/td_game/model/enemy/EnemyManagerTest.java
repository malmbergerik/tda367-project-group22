package td_game.model.enemy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import td_game.model.events.IGameEvent;
import td_game.model.events.IGameObserver;
import td_game.model.modelnit.GameModel;
import td_game.model.modelnit.GameObservable;
import td_game.model.path.Path;
import td_game.model.player.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EnemyManagerTest {

    private EnemyManager enemyManager;
    private List<ABaseEnemy> activeEnemies;
    private Player player;
    private Path path;

    @BeforeEach
    void setUp() {
        // 1. Dependencies
        GameModel model = new GameModel(16);
        path = model.getCurrentPath();
        activeEnemies = new ArrayList<>();
        player = new Player(new PlayerHealth(100), new PlayerMoney(0)); // Start with 0 money

        DamageSystem damageSystem = new DamageSystem(player);
        MoneySystem moneySystem = new MoneySystem(player);

        // Dummy Observer
        GameObservable dummyNotifier = new GameObservable() {
            @Override public void registerObserver(IGameObserver observer) {}
            @Override public void unregisterObserver(IGameObserver observer) {}
            @Override public void notifyObserver(IGameEvent event) {}
        };

        // 2. Initialize Manager
        enemyManager = new EnemyManager(activeEnemies, dummyNotifier, damageSystem, moneySystem);
    }

    @Test
    void testAddEnemy() {
        // Constructor: Health, Speed, Path, Damage/Reward
        ABaseEnemy skeleton = new Skeleton(10, 1, path, 5);
        enemyManager.addEnemy(skeleton); // Corrected method name

        assertEquals(1, activeEnemies.size());
        assertEquals(skeleton, activeEnemies.get(0));
    }

    @Test
    void testRemoveDeadEnemyAndGrantMoney() {
        // Setup: Add a Skeleton worth 50 gold (damageAmount is used as money reward in EnemyManager)
        ABaseEnemy skeleton = new Skeleton(10, 1, path, 50);
        enemyManager.addEnemy(skeleton);

        // Kill the skeleton
        skeleton.takeDamage(100);

        // Update manager (which should process removals)
        enemyManager.update();

        // Verify: Enemy removed
        assertTrue(activeEnemies.isEmpty(), "Dead enemy should be removed from list");

        // Verify: Player got money
        // Corrected: getMoney() returns int, not an object
        assertEquals(50, player.getMoney(), "Player should receive kill reward");
    }
}