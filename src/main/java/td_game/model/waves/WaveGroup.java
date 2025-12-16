package td_game.model.waves;

/**
 * Defines a specific group of enemies within a wave, detailing the type, count,
 * and timing of their spawning.
 */
public class WaveGroup {
    private final String enemyType;
    private final int enemyCount;
    private final double spawnInterval;
    private final double initialDelay;

    /**
     * Constructs a new WaveGroup configuration.
     *
     * @param enemyType     The type/name of the enemy to spawn (e.g., "Slime").
     * @param enemyCount    The total number of enemies in this group.
     * @param spawnInterval The time in seconds between each enemy spawn.
     * @param initialDelay  The delay in seconds before this group starts spawning.
     */
    public WaveGroup(String enemyType, int enemyCount, double spawnInterval, double initialDelay) {
        this.enemyType = enemyType;
        this.enemyCount = enemyCount;
        this.spawnInterval = spawnInterval;
        this.initialDelay = initialDelay;
    }

    public String getEnemyType() {
        return enemyType;
    }

    public int getCount() {
        return enemyCount;
    }

    public double getSpawnInterval() {
        return spawnInterval;
    }

    public double getInitialDelay() {
        return initialDelay;
    }
}