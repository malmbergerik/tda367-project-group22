package td_game.model.waves;

public class WaveGroup {
    private final String enemyType;
    private final int enemyCount;
    private final double spawnInterval;
    private final double initialDelay;

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
