package td_game.model.enemy;

import td_game.model.modelnit.GameModel;

public class EnemyWaveSpawner {
    private final EnemyManager enemyManager;
    private  final EnemyFactory enemyFactory;

    private int currentWave;
    private int enemiesSpawnedInWave;
    private int enemiesToSpawnInWave;
    private long lastSpawnTime;
    private long spawnInterval;

    private boolean waveActive;
    private boolean allEnemiesSpawned;

    public EnemyWaveSpawner(EnemyManager manager, EnemyFactory enemyFactory) {
        this.enemyManager = manager;
        this.enemyFactory = enemyFactory;
        this.spawnInterval = 2000; // 2 seconds
        this.waveActive = false;
    }

    public void startNextWave() {
        currentWave++;
        enemiesSpawnedInWave = 0;
        enemiesToSpawnInWave = currentWave * 5; // Example: increase enemies per wave
        waveActive = true;
        allEnemiesSpawned = false;
        lastSpawnTime = System.currentTimeMillis();
    }

    public void update() {
        if (!waveActive || allEnemiesSpawned) {
            return;
        }

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastSpawnTime >= spawnInterval) {
            spawnEnemy();
            lastSpawnTime = currentTime;
            enemiesSpawnedInWave++;

            if (enemiesSpawnedInWave >= enemiesToSpawnInWave) {
                allEnemiesSpawned = true;
            }
        }
    }

    private void spawnEnemy() {
        ABaseEnemy enemy = enemyFactory.createEnemy("Slime", 1, 0.2, enemyManager.gameModel.getCurrentPath());
        enemyManager.addEnemy(enemy);
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
