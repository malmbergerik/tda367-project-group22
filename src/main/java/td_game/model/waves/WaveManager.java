package td_game.model.waves;

import td_game.model.enemy.ABaseEnemy;
import td_game.model.enemy.EnemyFactory;
import td_game.model.enemy.EnemyManager;
import td_game.model.path.Path;

import java.util.LinkedList;
import java.util.Queue;

public class WaveManager {
    private final EnemyManager enemyManager;
    private final EnemyFactory enemyFactory;
    private final Path path;

    private Queue<Wave> allWaves;

    private Wave currentWave;
    private Queue<WaveGroup> groupsInCurrentWave;
    private WaveGroup currentGroup;

    private boolean waveActive = false;
    private boolean allEnemiesSpawned = false;

    // Timing & Counters
    private boolean isDelayPhase = true;
    private double timerSeconds = 0;
    private long lastSpawnTime;
    private int enemiesSpawnedInCurrentGroup = 0;

    public WaveManager(EnemyManager manager, EnemyFactory enemyFactory, Queue<Wave> waves, Path path) {
        this.enemyManager = manager;
        this.enemyFactory = enemyFactory;
        this.allWaves = waves;
        this.path = path;

        this.currentWave = null;
        this.groupsInCurrentWave = null;
        this.currentGroup = null;
    }

    public void startNextWave() {
        if (allWaves.isEmpty()) {
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
        if (!waveActive || allEnemiesSpawned || currentGroup == null) return;

        long now = System.nanoTime();
        double dt = (now - lastSpawnTime) / 1_000_000_000.0;
        lastSpawnTime = now;

        timerSeconds += dt;

        if (isDelayPhase) {
            if (timerSeconds >= currentGroup.getInitialDelay()) {
                isDelayPhase = false;
                timerSeconds = 0;
                spawnEnemy();
            }
        } else {
            if (timerSeconds >= currentGroup.getSpawnInterval()) {
                timerSeconds = 0;
                spawnEnemy();
            }
        }
    }

    private void spawnEnemy() {
        if (currentGroup == null) return;

        String enemyName = currentGroup.getEnemyType();

        ABaseEnemy enemy = enemyFactory.createEnemy(enemyName, this.path);

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
        System.out.println("  Starting Wave Group: " + currentGroup.getEnemyType() + " x" + currentGroup.getCount());
    }

    public boolean isWaveComplete() {
        return allEnemiesSpawned;
    }

    // --- DESSA METODER SAKNADES ---

    public boolean isWaveActive() {
        return waveActive;
    }

    public int getCurrentWave() {
        return currentWave != null ? currentWave.getWaveNumber() : 0;
    }
}