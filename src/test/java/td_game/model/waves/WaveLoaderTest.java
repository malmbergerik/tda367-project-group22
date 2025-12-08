package td_game.model.waves;

import org.junit.jupiter.api.Test;
import java.util.Queue;
import static org.junit.jupiter.api.Assertions.*;

class WaveLoaderTest {

    @Test
    void testLoadRealWavesFile() {
        WaveLoader loader = new WaveLoader();

        // Ladda den riktiga filen. Se till att "waves/waves.txt"
        // ligger i src/main/resources/waves/waves.txt
        Queue<Wave> waves = loader.loadWaves("waves/waves.txt");

        // Kontrollera att vi fick data
        assertNotNull(waves, "Ska returnera en kö, inte null");
        assertFalse(waves.isEmpty(), "Kön ska inte vara tom");

        // Inspektera första vågen
        Wave firstWave = waves.peek();
        assertNotNull(firstWave, "Första vågen ska inte vara null");
        assertEquals(1, firstWave.getWaveNumber(), "Första vågen ska ha ID 1");

        // Inspektera grupper i första vågen
        assertFalse(firstWave.getGroups().isEmpty(), "Vågen ska ha fiendegrupper");

        // Valfritt: Kontrollera specifik data från filen (t.ex. att första fienden är Skeleton)
        WaveGroup firstGroup = firstWave.getGroups().get(0);
        assertEquals("Skeleton", firstGroup.getEnemyType());
    }

    @Test
    void testLoadNonExistentFileThrowsException() {
        WaveLoader loader = new WaveLoader();

        // Detta testar att systemet kastar fel om filen saknas, vilket är bra error-handling
        assertThrows(RuntimeException.class, () -> {
            loader.loadWaves("waves/denna_fil_finns_inte.txt");
        });
    }
}