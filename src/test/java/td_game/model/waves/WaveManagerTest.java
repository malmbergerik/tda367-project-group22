package td_game.model.waves;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import td_game.model.enemy.ABaseEnemy;
import td_game.model.enemy.EnemyFactory;
import td_game.model.enemy.EnemyManager;
import td_game.model.enemy.Skeleton;
import td_game.model.events.IGameEvent;
import td_game.model.events.IGameObserver;
import td_game.model.modelnit.GameModel;
import td_game.model.modelnit.GameObservable;
import td_game.model.path.Path;
import td_game.model.player.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class WaveManagerTest {

    private WaveManager waveManager;
    private EnemyManager enemyManager;
    private Queue<Wave> testWaves;

    @BeforeEach
    void setUp() {
        // 1. Setup dependencies required for EnemyManager
        // We avoid instantiating the full GameModel if possible to keep the unit test isolated,
        // but we need a Path. We can try to load a model just to get a valid path.
        GameModel model = new GameModel(16);
        Path path = model.getCurrentPath();

        // 2. Setup Player and Systems (Required by EnemyManager)
        Player player = new Player(new PlayerHealth(100), new PlayerMoney(100));
        DamageSystem damageSystem = new DamageSystem(player);
        MoneySystem moneySystem = new MoneySystem(player);

        // 3. Setup EnemyFactory
        EnemyFactory factory = new EnemyFactory();
        // Register a Skeleton factory that matches the constructor signature: (hp, speed, path, money)
        factory.registerFactory("Skeleton", p -> new Skeleton(2, 0.5, p, 2));

        // 4. Create EnemyManager with correct dependencies
        // Constructor: (List<ABaseEnemy>, GameObservable, DamageSystem, MoneySystem)
        List<ABaseEnemy> activeEnemies = new ArrayList<>();

        // We need a dummy GameObservable
        GameObservable dummyNotifier = new GameObservable() {
            @Override
            public void registerObserver(IGameObserver observer) {}
            @Override
            public void unregisterObserver(IGameObserver observer) {}
            @Override
            public void notifyObserver(IGameEvent event) {}
        };

        enemyManager = new EnemyManager(activeEnemies, dummyNotifier, damageSystem, moneySystem);

        // 5. Create Manual Test Waves
        testWaves = new LinkedList<>();

        // Wave 1: 1 Skeleton, no delay
        Wave wave1 = new Wave(1);
        wave1.addGroup(new WaveGroup("Skeleton", 1, 1.0, 0.0));
        testWaves.add(wave1);

        // Wave 2: 5 Skeletons
        Wave wave2 = new Wave(2);
        wave2.addGroup(new WaveGroup("Skeleton", 5, 0.5, 0.0));
        testWaves.add(wave2);

        // 6. Create WaveManager
        waveManager = new WaveManager(enemyManager, factory, testWaves, path);
    }

    @Test
    void testStartNextWave() {
        // Check that no wave is active initially
        assertFalse(waveManager.isWaveActive(), "Wave should not be active before start");
        assertEquals(0, waveManager.getCurrentWave(), "Current wave should be 0 before start");

        // Start the wave
        waveManager.startNextWave();

        // Check that it became active and is Wave 1
        assertTrue(waveManager.isWaveActive(), "Wave should be active after start");
        assertEquals(1, waveManager.getCurrentWave(), "Current wave should be 1");
    }

    @Test
    void testWaveQueueConsumption() {
        // Start Wave 1
        waveManager.startNextWave();
        assertEquals(1, waveManager.getCurrentWave());

        // Force start next wave (simulating wave completion)
        waveManager.startNextWave();
        assertEquals(2, waveManager.getCurrentWave());

        // Attempt to start a 3rd wave (which doesn't exist)
        waveManager.startNextWave();

        // Ensure state remains consistent and doesn't crash
        // Depending on implementation, it might stay on last wave or go inactive,
        // but here we just ensure it didn't throw an exception.
        assertNotNull(waveManager);
    }

    @Test
    void testWaveStartButton() {
        assertFalse(waveManager.isWaveActive(), "Button should be ACTIVE initially");

        // Start Wave 1
        waveManager.startNextWave();

        // Verify: Game is running, Start Button is INACTIVE
        assertTrue(waveManager.isWaveActive(), "Button should be INACTIVE while wave is running");
        assertEquals(1, waveManager.getCurrentWave());

        // End of Wave
        // Skip timers we just tell the manager the wave is done.
        waveManager.waveFinished();

        // Verify: Game is paused again, Start Button is ACTIVE
        assertFalse(waveManager.isWaveActive(), "Button should be ACTIVE after wave finishes");

        //  Start Wave 2
        waveManager.startNextWave();

        // Verify: Game is running again
        assertTrue(waveManager.isWaveActive(), "Button should be INACTIVE for Wave 2");
        assertEquals(2, waveManager.getCurrentWave());
    }
}