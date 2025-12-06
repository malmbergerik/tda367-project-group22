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
    private final Path path; // Beroende injiceras direkt (Löser Law of Demeter)

    private Queue<Wave> allWaves; // Data injiceras

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

    // ÄNDRING: Tar emot Queue<Wave> och Path i konstruktorn
    public WaveManager(EnemyManager manager, EnemyFactory enemyFactory, Queue<Wave> waves, Path path) {
        this.enemyManager = manager;
        this.enemyFactory = enemyFactory;
        this.allWaves = waves;
        this.path = path;

        // Initialisera tillstånd men starta inte logik här
        this.currentWave = null;
        this.groupsInCurrentWave = null;
        this.currentGroup = null;
    }

    // Metoden reset() behövs kanske inte längre om GameModel skapar en ny WaveManager vid omstart,
    // men om den finns kvar ska den inte ladda filer själv.

    public void startNextWave() {
        if (allWaves.isEmpty()) {
            return;
        }
        currentWave = allWaves.poll();
        groupsInCurrentWave = new LinkedList<>(currentWave.getGroups());

        // Här kan du använda ditt nya LogEvent istället för sysout
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

        // ÄNDRING: Använder den injicerade 'path' variabeln direkt
        ABaseEnemy enemy = enemyFactory.createEnemy(enemyName, 1, 0.2, this.path);

        enemyManager.addEnemy(enemy);
        enemiesSpawnedInCurrentGroup++;

        if (enemiesSpawnedInCurrentGroup >= currentGroup.getCount()) {
            startNextGroup();
        }
    }

    // ... (resten av metoderna som startNextGroup, isWaveComplete osv är oförändrade)

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
        // Notera: Vi kollar inte enemyManager här för att undvika cirkulärt beroende om möjligt,
        // men för nuvarande logik är det okej då vi har referensen.
        return allEnemiesSpawned;
    }
}