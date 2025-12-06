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
        if (!waveActive || allEnemiesSpawned) {
            return;
        }

        long currentTime = System.nanoTime();
        double dt = (currentTime - lastSpawnTime) / 1_000_000_000.0;
        lastSpawnTime = currentTime;

        timerSeconds += dt;

        if (isDelayPhase) {
            if (timerSeconds>= currentGroup.getInitialDelay()) {
                isDelayPhase = false;
                timerSeconds = 0;
                spawnEnemy();
            }
        }
        else {
                if (timerSeconds >= currentGroup.getSpawnInterval()) {
                    timerSeconds = 0;
                    spawnEnemy();
                }
            }
        }
    }

    private void spawnEnemy() {
        ABaseEnemy enemy = enemyFactory.createEnemy("Slime", 1, 0.2, enemyManager.gameModel.getCurrentPath());
        ABaseEnemy enemy2 = enemyFactory.createEnemy("Skeleton", 1, 0.4, enemyManager.gameModel.getCurrentPath());

        enemyManager.addEnemy(enemy);
        enemyManager.addEnemy(enemy2);
    }


    public boolean isWaveComplete() {
        return allEnemiesSpawned && enemyManager.gameModel.getActiveEnemies().isEmpty();
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

    public void reset() {
        currentWave = 0;
        enemiesSpawnedInWave = 0;
        enemiesToSpawnInWave = 0;
        waveActive = false;
        allEnemiesSpawned = false;
    }


}
