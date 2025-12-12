package td_game.model.waves;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import td_game.model.enemy.EnemyFactory;
import td_game.model.enemy.EnemyManager;
import td_game.model.enemy.Skeleton; // Se till att denna import finns!
import td_game.model.modelnit.GameModel;
import td_game.model.path.Path;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;
/*
class WaveManagerTest {

    private WaveManager waveManager;
    private EnemyManager enemyManager;
    private Queue<Wave> testWaves;

    @BeforeEach
    void setUp() {
        // 1. Skapa en GameModel.
        // OBS: Detta förutsätter att "waves/waves.txt" finns i dina resurser,
        // annars kastar GameModel ett fel. Om det händer, se till att filen ligger rätt.
        GameModel model = new GameModel(16);

        // 2. Skapa och konfigurera EnemyFactory
        EnemyFactory factory = new EnemyFactory();
        // Vi måste registrera "Skeleton" eftersom vi använder det i testvågorna nedan
        factory.registerFactory("Skeleton", Skeleton::new);

        // 3. Skapa EnemyManager
        enemyManager = new EnemyManager(model, factory);

        // 4. Skapa manuelle test-vågor (istället för att ladda från fil)
        testWaves = new LinkedList<>();

        // Våg 1: 1 Skeleton, ingen delay
        Wave wave1 = new Wave(1);
        wave1.addGroup(new WaveGroup("Skeleton", 1, 1.0, 0.0));
        testWaves.add(wave1);

        // Våg 2: 5 Skeletons
        Wave wave2 = new Wave(2);
        wave2.addGroup(new WaveGroup("Skeleton", 5, 0.5, 0.0));
        testWaves.add(wave2);

        // 5. Hämta path från modellen (behövs för att skapa fiender)
        Path path = model.getCurrentPath();

        // 6. Skapa WaveManager med våra manuella objekt
        waveManager = new WaveManager(enemyManager, factory, testWaves, path);
    }

    @Test
    void testStartNextWave() {
        // Kontrollera att ingen våg är aktiv från början
        assertFalse(waveManager.isWaveActive(), "Vågen ska inte vara aktiv innan start");

        // Starta vågen
        waveManager.startNextWave();

        // Kontrollera att den blev aktiv och att det är våg 1
        assertTrue(waveManager.isWaveActive(), "Vågen ska vara aktiv efter start");
        assertEquals(1, waveManager.getCurrentWave(), "Nuvarande våg ska vara 1");
    }

    @Test
    void testWaveQueueConsumption() {
        // Starta våg 1
        waveManager.startNextWave();
        assertEquals(1, waveManager.getCurrentWave());

        // Tvinga start av nästa våg (simulerar att spelaren klarat vågen)
        waveManager.startNextWave();
        assertEquals(2, waveManager.getCurrentWave());

        // Försök starta en tredje våg (som inte finns)
        // Detta ska inte krascha programmet, utan bara ligga kvar eller hantera tomt läge
        waveManager.startNextWave();

        // Vi kollar bara att vi inte kraschade och att state är konsistent
        assertNotNull(waveManager);
    }
}

 */