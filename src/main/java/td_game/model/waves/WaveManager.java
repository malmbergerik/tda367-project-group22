package td_game.model.waves;

import td_game.model.enemy.ABaseEnemy;
import td_game.model.enemy.EnemyFactory;
import td_game.model.enemy.EnemyManager;
import td_game.model.path.Path;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Manages the flow of game waves, controlling when enemies are spawned
 * and tracking the progress of the current wave and wave groups.
 */
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

    /**
     * Constructs a WaveManager with the necessary dependencies to spawn and manage enemies.
     *
     * @param manager      The manager responsible for tracking active enemies in the game.
     * @param enemyFactory The factory used to create new enemy instances.
     * @param waves        The queue of all waves to be played in the level.
     * @param path         The path that enemies will follow.
     */
    public WaveManager(EnemyManager manager, EnemyFactory enemyFactory, Queue<Wave> waves, Path path) {
        this.enemyManager = manager;
        this.enemyFactory = enemyFactory;
        this.allWaves = waves;
        this.path = path;

        this.currentWave = null;
        this.groupsInCurrentWave = null;
        this.currentGroup = null;
    }

    /**
     * Initializes and starts the next wave in the queue.
     * Sets up the groups for the wave and resets timers.
     */
    public void startNextWave() {
        if (allWaves.isEmpty()) {
            return;
        }
        currentWave = allWaves.poll();
        groupsInCurrentWave = new LinkedList<>(currentWave.getGroups());

        startNextGroup();

        waveActive = true;
        allEnemiesSpawned = false;
        lastSpawnTime = System.nanoTime();
    }

    /**
     * Checks if all defined waves have been fully processed and completed.
     *
     * @return true if there are no more waves and the current wave is inactive.
     */
    public boolean isAllWavesCompleted() {
        return allWaves.isEmpty() && !waveActive;
    }

    /**
     * Marks the current wave as finished, stopping any active wave logic.
     */
    public void waveFinished() {
        waveActive = false;
    }

    /**
     * Updates the wave logic, handling the timing for enemy spawns based on
     * the current group's configuration (delays and intervals).
     */
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

    /**
     * Instantiates a single enemy defined by the current wave group and adds it to the game.
     *
     * Increments the count of enemies spawned for the current group. If the group limit
     * is reached, it triggers the start of the next group.
     *
     */
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

    /**
     * Transitions the manager to process the next group of enemies in the current wave.
     *
     * If no groups remain in the current wave, it flags the wave spawning as complete.
     * Otherwise, it resets the spawn counters and timers for the new group.
     *
     */
    private void startNextGroup() {
        if (groupsInCurrentWave.isEmpty()) {
            allEnemiesSpawned = true;
            currentGroup = null;
            return;
        }
        currentGroup = groupsInCurrentWave.poll();
        enemiesSpawnedInCurrentGroup = 0;
        isDelayPhase = true;
        timerSeconds = 0;
    }

    /**
     * Checks if all enemies for the current wave have been spawned.
     *
     * @return true if the wave has finished spawning all its groups.
     */
    public boolean isWaveComplete() {
        return allEnemiesSpawned;
    }

    /**
     * Checks if a wave is currently active (spawning enemies).
     *
     * @return true if a wave is running.
     */
    public boolean isWaveActive() {
        return waveActive;
    }

    public int getCurrentWave() {
        return currentWave != null ? currentWave.getWaveNumber() : 0;
    }
}