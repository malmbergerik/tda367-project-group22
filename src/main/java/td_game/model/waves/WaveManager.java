package td_game.model.waves;

import td_game.model.enemy.ABaseEnemy;
import td_game.model.enemy.EnemyFactory;
import td_game.model.enemy.EnemyManager;
import

import java.util.LinkedList;
import java.util.Queue;

public class WaveManager {
    private final EnemyManager enemyManager;
    private  final EnemyFactory enemyFactory;

    private Queue<Wave> allWaves;

    private int currentWave;
    private Queue<WaveGroup> groupsInCurrentWave;
    private WaveGroup currentGroup;

    private boolean waveActive = false;
    private boolean allEnemiesSpawned = false;

    // Timing & Counters
    private boolean isDelayPhase = true; // True = waiting for initialDelay; False = spawning enemies
    private double timerSeconds = 0;
    private long lastSpawnTime;
    private int enemiesSpawnedInCurrentGroup = 0;

    public WaveManager(EnemyManager manager, EnemyFactory enemyFactory) {
        this.enemyManager = manager;
        this.enemyFactory = enemyFactory;

        reset();
    }

    public void reset() {
        WaveLoader loader = new WaveLoader();
        // Ensure the path matches where your resources are located
        this.allWaves = loader.loadWaves("waves/waves.txt");

        this.currentWave = null;
        this.groupsInCurrentWave = null;
        this.currentGroup = null;

        this.waveActive = false;
        this.allEnemiesSpawned = false;
        this.timerSeconds = 0;
    }

    //Called by EnemyManager or GameModel to begin the next wave in the queue.

    public void startNextWave() {
        if (allWaves.isEmpty()) {
            System.out.println("Game Over! All waves completed!");
            return;
        }

        currentWave = allWaves.poll();

        groupsInCurrentWave = new LinkedList<>(currentWave.getGroups());

        System.out.println("Starting Wave " + currentWave.getWaveNumber());

        startNextGroup();

        waveActive = true;
        allEnemiesSpawned = false;
        lastSpawnTime = System.nanoTime();
    }

    public void update() {
        // If inactive or finished spawning, do nothing
        if (!waveActive || allEnemiesSpawned || currentGroup == null) return;

        // Calculate Delta Time in seconds
        long now = System.nanoTime();
        double dt = (now - lastTime) / 1_000_000_000.0;
        lastTime = now;

        timerSeconds += dt;

        if (isDelayPhase) {
            // PHASE 1: Waiting for the delay specified in waves.txt
            if (timerSeconds >= currentGroup.getInitialDelay()) {
                isDelayPhase = false;
                timerSeconds = 0; // Reset timer for the first spawn interval
                spawnEnemy();     // Spawn first enemy immediately after delay
            }
        } else {
            // PHASE 2: Spawning enemies at "Interval"
            if (timerSeconds >= currentGroup.getSpawnInterval()) {
                timerSeconds = 0; // Reset timer
                spawnEnemy();
            }
        }
    }

    private void spawnEnemy() {
        if (currentGroup == null) return;

        String enemyName = currentGroup.getEnemyType();
        ABaseEnemy enemy = enemyFactory.createEnemy(enemyName, 1, 0.2, enemyManager.gameModel.getCurrentPath());

        enemyManager.addEnemy(enemy);
        enemiesSpawnedInCurrentGroup++;

        if (enemiesSpawnedInCurrentGroup >= currentGroup.getCount()) {
            startNextGroup();
        }
    }

    private void startNextGroup() {
        if (groupsInCurrentWave.isEmpty()) {
            allEnemiesSpawned = true;
            currentGroup = null;
            System.out.println("Wave " + currentWave.getWaveNumber() + " spawning complete.");
            return;
        }

        currentGroup = groupsInCurrentWave.poll();

        enemiesSpawnedInCurrentGroup = 0;
        isDelayPhase = true;
        timerSeconds = 0;

        System.out.println("  Starting Wave Group: " + currentGroup.getEnemyType() +
                " x" + currentGroup.getCount());
    }


    public boolean isWaveComplete() {
        return allEnemiesSpawned && enemyManager.getActiveEnemies().isEmpty();
    }

    public boolean isWaveActive() {
        return waveActive;
    }

    public int getCurrentWave() {
        return currentWave;
    }

    public void setWaveActive(boolean active) {
        this.waveActive = active;
    }

}
